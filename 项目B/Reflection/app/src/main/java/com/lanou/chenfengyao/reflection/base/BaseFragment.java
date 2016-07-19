package com.lanou.chenfengyao.reflection.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanou.chenfengyao.reflection.utils.BindContent;
import com.lanou.chenfengyao.reflection.utils.NoLayoutBindException;


/**
 * Created by ChenFengYao on 16/4/10.
 * Fragment的基类
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //重新启动的时候，判断状态来决定是否显示出来
        //来解决Fragment的堆叠问题
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    //记录当前Fragment的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(setContent(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected <T extends View> T bindView(@IdRes int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T bindView(@IdRes int id, View view) {
        return (T) view.findViewById(id);
    }

    /**
     * 绑定布局的方法
     * 使用的时候,只需要在类前加上@BindContent(R.layout.xx)即可
     */
    private int setContent() {
        Class clazz = this.getClass();
        if (clazz.isAnnotationPresent(BindContent.class)) {
            BindContent bindContent = (BindContent) clazz.getAnnotation(BindContent.class);
            int id = bindContent.value();
            if (id > 0) {
                return id;
            } else {
                throw new NoLayoutBindException(clazz.getSimpleName() + "没有绑定布局");
            }
        } else {
            throw new NoLayoutBindException(clazz.getSimpleName() + "没有绑定布局");
        }
    }

    //退出时将context对象置空
    @Override
    public void onDestroy() {
        super.onDestroy();
        context = null;
    }
}
