package com.lanou.chenfengyao.gobangdemo.weiget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ChenFengYao on 16/1/21.
 * 棋盘类
 */
public class CheckerBoard extends View {
    private int height;
    private int weight;
    private int blank;
    private int chess[][];//二维数组,用来保存棋子的
    public static final int BLACK_CHESS = -1;
    public static final int WHITE_CHESS = 1;


    private boolean isBlack = true;//是否是轮到黑棋下子
    private boolean isFinish = false;//是否结束

    public CheckerBoard(final Context context, AttributeSet attrs) {
        super(context, attrs);
        chess = new int[19][19];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = widthMeasureSpec;
        height = weight = getMeasuredWidth();
        //  Log.d("CheckerBoard", "height:" + height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setAntiAlias(true);//抗锯齿
        Rect rect = new Rect(0, 0, weight, height);//尺寸变了,直接填满整个组件即可
        canvas.drawRect(rect, paint);//绘制底色
        //绘制线条
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        blank = ((weight - 10)) / 18;
        Log.d("CheckerBoard", "blank:" + blank);
        for (int i = 0; i < 19; i++) {
            canvas.drawLine(5 + (i * blank), 5, 5 + (i * blank), height - 10, paint);//画竖线
        }
        for (int i = 0; i < 19; i++) {
            canvas.drawLine(5, 5 + (i * blank), height - 10, 5 + (i * blank), paint);//画竖线
        }

        //绘制棋子
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (chess[i][j] == 0) {
                    continue;
                } else if (chess[i][j] == BLACK_CHESS) {
                    //如果是黑棋
                    paint.setColor(Color.BLACK);

                } else if (chess[i][j] == WHITE_CHESS) {
                    paint.setColor(Color.WHITE);
                }

                canvas.drawCircle(5 + (i * blank),
                        5 + (j * blank),
                        17, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下,判断位置
                if (!isFinish) {//比赛没有结束
                    getPosition(event);
                } else {
                    Toast.makeText(getContext(), "比赛结束,请点击重新开始", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void getPosition(MotionEvent event) {
        float pressheng = event.getX();
        Log.d("CheckerBoard", "pressheng:" + pressheng);
        int pointLine = (int) (pressheng / blank + 0.5f);

        int pointColumn = (int) (event.getY() / blank + 0.5f);
        if (chess[pointLine][pointColumn] != 0) {
            return;//如果有子了 就不能下了
        }
        if (isBlack) {
            //轮到黑棋了
            chess[pointLine][pointColumn] = BLACK_CHESS;
        } else {
            //轮到白棋了
            chess[pointLine][pointColumn] = WHITE_CHESS;
        }
        isBlack = !isBlack;//换手
        invalidate();
        checkWin(pointLine, pointColumn);
    }

    //判断胜负的算法
    //输入一个坐标,判断 行,列,斜线 是否练成5个了
    public void checkWin(int line, int column) {
        int now = chess[line][column];//获得当前的棋子类型
        //判断行
        int i = line;
        int j = column;
        int point = 0;
        while (i >= 0 && i < 19) {
            if (chess[i][column] == now) {
                point++;
                i--;
            } else {
                break;
            }
        }
        i = line;
        while (i >= 0 && i < 19) {
            if (chess[i][column] == now) {
                point++;
                i++;
            } else {
                break;
            }
        }
        if (point > 5) {
            showResult(now);
            return;
        }
        //判断列
        i = line;
        j = column;
        point = 0;
        while (j >= 0 && j < 19) {
            if (chess[line][j] == now) {
                point++;
                j--;
            } else {
                break;
            }
        }
        j = column;
        while (j >= 0 && j < 19) {
            if (chess[line][j] == now) {
                point++;
                j++;
            } else {
                break;
            }
        }
        if (point > 5) {
            showResult(now);
            return;
        }

        //判断斜线左上-右下
        point = 0;
        i = line;
        j = column;
        while (i >= 0 && i < 19 && j > 0 && j < 19) {
            if (chess[i][j] == now) {
                j--;
                i--;
                point++;
            } else {
                break;
            }
        }
        i = line;
        j = column;
        while (i >= 0 && i < 19 && j > 0 && j < 19) {
            if (chess[i][j] == now) {
                j++;
                i++;
                point++;
            } else {
                break;
            }
        }
        if (point > 5) {
            showResult(now);
            return;
        }

        //左下-右上
        point = 0;
        i = line;
        j = column;
        while (i >= 0 && i < 19 && j > 0 && j < 19) {
            if (chess[i][j] == now) {
                j++;
                i--;
                point++;
            } else {
                break;
            }
        }
        i = line;
        j = column;
        while (i >= 0 && i < 19 && j > 0 && j < 19) {
            if (chess[i][j] == now) {
                j--;
                i++;
                point++;
            } else {
                break;
            }
        }
        if (point > 5) {
            showResult(now);
            return;
        }

    }

    private void showResult(int now) {
        String s = "";
        isFinish = true;//比赛结束
        switch (now) {
            case BLACK_CHESS:
                s = "黑棋获胜";
                break;
            case WHITE_CHESS:
                s = "白棋获胜";
                break;
        }
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void restart() {
        chess = new int[19][19];
        isBlack = true;
        invalidate();//重新绘制
        isFinish = false;//又可以重新开始了
    }
}
