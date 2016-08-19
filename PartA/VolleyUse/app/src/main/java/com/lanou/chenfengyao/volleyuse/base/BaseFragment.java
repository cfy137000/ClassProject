package com.lanou.chenfengyao.volleyuse.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.chenfengyao.volleyuse.nettool.NetTool;

/**
 * Created by ChenFengYao on 16/8/15.
 */
public abstract class BaseFragment extends Fragment {
    //所有的子类,都会有这样的context对象
    //可以给Adapter,Dialog等使用
    protected Context mContext;

    protected NetTool mNetTool;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetTool = new NetTool(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayout() != 0) {
            //设置了布局,就绑定指定布局
            return inflater.inflate(setLayout(), container, false);
        }else {
            //如果没有绑定布局,就先打印出错误日志,并调用父类的方法,来防止项目崩溃
            Log.e("BaseFragment","Fragment:"+this.getClass().getSimpleName()+" 没有绑定布局");
            return super.onCreateView(inflater,container,savedInstanceState);
        }
    }

    //设置布局
    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        mNetTool.onDestroy();//取消未完成的网络请求
        super.onDestroy();
    }

    //初始化组件,在该方法里,做FindViewById的操作
    protected abstract void initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();//初始化数据
    }

    //初始化数据,例如网络请求,查询数据库等
    protected abstract void initData();

    //fragment本身的组件,来做FindViewByID的操作时调用
    //会在方法里,自动的getView-fragment所绑定的布局
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }

    //当要FindView的操作对象是指定的某个View的时候,调用
    //例如 popupwindow Dialog等
    protected <T extends View> T bindView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
