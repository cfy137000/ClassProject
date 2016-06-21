package com.lanou.chenfengyao.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Sysout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        Request request = new Request.Builder().url("http://apis.baidu.com/apistore/idservice/id?id=420984198704207896")
//                .header("apikey",  "05ac67fa1b96510639766fee04b02c1f")
//                .build();
//        Call call = okHttpClient.newCall(request);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.d(TAG, "failure");
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String htmlStr = response.body().string();
//                 Log.d(TAG, htmlStr);
//            }
//        });
//    OkHttpClientManager manager = OkHttpClientManager.getInstance();
        

        HashMap<String, String> map = new HashMap<>();
        map.put("phone number", "18624269142");
        OkHttpClientManager.postAsyn("http://api101.test.mirroreye.cn/index.php/user/send_code", new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d(TAG, "request:" + request);
            }

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
            }


        }, map);

    }
}
