package com.niepeng.goldcode.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>标题: </p>
 * <p>描述: </p>
 * <p>版权: lsb</p>
 * <p>创建时间: 2015年3月2日  上午9:21:32</p>
 * <p>作者：niepeng</p>
 */
public class TestJDKProxy {

	public static void main1(String[] args) {
		CustomInvocationHandler handler = new CustomInvocationHandler(new HelloWorldImpl());
		HelloWorld proxy = (HelloWorld)Proxy.newProxyInstance(TestJDKProxy.class.getClassLoader(), new Class[] {HelloWorld.class}, handler);
		String retValue = proxy.sayHello("niepeng");
		System.out.println(proxy.getClass().getName());
		System.out.println(retValue);
	}
	
	public static void main(String[] args) {
		final HelloWorld h = new HelloWorldImpl();
		HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(TestJDKProxy.class.getClassLoader(), new Class[] { HelloWorld.class }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("simpleBefore invocation");
				Object retVal = method.invoke(h, args);
				System.out.println("simpleAfter invocation");
				return retVal;
			}
		});
		String retValue = proxy.sayHello("niepeng");
		System.out.println(retValue);
	}
}