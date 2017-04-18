package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import com.niepeng.goldcode.util.SystemClock;

/**
 * 
 * Description: 计数器实现线速度
 * @author:     niepeng 
 * Create at:   2017年4月18日 下午7:46:11  
 */
public class CounterTest {
    
    static AtomicLong numAtomicLong = new AtomicLong(0);
    static final int NUM_LIMIT = 5;
    static final int THREAD_NUM = 20;
    
    
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREAD_NUM; i++) {
            final int tmp = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        if (canVisit()) {
                            process(tmp);
                        } else {
                            SystemClock.sleepRandom(500, 1000);
                            System.out.println("fail execute process.tmp=" + tmp);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        numAtomicLong.decrementAndGet();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
    

    private static boolean canVisit() {
        return numAtomicLong.incrementAndGet() <= NUM_LIMIT;
    }

    private static void process(int tmp) {
       System.out.println("process info..." + tmp + ",curentRun..." + numAtomicLong.get());
    }

}
