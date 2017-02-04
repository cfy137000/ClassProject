package com.lanou.chenfengyao.socketdemo.withrx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import rx.Observable;
import rx.functions.Func1;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/12/2,
 * otherwise, I do not know who create it either.
 */

public class RxAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<Socket> socketObservable = Observable.create(new SocketObservable());
        socketObservable.flatMap(new Func1<Socket, Observable<String>>() {
            @Override
            public Observable<String> call(Socket socket) {
                try {
                    InputStream inputStream = socket.getInputStream();
                    return Observable.create(new InputObservable(inputStream));

                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            }
        });


    }
}
