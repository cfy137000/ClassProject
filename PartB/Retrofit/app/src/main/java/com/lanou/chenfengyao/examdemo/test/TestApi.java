package com.lanou.chenfengyao.examdemo.test;

import com.lanou.chenfengyao.examdemo.DetailBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/27,
 * otherwise, I do not know who create it either.
 */

public class TestApi {
    public static final String BASE_URL = "http://food.boohee.com/fb/v1/";
    public static final String PATH_CATEGORY = "feeds/category_feed";

    public interface GetNetData {
        @GET(value = PATH_CATEGORY)
        Observable<DetailBean> getDetail(@Query("page") int page,
                                         @Query("category") int category,
                                         @Query("per") int per);
    }
}
