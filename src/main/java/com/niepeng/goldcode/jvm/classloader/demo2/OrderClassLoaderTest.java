package com.niepeng.goldcode.jvm.classloader.demo2;

/**
 * 改变双亲模式的加载顺序
 *
 */
public class OrderClassLoaderTest {

  static final String classFilePath = "/Users/lsb/data/code/git/goldcode/goldcode/target/classes/";

  public static void main(String[] args) throws ClassNotFoundException {
    OrderClassLoader myLoader = new OrderClassLoader();
    Class clz = myLoader.loadClass("com.niepeng.goldcode.jvm.classloader.demo2.DemoA");
    System.out.println(clz.getClassLoader());

    System.out.println("==== Class Loader Tree ====");
    ClassLoader cl = myLoader;
    while (cl != null) {
      System.out.println(cl);
      cl = cl.getParent();
    }

  }
}