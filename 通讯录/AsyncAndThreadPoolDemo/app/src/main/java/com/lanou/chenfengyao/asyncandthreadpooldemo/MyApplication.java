package com.lanou.chenfengyao.asyncandthreadpooldemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by ChenFengYao on 16/3/31.
 */
public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
