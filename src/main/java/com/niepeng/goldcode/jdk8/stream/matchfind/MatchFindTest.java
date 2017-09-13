package com.niepeng.goldcode.jdk8.stream.matchfind;


import com.niepeng.goldcode.jdk8.stream.Dish;
import java.util.Arrays;
import java.util.List;

/**
 * allMatch anyMatch noneMatch
 *
 * findAny   返回任意一个
 * findFirst 返回第一个
 * 两者的区别是在并行上,如果一般使用,不关心返回是哪个,用findAny.
 *
 * @author 聂鹏
 * @version 1.0
 * @date 17/9/12
 */

public class MatchFindTest {

  public void testMatch(List<Dish> dishList) {

    if(dishList.stream().anyMatch(Dish::isVegetarian)) {
      System.out.println("1.The dishList is (somewhat) vegetarian friendly!!");
    }

    if(dishList.stream().allMatch(d -> d.getCalories() < 500)) {
      System.out.println("2.The dishList is all of calories < 500");
    }

    dishList.stream().filter(Dish::isVegetarian)
        .findAny()   // 返回一个 Optional<Dish> 对象
        .ifPresent(d -> System.out.println("3."+d.getName()));




  }

  public static void main(String[] args) {
    List<Dish> dishList = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));


    MatchFindTest m = new MatchFindTest();
    m.testMatch(dishList);

  }


  }