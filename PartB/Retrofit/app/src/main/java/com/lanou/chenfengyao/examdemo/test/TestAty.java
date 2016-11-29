package com.lanou.chenfengyao.examdemo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.lanou.chenfengyao.examdemo.DetailBean;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/27,
 * otherwise, I do not know who create it either.
 */

public class TestAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpUtil.getData(1, 1, 10, new HttpListener<DetailBean>() {
            @Override
            public void onSuccess(DetailBean detailBean) {
                Log.d("Sysout-onSuccess", detailBean.getTotal_pages() + "");
                Toast.makeText(TestAty.this, detailBean.getFeeds().get(0).getContent_type()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.d("Sysout-onError", e.getMessage());
            }
        });
    }
}
