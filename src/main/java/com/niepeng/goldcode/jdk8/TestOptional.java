package com.niepeng.goldcode.jdk8;


import java.util.Optional;
import junit.framework.TestCase;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/7/1
 */
public class TestOptional extends TestCase {

  public void test_init() {
    // 参数不能是null
    Optional<Integer> optional1 = Optional.of(1);
    // 参数可以是null
    Optional<Integer> optional2 = Optional.ofNullable(null);
    // 参数可以是非null
    Optional<Integer> optional3 = Optional.ofNullable(2);

  }

  public void test_orElse() {
    Optional<TestUserBean> user = Optional.empty();
    // 而不是 return user.isPresent() ? user.get() : null; 这样失去意义了
    TestUserBean t1 = user.orElse(null);
  }

  public void test_orElseGet() {
    Optional<TestUserBean> user = Optional.empty();
    ////而不要 return user.isPresent() ? user: fetchAUserFromDatabase();这样失去意义了
    TestUserBean userBean =  user.orElseGet(() -> fetchAUserFromDatabase());
  }

  public void test_isPresent() {
    Optional<TestUserBean> user = Optional.ofNullable(new TestUserBean("niepeng", 18));
    /*
    而不要下边那样
    if (user.isPresent()) {
      System.out.println(user.get());
    }*/
    user.ifPresent(System.out::println);
  }

  public void test_map() {
    Optional<TestUserBean> user = Optional.ofNullable(new TestUserBean("niepeng", 18));
    /*
    if(user != null) {
        user.getName();
    } else {
       "defaultValue"
    }
     */
    String  name = user.map(u -> u.getName()).orElse("defaultValue");
    System.out.println("test_map=" + name);
  }

  private TestUserBean fetchAUserFromDatabase() {
    return new TestUserBean();
  }


}

class TestUserBean {

  private String name;

  private int age;

  public TestUserBean(){}

  public TestUserBean(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }
  public int getAge() {
    return age;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setAge(int age) {
    this.age = age;
  }
}