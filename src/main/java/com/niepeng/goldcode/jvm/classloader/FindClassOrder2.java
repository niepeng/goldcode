package com.niepeng.goldcode.jvm.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * jvm启动参数中指定:
 * -Xbootclasspath/a:/Users/lsb/data/tmp/jvmtest/ 还是加载当前目录下的类
 */
public class FindClassOrder2 {

  static final String classFilePath = "/Users/lsb/data/code/git/goldcode/goldcode/target/classes/";

  public static void main(String[] args) throws Exception {
    ClassLoader cl = FindClassOrder2.class.getClassLoader();
    byte[] bHelloLoader = loadClassBytes("com.niepeng.goldcode.jvm.classloader.HelloLoader");
    Method md_defineClass=ClassLoader.class.getDeclaredMethod("defineClass", byte[].class,int.class,int.class);
    md_defineClass.setAccessible(true);
    md_defineClass.invoke(cl, bHelloLoader,0,bHelloLoader.length);
    md_defineClass.setAccessible(false);

    HelloLoader loader = new HelloLoader();
    System.out.println(loader.getClass().getClassLoader());
    loader.print();
  }

  private static byte[] loadClassBytes(String classPackageFile) throws IOException {
    String fileNamePath = classFilePath + classPackageFile.replaceAll("\\.", "/") + ".class";
    File f = new File(fileNamePath);
    if (!f.exists()) {
      throw new FileNotFoundException(fileNamePath);
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
    BufferedInputStream in = null;
    try {
      in = new BufferedInputStream(new FileInputStream(f));
      int buf_size = 1024;
      byte[] buffer = new byte[buf_size];
      int len = 0;
      while (-1 != (len = in.read(buffer, 0, buf_size))) {
        bos.write(buffer, 0, len);
      }
      return bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      bos.close();
    }
  }
}