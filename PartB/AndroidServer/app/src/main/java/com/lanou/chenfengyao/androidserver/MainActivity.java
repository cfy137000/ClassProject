package com.lanou.chenfengyao.androidserver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SimpleHttpServer simpleHttpServer;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebConfiguration webConfiguration = new WebConfiguration();
        webConfiguration.setPort(8088);
        webConfiguration.setMaxParallels(50);
        simpleHttpServer = new SimpleHttpServer(webConfiguration);
        imageView = (ImageView) findViewById(R.id.image_view);

        simpleHttpServer.registerResourceHandler(new ResourceInAssetsHanler(this));
        simpleHttpServer.registerResourceHandler(new UploadImageHandler() {
            @Override
            protected void onImageLoaded(final String path) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        showImg(path);
                    }
                });
            }
        });

        simpleHttpServer.startAsync();


    }

    private void showImg(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);
        Toast.makeText(MainActivity.this, "图片上传成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        try {
            simpleHttpServer.stopAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
