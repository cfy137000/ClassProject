package com.example.chenfengyao.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

/**
 * Created by ChenFengYao on 15/10/28.
 */
public class FragmentRight extends FragmentLeft {
    TextView right;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right,null);
        right = (TextView) view.findViewById(R.id.tv_right);
        EventBus.getDefault().register(this);//订阅
        return view;
    }

    //写回调方法
    public void onEvent(MyEvent.FirstEvent firstEvent){
        right.setText(firstEvent.getText());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消订阅
    }
}
