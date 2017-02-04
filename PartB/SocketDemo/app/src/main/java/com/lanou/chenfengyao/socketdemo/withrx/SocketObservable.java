package com.lanou.chenfengyao.socketdemo.withrx;

import java.io.IOException;
import java.net.Socket;

import rx.Observable;
import rx.Subscriber;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/12/2,
 * otherwise, I do not know who create it either.
 */

public class SocketObservable implements Observable.OnSubscribe<Socket> {
    private static final String HOST = "";
    private static final int PORT = 0;
    private static Socket sSocket;
    @Override
    public void call(Subscriber<? super Socket> subscriber) {
        if(sSocket == null){
            try {
                sSocket = new Socket(HOST,PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        subscriber.onNext(sSocket);
    }
}
