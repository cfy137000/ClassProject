package com.lanou.chenfengyao.gobangdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lanou.chenfengyao.gobangdemo.utils.BindContent;
import com.lanou.chenfengyao.gobangdemo.utils.BindView;

import java.lang.reflect.Field;

/**
 * Created by ChenFengYao on 16/1/21.
 * 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentInject();//绑定布局
        viewInject();//绑定组件
        initData();
    }

    public abstract void initData();

    private void contentInject(){
        Class clazz = this.getClass();
        if(clazz.isAnnotationPresent(BindContent.class)){
            BindContent bindContent = (BindContent) clazz.getAnnotation(BindContent.class);
            int id = bindContent.value();
            if(id > 0){
                this.setContentView(id);
            }
        }
    }

    //遍历注释,去执行findViewById的方法
    private void viewInject() {
        Class clazz = this.getClass();//将当期对象转化成类对象
        Field[] fields = clazz.getDeclaredFields();//获得所有的成员变量
        for (Field field : fields) {
            //循环遍历
            if (field.isAnnotationPresent(BindView.class)) {
                BindView bindView = field.getAnnotation(BindView.class);
                int id = bindView.value();
                if (id > 0) {
                    //如果成员变量被BindView修饰
                    field.setAccessible(true);//让这个成员可以被修改
                    try {
                        field.set(this, this.findViewById(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
