package com.niepeng.goldcode.jdk8.stream.summary;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/9/13
 */

public class SummaryTest {


  public static void main(String[] args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );


    // 1.找出2011年发生的所有交易，并按交易额排序(从低到高)。
    List<Transaction> result1 =  transactions.stream().filter(d -> d.getYear() == 2011)
//        .sorted((a,b) -> Integer.compare(a.getValue(), b.getValue()))
        .sorted(Comparator.comparing(Transaction::getValue))
        .collect(Collectors.toList());

    System.out.println("找出2011年发生的所有交易，并按交易额排序(从低到高):");
    result1.stream().forEach(System.out:: println);
    System.out.println("\n");

    // 2.交易员都在哪些不同的城市工作过？
    List<String> result2 = transactions.stream().map(d -> d.getTrader().getCity())
        .distinct().collect(Collectors.toList());

    System.out.println("2.交易员都在哪些不同的城市工作过:");
    result2.stream().forEach(System.out:: println);
    System.out.println("\n");

    // 3.查找所有来自于剑桥的交易员，并按照姓名排序
    List<Trader> result3 = transactions.stream().map(d-> d.getTrader())
        .filter(d -> "Cambridge".equals(d.getCity()))
        .distinct()
        .sorted(Comparator.comparing(Trader::getName))
        .collect(Collectors.toList());

    System.out.println("3.查找所有来自于剑桥的交易员，并按照姓名排序:");
    result3.stream().forEach(System.out:: println);
    System.out.println("\n");

    // 4.返回所有交易员的姓名字符串，按字母顺序排序
    String result4 = transactions.stream().map(d -> d.getTrader().getName())
        .distinct()
        .sorted()
        .collect(Collectors.joining());  //等同于 .reduce("", (n1,n2) -> n1 + n2); joining效率高.内部用StringBuilder实现

    System.out.println("4.返回所有交易员的姓名字符串，按字母顺序排序:");
    System.out.println(result4);
    System.out.println("\n");

    // 5.有没有交易员是在米兰工作过？
//    boolean flag = transactions.stream().map(d -> d.getTrader().getCity())
//        .distinct()
//        .anyMatch(d -> "Milan".equals(d));
    boolean result5 = transactions.stream().anyMatch(d-> "Milan".equals(d.getTrader().getCity()));

    System.out.println("5.有没有交易员是在米兰工作过:");
    System.out.println(result5);
    System.out.println("\n");


    // 6.打印生活在剑桥的交易员的所有交易额
    List<Integer> result6 = transactions.stream()
        .filter(d -> "Cambridge".equals(d.getTrader().getCity()))
        .map(Transaction::getValue)
        .collect(Collectors.toList());
    System.out.println("6.打印生活在剑桥的交易员的所有交易额:");
    result6.stream().forEach(System.out:: println);
    System.out.println("\n");

    // 7.所有交易中，最高的交易额是多少？
    int result7 = transactions.stream()
        .map(d-> d.getValue())
        .reduce(0, Integer::max);

    System.out.println("7.所有交易中，最高的交易额是多少:");
    System.out.println(result7);
    System.out.println("\n");

    // 8.找到交易额最小的交易
//    Transaction result8 = transactions.stream()
//        .sorted((t1,t2) -> Integer.compare(t1.getValue(), t2.getValue()))
//        .limit(1).collect(Collectors.toList()).get(0);

//    Optional<Transaction> result8 = transactions.stream()
//        .reduce((t1,t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

    Optional<Transaction> result8 = transactions.stream()
        .min(Comparator.comparing(Transaction::getValue));

        System.out.println("8.找到交易额最小的交易:");
    System.out.println(result8);
    System.out.println("\n");

  }
}