package com.niepeng.goldcode.common.zookeeper;
/**
 * Created by lsb on 17/4/28.
 */

import org.I0Itec.zkclient.ZkClient;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lsb@tzg.cn
 * @date 17/4/28
 */
public class ZooKeeperHello2 {

  public static void main(String[] args) throws Exception {
    ZkClient zkClient = new ZkClient("localhost:2181,localhost:2182,localhost:2183");
    String node = "/app2";
    if (!zkClient.exists(node)) {
      zkClient.createPersistent(node, "hello zk");
    }
    System.out.println(zkClient.readData(node));
  }

}