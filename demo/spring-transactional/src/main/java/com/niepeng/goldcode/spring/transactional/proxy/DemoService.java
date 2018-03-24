package com.niepeng.goldcode.spring.transactional.proxy;


/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/3/23
 */

public interface DemoService {

  public void trCreate();

  public void trDelete();

  public void normalCreate();

  public void normalDelete();


}