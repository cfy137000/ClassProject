package com.lanou.chenfengyao.headerrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/25,
 * otherwise, I do not know who create it either.
 */

public class MainAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private List<String> data;

    public MainAdapter() {
        data = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            data.add(String.valueOf((char) (i + 'A')));
        }
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.item_main);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setText(R.id.item_tv, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
