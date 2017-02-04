package xyz.chenfy.rxjavademo.javaonly;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by hasee on 2016/8/1.
 * Observable和Subscriber可以做任何事情
 * Observable可以是一个数据库查询,Subscriber用来显示查询结果
 * Observable可以是屏幕上的点击事件,Subscriber用来响应点击事件
 *
 * Observable和Subscriber是独立于中间的变换过程的
 * 在Observable和Subscriber中间可以增减任何数量的map.整个系统是高度可组合的,
 * 操作数据是一个很简单的过程.
 */
public class Main {

    public static void main(String args[]) {
        Main main = new Main();
        main.first();
        main.easyUse();
        main.mapOperators();
    }

    /**
     * 使用map操作符来对值进行修改
     * map()操作符就是用于变换Observable对象的,在一个Observable对象上可以多次使用map,最终传递给Subscriber对象
     * 它不必返回Observable对象的类型
     */
    public void mapOperators() {
        Observable.just("Hello World").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.replace("World", "change by map");//第一次修改
            }
        }).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.length();//第二次修改
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "字母的个数是" + integer;//第三次修改
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });


        Observable<String> observable = Observable.just("Hello Girl")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "aaa";
                    }
                });


    }

    /**
     * 简化一些的使用
     */
    public void easyUse() {
        //创建被观察者对象
        Observable<String> myObservable = Observable.just("Hello World2");
        //并不关心OnComplete和OnError，我们只需要在onNext的时候做一些处理，这时候就可以使用Action1类。
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable.subscribe(onNextAction);
        //或者一行
        Observable.just("Hello one line").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    /**
     * RxJava 的初次使用
     */
    public void first() {
        //被观察者
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World");
                subscriber.onCompleted();
            }
        });

        //观察者
        Subscriber<String> mySubscriber = new Subscriber<String>() {
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

        myObservable.subscribe(mySubscriber);
    }
}
