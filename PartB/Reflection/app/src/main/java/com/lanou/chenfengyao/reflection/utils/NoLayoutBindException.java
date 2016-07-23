package com.lanou.chenfengyao.reflection.utils;

/**
 * Created by ChenFengYao on 16/4/10.
 * 没有绑定布局的异常
 */
public class NoLayoutBindException extends RuntimeException {
    public NoLayoutBindException(String detailMessage) {
        super(detailMessage);
    }
}
