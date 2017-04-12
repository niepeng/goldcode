package com.niepeng.goldcode.designpattern.chain;


public class Request {

    private String target;

//    private List<Parameter> parameters = CollectionUtil.newArrayList();
//    
//    private List<BinaryItem> binaryItems = CollectionUtil.newArrayList();
    
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
}
