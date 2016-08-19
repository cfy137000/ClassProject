package com.lanou.chenfengyao.volleyuse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lanou.chenfengyao.volleyuse.nettool.NetTool;

/**
 * Created by ChenFengYao on 16/8/15.
 * Activity的基类
 */
public abstract class BaseAty extends AppCompatActivity implements View.OnClickListener {
    protected NetTool mNetTool;//网络请求的工具类

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetTool = new NetTool(this);//设置了tag是自己,当Activity被销毁的时候,可以通过这个tag来取消此Activity的所有网络请求,来减少内存占用
        if (setLayout() != 0) {
            setContentView(setLayout());//绑定布局
        } else {
            //没有绑定布局,弹出错误日志
            Log.e("BaseAty","Activity:"+this.getClass().getSimpleName()+" 没有绑定布局");
        }
        initView();//初始化组件,findViewById的操作
        initData();//初始化数据

    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initData();

    //简化findViewById
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }



    //设置点击事件的方法,方法是不固定参数个数的,该方法可以不写
    protected void setClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    //在Activity销毁的时候,把未完成的网络请求取消了
    @Override
    protected void onDestroy() {
        mNetTool.onDestroy();
        mNetTool = null;
        super.onDestroy();
    }
}
