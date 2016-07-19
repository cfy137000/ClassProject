package com.lanou.chenfengyao.reflection.utils;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ChenFengYao on 16/4/5.
 * 为Activity绑定布局的
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindContent {
    @LayoutRes int value() default 0;
}
