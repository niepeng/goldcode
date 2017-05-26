package com.niepeng.goldcode.common.rpc.simpleSocket;
/**
 * Created by lsb on 17/5/26.
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 聂鹏
 * @version 1.0
 * @date 17/5/26
 */

public class RPCProvider {

  static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public static void provider(String hostName, int port) throws Exception {

    ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress(hostName, port));

    try {
      while (true) {
        executor.execute(new ProviderTask(serverSocket.accept()));
      }
    } finally {
      serverSocket.close();
    }

  }

  private static class  ProviderTask implements  Runnable {

    Socket client = null;
    public ProviderTask(Socket socket) {
      this.client = socket;
    }

    @Override
    public void run() {
      ObjectInputStream input = null;
      ObjectOutputStream output = null;

      try {
        input = new ObjectInputStream(client.getInputStream());
        /**
         * 自定义规范:
         * 1.接口个名称
         * 2.方法名称
         * 3.请求参数类型列表
         * 4.请求参数值列表
         *
         * 得到输入值后,反射调用方法得到结果
         */
        String interfaceName = input.readUTF();
        String methodName = input.readUTF();
        Class<?>[] paramterTypes = (Class<?>[]) input.readObject();
        Object[] arguments = (Object[]) input.readObject();

        Class<?> service = Class.forName(interfaceName);
        Method method = service.getMethod(methodName, paramterTypes);
        Object result = method.invoke(service.newInstance(), arguments);

        output = new ObjectOutputStream(client.getOutputStream());
        output.writeObject(result);


      } catch(Exception e) {
        e.printStackTrace();
      } finally {

        if(output != null) {
            try {
              output.close();
            } catch(IOException e) {
              e.printStackTrace();
            }
        }

        if(input != null) {
          try {
            input.close();
          } catch(IOException e) {
            e.printStackTrace();
          }
        }

        if(client != null) {
          try {
            client.close();
          } catch(IOException e) {
            e.printStackTrace();
          }
        }
      }

    }
  }

}