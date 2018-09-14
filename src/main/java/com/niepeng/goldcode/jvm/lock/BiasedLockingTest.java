package com.niepeng.goldcode.jvm.lock;


import java.util.List;
import java.util.Vector;

/**
 * 偏向锁测试
 *
 * 对比下面2个不同的jvm参数的结果:(使用偏向锁和不使用偏向锁的性能对比)
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0（这里保证系统启动的时候就开启偏向锁，因为系统启动的时候没有开启，JVM设计者认为系统启动的时候竞争比较激烈）
 *  执行花费 493 毫秒, 比不使用偏向锁性能提升 25%
 *
 *
 * -XX:-UseBiasedLocking
 * 执行花费: 665 毫秒
 *
 *
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/13
 */

public class BiasedLockingTest {

  public static List<Integer> numberList = new Vector<Integer>();

  public static void main(String[] args) throws InterruptedException {
    long begin = System.currentTimeMillis();
    int count = 0;
    int startnum = 0;
    while (count < 10000000) {
      numberList.add(startnum);
      startnum += 2;
      count++;
    }
    long end = System.currentTimeMillis();
    System.out.println(end - begin);
  }


}