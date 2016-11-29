package com.lanou.chenfengyao.examdemo.test;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/27,
 * otherwise, I do not know who create it either.
 */

public interface HttpListener<Bean> {
    void onSuccess(Bean bean);

    void onError(Throwable e);
}
