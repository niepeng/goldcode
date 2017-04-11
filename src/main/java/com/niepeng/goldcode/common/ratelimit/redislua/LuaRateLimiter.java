package com.niepeng.goldcode.common.ratelimit.redislua;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import redis.clients.jedis.Jedis;
import wint.lang.utils.FileUtil;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: niepeng</p>
 * <p>创建时间: 2017年4月11日  下午21:10:56</p>
 * <p>作者：niepeng</p>
 */
public class LuaRateLimiter {
	
	private Jedis jedis;
    private long intervalInMills;
    private long limit;
    private double intervalPerPermit;
    private String scriptSha1;
	
	public static LuaRateLimiter create(Jedis jedis, long limit, long intervalInMills) {
		return new LuaRateLimiter(jedis, limit, intervalInMills);
	}
    
    /**
     * 默认总数为5个，5秒为整个周期，即：限制为：5秒内请求为5个
     * @param jedis
     */
    public LuaRateLimiter(Jedis jedis) {
        this(jedis, 5, 5000);
    }
    
	public LuaRateLimiter(Jedis jedis, long limit, long intervalInMills) {
		this.jedis = jedis;
		this.limit = limit;
		this.intervalInMills = intervalInMills;
		intervalPerPermit = intervalInMills * 1.0 / limit;

		try {
			String filePath = "/Users/lsb/data/code/git/goldcode/goldcode/src/main/java/com/niepeng/goldcode/common/ratelimit/redislua/rate_limiter.lua";
			String script = FileUtil.readAsString(new File(filePath));
			scriptSha1 = jedis.scriptLoad(script);
			System.out.println(scriptSha1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
	public boolean access(String userId) {
		String key = genKey(userId);
		long result = (long) jedis.evalsha(scriptSha1, 1, key, 
				String.valueOf(intervalPerPermit), 
				String.valueOf(System.currentTimeMillis()), 
				String.valueOf(intervalPerPermit), 
				String.valueOf(limit),
				String.valueOf(intervalInMills));
		return result == 1L;
	}

	private String genKey(String userId) {
        return "lua:rate:limiter:" + intervalInMills + ":" + limit + ":" + userId;
    }
	

}