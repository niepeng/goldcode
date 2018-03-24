package com.niepeng.goldcode.spring.transactional.proxy;


import org.springframework.stereotype.Service;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/3/23
 */

@Service
public class DemoerviceImpl implements DemoService {

  @Override
  public void trCreate() {
    System.out.println("trCreate:聂鹏1号tr");
  }

  @Override
  public void trDelete() {
    System.out.println("trDelete:聂鹏1号tr");
  }

  @Override
  public void normalCreate() {
    System.out.println("normalCreate:聂鹏1号tr");
  }

  @Override
  public void normalDelete() {
    System.out.println("normalDelete:聂鹏1号tr");
  }


}