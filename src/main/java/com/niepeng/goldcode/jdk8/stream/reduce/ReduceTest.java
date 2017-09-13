package com.niepeng.goldcode.jdk8.stream.reduce;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/9/13
 */

public class ReduceTest {


  public static void main(String[] args) {

    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
    int initValue = 1;
    // list元素和
//    int sum = someNumbers.stream().reduce(initValue, (a, b) -> a + b);
    int sum = someNumbers.stream().reduce(initValue, Integer::sum);
    System.out.println("sum=" + sum);

    // 无初始值,返回Optional
    Optional<Integer> sumOptional = someNumbers.stream().reduce((a, b) -> (a + b));
    System.out.println("sumOptional->" + sumOptional.orElse(0));

     // list元素相乘
    int product = someNumbers.stream().reduce(1, (a, b) -> a * b);
    System.out.println("相乘的值=" + product);

    // 获取list中的最大值
    Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
    System.out.println("max->" + max.orElse(0));


  }

}