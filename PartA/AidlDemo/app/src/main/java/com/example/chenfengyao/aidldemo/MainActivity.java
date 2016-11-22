package com.example.chenfengyao.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bindBtn,controlBtn,changeDataBtn;

    private MyTestAIDL myTestAIDL;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myTestAIDL = MyTestAIDL.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myTestAIDL = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindBtn = (Button) findViewById(R.id.btn_bind);
        controlBtn = (Button) findViewById(R.id.btn_control);
        changeDataBtn = (Button) findViewById(R.id.btn_change);

        bindBtn.setOnClickListener(this);
        controlBtn.setOnClickListener(this);
        changeDataBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind://绑定服务
                Intent intent = new Intent("com.example.chenfengyao.aidldemo.TestService");
                intent.setPackage("com.example.chenfengyao.aidldemo");
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_control://控制服务
                if(myTestAIDL!=null){
                    try {
                        myTestAIDL.controlService(0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_change://传递数据
                TestBean testBean= new TestBean();
                testBean.testString = "Test";
                try {
                    myTestAIDL.changeData(testBean);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
