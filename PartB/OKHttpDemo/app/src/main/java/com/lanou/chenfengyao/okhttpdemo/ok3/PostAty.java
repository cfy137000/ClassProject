package com.lanou.chenfengyao.okhttpdemo.ok3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lanou.chenfengyao.okhttpdemo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ChenFengYao on 16/9/5.
 */
public class PostAty extends AppCompatActivity implements View.OnClickListener {
    private Button mButton;
    private String imgUrl = "http://192.168.31.169/file/post.php";
    private String getUrl = "http://192.168.31.169/file/getTest.php?username=aaa";
    private OkHttpClient client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_post);
        mButton = (Button) findViewById(R.id.post);
        mButton.setOnClickListener(this);
        findViewById(R.id.get).setOnClickListener(this);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post://上传图片
                try {
                    post();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.get://get请求
                get();
                break;
        }
    }


    /**
     * get请求
     */
    private void get(){
        Request request = new Request.Builder()
                .url(getUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("PostAty", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("PostAty", response.body().string());
            }
        });
    }


    /**
     * get请求
     * @throws IOException
     */
    private void post() throws IOException {
        byte[] bytes;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), bytes);


        RequestBody requestBody = new MultipartBody.Builder().addFormDataPart("file", "file.png", fileBody)
                .setType(MultipartBody.FORM)
                .build();

        final Request request = new Request.Builder()
                .url(imgUrl)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("PostAty", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("PostAty", response.body().string());
            }
        });
    }
}
