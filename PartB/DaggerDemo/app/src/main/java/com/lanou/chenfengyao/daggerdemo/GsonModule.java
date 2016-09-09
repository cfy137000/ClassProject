package com.lanou.chenfengyao.daggerdemo;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChenFengYao on 16/9/8.
 * 有的时候 项目里引用了第三方的类库,第三方类库有不能修改,所以不能把Inject的注解加到
 * 这些类中,所以就需要新建一个类来提供Gson的实例
 */
@Module
public class GsonModule {
    @Provides
    public Gson providedGson(){
        return new Gson();

    }
}
