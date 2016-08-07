package com.lanou.chenfengyao.chanyouji.another;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou.chenfengyao.chanyouji.R;
import com.timehop.stickyheadersrecyclerview.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ChenFengYao on 16/6/25.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyVh> implements com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter<RvAdapter.MyVh> {
    private Context context;
    private List<String> data;
    private List<String> head;

    public RvAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("这是" + i);
        }
        head = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            head.add("head" + i);
        }
    }

    @Override
    public MyVh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);
        MyVh myVh = new MyVh(view);
        return myVh;
    }

    @Override
    public void onBindViewHolder(MyVh holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public long getHeaderId(int position) {
       // Log.d("RvAdapter", "position:" + position);
        return position/11;//当id变了的时候,就会把头加进去
    }

    @Override
    public MyVh onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyVh myVh = new MyVh(view);
        return myVh;
    }


    @Override
    public void onBindHeaderViewHolder(MyVh holder, int position) {
        Log.d("RvAdapter", "position:" + position);

        holder.textView.setText("tou-"+position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyVh extends RecyclerView.ViewHolder {
        TextView textView;

        public MyVh(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
