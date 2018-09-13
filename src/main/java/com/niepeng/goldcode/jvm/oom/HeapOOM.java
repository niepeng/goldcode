package com.niepeng.goldcode.jvm.oom;


import java.util.ArrayList;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/13
 */

public class HeapOOM {

  public static void main(String[] args) {
    ArrayList<byte[]> list=new ArrayList<byte[]>();
    for(int i=0;i<1024 * 1024;i++){
      list.add(new byte[1024*1024]);
    }

  }
}