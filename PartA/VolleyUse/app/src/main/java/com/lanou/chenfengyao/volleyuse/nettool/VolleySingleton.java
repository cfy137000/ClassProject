package com.lanou.chenfengyao.volleyuse.nettool;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lanou.chenfengyao.volleyuse.base.MyApp;

/**
 * Created by ChenFengYao on 16/8/16.
 * Volley使用的单例类
 * 里面有需要单例的东西 如:RequestQueue,ImageLoader
 */
public class VolleySingleton {
    private static VolleySingleton ourInstance;

    private ImageLoader mImageLoader;
    private RequestQueue mRequestQueue;

    public static VolleySingleton getInstance() {
        if (ourInstance == null) {
            synchronized (VolleySingleton.class) {
                if (ourInstance == null) {
                    ourInstance = new VolleySingleton();
                }
            }
        }

        return ourInstance;
    }

    private VolleySingleton() {
        //初始化RequestQueue和ImageLoader
        mRequestQueue = Volley.newRequestQueue(MyApp.getContext());
        mImageLoader = new ImageLoader(mRequestQueue, new MemoryCache());
    }

    //获得ImageLoader
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    //将请求添加到请求队列
    public void addRequest(Request request) {
        mRequestQueue.add(request);
    }

    //将请求添加到请求队列
    //添加tag 方便以后可以取消
    public void addRequest(Request request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        addRequest(request);
    }

    //通过tag来取消一个在请求队列里的请求
    //例如ActivityFinish了,则不需要再请求网络数据了
    //就可以把这个网络请求取消了
    public void removeRequest(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}
