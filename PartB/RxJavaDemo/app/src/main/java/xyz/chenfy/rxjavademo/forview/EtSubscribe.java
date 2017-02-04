package xyz.chenfy.rxjavademo.forview;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/30,
 * otherwise, I do not know who create it either.
 */

public class EtSubscribe implements Observable.OnSubscribe<String> {
    private EditText mEditText;
    private final MyTextWatch mMyTextWatch;

    public EtSubscribe(EditText editText) {
        mEditText = editText;
        mMyTextWatch = new MyTextWatch();
        mEditText.addTextChangedListener(mMyTextWatch);

    }

    @Override
    public void call(Subscriber<? super String> subscriber) {
        Log.d("EtSubscribe", "dingyue");
        mMyTextWatch.setSubscriber(subscriber);
    }

    class MyTextWatch implements TextWatcher{
        Subscriber<? super String> subscriber;

        public void setSubscriber(Subscriber<? super String> subscriber) {
            if(this.subscriber == null) {
                this.subscriber = subscriber;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            subscriber.onNext(s.toString());
        }
    }
}
