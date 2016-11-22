package com.wswenyue.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ChenFengYao on 16/9/26.
 */
public class LooperAty extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_looper);
        findViewById(R.id.looper).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(LooperAty.this, "asdfsadf", Toast.LENGTH_SHORT).show();
                Looper.loop();
//                Handler handler = new Handler();
            }
        }).start();
    }
}
