package com.lanou.chenfengyao.examdemo.internet;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/16,
 * otherwise, I do not know who create it either.
 */
public class HttpManager {
    private static final AtomicReference<HttpManager> INSTANCE = new AtomicReference<HttpManager>();
    //基础网址
    public static final String BASE_RUL = "http://food.boohee.com/fb/v1/";
    private Retrofit mRetrofit;

    public static HttpManager getInstance() {
        for (; ; ) {
            HttpManager current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new HttpManager();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private HttpManager() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_RUL)
                .build();
    }

    public <Bean> void get(Class<GetWithPath<Bean>> tClass, Subscriber<Bean> subscriber, String path) {
        GetWithPath<Bean> getWithPath = mRetrofit.create(tClass);
        getWithPath.getData(path)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
