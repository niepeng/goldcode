package com.niepeng.goldcode.common.lazyqueue.v1;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/27
 */

public class Test {

  public static void main(String[] args) throws InterruptedException {
    Job job = new Job();
    for (int i = 0; i < 2; i++) {
      Thread.sleep(10);
      job.put(5, "type5", new Integer(i), 5);
    }

    for (int i = 0; i < 2; i++) {
      Thread.sleep(10);
      job.put(3, "type3", new Integer(i), 5);
    }


    for (int i = 0; i < 2; i++) {
      Thread.sleep(10);
      job.put(10, "type10", new Integer(i), 5);
    }

    System.out.println("调用完成,等待延迟队列执行....");

    Thread.sleep(1000 * 10000);
  }
}