package com.lanou.chenfengyao.bitmapresizedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by ChenFengYao on 2016/11/2.
 * 工具类里的方法 我们通常会写上static
 * 工具类通常 没有成员变量 只有方法
 * 调用起来比较简单
 */

public class BitmapTool {
    //不指定宽高
    public static final int UNDEFINE = -1;


    //给定ImageView 来拿到ImageView的宽高
    //如果ImageView 没有给宽高信息,或者 拿不到ImageView的宽高信息
    //就使用屏幕的宽高信息,因为一张图片的像素点个数
    // ,超出屏幕的像素点个数,是没有意义的
    public static WidthAndHeight getReqWH(ImageView imageView) {
        int reqW, reqH;
        reqH = imageView.getHeight();
        reqW = imageView.getWidth();
        if (reqH <= 0 || reqW <= 0) {
            //ImageView 没有给宽高信息
            //TODO context要使用Application的
            //TODO ImageView是有可能为空的
            Context context = imageView.getContext();
            //获得屏幕的宽高
            DisplayMetrics displayMetrics
                    = context.getResources()
                    .getDisplayMetrics();
            reqH = displayMetrics.heightPixels;
            reqW = displayMetrics.widthPixels;
        }

        //把图片的宽高信息返回
        WidthAndHeight widthAndHeight = new WidthAndHeight();
        widthAndHeight.setHeight(reqH);
        widthAndHeight.setWidth(reqW);
        return widthAndHeight;

    }

    public static class WidthAndHeight {
        private int height, width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }


    /**
     * 精确压缩到指定的宽高
     * 在使用的时候,为了保证图片的比例正常,我们会计算一下
     * 压缩的比例,然后把宽高 按照比例去压缩
     * 它的缺点是,需要一张原图,原图是会加载进内存的
     * 可以直接给目标宽高的值,就会把图片压缩到目标宽高
     * 如果目标宽高给定的是 UNDEFINE 代表目标宽高是要根据
     * ImageView的宽高 动态计算出来的
     * @param reqW   目标宽
     * @param reqH   目标高
     * @param bitmap 原始图片
     * @return 压缩后的图片
     */
    public static Bitmap resizeAccurate(int reqW
            , int reqH
            , Bitmap bitmap
            , ImageView imageView) {
        if(reqH == UNDEFINE || reqW == UNDEFINE){
            WidthAndHeight reqWH = getReqWH(imageView);
            //防止图片被拉伸,所以选择图片宽高和 计算出宽高的最小值
            reqH = Math.min(bitmap.getHeight(),reqWH.getHeight());
            reqW = Math.min(bitmap.getWidth(),reqWH.getWidth());
        }

        //还要加上 保证比例的计算代码
        float scaled = Math.max(bitmap.getHeight()/reqH,
                bitmap.getWidth()/reqW);

        reqH = (int) (bitmap.getHeight() / scaled);
        reqW = (int) (bitmap.getWidth() / scaled);

        bitmap = Bitmap
                .createScaledBitmap(bitmap, reqW, reqH, false);
        return bitmap;
    }
}
