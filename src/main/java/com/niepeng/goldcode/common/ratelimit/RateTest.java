package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import com.niepeng.goldcode.util.SystemClock;

import sun.util.logging.resources.logging;

/**
 * 
 * Description: 固定速率
 * @author:     niepeng 
 * Create at:   2017年4月18日 下午22:04:56  
 */
public class RateTest {
    
    // 速率：3个/秒
    static final double rate = 3;
    
    static AtomicLong lastVisitTime = new AtomicLong(0L);
    
    static final int THREAD_NUM = 20;
    
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM/2);
        // 请求之间间隔时间，单位毫秒
        final long intervalTime = (long) (1.d / rate * 1000);
        for (int i = 0; i < THREAD_NUM; i++) {
            final int tmp = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        long now = System.currentTimeMillis();
                        if (now < lastVisitTime.get() + intervalTime) {
                            SystemClock.sleepRandom(300, 400);
                            System.out.println(tmp + ",fail:" + now);
                            return;
                        }
                        lastVisitTime.set(now);
                        System.out.println(tmp + ",access:" + now + "\n");
                        SystemClock.sleepRandom(100, 200);
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
