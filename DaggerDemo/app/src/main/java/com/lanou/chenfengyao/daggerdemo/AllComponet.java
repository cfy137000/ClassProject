package com.lanou.chenfengyao.daggerdemo;

import dagger.Component;

/**
 * Created by ChenFengYao on 16/9/8.
 */

//表明,引用module为AModule.class 同时依赖两个module
@Component(modules = {GsonModule.class,AModule.class})
public interface AllComponet {
    void inject(MainActivity mainActivity);
}
