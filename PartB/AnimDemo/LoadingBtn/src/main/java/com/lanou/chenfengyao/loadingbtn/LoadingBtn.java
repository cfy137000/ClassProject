package com.lanou.chenfengyao.loadingbtn;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/23,
 * otherwise, I do not know who create it either.
 */

public class LoadingBtn extends Button {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_CHANGE = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_ERROR = 3;

    private int mState;//用来记录状态

    private int mWidth, mHeight;//组件的宽高

    private int mChangeWidth;//当改变的时候,圆角矩形的宽度
    private int mErrorCircleR;//错误的半径

    private Paint mBgPaint;//画背景的画笔
    private Paint mLinePaint;//画线的画笔
    private ObjectAnimator mChangeAnim;
    private ObjectAnimator mRotateAnim;


    public LoadingBtn(Context context) {
        this(context, null);
    }

    public LoadingBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化方法
    private void init() {
        //mState = STATE_NORMAL;
        setState(STATE_NORMAL);

        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);//抗锯齿
        mBgPaint.setColor(0xFF33FDDB);//设置背景颜色
        mBgPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        setPadding(15, 0, 15, 0);//设置padding
        setBackgroundColor(0x00000000);
        mBgPaint.setDither(true);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(0xFF000000);//黑色的线
        mLinePaint.setStrokeWidth(8);//线宽
        //只画线
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setDither(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mHeight = getHeight();
        mWidth = getWidth();
        switch (mState) {
            case STATE_NORMAL:
                RectF rectF = new RectF(0, 0, mWidth, mHeight);
                canvas.drawRoundRect(rectF, mHeight / 2, mHeight / 2,
                        mBgPaint);
                super.onDraw(canvas);//画字
                break;
            case STATE_CHANGE:
                RectF changeRectF = new RectF((mWidth - mChangeWidth) / 2
                        , 0
                        , mChangeWidth + (mWidth - mChangeWidth) / 2
                        , mHeight);
                canvas.drawRoundRect(changeRectF,
                        mHeight / 2, mHeight / 2,
                        mBgPaint);
                break;
            case STATE_LOADING:
                //画一个背景的圆
                canvas.drawCircle(mWidth / 2,
                        mHeight / 2,
                        mHeight / 2, mBgPaint);
                //画Loading的线
                RectF arcRectF = new RectF((mWidth - mChangeWidth) / 2 + 25
                        , 25
                        , mChangeWidth + (mWidth - mChangeWidth) / 2 - 25
                        , mHeight - 25);

                canvas.drawArc(arcRectF, 0, 110, false, mLinePaint);

                break;
            case STATE_ERROR:
                //画一个背景的圆
                canvas.drawCircle(mWidth / 2,
                        mHeight / 2,
                        mHeight / 2, mBgPaint);
                canvas.rotate(45, mWidth / 2, mHeight / 2);//让画布旋转45°

                canvas.drawLine(mWidth / 2 - mHeight / 2 + 10
                        , mHeight / 2, mWidth / 2 + mHeight / 2 - 10,
                        mHeight / 2, mLinePaint);
                canvas.rotate(90, mWidth / 2, mHeight / 2);//接着旋转90°
                canvas.drawLine(mWidth / 2 - mHeight / 2 + 10
                        , mHeight / 2, mWidth / 2 + mHeight / 2 - 10,
                        mHeight / 2, mLinePaint);
                //画圈
                canvas.drawCircle(mWidth / 2 , mHeight / 2,mErrorCircleR, mBgPaint);
                break;
        }
    }

    public int getErrorCircleR() {
        return mErrorCircleR;
    }

    public void setErrorCircleR(int errorCircleR) {
        mErrorCircleR = errorCircleR;
    }

    public int getChangeWidth() {
        return mChangeWidth;
    }

    public void setChangeWidth(int changeWidth) {
        mChangeWidth = changeWidth;
    }

    //开始播放Loading动画
    private void startLoadingAnim() {
      //  setEnabled(true);
        setState(STATE_LOADING);

        mRotateAnim = ObjectAnimator.ofFloat(this,
        "rotation", 0, 360);
        //让动画无限循环
        mRotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        mRotateAnim.setRepeatMode(ValueAnimator.RESTART);
        mRotateAnim.start();
    }


    /****************
     * 暴露给用户的方法 *
     *****************/

    //让组件 变成loading状态
    public void startLoading() {
        startChange2Loading();
    }

    //让组件变成 error状态
    public void startError() {
//        setEnabled(false);
        mRotateAnim.cancel();
        setRotation(0);//把它转回0位置
                //让它不转
        //mState = STATE_ERROR;
        setState(STATE_ERROR);
        ObjectAnimator errorAnim
                 = ObjectAnimator.ofInt(this,
                "errorCircleR",mHeight / 2,-100);
        errorAnim.setDuration(2000);
        errorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        errorAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //还原回初始状态
                change2Normal();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        errorAnim.start();
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
        invalidate();
        switch (state){
            case STATE_NORMAL:
            case STATE_LOADING:
                setEnabled(true);
                break;
            case STATE_ERROR:
            case STATE_CHANGE:
                setEnabled(false);
                break;
        }
    }

    //还原为初始状态
    private void change2Normal() {
//        setEnabled(false);//不能点击
//        mState = STATE_CHANGE;
        setState(STATE_CHANGE);
        mChangeAnim = ObjectAnimator.ofInt(this
                , "changeWidth", mHeight, mWidth);
        mChangeAnim.setDuration(2000);
        mChangeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();//当值改变的时候,重新绘制
            }
        });


        mChangeAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画完成
               setState(STATE_NORMAL);
             //   setEnabled(true);
            }


            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mChangeAnim.start();
    }

    //将状态变为Change
    private void startChange2Loading() {
        setState(STATE_CHANGE);
//        mState = STATE_CHANGE;
        //开始Loading动画
//        setEnabled(false);

        //定义属性动画
        mChangeAnim = ObjectAnimator.ofInt(this
                , "changeWidth", mWidth, mHeight);
        mChangeAnim.setDuration(2000);
        mChangeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();//当值改变的时候,重新绘制
            }
        });
        mChangeAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画完成
                startLoadingAnim();
            }


            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mChangeAnim.start();
    }


}
