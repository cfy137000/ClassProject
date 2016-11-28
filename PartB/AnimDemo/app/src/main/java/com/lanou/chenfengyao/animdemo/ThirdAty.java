package com.lanou.chenfengyao.animdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/25,
 * otherwise, I do not know who create it either.
 */
public class ThirdAty extends AppCompatActivity {
    @Bind(R.id.test)
    TextView test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);

        if (test == null){
            Log.d("ThirdAty", "null");
        }else {
            Log.d("ThirdAty", "bu");
        }
    }
}
