package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.Semaphore;

/**
 * 
 * Description: Semphore进行并发流控
 * @author:     niepeng 
 * Create at:   2017年4月18日 下午7:56:12  
 */
public class SemaphoreTest extends BaseTest {
    
    static final int VISIT_NUM_LIMIT = 5;
    static final int THREAD_NUM = 20;
    
    private Semaphore semaphore;
    
    public SemaphoreTest() {
        super(THREAD_NUM);
        semaphore = new Semaphore(VISIT_NUM_LIMIT);
    }
    
    @Override
    public boolean invoke(int index) {
        try {
            semaphore.acquire();
            System.out.println("acquire:" + index);
            Thread.sleep((long) (Math.random() * 3000));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println("release:" + semaphore.availablePermits() + "--------");
        }
        return false;
    }
    
    public static void main(String[] args) {
        BaseTest r = new SemaphoreTest();
        r.execute();
    }

}
