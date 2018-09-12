package com.niepeng.goldcode.jvm.problem;

/**
 * 写一段代码，让2个线程，相互死锁(A等待B，B等待A)。导出线程dump，并分析。给出死锁代码，线程dump和分析过程
 */
public class DeadLockMain {

  public static void main(String[] args) throws InterruptedException {
    A a = new A();
    B b = new B();

    a.next = b;
    b.next = a;

    Thread at = new Thread(a, "AThread");
    Thread bt = new Thread(b, "BThread");

    //启动线程
    at.start();
    Thread.sleep(10);
    bt.start();
    Thread.sleep(10);
  }


}

class A extends Base implements Runnable {

  Base next;

  @Override
  public void run() {
    move();
  }

  @Override
  public synchronized void move() {
    System.out.println("当前线程：" + Thread.currentThread().getName()
        + " | 进入了" + this.getClass().getSimpleName() + " 获取到资源 | 准备调用："
        + next.getClass().getSimpleName());


    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    next.move();
  }
}

class B extends Base implements Runnable {

  Base next;

  @Override
  public void run() {
    move();
  }

  @Override
  public synchronized void move() {
    System.out.println("当前线程：" + Thread.currentThread().getName()
        + " | 进入了" + this.getClass().getSimpleName() + " 获取到资源 | 准备调用："
        + next.getClass().getSimpleName());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    next.move();
  }
}

class Base {
  public synchronized void move(){
  }
}
