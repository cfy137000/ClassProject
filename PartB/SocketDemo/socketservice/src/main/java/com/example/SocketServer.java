package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Created by ChenFengYao on 16/7/25.
 */
public class SocketServer {
    //线程池,为并发考虑
    private ExecutorService mThreadPool;
    private WebConfig mWebConfig;
    private boolean isEnable;
    private ServerSocket mServerSocket;


    public SocketServer(WebConfig webConfig) {
        mWebConfig = webConfig;
        mThreadPool = Executors.newCachedThreadPool();

    }

    /**
     * 启动server(异步)
     */
    public void startAsync() {
        isEnable = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProcSycn();
            }
        }).start();
    }

    /**
     * 停止Server(异步)
     */
    public void stopAsync() throws IOException {
        if (isEnable) {
            return;
        }
        isEnable = false;
        mServerSocket.close();
        mServerSocket = null;

    }

    //异步执行
    private void doProcSycn() {
        try {
            InetSocketAddress socketAddress
                    = new InetSocketAddress(mWebConfig.getPort());
            mServerSocket = new ServerSocket();//创建出ServerSocket对象
            mServerSocket.bind(socketAddress);

            while (isEnable) {
                //ServerSocket的accept方法会让ServerSocket一直等在这行代码,
                //直到有远程的Socket对象连入
                final Socket remotePeer = mServerSocket.accept();
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String s = null;
                        try {
                            s = new String("一个远程连接已连接:".getBytes(), "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        out.println(s + remotePeer.getRemoteSocketAddress().toString());

                        onAcceptRemotePeer(remotePeer);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void onAcceptRemotePeer(Socket client) {
        try {
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;
            while (flag) {
                //接收从客户端发送过来的数据
                String str = buf.readLine();

                if (str == null || "".equals(str)) {
                    System.out.println("没有消息退出");
                    flag = false;
                } else {
                    if ("bye".equals(str)) {
                        //如果客户端发送的消息是"bye"就退出程序
                        flag = false;
                    } else {
                        System.out.println("收到消息" + str);
                        //将接收到的字符串前面加上echo，发送到对应的客户端
                        out.println("echo:" + str);
                    }
                }
            }
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
