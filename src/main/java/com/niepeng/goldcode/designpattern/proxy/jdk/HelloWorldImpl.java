package com.niepeng.goldcode.designpattern.proxy.jdk;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2015年3月2日  上午9:18:49</p>
 * <p>作者：niepeng</p>
 */
public class HelloWorldImpl implements HelloWorld {  
    @Override  
    public String sayHello(String name) {  
        System.out.println("Hello =>" + name);  
        return "retrun:" + name;
    }  
}  