package com.niepeng.goldcode.jvm.classloader;

/**
 *
 * 把当前类的的 I am in app loader. 修改下,编译后把class文件放到/Users/lsb/data/tmp/jvmtest/ 这个路径下
 *
 * jvm启动参数中指定:
 * -Xbootclasspath/a:/Users/lsb/data/tmp/jvmtest/  观察输出的结果
 *
 */

public class HelloLoader {

  public void print() {
    System.out.println("I am in app loader.");
  }
}