package com.example.chenfengyao.slidemenulistview.waytwo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chenfengyao.slidemenulistview.R;
import com.example.chenfengyao.slidemenulistview.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ChenFengYao on 15/12/10.
 */
public class TwoAdapter extends BaseAdapter implements SlidingButtonView.IonSlidingButtonListener {
    //数据类
    private List<String> mDatas = new ArrayList<>();
    //侧滑菜单
    private SlidingButtonView mMenu = null;

    private Context context;

    public TwoAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 10; i++) {
            mDatas.add(i + "");//模拟添加数据
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }

        holder.textView.setText(mDatas.get(position));
        //设置内容布局的宽为屏幕宽度
        holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(context);


        holder.btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;//菜单打开就对mMenu赋值
    }

    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if(menuIsOpen()){//如果menu是打开的
            if(mMenu != slidingButtonView){

                closeMenu();
            }
        }
    }
    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }
    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        Log.i("asd", "mMenu为null");
        return false;
    }


    //数据Holder
    class MyViewHolder {
        public TextView btn_Delete;//删除按钮
        public TextView textView;//内容
        public ViewGroup layout_content;

        public MyViewHolder(View itemView) {
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            textView = (TextView) itemView.findViewById(R.id.text);
            layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);

            //讲自己设置给滚动条
            ((SlidingButtonView) itemView).setSlidingButtonListener(TwoAdapter.this);


        }
    }
}
