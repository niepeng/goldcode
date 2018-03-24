package com.niepeng.goldcode.spring.transactional.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/3/24
 */

public class MyInvocationHandler implements InvocationHandler {

  private Object object;

  public MyInvocationHandler(Object object) {
    this.object = object;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.getName().startsWith("tr")) {
      System.out.println("ready execute " + method.getName() + " method");
    }
    return method.invoke(object, args);
  }

  public static void main(String[] args) {
    InvocationHandler invocationHandler = new MyInvocationHandler(new DemoerviceImpl());
    DemoService userService = (DemoService) Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class[]{DemoService.class}, invocationHandler);
    userService.trCreate();
    userService.trDelete();
    userService.normalCreate();
    userService.normalDelete();
  }
}