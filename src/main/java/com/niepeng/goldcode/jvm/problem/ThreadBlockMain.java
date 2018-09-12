package com.niepeng.goldcode.jvm.problem;

import java.util.Scanner;

public class ThreadBlockMain {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("请输入第一串字符：");
    String firStr = sc.next();
    System.out.println("你输入的是" + firStr);
    System.out.println("hello world");
  }
}