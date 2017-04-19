package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.atomic.AtomicLong;

import com.niepeng.goldcode.util.SystemClock;

/**
 * 
 * Description: 固定速率
 * @author:     niepeng 
 * Create at:   2017年4月18日 下午22:04:56  
 */
public class RateTest extends BaseTest {
    
    // 速率：3个/秒
    static final double rate = 3;
    static AtomicLong lastVisitTime = new AtomicLong(0L);
    static final int THREAD_NUM = 20;
    
    // 根据速率计算得到访问的间隔时间，单位：毫秒
    long intervalTime;
    
    public RateTest() {
        super(THREAD_NUM);
        intervalTime = (long) (1.d / rate * 1000);
    }
    
    @Override
    public boolean invoke(final int index) {
        try {
            long now = System.currentTimeMillis();
            if (now < lastVisitTime.get() + intervalTime) {
                SystemClock.sleepRandom(300, 400);
                System.out.println(index + ",fail:" + now);
                return false;
            }
            lastVisitTime.set(now);
            System.out.println(index + ",access:" + now + "\n");
            SystemClock.sleepRandom(100, 200);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        BaseTest r = new RateTest();
        r.execute();
    }
    
    
}
