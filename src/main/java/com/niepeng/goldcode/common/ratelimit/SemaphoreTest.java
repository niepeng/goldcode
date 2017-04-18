package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * Description: Semphore进行并发流控
 * @author:     niepeng 
 * Create at:   2017年4月18日 下午7:56:12  
 */
public class SemaphoreTest {
    
    static final int THREAD_NUM = 20;
    static final int VISIT_NUM_LIMIT = 5;
    
    public static void main(String[] args) {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(VISIT_NUM_LIMIT);

        for (int i = 0; i < THREAD_NUM; i++) {
            final int tmp = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("acquire:" + tmp);
                        Thread.sleep((long) (Math.random() * 3000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                        System.out.println("release:" + semaphore.availablePermits() + "--------");
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

}
