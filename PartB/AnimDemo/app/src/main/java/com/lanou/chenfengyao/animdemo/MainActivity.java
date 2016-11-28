package com.lanou.chenfengyao.animdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainBtn = (Button) findViewById(R.id.main_btn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.lanou.chenfengyao.headerrecyclerview.MainActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(MainActivity.this,
//                        SecondAty.class);
//                startActivity(intent, ActivityOptions
//                        .makeSceneTransitionAnimation(MainActivity.this)
//                        .toBundle());
            }
        });
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options
                            = ActivityOptions.makeSceneTransitionAnimation(
                            MainActivity.this,fab,fab.getTransitionName());
                    startActivity(new Intent(MainActivity.this,
                            OtherActivity.class),options.toBundle());
                }else {
                    startActivity(new Intent(MainActivity.this,OtherActivity.class));
                }
            }
        });
    }
}
