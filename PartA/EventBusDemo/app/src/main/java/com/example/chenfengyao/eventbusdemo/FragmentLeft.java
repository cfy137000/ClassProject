package com.example.chenfengyao.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.greenrobot.event.EventBus;


/**
 * Created by ChenFengYao on 15/10/28.
 */
public class FragmentLeft extends Fragment implements View.OnClickListener {
    Button one,two,three;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left,null);
        one = (Button) view.findViewById(R.id.btn_one);
        two = (Button) view.findViewById(R.id.btn_two);
        three = (Button) view.findViewById(R.id.btn_thr);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        String text = "";
        int what = 0;
        switch (v.getId()){
            case R.id.btn_one:
              //  text = "one";
                what = 1;
                break;
            case R.id.btn_two:
                text = "two";
                what = 2;
                break;
            case R.id.btn_thr:
                text = "three";
                break;
        }
        EventBus.getDefault().post(new MyEvent.ServiceEvent(what));//发布消息
    }
}
