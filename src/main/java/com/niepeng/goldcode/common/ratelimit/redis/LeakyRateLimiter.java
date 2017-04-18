package com.niepeng.goldcode.common.ratelimit.redis;

/**
 * 
 * Description: 漏桶算法
 * @author:     niepeng 
 * @version:    1.0  
 * Create at:   2017年4月18日 下午11:21:31  
 *
 */
public class LeakyRateLimiter {

    // 漏桶容量
    private long capacity;
    // 水漏出的速度:  x 个/毫秒
    private double rate;
    // 当前水量
    private long water;
    
    // 上一次出水时间点
    private long lastTime;
    
    public LeakyRateLimiter(long capacity, double rate, long water) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = water;
    }
    
    public synchronized boolean access() {
        long now = System.currentTimeMillis();
        long outNum = (long)((now - lastTime) * rate);
        if(outNum > 0) {
            water = Math.max(0, water - outNum);
            lastTime = now;
        }
        
        // 水还未满，加水    
        if(water < capacity) {
            water ++ ;
            return true;
        }
        return false;
    }
    
}
