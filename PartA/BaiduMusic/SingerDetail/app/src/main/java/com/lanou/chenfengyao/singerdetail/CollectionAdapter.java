package com.lanou.chenfengyao.singerdetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenFengYao on 16/8/21.
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyVH> {
    private Context mContext;
    private List<String> mStringList;

    public CollectionAdapter(Context context) {
        mContext = context;
        mStringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mStringList.add("这是专辑" + i);
        }
    }

    @Override
    public CollectionAdapter.MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item,parent,false);
        MyVH myVH = new MyVH(view);
        return myVH;
    }

    @Override
    public void onBindViewHolder(CollectionAdapter.MyVH holder, int position) {
        holder.textView.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }
    class MyVH extends RecyclerView.ViewHolder{
        TextView textView;
        public MyVH(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

}
