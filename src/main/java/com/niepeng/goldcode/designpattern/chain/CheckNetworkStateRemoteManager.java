package com.niepeng.goldcode.designpattern.chain;


public class CheckNetworkStateRemoteManager extends RemoteManager {
    
    private RemoteManager remoteManager;

    public CheckNetworkStateRemoteManager(RemoteManager remoteManager) {
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
        System.out.println("checkNetWorkState...");
        if (!hasNetWork()) {
            return new Response(3, null, "没有可用的网络连接。");
        }
        return remoteManager.execute(request);
    }

    private boolean hasNetWork() {
        return true;
    }
    

}
