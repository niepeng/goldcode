package com.niepeng.goldcode.jvm.classloader.demo2;

import com.niepeng.goldcode.jvm.classloader.HelperUtil;
import java.io.IOException;

public class OrderClassLoader extends  ClassLoader {


  static final String classFilePath = "/Users/lsb/data/code/git/goldcode/goldcode/target/classes/";


  @Override
  protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    // First, check if the class has already been loaded
    Class re = findClass(name);
    if (re == null) {
      System.out.println("无法载入类:" + name + " 需要请求父加载器");
      return super.loadClass(name, resolve);
    }
    return re;
  }


  @Override
  protected Class<?> findClass(String className) throws ClassNotFoundException {
    Class clazz = this.findLoadedClass(className);
    if (null == clazz) {
      byte[] bytes = null;
      try {
        // 读取class文件转换成byte数组
        bytes = HelperUtil.loadClassBytes(classFilePath, className);
        clazz = defineClass(className, bytes, 0, bytes.length);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return clazz;
  }

}