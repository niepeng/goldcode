package com.niepeng.goldcode.designpattern.chain;

public class HttpRemoteManager extends RemoteManager {

    @Override
    public Request createQueryRequest(String target) {
        return new Request();
    }

    @Override
    public Request createPostRequest(String target) {
        return new Request();
    }

    @Override
    public Response execute(Request request) {
        System.out.println("http request execute get value...");
        // do something 
        return new Response(1, null, "");
    }

}
