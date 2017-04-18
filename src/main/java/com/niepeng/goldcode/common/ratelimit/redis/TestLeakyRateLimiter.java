package com.niepeng.goldcode.common.ratelimit.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.niepeng.goldcode.util.SystemClock;

public class TestLeakyRateLimiter {
    
    static final int THREAD_NUM = 20;

    public static void main(String[] args) {
        // 漏桶容量
        long capacity = 5;
        // 水漏出的速度:  x 个/毫秒
        double rate = 0.03d;
        // 当前水量
        long water = 0;
        final LeakyRateLimiter leakyRateLimiter = new LeakyRateLimiter(capacity, rate, water);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM / 3);
        for (int i = 0; i < THREAD_NUM; i++) {
            final int tmp = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        SystemClock.sleep(10);
                        if(leakyRateLimiter.access()) {
                            System.out.println("success--," + tmp + System.currentTimeMillis());
                        } else {
                            System.out.println("fail--," + tmp + System.currentTimeMillis());
                        }
                        SystemClock.sleepRandom(20, 40);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
    
    
}
