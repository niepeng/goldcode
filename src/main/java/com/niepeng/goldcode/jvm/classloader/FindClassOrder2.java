package com.niepeng.goldcode.jvm.classloader;

import java.lang.reflect.Method;

/**
 * jvm启动参数中指定:
 * -Xbootclasspath/a:/Users/lsb/data/tmp/jvmtest/ 还是加载当前目录下的类
 */
public class FindClassOrder2 {

  static final String classFilePath = "/Users/lsb/data/code/git/goldcode/goldcode/target/classes/";

  public static void main(String[] args) throws Exception {
    ClassLoader cl = FindClassOrder2.class.getClassLoader();
    byte[] bHelloLoader = HelperUtil.loadClassBytes(classFilePath, "com.niepeng.goldcode.jvm.classloader.HelloLoader");
    Method md_defineClass=ClassLoader.class.getDeclaredMethod("defineClass", byte[].class,int.class,int.class);
    md_defineClass.setAccessible(true);
    md_defineClass.invoke(cl, bHelloLoader,0,bHelloLoader.length);
    md_defineClass.setAccessible(false);

    HelloLoader loader = new HelloLoader();
    System.out.println(loader.getClass().getClassLoader());
    loader.print();
  }

}