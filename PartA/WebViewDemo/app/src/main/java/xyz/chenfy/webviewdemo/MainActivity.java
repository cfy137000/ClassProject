package xyz.chenfy.webviewdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.main_web);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//要想和js交互 必须开启Js
        webView.loadUrl("file:///android_asset/test.html");//加载本地页面
        webView.addJavascriptInterface(this, "demo");//demo是别名,和Js带代码里的是对应关系
    }

    //被js代码调用的方法,方法名需要和js代码一一对应
    @JavascriptInterface
    void clickOnAndroid(){
        Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:wave()");

            }
        });
       
    }
}
