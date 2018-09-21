package com.niepeng.goldcode.designpattern.singleton;

/**
 * 缺点：开销大，耗时长
 */
public class LazySingleton {

  private LazySingleton() {
    System.out.println("LazySingleton is create");
  }

  private static LazySingleton instance = null;

  public static synchronized LazySingleton getInstance() {
    if (instance == null) {
      instance = new LazySingleton();
    }
    return instance;
  }
}