package com.niepeng.goldcode.common.masterworker;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/6/25
 */

public class SubWorker extends Worker {

  public Object handle(Object input) {
    Integer i = (Integer) input;
    return i * i * i;
  }
}