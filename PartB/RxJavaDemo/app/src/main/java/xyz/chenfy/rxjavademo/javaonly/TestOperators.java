package xyz.chenfy.rxjavademo.javaonly;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by hasee on 2016/8/1.
 * 操作符
 */
public class TestOperators {
    public static void main(String args[]) {
        TestOperators testOperators = new TestOperators();
        testOperators.fun();
    }

    //该方法根据输入的字符串返回一个网站的url列表
    Observable<List<String>> query(String text) {
        List<String> urls = new ArrayList<>();
        urls.add("aaaaa");
        urls.add("bbbbb");
        urls.add("ccccc");
        urls.add("null");
        //Observable.create(urls);
        return Observable.just(urls);
    }

    public void fun() {
        System.out.println("打印URL");
        query("Hello,world")
                //Observable.flatMap()接收一个Observable的输出作为输入,同时输出另一个Observable
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
        System.out.println("不打印URL了,而是要打印收到的每个网站的标题");
        //不打印URL了,而是要打印收到的每个网站的标题
        query("Hello,world!")
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);//将集合中的每一个元素依次返回
                    }
                }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return getTitle(s); //根据网址获得标题
            }
        }).filter(new Func1<String, Boolean>() {//filter()输出 和输入相同 的元素,并且会过滤掉那些不满足检查条件的.
            @Override
            public Boolean call(String s) {
                return s != null;//如果不想输出null,可以从返回的title列表中过滤掉null值
            }
        })
                .take(5)//take()输出最多指定数量的结果
                .doOnNext(new Action1<String>() {//doOnNext()允许我们在每次输出一个元素之前做一些额外的事情
                    @Override
                    public void call(String s) {
                        saveTitle(s);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);//打印标题
                    }
                });
    }

    //返回网站的标题,如果404就返回null
    Observable<String> getTitle(String url) {
        return Observable.just(url)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return "null".equals(s) ? null : "处理成标题" + s;
                    }
                });

    }

    //保存起来
    void saveTitle(String title) {
        System.out.println("保存:" + title);
    }
}
