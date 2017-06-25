package com.niepeng.goldcode.common.rpc.simpleSocket;
/**
 * Created by lsb on 17/5/26.
 */


/**
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/5/26
 */

public class EchoServiceImpl implements EchoService {

  @Override
  public String echo(String ping) {
    return ping != null ? ping + " --> I am ok." : " I am ok";
  }
}