package com.lanou.chenfengyao.volleyuse.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by ChenFengYao on 16/8/15.
 * 有全局的Context 需要在清单文件中进行注册
 */
public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
