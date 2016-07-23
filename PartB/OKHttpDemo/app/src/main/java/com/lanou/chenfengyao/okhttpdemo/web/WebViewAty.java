package com.lanou.chenfengyao.okhttpdemo.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.lanou.chenfengyao.okhttpdemo.R;

/**
 * Created by ChenFengYao on 16/7/14.
 */
public class WebViewAty extends AppCompatActivity{
    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web);
        webView.addJavascriptInterface(this,"demo");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.31.228:8080");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return true;
            }
        });
    }

    @JavascriptInterface
    void onWebClick(){
        Toast.makeText(this, "点击了web按钮1", Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    void onWebClick2(){
        Toast.makeText(this, "点击了web按钮2", Toast.LENGTH_SHORT).show();
    }
}
