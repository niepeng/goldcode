package com.niepeng.goldcode.jdk8.stream.flatmap;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * flatMap: flatmap方法让你把一个流中的每个值都换成另一个流,然后把所有的流连接起来成为一个流
 *
 * @author 聂鹏
 * @version 1.0
 * @date 17/9/12
 */

public class FlatMapTest {

  /**
   * 给定2个数字列表,返回所有的数对
   *
   * 如:给定:[1, 2, 3]   [3, 4]
   * 返回:[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
   */
  public List<int[]> data() {
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);
    List<int[]> pairs = numbers1.stream()
        .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
        .collect(Collectors.toList());

    return pairs;
  }

  public List<String> findDistinct() {
    String[] array = {"hello", "world"};
    Stream<String> words = Arrays.stream(array);
    List<String> result = words.map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(Collectors.toList());

    return result;
  }

  public static void main(String[] args) {
    FlatMapTest t = new FlatMapTest();
    List<int[]> list1 = t.data();
//    println(list1);

    List<String> list2 = t.findDistinct();
    list2.stream().forEach(System.out:: println);

  }

  private static void println(List<int[]> list) {
    if(list == null) {
      return;
    }
    for(int[] arrray : list) {
      println(arrray);
    }
  }

  private static void println(int[] arrray) {
    if (arrray == null) {
      return;
    }
    System.out.print("(");
    for (int i : arrray) {
      System.out.print(i + ",");
    }
    System.out.print(") \n");
  }

}