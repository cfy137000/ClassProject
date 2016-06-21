package com.lanou.chenfengyao.asyncandthreadpooldemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.main_img);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final DownloadImageTask downloadImageTask =  new DownloadImageTask();
              downloadImageTask.execute("http://img2.imgtn.bdimg.com/it/u=1457437487,655486635&fm=11&gp=0.jpg");

        final CountDownTimer timer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                Toast.makeText(MainActivity.this, String.valueOf(millisUntilFinished / 1000), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        timer.start();
    }




    class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                DeskCache deskCache = new DeskCache();
                Bitmap bitmap = deskCache.getBitmap(params[0]);
                if(bitmap!=null){
                    Log.d("Sysout","从缓存拿下来的");
                    return bitmap;
                }
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
                deskCache.putBitmap(params[0],bitmap);
                in.close();
                connection.disconnect();
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.d("Sysout","sysout");

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        }
    }


}
