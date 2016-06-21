package com.example.chenfengyao.indexlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

public class SectionBar extends View {
    //索引条的字母
    private static final char[] WORDS = new char[]{
            '#','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'
    };

    private int wordSize = 16;//文字大小
    private int preHeight = wordSize + 2;//每一个的高度

    private ListView mListView;//传入的要索引的ListView
    private Indexer mSectionIndexter;
    // 垂直居中
    private int heightCenter;
    private Paint mPaint;

    public SectionBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public SectionBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {//初始化
        wordSize = convertDpToPixel(wordSize, getContext());
        preHeight = convertDpToPixel(preHeight, getContext());
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.index_word));
        mPaint.setTextSize(preHeight);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);//抗锯齿
    }

    public void setListView(ListView listView) {
        mListView = listView;
        Adapter adapter = mListView.getAdapter();
        if (adapter == null || !(adapter instanceof Indexer)) throw new RuntimeException("ListView must set Adapter or Adapter must implements Indexer interface");
        mSectionIndexter = (Indexer) adapter;
    }

    //触摸事件 核心代码
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int startY = (int) event.getY() - heightCenter;
        int index = startY / preHeight;
        if (index >= WORDS.length) {
            index = WORDS.length - 1;
        } else if (index < 0) {
            index = 0;
        }
        //当手指按下或移动
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            int position = mSectionIndexter.getStartPositionOfSection(String.valueOf(WORDS[index]));
            if (position == -1) {
                return true;//不做任何处理
            }
            mListView.setSelection(position);//显示当前条目
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        heightCenter = getMeasuredHeight()/2 - preHeight*WORDS.length/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < WORDS.length; i++) {
            //绘制文字
            canvas.drawText(String.valueOf(WORDS[i]), getMeasuredWidth()/2, preHeight
                    + (i * preHeight) + heightCenter, mPaint);
        }
        super.onDraw(canvas);
    }

    //从dp转成px
    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int)(dp * (metrics.densityDpi / 160f) + 0.5);
        return px;
    }

}
