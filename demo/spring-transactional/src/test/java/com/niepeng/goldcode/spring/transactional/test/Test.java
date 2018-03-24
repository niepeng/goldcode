package com.niepeng.goldcode.spring.transactional.test;

import com.niepeng.goldcode.spring.transactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@51huadian.cn
 * @date 18/3/24
 */

public class Test extends BaseTest  {

  @Autowired
  private UserService userService;

  @org.junit.Test
  public void test() {
    userService.opt1();
  }
}