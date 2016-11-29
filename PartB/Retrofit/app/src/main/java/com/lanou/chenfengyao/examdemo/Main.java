package com.lanou.chenfengyao.examdemo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/18,
 * otherwise, I do not know who create it either.
 */

public class Main {
    public static void main(String[] args) {
       Observable.just("aaa")
               .map(new Func1<String, String>() {
                   @Override
                   public String call(String s) {
                       return null;
                   }
               });

    }
}
