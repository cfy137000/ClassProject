package com.lanou.chenfengyao.drawabledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.main_iv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon);
        CircleDrawable circleDrawable = new CircleDrawable(bitmap);
        mImageView.setImageDrawable(circleDrawable);
    }
}
