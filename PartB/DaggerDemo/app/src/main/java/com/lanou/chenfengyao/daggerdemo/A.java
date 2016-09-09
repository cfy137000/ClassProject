package com.lanou.chenfengyao.daggerdemo;

import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

/**
 * Created by ChenFengYao on 16/9/8.
 * 这个类是Activity所需要的,在传统写法中 会在MainActivity中去new出来
 * 现在不用了
 */


public class A {
    private String field;

    @Inject
    public A() {

        field = "aaa";
    }

    public void doSomething(TextView textView) {
        textView.setText("A set Text");
        Log.d("A", "A Do Something");
    }
}
