package com.lanou.chenfengyao.androidserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SimpleHttpServer simpleHttpServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebConfiguration webConfiguration = new WebConfiguration();
        webConfiguration.setPort(8088);
        webConfiguration.setMaxParallels(50);
        simpleHttpServer = new SimpleHttpServer(webConfiguration);
        simpleHttpServer.startAsync();
    }

    @Override
    protected void onDestroy() {
        try {
            simpleHttpServer.stopAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
