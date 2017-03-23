package com.niepeng.goldcode.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2015年3月2日  上午9:19:46</p>
 * <p>作者：niepeng</p>
 */
public class CustomInvocationHandler implements InvocationHandler {
	
	private Object target;  
	  
    public CustomInvocationHandler(Object target) {  
        this.target = target;  
    }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Before invocation");
		Object retVal = method.invoke(target, args);
		System.out.println("After invocation");
		return retVal;
	}

}