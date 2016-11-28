package com.lanou.chenfengyao.headerrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
        setContentView(R.layout.activity_main1);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        MainAdapter mainAdapter = new MainAdapter();
        View headView = LayoutInflater
                .from(this)
                .inflate(R.layout.head_view,null);

        View textHead = LayoutInflater
                .from(this)
                .inflate(R.layout.text_head,null);
        //把之前写好的Adapter 放到HeaderAdapter里包装上
        HeaderAdapter headerAdapter =
                new HeaderAdapter(mainAdapter);
        //添加头布局
        headerAdapter.addHeadView(headView);
        headerAdapter.addHeadView(textHead);
        //使用包装后的HeaderAdapter
        mRecyclerView.setAdapter(headerAdapter);
    }
}
