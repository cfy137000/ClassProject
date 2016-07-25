package com.lanou.chenfengyao.socketservice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ChenFengYao on 16/3/23.
 */
public class AndroidService {
    public static void main(String[] args) throws IOException {
        ServerSocket serivce = new ServerSocket(40000);
        while (true) {
            //等待客户端连接
            Socket socket = serivce.accept();
            new Thread(new AndroidRunable(socket)).start();
        }
    }
}
