package com.niepeng.goldcode.designpattern.singleton;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 一个可被序列化的单例
 * 一般需要序列化和反序列化的单例场景不多
 */

public class SerSingleton implements Serializable {

  String name;

  private SerSingleton() {
    System.out.println("SerSingleton is create");
    name = "SerSingleton";
  }

  private static SerSingleton serSingleton = new SerSingleton();
  public static SerSingleton getInstance() {
    return serSingleton;
  }

  public static void createString() {
    System.out.println("createString in Singleton");
  }

  /**
   * 阻止生成新的实例,总是返回当前对象
   * @return
   */
  private Object readResolve() {
    return serSingleton;
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    String fileName = "serSingleton.txt";
    SerSingleton s0 = null;

    // 先将实例序列化到文件
    SerSingleton s1 = SerSingleton.getInstance();
    FileOutputStream fos = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(s1);
    oos.flush();
    oos.close();

    // 从文件读出原有的单例类
    FileInputStream fis = new FileInputStream(fileName);
    ObjectInputStream ois = new ObjectInputStream(fis);
    s0 = (SerSingleton)ois.readObject();

    // 如果不写 readResolve 方法 那么返回false,如果写 readResolve方法,返回true
    System.out.println(s0 == s1);
  }

}