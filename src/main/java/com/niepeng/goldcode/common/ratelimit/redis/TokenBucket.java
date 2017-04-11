package com.niepeng.goldcode.common.ratelimit.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2017年4月11日  下午4:18:17</p>
 * <p>作者：niepeng</p>
 */
public class TokenBucket {

	 private long lastRefillTime;
     private long tokensRemaining;

     public TokenBucket(long lastRefillTime, long tokensRemaining) {
         this.lastRefillTime = lastRefillTime;
         this.tokensRemaining = tokensRemaining;
     }

     public static TokenBucket fromHash(Map<String, String> hash) {
         long lastRefillTime = Long.parseLong(hash.get("lastRefillTime"));
         int tokensRemaining = Integer.parseInt(hash.get("tokensRemaining"));
         return new TokenBucket(lastRefillTime, tokensRemaining);
     }

     public Map<String, String> toHash() {
         Map<String, String> hash = new HashMap<String, String>();
         hash.put("lastRefillTime", String.valueOf(lastRefillTime));
         hash.put("tokensRemaining", String.valueOf(tokensRemaining));
         return hash;
     }

	public long getLastRefillTime() {
		return lastRefillTime;
	}

	public void setLastRefillTime(long lastRefillTime) {
		this.lastRefillTime = lastRefillTime;
	}

	public long getTokensRemaining() {
		return tokensRemaining;
	}

	public void setTokensRemaining(long tokensRemaining) {
		this.tokensRemaining = tokensRemaining;
	}
     
}
