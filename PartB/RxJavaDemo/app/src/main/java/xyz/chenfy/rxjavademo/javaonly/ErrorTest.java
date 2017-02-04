package xyz.chenfy.rxjavademo.javaonly;

import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by ChenFengYao on 16/8/2.
 * 可以在订阅者的中获取异常信息
 * 这种模式的好处
 * 1.只要有异常发生onError()一定会被调用,这极大的简化了错误处理。
 * 只需要在一个地方处理错误即可以。
 * 2.操作符不需要处理异常,将异常处理交给订阅者来做，
 * Observerable的操作符调用链中一旦有一个抛出了异常，
 * 就会直接执行onError()方法
 * 3.你能够知道什么时候订阅者已经接收了全部的数据。
 */
public class ErrorTest {
    public static void main(String args[]) {
        final Random random = new Random();
        Observable.just("Hello World!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        if (random.nextBoolean())
                            throw new RuntimeException(s);
                        return s;
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("抛出了异常:" + e.toString());
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }
}
