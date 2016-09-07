package com.lanou.chenfengyao.daggerdemo;

import dagger.Component;

/**
 * Created by ChenFengYao on 16/9/7.
 * 连接提供依赖和消费依赖对象的组件
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    //参数必须是真正消耗依赖的类型MainActivity,而不可以写成欺负类,
    void inject(MainActivity activity);
}
