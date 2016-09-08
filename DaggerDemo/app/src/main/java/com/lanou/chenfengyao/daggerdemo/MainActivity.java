package com.lanou.chenfengyao.daggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Inject //被注入的属性是不能写成私有的
    public A a;

    @Inject
    public Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.user_desc_line);

        //注入
       // DaggerAComponent.builder().build().inject(this);
        DaggerAllComponet.builder().build().inject(this);
        String s = mGson.toJson(a);
        Log.d("MainActivity", s);

        a.doSomething(mTextView);

    }
}
