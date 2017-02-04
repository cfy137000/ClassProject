package xyz.chenfy.rxjavademo.forview;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by ChenFengYao on 16/9/18.
 */
public class ViewClickOnSubscribe implements Observable.OnSubscribe<View> {
    private View[] mViews;
    private MyClickListener mMyClickListener;

    public ViewClickOnSubscribe(Button ... views) {
        mViews = views;

    }

    @Override
    public void call(Subscriber<? super View> subscriber) {
        MyClickListener myClickListener = new MyClickListener(subscriber);
        for (View view : mViews) {
            view.setOnClickListener(myClickListener);
        }
        Log.d("ViewClickOnSubscribe", "call");
    }

    class MyClickListener implements View.OnClickListener{
        private Subscriber<? super View> mSubscriber;

        public MyClickListener(Subscriber<? super View> subscriber) {
            mSubscriber = subscriber;
        }

        @Override
        public void onClick(View view) {
            mSubscriber.onNext(view);
        }
    }
}
