package com.niepeng.goldcode.designpattern.chain;


public class Response {

    private int code = -1;
    
    private String message;
    
    private Object model;
    
    public Response(int code, Object model, String message) {
        this.code = code;
        this.model = model;
        this.message = message;
    }
    
    public boolean isSuccess() {
//        return (code == MessageCodes.SUCCESS);
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
    
}
