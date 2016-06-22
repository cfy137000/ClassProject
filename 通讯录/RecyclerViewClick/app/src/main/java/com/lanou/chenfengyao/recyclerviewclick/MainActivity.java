package com.lanou.chenfengyao.recyclerviewclick;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    MainAdapter mainAdapter;
    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.mainRv);
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("This is " + i);
        }
        mainAdapter = new MainAdapter(this,datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Log.d("MainActivity", "vh.getLayoutPosition():" + vh.getLayoutPosition());
            }

        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag;
                int swipFlag = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
                    //如果是网格,则有4个方向
                    dragFlag = ItemTouchHelper.UP|ItemTouchHelper.DOWN
                            |ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                }else {
                    //只有2个方向
                    dragFlag = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    swipFlag = ItemTouchHelper.END;//触发滑动的方向
                }
                return makeMovementFlags(dragFlag,swipFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPos = viewHolder.getLayoutPosition();
                int toPos = target.getLayoutPosition();
                Collections.swap(datas,fromPos,toPos);
                mainAdapter.notifyItemMoved(fromPos,toPos);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getLayoutPosition();
                mainAdapter.notifyItemRemoved(pos);
                datas.remove(pos);
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
               //如果当前状态不是空闲的
                if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
                   viewHolder.itemView.setBackgroundColor(Color.YELLOW);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
