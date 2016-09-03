package com.lanou.chenfengyao.bitmapresize;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Log.d("MainActivity", "bitmap.getRowBytes():" + bitmap.getHeight());
        bitmap = resizeBitmap2(bitmap,10,10);
        Log.d("MainActivity", "bitmap.getHeight():" + bitmap.getHeight());
    }

    public Bitmap resizeBitmap2(Bitmap bitmap, int reqW, int reqH){
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,reqW,reqH,false);
        return newBitmap;
    }

    //当bitmap已经进入内存时的压缩方法
    public Bitmap resizeBitmap(Bitmap bitmap, int reqW, int reqH) {
        Matrix matrix = new Matrix();
        int bitmapH = bitmap.getHeight();
        int bitmapW = bitmap.getWidth();
        float scaleH = reqH / (float) bitmapH;
        float scaleW = reqW / (float) bitmapW;
        float scale = Math.min(scaleH, scaleW);
        matrix.setScale(scale, scale);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapW, bitmapH, matrix, false);
        return bitmap;
    }

    public Bitmap decodeResMipmap(Resources res, int id, int reqWidth, int reqHeight) {
        // 1.先将inJustDecodeBounds设置为true
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 为true后 ,解析图片原始宽高
        BitmapFactory.decodeResource(res, id, options);

        // 计算采样率
        options.inSampleSize = countInSampleSize(options, reqWidth, reqHeight);

        // 设置false
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, id, options);
    }

    /**
     * 计算采样率方法
     * req请求
     */
    public int countInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


    /**
     * BitmapFactory.Options缩放图片
     * 主要使用它的inSampleSize参数,采样率
     * 为1时,原始大小, 大于1时,比如2,采样后的图片为原图的1/2,像素是原图的1/4,内存也是原图1/4
     * 如一张1024x1024像素的图片,采用ARGB8888格式存储,内存1024x1024x4即4mb,如果采样率设置为2
     * 内容是512x512x4即1mb
     * 采样率同样适用于宽和高,如:采样率是4,缩放比例1/16,  缩放比例是: 1/samplesize的2次方
     * 官方推荐:采样率的取值是1,2,4,8,16等
     * 如果不是2的指数,系统会向下取整选一个最接近2的指数来替代,比如3,会选择2替代
     *
     *
     * 如果ImageView的大小是100x100像素
     * 图片原始大小是200x200,那么采样率设置为2即可
     *
     *
     * 采样率获取步骤
     * 1.将Options的inJustDecodeBounds设置为true并加载图片
     * 2.从Options中取出图片的原始宽高信息,对于outWidth和outHeight参数
     * 3.根据采用率规则并结合目标View所需大小计算出采样率
     * 4.将Options的inJustDecodeBounds设置false,重新加载
     *
     * inJustDecodeBounds 参数:此参数为true时,BitmapFactory只会解析图片原始宽高,不会真正去加载图片
     * 所以这个操作是轻量级的,注意:此时放在不同mipmap下,获取的结果分辨率密度会不同
     *
     */
}
