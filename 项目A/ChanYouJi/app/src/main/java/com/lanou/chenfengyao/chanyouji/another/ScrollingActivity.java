package com.lanou.chenfengyao.chanyouji.another;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lanou.chenfengyao.chanyouji.FastBlur;
import com.lanou.chenfengyao.chanyouji.R;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

public class ScrollingActivity extends AppCompatActivity {
   // private NestedScrollView nestedScrollView;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView bottomIv, topIv;
    int max;

    private RecyclerView recyclerView;
    private RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
       // nestedScrollView = (NestedScrollView) findViewById(R.id.main_scroll);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomIv = (ImageView) findViewById(R.id.bottom_iv);
        topIv = (ImageView) findViewById(R.id.top_iv);
        //处理成毛玻璃的图片
        topIv.setImageBitmap(FastBlur.doBlur(BitmapFactory.decodeResource(getResources(), R.mipmap.test, new BitmapFactory.Options()), 15));


        topIv.setAlpha(0f);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //滑动的最大距离是 AppBarLayout - toolBar - 状态栏高度
                //在滑动的时候,动态处理透明度
                max = appBarLayout.getHeight() - toolbar.getHeight() - getStatusBarHeight();
                topIv.setAlpha((-verticalOffset) / (float) max);

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        rvAdapter = new RvAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(rvAdapter));
    }


    //获得状态栏的高度
    private int getStatusBarHeight() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }
}
