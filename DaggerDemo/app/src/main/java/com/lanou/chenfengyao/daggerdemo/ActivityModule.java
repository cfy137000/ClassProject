package com.lanou.chenfengyao.daggerdemo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChenFengYao on 16/9/7.
 * 提供依赖的类
 */
@Module
public class ActivityModule {
    @Provides UserModel provideUserModel(){
        return new UserModel();
    }
}
