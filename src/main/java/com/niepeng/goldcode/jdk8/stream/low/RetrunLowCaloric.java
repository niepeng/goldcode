package com.niepeng.goldcode.jdk8.stream.low;

import com.niepeng.goldcode.jdk8.stream.Dish;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 返回低热量的菜肴名称的, 并按照卡路里排序
 *
 * @author 聂鹏
 * @version 1.0
 * @date 17/9/11
 */

public class RetrunLowCaloric {

  public List<String> jdk7Impl(List<Dish> menu, int maxCalories) {
    List<Dish> lowCaloricDishes = new ArrayList<>();
    for (Dish d : menu) {
      if (d.getCalories() < maxCalories) {
        lowCaloricDishes.add(d);
      }
    }

    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
      @Override
      public int compare(Dish o1, Dish o2) {
        return Integer.compare(o1.getCalories(), o2.getCalories());
      }
    });

    List<String> lowCaloricDishesName = new ArrayList<>();
    for (Dish d : lowCaloricDishes) {
      lowCaloricDishesName.add(d.getName());
    }

    return lowCaloricDishesName;

  }

  public List<String> jdk8Impl(List<Dish> menu, int maxCalories) {
    List<String> lowCaloricDishesName = menu.stream()
                                        .filter(d -> d.getCalories() < maxCalories)
                                        .sorted(Comparator.comparing(Dish :: getCalories))
                                        .map(Dish :: getName)
                                        .collect(Collectors.toList());

    return lowCaloricDishesName;
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

    RetrunLowCaloric r = new RetrunLowCaloric();
    int maxCalories = 400;
    List<String> jdk7List = r.jdk7Impl(dishList, maxCalories);
    List<String> jdk8List = r.jdk8Impl(dishList, maxCalories);
//    println(jdk7List);
//    println(jdk8List);
    jdk7List.stream().forEach(System.out::print);
    System.out.println();
    jdk8List.stream().forEach(System.out::print);
  }

  private static void println(List<String> list) {
    for(String s : list) {
      System.out.print(s + ", ");
    }
    System.out.println();

  }
}