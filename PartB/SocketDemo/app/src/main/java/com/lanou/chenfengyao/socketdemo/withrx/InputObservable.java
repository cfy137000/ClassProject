package com.lanou.chenfengyao.socketdemo.withrx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import rx.Observable;
import rx.Subscriber;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/12/2,
 * otherwise, I do not know who create it either.
 */

public class InputObservable implements Observable.OnSubscribe<String> {
    InputStream mInputStream;

    public InputObservable(InputStream inputStream) {
        mInputStream = inputStream;
    }

    @Override
    public void call(Subscriber<? super String> subscriber) {
        InputStreamReader inputStreamReader
                = new InputStreamReader(mInputStream);
        BufferedReader bufferedReader
                = new BufferedReader(inputStreamReader);

        while (true) {
            String result = "";
            String line = "";

            try {
                while (!"".equals(line = bufferedReader.readLine())) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

           subscriber.onNext(result);

        }
    }
}
