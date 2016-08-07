package com.lanou.chenfengyao.chanyouji;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by moon.zhong on 2015/2/3.
 */
public class ListAdapter extends BaseAdapter {

    private List<Bean> mData ;
    private LayoutInflater mInflater ;
    private Context mContext ;

    public ListAdapter(List<Bean> data, Context context) {
        this.mData = data;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext) ;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_listview, null) ;
            viewHolder = new ViewHolder() ;
            viewHolder.txt1 = (TextView) convertView.findViewById(R.id.id_list_txt1);
            viewHolder.txt2 = (TextView) convertView.findViewById(R.id.id_list_txt2);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)convertView.getTag() ;
        viewHolder.txt1.setText(mData.get(position).getTxt1());
        viewHolder.txt2.setText(mData.get(position).getTxt2());
        return convertView;
    }

    class ViewHolder{
        TextView txt1;
        TextView txt2;
    }
}
