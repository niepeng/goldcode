package com.niepeng.goldcode.common.ratelimit.redis;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;

import com.niepeng.goldcode.common.redis.RedisClient;
import com.niepeng.goldcode.util.DateUtil;
import com.niepeng.goldcode.util.SystemClock;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: niepeng</p>
 * <p>创建时间: 2017年4月11日  下午3:54:22</p>
 * <p>作者：niepeng</p>
 */
public class TestRateLimiter {
	
	public static void main(String[] args) {
		TestRateLimiter t = new TestRateLimiter();
		try {
			t.call();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			SystemClock.sleep(1000 * 3600);
			t.close();
		}
		
	}
	
	private void call() {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		long limit = 4;
		long intervalInMills = 2000;
		final RateLimiter rateLimiter = RateLimiter.create(getJedis(), limit, intervalInMills);
		executor.execute(new CallTask(rateLimiter, "user"));
//		executor.shutdown();
	}
	
	public Jedis getJedis() {
		 return RedisClient.getRedisClient().getJedis();
	}
	
	public void close() {
		RedisClient.getRedisClient().Close();
	}
}

class CallTask implements Runnable {
	
	private RateLimiter rateLimiter;
	private boolean runing = true;
	private String userId;

	public CallTask(RateLimiter rateLimiter, String userId) {
		this.rateLimiter = rateLimiter;
		this.userId = userId;
	}

	@Override
	public void run() {
		while (runing) {
			if (rateLimiter.access(userId)) {
				getData();
			} else {
				SystemClock.sleep(100);
			}
		}
	}

	// 模拟调用合作伙伴API接口
	private void getData() {
		System.out.println(DateUtil.format(new Date()) + ", userId=" + userId + "," +Thread.currentThread().getName() + " runing!");
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
