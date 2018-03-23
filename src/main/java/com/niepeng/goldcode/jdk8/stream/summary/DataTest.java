package com.niepeng.goldcode.jdk8.stream.summary;


import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/9/14
 */

public class DataTest {


  public static void main(String[] args) {
    // 勾股数 方法一
//    Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100).boxed()
//        .flatMap(a -> IntStream.rangeClosed(a, 100)
//        .filter(b -> Math.sqrt(a*a + b * b) % 1 == 0)
//        .mapToObj(k -> new int[] {a, k, (int)Math.sqrt(a*a + k * k)} )
//    );

    // 勾股数 方法二(效率更高,平方根只需要计算一次)
    Stream<double[]> pythagoreanTriples =
        IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100)
            .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a+b*b)})
            .filter(c -> c[2] % 1 == 0)
        );

    pythagoreanTriples.limit(10)
        .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));


    System.out.println("生成偶数序列:");
    Stream.iterate(0, n -> n + 2).limit(5).forEach(System.out::println);

    // 生成被波那契元组序列 0,1,1,2,3,5,8,13,21...
    System.out.println("方法一生成被波那契元组序列0,1,1,2,3,5,8,13,21 ...:");
//    Stream.iterate(new int[]{0, 1}, t -> new int[]{ t[1], t[0] + t[1]}).limit(10).forEach(t -> System.out.println(t[0]));
    Stream.iterate(new int[]{0, 1}, t -> new int[]{ t[1], t[0] + t[1]}).limit(10).map(t -> t[0]).forEach(System.out::println);

    System.out.println("方法二生成被波那契元组序列0,1,1,2,3,5,8,13,21 ...:");
    IntSupplier fib = new IntSupplier() {
      private int previous = 0;
      private int current = 1;

      public int getAsInt() {
        int oldPrevious = this.previous;
        int nextValue = this.previous + this.current;
        this.previous = this.current;
        this.current = nextValue;
        return oldPrevious;
      }
    };
    IntStream.generate(fib).limit(10).forEach(System.out::println);

    // 随机生成5个值
    System.out.println("随机生成5个值:");
    Stream.generate(Math::random).limit(5).forEach(System.out::println);


  }
}