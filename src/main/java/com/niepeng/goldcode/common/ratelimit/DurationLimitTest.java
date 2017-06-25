package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.niepeng.goldcode.util.SystemClock;

public class DurationLimitTest {

    public static void main(String[] args) throws Exception {
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<Long, AtomicLong>() {
            @Override
            public AtomicLong load(Long seconds) throws Exception {
                return new AtomicLong(0);
            }
        });

        // 每秒限制3个
        long limit = 3;

        while (true) {
            SystemClock.sleep(200);
            //得到当前秒
            long currentSeconds = System.currentTimeMillis() / 1000;
            if (counter.get(currentSeconds).incrementAndGet() > limit) {
                System.out.println("限流了:" + currentSeconds * 1000);
                continue;
            }
            //业务处理
            System.out.println("process success." + System.currentTimeMillis());
        }
    }
}
