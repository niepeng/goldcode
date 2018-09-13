package com.niepeng.goldcode.jvm.oom;


import java.nio.ByteBuffer;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/13
 */

public class DirectOOM {

  public static void main(String[] args) {
    for (int i = 0; i < 1024 * 1024; i++) {
      ByteBuffer.allocateDirect(1024 * 1024);
      System.out.println(i);
      System.gc();
    }

  }
}