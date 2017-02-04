package com.lanou.chenfengyao.socketdemo.withrx;

import android.view.View;
import android.widget.Button;

import java.io.OutputStream;
import java.io.PrintStream;

import rx.Observable;
import rx.Subscriber;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/12/2,
 * otherwise, I do not know who create it either.
 */

public class OutputObservable implements Observable.OnSubscribe<PrintStream> {
    private Button mButton;
    private OutputStream mOutputStream;
    private final PrintStream mPrintStream;

    public OutputObservable(Button button, OutputStream outputStream) {
        mButton = button;
        mOutputStream = outputStream;
        mPrintStream = new PrintStream(outputStream);

    }

    @Override
    public void call(final Subscriber<? super PrintStream> subscriber) {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriber.onNext(mPrintStream);
            }
        });
    }
}
