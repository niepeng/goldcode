package com.niepeng.goldcode.common.ratelimit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseTest {

    private int threadNum;

    private ExecutorService executorService;

    public BaseTest() {
        this(5);
    }

    public BaseTest(int threadNum) {
        this.threadNum = threadNum;
        executorService = Executors.newFixedThreadPool(threadNum);
    }

    public abstract boolean invoke(final int index);

    public void execute() {
        for (int i = 0; i < threadNum; i++) {
            final int tmp = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    invoke(tmp);
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

}
