package com.lanou.chenfengyao.socketdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MainActivity extends AppCompatActivity {
    private Button sendBtn;
    private EditText mainEt;
    private String host  = "192.168.31.228";
    private int port = 8088;
    private Socket client;

    private PrintStream out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.send_btn);
        mainEt = (EditText) findViewById(R.id.main_et);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String str = mainEt.getText().toString();
                    //发送数据到服务端
                    out.println(str);
                    if("bye".equals(str)){
                        out.close();
                        try {
                            client.close();
                            client = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


            }
        });

        initSocket();
    }

    private void initSocket() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    client = new Socket(host, port);
                    client.setKeepAlive(true);
                   // client.setSoTimeout(30000);

                    //获取Socket的输出流，用来发送数据到服务端
                    out = new PrintStream(client.getOutputStream());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
