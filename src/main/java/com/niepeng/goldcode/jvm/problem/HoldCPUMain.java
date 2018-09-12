package com.niepeng.goldcode.jvm.problem;


public class HoldCPUMain {

  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        int i = 0;
        boolean flag = true;
        while(true) {
          i++;
          System.out.println(i);
          if(!flag) {
            break;
          }
        }
        System.out.println("end world");
      }
    }).start();
  }
}