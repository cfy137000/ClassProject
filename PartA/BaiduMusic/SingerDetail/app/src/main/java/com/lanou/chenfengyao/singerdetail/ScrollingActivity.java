package com.lanou.chenfengyao.singerdetail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ScrollingActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        TabLayout.Tab tab = mTabLayout.newTab();
        tab.setText("全部歌曲");
        mTabLayout.addTab(tab);
        TabLayout.Tab tab1 = mTabLayout.newTab();
        tab1.setText("专辑");
        mTabLayout.addTab(tab1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter = new MainAdapter(this);
        mRecyclerView.setAdapter(mainAdapter);
    }
}
