package com.lanou.chenfengyao.recyclerviewclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    MainAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.mainRv);
        mainAdapter = new MainAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Log.d("MainActivity", "vh.getLayoutPosition():" + vh.getLayoutPosition());
            }

        });
    }

}
