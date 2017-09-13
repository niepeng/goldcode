package com.niepeng.goldcode.jdk8.stream.summary;

/**
 * 交易员
 *
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/9/13
 */

public class Trader {

  private final String name;
  private final String city;
  public Trader(String n, String c){
    this.name = n;
    this.city = c;
  }
  public String getName(){
    return this.name;
  }
  public String getCity(){
    return this.city;
  }
  public String toString(){
    return "Trader:"+this.name + " in " + this.city;
  }
}
