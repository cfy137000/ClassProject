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
import android.widget.TextView;

import java.util.Collections;

public class ScrollingActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private CollectionAdapter mCollectionAdapter;
    private TextView playAllTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        playAllTv = (TextView) findViewById(R.id.play_all);
        TabLayout.Tab tab = mTabLayout.newTab();
        tab.setText("全部歌曲");
        mTabLayout.addTab(tab);
        TabLayout.Tab tab1 = mTabLayout.newTab();
        tab1.setText("专辑");
        mTabLayout.addTab(tab1);



        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainAdapter = new MainAdapter(this);
        mCollectionAdapter = new CollectionAdapter(this);
        mRecyclerView.setAdapter(mMainAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    mRecyclerView.setAdapter(mMainAdapter);
                    playAllTv.setVisibility(View.VISIBLE);
                }else {
                    mRecyclerView.setAdapter(mCollectionAdapter);
                    playAllTv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
