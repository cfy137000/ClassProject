package com.lanou.chenfengyao.bitmapresizedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mainIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainIv = (ImageView) findViewById(R.id.main_iv);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bitmap = BitmapFactory
                .decodeResource(getResources(), R.mipmap.test,options);
        //Bitmap 原始的宽高 采样率
        Log.d("MainActivity", "bitmap.getWidth():"
                + bitmap.getWidth());
        Log.d("MainActivity", "bitmap.getHeight():"
                + bitmap.getHeight());
        Log.d("MainActivity", "bitmap.getByteCount():" + bitmap.getByteCount());

        mainIv.setImageBitmap(bitmap);


        Bitmap smallBitmap = resize(100,100);
        Log.d("MainActivity", "smallBitmap.getHeight():" + smallBitmap.getHeight());
        Log.d("MainActivity", "smallBitmap.getWidth():" + smallBitmap.getWidth());
      //  mainIv.setImageBitmap(smallBitmap);
        Bitmap smallBitmapAccurate
                = BitmapTool.resizeAccurate(BitmapTool.UNDEFINE,BitmapTool.UNDEFINE,bitmap,mainIv);
        Log.d("MainActivity", "smallBitmapAccurate.getHeight():"
                + smallBitmapAccurate.getHeight());
        Log.d("MainActivity", "smallBitmapAccurate.getWidth():"
                + smallBitmapAccurate.getWidth());
      //  mainIv.setImageBitmap(smallBitmapAccurate);

    }



    //改变色彩范围
    public void fun(Bitmap bitmap){
        bitmap.setConfig(Bitmap.Config.RGB_565);
    }

    /**
     * 精确压缩到指定的宽高
     * 在使用的时候,为了保证图片的比例正常,我们会计算一下
     * 压缩的比例,然后把宽高 按照比例去压缩
     * 它的缺点是,需要一张原图,原图是会加载进内存的
     * @param reqW 目标宽
     * @param reqH 目标高
     * @param bitmap 原始图片
     * @return 压缩后的图片
     */
    private Bitmap resizeAccurate(int reqW
            ,int reqH
            ,Bitmap bitmap){
        float scaled = Math.max(bitmap.getHeight()/reqH,
                bitmap.getWidth()/reqW);

        reqH = (int) (bitmap.getHeight() / scaled);
        reqW = (int) (bitmap.getWidth() / scaled);

        bitmap = Bitmap
                .createScaledBitmap(bitmap,reqW,reqH,false);
        return bitmap;
    }

    /**
     * 原图不加载进内存,通过采样率来压缩图片
     * 用采样率压缩的好处是 原图不加载进内存,
     * 但是,不能做到精确的压缩到目标宽高
     * 采样率的有效值只能是2的整数次幂,如果不是,就会自动改成最接近的值
     * @param reqW 要求的宽(目标宽)
     * @param reqH 要求的高
     * @return 压缩后的图片
     */
    private Bitmap resize(int reqW, int reqH) {
        //加载图片的设置类,可以设置怎么加载图片
        BitmapFactory.Options options
                = new BitmapFactory.Options();
        //只解码图片的宽高,使用这个options,不会真正把图片加载进内存
        //只会读取图片的宽高信息
        options.inJustDecodeBounds = true;
        //使用这个Options来加载图片
        BitmapFactory.decodeResource(getResources()
                , R.mipmap.test
                , options);
        //拿到图片的宽高信息
        int w = options.outWidth;
        int h = options.outHeight;
        //设定采样率,由宽/目标宽 和 高/目标高的最大值决定
        //采样率越大 图片越小

        options.inSampleSize = Math.max(w/reqW,h/reqH);
        //不只解析宽高信息
        options.inJustDecodeBounds = false;
        //获得压缩后的图片
        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(),R.mipmap.test,options);
        return bitmap;
    }




}
