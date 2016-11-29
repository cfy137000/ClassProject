package com.lanou.chenfengyao.examdemo.test;

import com.lanou.chenfengyao.examdemo.DetailBean;


import rx.Observable;


/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/27,
 * otherwise, I do not know who create it either.
 */

public class HttpUtil {
    public static void getData(int page,
                               int category,
                               int per,
                               HttpListener<DetailBean> listener) {
        TestApi.GetNetData getNetData = HttpManager.getInstance().getInterface();
        Observable<DetailBean> detail = getNetData.getDetail(page, category, per);
        HttpManager.getInstance().sendRequest(detail, listener);
    }
}
