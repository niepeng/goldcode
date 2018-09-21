package com.niepeng.goldcode.designpattern.singleton;

/**
 * 使用内部类
 * 当StaticSingleton 被加载时,内部类并不会被初始化
 * 当getInstance方法被调用时,才会加载SingletonHolder,从而初始化instance属性,由于实例的机那里是在类加载时完成,天生对多线程友好,getInstace()不需要使用同步关键字
 *
 * 注意:通常情况实例只有一个,如果代码反射,强行调用单例类的私有构造方法,生成多个实例
 */
public class StaticSingleton {

  private StaticSingleton() {
    System.out.println("StaticSingleton");
  }

  private static class SingletonHolder {

    private static StaticSingleton instance = new StaticSingleton();
  }

  public static StaticSingleton getInstace() {
    return SingletonHolder.instance;
  }
}