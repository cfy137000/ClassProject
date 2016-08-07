package com.lanou.chenfengyao.chanyouji.another;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ChenFengYao on 16/6/25.
 */
public interface StickyRecyclerHeadersAdapter<VH extends RecyclerView.ViewHolder> {
    public long getHeaderId(int position);

    public VH onCreateHeaderViewHolder(ViewGroup parent);

    public void onBindHeaderViewHolder(VH holder, int position);

    public int getItemCount();
}