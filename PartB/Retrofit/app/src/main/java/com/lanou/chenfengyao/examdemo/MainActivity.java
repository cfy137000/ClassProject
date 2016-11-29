package com.lanou.chenfengyao.examdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lanou.chenfengyao.examdemo.internet.CustomGsonConverterFactory;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton, widthGsonBtn, widthRxButton;


    //  请求的url
    // http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10
    private static final String baseUrl = "http://food.boohee.com/fb/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.get_btn);
        mButton.setOnClickListener(this);

        widthGsonBtn = (Button) findViewById(R.id.get_with_gson_btn);
        widthGsonBtn.setOnClickListener(this);

        widthRxButton = (Button) findViewById(R.id.get_with_rx_btn);
        widthRxButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_btn:
                getNetData();
                break;
            case R.id.get_with_gson_btn:
                getNetDataWithGson();
                break;
            case R.id.get_with_rx_btn:
                getDataWithRx();
                break;
        }
    }

    //获取网络数据 与RxJava相结合
    private void getDataWithRx() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //.client(client)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        GetDataWithRx getDataWithRx = retrofit.create(GetDataWithRx.class);
        Observable<DetailBean> detail = getDataWithRx.getDetail(1, 3, 10);
        detail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detailBean -> {
                    Log.d("MainActivity", "detailBean:" + detailBean);
                },throwable -> {
                    Log.d("MainActivity", throwable.getMessage());
                });

    }


    //get请求(直接解析出实体类)
    private void getNetDataWithGson() {
        //构建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDetailWithGson getDetailWithGson
                = retrofit.create(GetDetailWithGson.class);

        Call<DetailBean> detail = getDetailWithGson.getDetail(1, 3, 10);
        detail.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                DetailBean body = response.body();
                Log.d("MainActivity", "body.getFeeds().size():" + body.getFeeds().size());
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {
                Log.d("MainActivity", "error");
            }
        });


    }

    //get请求(不解析出来)
    public void getNetData() {
        //构建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();

        //构建出接口对象
        GetDetail getDetail = retrofit.create(GetDetail.class);

        //利用接口对象构建出请求对象,构建出的这个请求对象就是OkHttp的
        //用法和OkHttp一致
        Call<ResponseBody> detail = getDetail.getDetail(1, 3, 10);
        detail.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Sysout-get", s);
                Log.d("Sysout-get", Thread.currentThread().getName());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
}
