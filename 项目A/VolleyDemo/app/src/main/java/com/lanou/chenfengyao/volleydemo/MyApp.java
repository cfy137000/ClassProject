package com.lanou.chenfengyao.volleydemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by ChenFengYao on 16/5/21.
 */
public class MyApp extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
