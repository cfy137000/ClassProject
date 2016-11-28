package com.lanou.chenfengyao.animdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/25,
 * otherwise, I do not know who create it either.
 */
public class SecondAty extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.second_btn_2)
    TextView secondBtn2;
    @Bind(R.id.second_btn1)
    TextView secondBtn1;
    @Bind(R.id.second_btn3)
    TextView secondBtn3;
    @Bind(R.id.second_btn4)
    TextView secondBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在setContentView之前,设置好动画效果
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        Transition explored = TransitionInflater.from(this)
                .inflateTransition(R.transition.explode);
        //设置Aty进入退出,再进的动画
        getWindow().setExitTransition(explored);
        getWindow().setEnterTransition(explored);
        getWindow().setReenterTransition(explored);


        secondBtn1.setOnClickListener(this);
        secondBtn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.second_btn1: {
                Intent intent = new Intent(this, FourActivity.class);
                Pair one = new Pair(secondBtn1, ViewCompat.getTransitionName(secondBtn1));
                Pair two = new Pair(secondBtn2, ViewCompat.getTransitionName(secondBtn2));
                Pair three = new Pair(secondBtn3, ViewCompat.getTransitionName(secondBtn3));
                Pair four = new Pair(secondBtn4, ViewCompat.getTransitionName(secondBtn4));
                Bundle bundle = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this, one, two, three, four)
                        .toBundle();
                startActivity(intent, bundle);
            }
            break;
            case R.id.second_btn_2: {
                Intent intent = new Intent();
                intent.setClass(this, ThirdAty.class);
                startActivity(intent, ActivityOptions
                        .makeSceneTransitionAnimation(this, v, "sharedView")
                        .toBundle());
                break;
            }
        }
    }
}
