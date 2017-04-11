package com.niepeng.goldcode.common.ratelimit.redis;

import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: niepeng</p>
 * <p>创建时间: 2017年4月11日  下午4:10:56</p>
 * <p>作者：niepeng</p>
 */
public class RateLimiter {
	
	private Jedis jedis;
    private long intervalInMills;
    private long limit;
    private double intervalPerPermit;
	
	public static RateLimiter create(Jedis jedis, long limit, long intervalInMills) {
		return new RateLimiter(jedis, limit, intervalInMills);
	}
    
    /**
     * 默认总数为5个，5秒为整个周期，即：限制为：5秒内请求为5个
     * @param jedis
     */
    public RateLimiter(Jedis jedis) {
        this(jedis, 5, 5000);
    }
    
	public RateLimiter(Jedis jedis, long limit, long intervalInMills) {
		this.jedis = jedis;
		this.limit = limit;
		this.intervalInMills = intervalInMills;
		intervalPerPermit = intervalInMills * 1.0 / limit;
	}
    
	public boolean access(String userId) {
		String key = genKey(userId);
		Map<String, String> counter = jedis.hgetAll(key);
		if (counter.size() == 0) {
			TokenBucket tokenBucket = new TokenBucket(System.currentTimeMillis(), limit - 1);
			jedis.hmset(key, tokenBucket.toHash());
			return true;
		} 
		
		TokenBucket tokenBucket = TokenBucket.fromHash(counter);
		long lastRefillTime = tokenBucket.getLastRefillTime();
		/*
		 * 桶中需要补充数量
		 * 	1.过了整个周期了，需要补到最大值
		 *  2.如果到了至少补充一个的周期了，那么需要补充部分，否则不补充
		 */
		long currentTokensRemaining;
		long refillTime = System.currentTimeMillis();
		long intervalSinceLast = refillTime - lastRefillTime;
		if(intervalSinceLast > intervalInMills) {
			currentTokensRemaining = limit;
		} else {
			long grantedTokens = (long) (intervalSinceLast / intervalPerPermit);
			if(grantedTokens < 1) {
				refillTime = lastRefillTime;
			}
			currentTokensRemaining = Math.min(grantedTokens + tokenBucket.getTokensRemaining(), limit);
		}
		
		tokenBucket.setLastRefillTime(refillTime);
		if (currentTokensRemaining == 0) {
			tokenBucket.setTokensRemaining(currentTokensRemaining);
			jedis.hmset(key, tokenBucket.toHash());
			return false;
		} else {
			tokenBucket.setTokensRemaining(currentTokensRemaining - 1);
			jedis.hmset(key, tokenBucket.toHash());
			return true;
		}
	}

	private String genKey(String userId) {
        return "rate:limiter:" + intervalInMills + ":" + limit + ":" + userId;
    }
	

}
