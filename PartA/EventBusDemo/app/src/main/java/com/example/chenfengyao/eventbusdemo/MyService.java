package com.example.chenfengyao.eventbusdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import de.greenrobot.event.EventBus;

/**
 * Created by ChenFengYao on 15/10/28.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        Log.i("Sysout","destory");
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void next(){
        Log.i("Sysout","下一曲");
    }

    public void onEvent(MyEvent.ServiceEvent serviceEvent){
        switch (serviceEvent.getWhat()){
            case 1:
                next();//下一曲
                break;
            case 2:
                stopSelf();//服务停止
                break;
        }

    }
}
