package com.lanou.chenfengyao.examdemo;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/15,
 * otherwise, I do not know who create it either.
 */

public interface GetDataWithRx {
    @GET(value = "feeds/category_feed")
    Observable<DetailBean> getDetail(@Query("page") int page,
                                     @Query("category") int category,
                                     @Query("per") int per);
}
