package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.atomic.AtomicLong;

import com.niepeng.goldcode.util.SystemClock;

/**
 * 
 * Description: 计数器实现线速度
 * @author:     niepeng 
 * Create at:   2017年4月18日 下午7:46:11  
 */
public class CounterTest extends BaseTest {
    
    static AtomicLong numAtomicLong = new AtomicLong(0);
    static final int NUM_LIMIT = 5;
    
    @Override
    public boolean invoke(int index) {
        try {
            if (canVisit()) {
                process(index);
                return true;
            }
            SystemClock.sleepRandom(500, 1000);
            System.out.println("fail execute process.tmp=" + index);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            numAtomicLong.decrementAndGet();
        }
        return false;
    }
    
    
    private static boolean canVisit() {
        return numAtomicLong.incrementAndGet() <= NUM_LIMIT;
    }

    private static void process(int tmp) {
       System.out.println("process info..." + tmp + ",curentRun..." + numAtomicLong.get());
    }
    
    public static void main(String[] args) {
        BaseTest r = new CounterTest();
        r.execute();
    }

}
