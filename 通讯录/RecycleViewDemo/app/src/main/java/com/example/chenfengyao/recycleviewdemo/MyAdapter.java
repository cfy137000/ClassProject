package com.example.chenfengyao.recycleviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MJJ on 2015/7/25.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements SlidingButtonView.SlidingButtonListener {

    private Context mContext;

    //点击事件和删除按钮的接口
    private IonSlidingViewClickListener mIDeleteBtnClickListener;
    //数据类
    private List<String> mDatas = new ArrayList<>();
    //侧滑菜单
    private SlidingButtonView mMenu = null;

    public MyAdapter(Context context) {

        mContext = context;
        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;

        for (int i = 0; i < 10; i++) {
            mDatas.add(i+"");//模拟添加数据
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.textView.setText(mDatas.get(position));
        //设置内容布局的宽为屏幕宽度
        holder.textView.getLayoutParams().width = Utils.getScreenWidth(mContext);

        holder.textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }

            }
        });
        holder.btn_Delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup arg0, int flag) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, arg0,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }


    //数据Holder
    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView btn_Delete;//删除按钮
        public TextView textView;//内容
        public MyViewHolder(View itemView) {
            super(itemView);
            btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
            textView = (TextView) itemView.findViewById(R.id.text);

            //讲自己设置给滚动条
            ((SlidingButtonView) itemView).setSlidingButtonListener(MyAdapter.this);
        }
    }


    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;//菜单打开就对mMenu赋值
    }

    /**
     * 滑动或者点击了Item监听
     * @param slidingButtonView
     */
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
        Log.i("asd","mMenu为null");
        return false;
    }


//暴露出去的接口
    public interface IonSlidingViewClickListener {
    //每一条的点击事件
        void onItemClick(View view,int position);
    //点击删除按钮
        void onDeleteBtnCilck(View view,int position);
    }
}

