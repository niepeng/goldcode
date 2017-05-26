package com.niepeng.goldcode.common.rpc.simpleSocket;
/**
 * Created by lsb on 17/5/26.
 */


import java.net.InetSocketAddress;

/**
 * @author 聂鹏
 * @version 1.0
 * @email lisenbiao@51huadian.cn
 * @date 17/5/26
 */

public class RPCTest {

  static final String host =  "localhost";
  static final int port = 8088;

  public static void main(String []args) throws  Exception{
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          RPCProvider.provider(host, port);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();


    RPCImporter<EchoService> importer = new RPCImporter<EchoService>();
    EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress(host, port));

    System.out.println(echo.echo("hello world."));



  }
}