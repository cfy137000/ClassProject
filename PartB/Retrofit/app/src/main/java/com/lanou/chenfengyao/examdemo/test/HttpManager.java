package com.lanou.chenfengyao.examdemo.test;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/27,
 * otherwise, I do not know who create it either.
 */
public class HttpManager {
    private static final AtomicReference<HttpManager> INSTANCE = new AtomicReference<HttpManager>();
    private final Retrofit mRetrofit;


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
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(TestApi.BASE_URL)
                .build();
    }

    public TestApi.GetNetData getInterface(){
        return mRetrofit.create(TestApi.GetNetData.class);
    }

    public <Bean> void sendRequest(Observable<Bean> observable,HttpListener<Bean> httpListener){
        
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpListener::onSuccess,
                        httpListener::onError);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
