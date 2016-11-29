package com.lanou.chenfengyao.examdemo;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/14,
 * otherwise, I do not know who create it either.
 * <p>
 * 网址是http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10
 * 那么 baseUrl 就应该是所有url共有的网址,
 * 即http://food.boohee.com/fb/v1
 * 那么这个接口的value 就应该是feeds/category_feed
 */

public interface GetDetail {
    //参数用@query来修饰
    @GET(value = "feeds/category_feed")
    Call<ResponseBody> getDetail(@Query("page") int page,
                                 @Query("category") int category,
                                 @Query("per") int per);
}
