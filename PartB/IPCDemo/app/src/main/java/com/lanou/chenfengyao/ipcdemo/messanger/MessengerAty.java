package com.lanou.chenfengyao.ipcdemo.messanger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lanou.chenfengyao.ipcdemo.R;

public class MessengerAty extends AppCompatActivity implements View.OnClickListener {
    private Messenger mService;
    private TestBean mTestBean;

    //用于接收的Messenger
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MessengerService.GET_RESULT) {
                Log.i("Sysout", "Int form process B is "+msg.arg1);
            } else {
                super.handleMessage(msg);
            }
        }
    });

    private boolean isBinding = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Log.d("MessengerAty", "连接成功");

            isBinding = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isBinding = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager_aty);
        bindService(new Intent(this, MessengerService.class)
                , mConnection, BIND_AUTO_CREATE);


        findViewById(R.id.messenger_send_btn).setOnClickListener(this);

        mTestBean = new TestBean();
        mTestBean.setName("aaa");
        mTestBean.setAge(18);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messenger_send_btn:
                Message message = Message.obtain();
                message.what = MessengerService.GET_RESULT;
//                message.obj = mTestBean;
                Bundle bundle = new Bundle();
                bundle.putParcelable("key",mTestBean);
                bundle.putInt("int",1);
                bundle.putString("aa","aaaaaa");
                message.setData(bundle);
                message.replyTo = mMessenger;
                try {
                    //发送消息
                    Log.d("MessengerAty", "发送消息");
                    mService.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
