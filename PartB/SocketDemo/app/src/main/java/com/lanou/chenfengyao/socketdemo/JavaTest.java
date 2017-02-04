package com.lanou.chenfengyao.socketdemo;

import rx.Observable;
import rx.Subscriber;
import rx.internal.util.PlatformDependent;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/12/2,
 * otherwise, I do not know who create it either.
 */

public class JavaTest {
    public static int a = 0;
    public static void main(String[] args) {

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                a++;
                subscriber.onNext(a + "");
                System.out.println("call");
            }
        });

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        subscriber.add(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
        observable.subscribe(subscriber);

    }
}
