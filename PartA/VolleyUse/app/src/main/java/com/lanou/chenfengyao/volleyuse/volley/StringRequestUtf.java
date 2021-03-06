package com.lanou3g.lesson.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by dllo on 15/8/7.
 */
public class StringRequestUtf extends StringRequest {

    public StringRequestUtf(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public StringRequestUtf(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed = null;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            try {
                parsed = new String(response.data,"utf-8");
            } catch (UnsupportedEncodingException e1) {
                parsed = new String(response.data);
            }
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
