package com.niepeng.goldcode.jvm.oom;


/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/13
 */

public class ThradStackOOM {

  public static void main(String[] args) {
    for(int i=0;i<1000 * 1000;i++){
      new Thread(new SleepThread(),"Thread"+i).start();
      System.out.println("Thread"+i+" created");
    }

  }
}

 class SleepThread implements Runnable {
  @Override
  public void run(){
    try {
      Thread.sleep(10000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

