package com.lanou.chenfengyao.examdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/14,
 * otherwise, I do not know who create it either.
 */

public interface GetDetailWithGson {
    @GET(value = "feeds/category_feed")
    Call<DetailBean> getDetail(@Query("page") int page,
                               @Query("category") int category,
                               @Query("per") int per);
}

