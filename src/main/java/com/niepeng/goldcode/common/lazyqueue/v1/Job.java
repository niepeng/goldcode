package com.niepeng.goldcode.common.lazyqueue.v1;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/27
 */
@Slf4j
public class Job {

  private static final int defaultDelayTimeSeconds = 30;

  DelayQueue<Task> delayQueue = new DelayQueue<>();

  ExecutorService executorService = new ThreadPoolExecutor(10, 30, 30, TimeUnit.MINUTES, new LinkedBlockingDeque<>(10), new CallerRunsPolicy());


  Thread daemonThread;

  public Job() {
    daemonThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          execute();
        }
      }
    });

    daemonThread.setDaemon(true);
    daemonThread.setName("delayQueueJob");
    daemonThread.start();
  }


  public void put(long delayTime, String type, Object params, int degree) {
    log.info("【延时队列】推送:{}, params={}", type, params);
    long nanoTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.SECONDS);
    Task task = new Task(nanoTime, type, params, degree);
    delayQueue.put(task);
  }


  public void put(Task task) {
    long nanoTime = TimeUnit.NANOSECONDS.convert(defaultDelayTimeSeconds, TimeUnit.SECONDS);
    Task t = new Task(nanoTime, task.getType(), task.getParams(), task.getDegree() - 1);
    delayQueue.put(t);
  }

  public void execute() {
    Task task = null;
    try {
      task = delayQueue.take();
      log.info("【延时队列】执行任务:{}", task);
      final Task finalTask = task;
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          try {
            runTask(finalTask);
          } catch (Exception e) {
            log.error("【延时队列】异常,重新添加，任务信息：{}异常信息：{}", finalTask, e);
            tryAgainTask(finalTask);
          }
        }
      });
    } catch (Exception e) {
      log.error("【延时队列】异常,重新添加，任务信息：{}异常信息：{}", task, e);
      tryAgainTask(task);
    }
  }

  private void tryAgainTask(Task task) {
    if (task != null) {
      if (task.getDegree() <= 0) {
        log.warn("【延时队列】任务剩余次数为0，查询失败, task={}", task);
      } else {
        put(task);
      }
    }
  }

  /**
   * 结束不想处理的任务
   */
  public void endTask(Task task) {
    delayQueue.remove(task);
  }

  private void runTask(Task task) throws InterruptedException {
    /**
     * 根据task中的属性,来处理业务逻辑
     */
    Thread.sleep(1000);
    log.info("【延时队列】执行任务完成");
  }

}