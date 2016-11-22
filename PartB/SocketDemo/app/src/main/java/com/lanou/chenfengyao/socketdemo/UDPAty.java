package com.lanou.chenfengyao.socketdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ChenFengYao on 16/7/26.
 */
public class UDPAty extends AppCompatActivity {
    private Button sendBtn;
    private EditText mainEt;
    private String mHost = "192.168.43.162";
    private int port = 3000;

    private DatagramSocket mDatagramSocket;
    private InetSocketAddress mSocketAddress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUDP();

        sendBtn = (Button) findViewById(R.id.send_btn);
        mainEt = (EditText) findViewById(R.id.main_et);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String recData = mainEt.getText().toString();
                        //发送数据到
                        try {
                            DatagramPacket dpSend = new DatagramPacket(recData.getBytes()
                                    , recData.getBytes().length
                                    , mSocketAddress);
                            mDatagramSocket.send(dpSend);

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }

    private void initUDP() {
        try {
            mDatagramSocket = new DatagramSocket(port);
            //每个数据包的大小
            new Thread(new Runnable() {

                private String mData;

                @Override
                public void run() {
                    while (true) {
                        mSocketAddress = new InetSocketAddress(mHost,port);
                        try {
                            //一直等待接收数据
                            byte[] buffer = new byte[1024];
                            DatagramPacket receiver = new DatagramPacket(buffer, 1024);
                            mDatagramSocket.receive(receiver);
                            mData = new String(receiver.getData(),0,receiver.getLength());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UDPAty.this, mData, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
