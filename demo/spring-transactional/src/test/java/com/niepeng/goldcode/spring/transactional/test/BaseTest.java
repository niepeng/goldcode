package com.niepeng.goldcode.spring.transactional.test;

import com.niepeng.goldcode.spring.transactional.App;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootTest(classes = App.class)
@ComponentScan("com.niepeng")
@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@WebAppConfiguration
public class BaseTest {

  @BeforeClass
  public static void init() {
    System.setProperty("service.tag", "local");
    System.setProperty("server.port", "8090");

  }

}