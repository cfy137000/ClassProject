package com.lanou.chenfengyao.daggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    @Inject
    UserModel mUserModel;//使用@Inject标识被注入的对象 不能为private
    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.user_desc_line);

        mActivityComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule()).build();
        mActivityComponent.inject(this);

        mTextView.setText(mUserModel.name);
    }
}
