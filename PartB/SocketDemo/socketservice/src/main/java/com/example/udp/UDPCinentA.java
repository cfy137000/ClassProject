package com.example.udp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by ChenFengYao on 16/7/26.
 *
 */
public class UDPCinentA {
    public static void main(String args[]){
        int port = 8089;
        try {
            //用来接收数据 和发送数据的工具
            //定义接收数据的端口
            DatagramSocket datagramSocket =
                    new DatagramSocket(port);
            //每个数据包的大小
            byte[] buffer = new byte[1024];
            //用来接收的Packet
            DatagramPacket receiver =
                    new DatagramPacket(buffer,1024);
            System.out.println("客户端A准备接收数据");
            while (true){
                //一直等待接收数据
                datagramSocket.receive(receiver);

                String data = new String(receiver.getData()
                        ,receiver.getOffset(),receiver.getLength());
                System.out.println("客户端A接收到了数据"+data);
                //模拟回复数据
                String recData = "你说的:"+data+" 我知道了";
                DatagramPacket dpSend = new DatagramPacket(recData.getBytes()
                        , recData.getBytes().length

                String data = new String(receiver.getData(),
                        receiver.getOffset(),
                        receiver.getLength());
                System.out.println("客户端A接收到了数据"+data);
                //模拟回复数据
                String recData = "你说的:"+data+" 我知道了";
                DatagramPacket dpSend = new DatagramPacket(recData.getBytes(),
                        recData.getBytes().length

                        , receiver.getAddress(), receiver.getPort());
                datagramSocket.send(dpSend);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
