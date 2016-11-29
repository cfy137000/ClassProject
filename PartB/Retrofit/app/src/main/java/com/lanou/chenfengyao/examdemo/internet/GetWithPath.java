package com.lanou.chenfengyao.examdemo.internet;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/16,
 * otherwise, I do not know who create it either.
 */

public interface GetWithPath <T>{
    @GET("{path}")
    Observable<T> getData(@Path("path")String path);
}
