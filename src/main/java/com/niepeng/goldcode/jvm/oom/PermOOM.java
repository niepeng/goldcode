package com.niepeng.goldcode.jvm.oom;


import java.util.ArrayList;
import java.util.List;
import net.sf.cglib.beans.BeanGenerator;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/13
 */

public class PermOOM {

  public static void main(String[] args) {
    List<Object> list = new ArrayList<>();
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      BeanGenerator generator = new BeanGenerator();
      generator.addProperty("i" + i, int.class);
      generator.create();
    }
  }
}