package com.niepeng.goldcode.common.ratelimit;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;
import com.niepeng.goldcode.util.DateUtil;

/**
 * 介绍文档：google的ratelimiter文档翻译
 * http://ifeve.com/guava-ratelimiter/
 * 
 * 自己博客文章：限流实现方案
 * http://blog.csdn.net/lsblsb/article/details/69486012 
 * 
 * 
 * @author niepeng
 *
 */
public class GuavaRateLimiterTest {

	private int permitsPerSecond = 10; // 每秒10个许可
	private int threadNum = 3;

	public static void main(String[] args) {
		new GuavaRateLimiterTest().call();
	}

	private void call() {
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		final RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);
		for (int i = 0; i < threadNum; i++) {
			executor.execute(new ApiCallTask(rateLimiter));
		}
		executor.shutdown();
	}
}

class ApiCallTask implements Runnable {
	
	private RateLimiter rateLimiter;
	private boolean runing = true;

	public ApiCallTask(RateLimiter rateLimiter) {
		this.rateLimiter = rateLimiter;
	}

	@Override
	public void run() {
		while (runing) {
			rateLimiter.acquire(); // or rateLimiter.tryAcquire()
			getData();
		}
	}

	// 模拟调用合作伙伴API接口
	private void getData() {
		System.out.println(DateUtil.format(new Date()) + ", " +Thread.currentThread().getName() + " runing!");
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}