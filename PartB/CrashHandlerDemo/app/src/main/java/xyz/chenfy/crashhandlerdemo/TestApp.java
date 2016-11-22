package xyz.chenfy.crashhandlerdemo;

import android.app.Application;

/**
 * Created by hasee on 2016/7/30.
 */
public class TestApp extends Application {
    private static TestApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //在这里为应用设置异常处理,然后程序才能获取未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    
}
