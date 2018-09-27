package com.niepeng.goldcode.common.lazyqueue.v1;


import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Data;
import lombok.ToString;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 18/9/27
 */
@Data
@ToString
public class Task implements Delayed {

  /**
   * 执行时间
   */
  private final long time;

  /**
   * 类型
   */
  private final String type;

  /**
   * 延迟队列执行参数
   */
  private Object params;

  /**
   * 重试次数
   */
  private int degree;

  /**
   * 执行序列
   */
  private final long sequenceNumber;

  private static AtomicLong sequence = new AtomicLong(0);

  public Task(long delayTime, String type, Object params, int degree) {
    this.time = System.nanoTime() + delayTime;
    this.type = type;
    this.params = params;
    this.degree = degree;
    sequenceNumber = sequence.getAndIncrement();
  }


  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    if (o == this) {
      return 0;
    }

    if (o instanceof Task) {
      Task task = (Task) o;
      long timeDistance = time - task.time;
      if (timeDistance > 0) {
        return 1;
      }
      if (timeDistance < 0) {
        return -1;
      }

      return sequenceNumber < task.sequenceNumber ? -1 : 1;
    }

    long distance = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
    return distance == 0 ? 0 : (distance > 0 ? 1 : -1);
  }
}