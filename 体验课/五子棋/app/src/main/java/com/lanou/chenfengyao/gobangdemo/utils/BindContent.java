package com.lanou.chenfengyao.gobangdemo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ChenFengYao on 16/1/21.
 * 为Activity绑定布局的
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindContent {
    int value() default 0;
}
