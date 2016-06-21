package com.example.chenfengyao.slidemenulistview.waytwo;

/**
 * Created by MJJ on 2015/7/25.
 * 横向的滚动条
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.chenfengyao.slidemenulistview.R;

public class SlidingButtonView extends HorizontalScrollView   {

    private TextView mTextView_Delete;//删除按钮

    private int mScrollWidth;//滚动条可以滚动的范围

    private IonSlidingButtonListener mIonSlidingButtonListener;

    private Boolean isOpen = false;//标记是否打开
    private Boolean once = false;//标记是否是第一次加载


    public SlidingButtonView(Context context) {
        this(context, null);
    }

    public SlidingButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setOverScrollMode(OVER_SCROLL_NEVER);//没有边界回弹效果
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(!once){//如果是第一次加载,则对里面的删除按钮赋值
            mTextView_Delete = (TextView) findViewById(R.id.tv_delete);
            once = true;
        }

    }

    //用于放置子View的位置的方法
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(0,0);
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度
            mScrollWidth = mTextView_Delete.getWidth();
            Log.i("asd", "mScrollWidth:" + mScrollWidth);
        }

    }


    //收拾的判断
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                mIonSlidingButtonListener.onDownOrMove(this);
                break;
            case MotionEvent.ACTION_UP:
                changeScrollx();
                return true;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 按滚动条被拖动距离判断关闭或打开菜单
     */
    public void changeScrollx(){
        if(getScrollX() >= (mScrollWidth/2)){
            this.smoothScrollTo(mScrollWidth, 0);
            isOpen = true;
            mIonSlidingButtonListener.onMenuIsOpen(this);
        }else{
            this.smoothScrollTo(0, 0);
            isOpen = false;
        }
    }



    /**
     * 关闭菜单
     */
    public void closeMenu()
    {
        if (!isOpen){
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = false;
    }



//讲横向的滚动条设置监听
    public void setSlidingButtonListener(IonSlidingButtonListener listener){
        mIonSlidingButtonListener = listener;//监听赋值,就是adapter
    }

    //滑动监听接口
    public interface IonSlidingButtonListener{
        void onMenuIsOpen(View view);//判断菜单是否打开
        void onDownOrMove(SlidingButtonView slidingButtonView);//滑动或者点击了Item监听
    }


}
