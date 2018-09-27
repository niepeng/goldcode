package com.niepeng.goldcode.generics;


import java.util.ArrayList;
import java.util.List;

/**
 * 泛型中:
 * List<? extends Frut> 和  List<? super Fruit>  的使用
 *
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/25
 */

public class Main {


  public static void main1(String[] args) {
    /**
     * List<? extends Frut> 表示 “具有任何从Fruit继承类型的列表”，编译器无法确定List所持有的类型，所以无法安全的向其中添加对象。
     * 可以添加null,因为null 可以表示任何类型。
     * 所以List 的add 方法不能添加任何有意义的元素，但是可以接受现有的子类型List<Apple> 赋值。
     */
    List<? extends Fruit> flist = new ArrayList<Apple>();
//     flist.add(new Apple());  // compile error
//     flist.add(new Fruit());  // compile error:
//     flist.add(new Object()); //compile error:
    flist.add(null); // only work for null


    Fruit fruit = flist.get(0);
    flist.contains(new Fruit());
    flist.contains(new Apple());

  }

  public static void main(String[] args) {
    /**
     * List<? super Fruit> 表示“具有任何Fruit超类型的列表”，列表的类型至少是一个 Fruit 类型，因此可以安全的向其中添加Fruit 及其子类型。
     * 由于List<? super Fruit>中的类型可能是任何Fruit 的超类型，无法赋值为Fruit的子类型Apple的List<Apple>.
     */
    List<? super Fruit> flist = new ArrayList<Fruit>();
    flist.add(new Fruit());
    flist.add(new Apple());
    flist.add(new RedApple());
//    List<? super Fruit> flist = new ArrayList<Apple>();  // compile error
  }
}