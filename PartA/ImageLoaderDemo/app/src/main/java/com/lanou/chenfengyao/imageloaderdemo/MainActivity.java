package com.lanou.chenfengyao.imageloaderdemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

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

        //可以设置ImageLoader的缓存路径
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
//        //通过配置Builder类可以设置ImageLoader的各个属性
//        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);
//        //设置ImageLoader的硬盘缓存路径
//        builder.diskCache(new UnlimitedDiskCache(cacheDir));
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration
//                .Builder(this)
//                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
//                .threadPoolSize(3)//线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
//                .memoryCacheSize(2 * 1024 * 1024)
//                .discCacheSize(50 * 1024 * 1024)
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .discCacheFileCount(100) //缓存的文件数量
//                .discCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
//                .writeDebugLogs() // Remove for release app
//                .build();//开始构建

        imageLoader.init(configuration);
        // Load image, decode it to Bitmap and display Bitmap in ImageView (or any other view
        //which implements ImageAware interface)
        imageLoader.displayImage(imageUri, imageView);


    }
}
