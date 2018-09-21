package com.niepeng.goldcode.designpattern.singleton;

/**
 * 最简单的单例实现
 * 缺点：无法对instance实例做延迟加载（单例类被加载时instance就会被创建，有时候可能不需要）
 */
public class Singleton {

  private Singleton() {
    System.out.println("Singleton is create");
  }

  private static Singleton instance = new Singleton();

  public static Singleton getInstance() {
    return instance;
  }
}