package com.niepeng.goldcode.spring.transactional;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/16
 */
@Configuration
//@MapperScan("com.niepeng.springbootts.dao")
@MapperScan("com.niepeng.goldcode.spring.transactional.dao")
// 启动注解事务管理
@EnableTransactionManagement
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class App {

  public static void main(String args []) {
    SpringApplication.run(App.class, args);
  }

}