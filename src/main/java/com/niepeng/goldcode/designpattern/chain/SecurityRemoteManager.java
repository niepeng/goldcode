package com.niepeng.goldcode.designpattern.chain;

import wint.help.codec.MD5;

public class SecurityRemoteManager extends RemoteManager {
    
    private RemoteManager remoteManager;

    public SecurityRemoteManager(RemoteManager remoteManager) {
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
        System.out.println("Security...");
        boolean useSign = true;
        if (useSign) {
            // 这里考虑到在AutoConnectRemoteManager中会重试，会出现重复添加的问题，所以需要先判断是否已经存在
            if(hasNotSign(request)) {
                long ts = System.currentTimeMillis();
//                request.addParameter("ts", ts);
                String signKey = "ssss";
                String sign = genSignNew(ts, signKey);
//                request.addParameter("sign", sign);
            }
        }
        return remoteManager.execute(request);
    }
    
    private boolean hasNotSign(Request request) {
//        List<Parameter> paramList = request.getParameters();
//        if (paramList == null) {
//            return true;
//        }
//        for (Parameter temp : paramList) {
//            if ("sign".equals(temp.name)) {
//                return false;
//            }
//        }
        return true;
    }
    
    private String genSignNew(long ts, String signKey) {
        return MD5.encrypt((ts + signKey).getBytes()).toLowerCase();
    }

}
