package com.example.chenfengyao.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ChenFengYao on 15/11/6.
 */
public class TestService extends Service {

    MyTestAIDL.Stub stub = new MyTestAIDL.Stub() {
        @Override
        public void controlService(int flag) throws RemoteException {
            Log.i("TestService","flag:"+flag);
        }

        @Override
        public void changeData(TestBean testBean) throws RemoteException {
            Log.i("TestService","testBean:"+testBean.testString);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
