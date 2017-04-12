package com.niepeng.goldcode.designpattern.chain;

import com.niepeng.goldcode.util.SystemClock;

public class RecordCallTimeRemoteManager extends RemoteManager {
    
    private RemoteManager remoteManager;
    
    public RecordCallTimeRemoteManager(RemoteManager remoteManager) {
        super();
        this.remoteManager = remoteManager;
    }

    @Override
    public Request createQueryRequest(String target) {
        return remoteManager.createQueryRequest(target);
    }

    @Override
    public Request createPostRequest(String target) {
        return remoteManager.createPostRequest(target);
    }

    @Override
    public Response execute(Request request) {
        System.out.println("calc call time start...");
        long start = System.currentTimeMillis();
        SystemClock.sleepRandom(1000, 2000);
        Response response = remoteManager.execute(request);
        long end = System.currentTimeMillis();
        System.out.println("calc call time end ...,totalTime=" + (end - start));
        return response;
    }

}
