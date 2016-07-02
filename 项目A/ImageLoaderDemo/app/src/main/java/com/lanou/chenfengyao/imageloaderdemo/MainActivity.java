package com.lanou.chenfengyao.imageloaderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {
    private String imageUri =
            "http://www.deskcar.com/desktop/fengjing/2013812103350/11.jpg";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.main_iv);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        //在使用ImageLoader之前,必须要先对他进行配置
        //可以使用createDefault命令来获取一个ImageLoader的默认配置
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        imageLoader.init(configuration);
        // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
        //which implements ImageAware interface)
        imageLoader.displayImage(imageUri, imageView);


    }
}
