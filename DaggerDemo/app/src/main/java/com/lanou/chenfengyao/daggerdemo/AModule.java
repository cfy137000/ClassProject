package com.lanou.chenfengyao.daggerdemo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChenFengYao on 16/9/8.
 */
@Module
public class AModule {
    @Provides
    public A provideA(){
        return new A();
    }
}
