package com.lanou.chenfengyao.ipcdemo.messanger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/12/6,
 * otherwise, I do not know who create it either.
 */

public class MessengerService extends Service {
    public static final int GET_RESULT = 1;
    private final Messenger mMessenger = new Messenger(new Handler() {
        private int remoteInt = 1;

        @Override
        public void handleMessage(Message msg) {
            Log.d("MessengerService", "收到了消息");
            if (msg.what == GET_RESULT) {
                try {
                    Message message = obtainMessage();
                    message.what = GET_RESULT;
                    message.arg1 = remoteInt++;
                    Bundle data = msg.getData();
//                    ClassLoader classLoader = data.getClassLoader();
                    data.setClassLoader(getClass().getClassLoader());
                    TestBean testBean = data.getParcelable("key");
                    Log.d("MessengerService", testBean.getName());
//                   int i =  data.getInt("int");
//                    Log.d("MessengerService", "i:" + i);
                    String aa = data.getString("aa");
                    Log.d("MessengerService", aa + "");
                   // Log.d("MessengerService", testBean.getName());
                    //  Object obj = msg.obj;
                    //  Log.d("MessengerService", obj.getClass().getSimpleName());
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                super.handleMessage(msg);
            }
        }
    });

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
