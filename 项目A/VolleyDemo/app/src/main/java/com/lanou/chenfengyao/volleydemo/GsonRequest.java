package com.lanou.chenfengyao.volleydemo;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by ChenFengYao on 16/5/19.
 */
public class GsonRequest<T> extends Request<T> {
    private Response.Listener<T> mListener;
    private Class<T> clazz;

    public GsonRequest(int method, String url, Response.Listener<T> listener,
                         Response.ErrorListener errorListener,Class<T> clazz) {
        super(method, url, errorListener);
        mListener = listener;
        this.clazz = clazz;
    }
    public GsonRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener,Class<T> clazz) {
        this(Method.GET, url, listener, errorListener,clazz);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        Log.d("GsonRequest", "response.headers:" + response.headers);
        String temp = new String(response.data);
        Log.d("GsonRequest", temp);
        Gson gson = new Gson();
        T t = gson.fromJson(parsed,clazz);
        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
