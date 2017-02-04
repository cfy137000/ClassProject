package xyz.chenfy.rxjavademo.forview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import xyz.chenfy.rxjavademo.R;

/**
 * Created by ChenFengYao on 16/9/18.
 */
public class ViewAty extends AppCompatActivity {
    private Button onceBtn;
    private EditText onceEt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_view);

        onceBtn = (Button) findViewById(R.id.once_btn);
//        Observable.create()

        Observable<View> observable = Observable.create(new ViewClickOnSubscribe(onceBtn))
//                .debounce(1000, TimeUnit.MILLISECONDS);
        .throttleFirst(1,TimeUnit.SECONDS);
        observable.subscribe(new Action1<View>() {
            @Override
            public void call(View view) {
                Log.d("ViewAty", "被点击");
            }
        });

        onceEt = (EditText) findViewById(R.id.once_et);
        Observable.create(new EtSubscribe(onceEt))
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d("ViewAty--befor", s);
                        return s;
                    }
                }).debounce(1000,TimeUnit.MILLISECONDS)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.d("ViewAty--after", s);
                        return s;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("ViewAty--finally", s);
            }
        });

    }
}
