package com.lanou.chenfengyao.reflection.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.lanou.chenfengyao.reflection.utils.BindContent;
import com.lanou.chenfengyao.reflection.utils.NoLayoutBindException;


/**
 * Created by ChenFengYao on 16/4/5.
 * Activity的基类,所有的Activity默认使用Auto布局
 */
public abstract class BaseAty extends AppCompatActivity {
    protected FragmentControl fragmentControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent();//绑定布局
        setAty();
        initView();
        initData();
        fragmentControl = new FragmentControl(getSupportFragmentManager());
    }

    /**
     * 绑定布局的方法
     * 使用的时候,只需要在类前加上@BindContent(R.layout.xx)即可
     */
    private void setContent() {
        Class clazz = this.getClass();
        if (clazz.isAnnotationPresent(BindContent.class)) {
            BindContent bindContent = (BindContent) clazz.getAnnotation(BindContent.class);
            int id = bindContent.value();
            if (id > 0) {
                this.setContentView(id);
            }
        } else {
            throw new NoLayoutBindException(clazz.getSimpleName() + "没有绑定布局");
        }
    }

    public FragmentControl getFragmentControl() {
        return fragmentControl;
    }

    /**
     * 在绑定布局前如果需要多Activity进行设置
     * 则复写此方法
     */
    protected void setAty() {
        //在Activity创建的时候加入到List中
        ActivityControl.addAty(this);
    }


    /**
     * 绑定布局里的组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected <T extends View> T bindView(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected <T extends View> T bindView(@IdRes int id, View view) {
        return (T) view.findViewById(id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!fragmentControl.backFragment()) {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        //在销毁的时候让Activity绑定一个空的布局,可以有效降低内存
        ActivityControl.removeAty(this);//从ActivityList中移除
        super.onDestroy();
    }
}
