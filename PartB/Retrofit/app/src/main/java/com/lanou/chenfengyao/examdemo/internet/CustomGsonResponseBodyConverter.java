package com.lanou.chenfengyao.examdemo.internet;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/**
 * If there is no bug, then it is created by ChenFengYao on 2016/11/16,
 * otherwise, I do not know who create it either.
 */

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        Log.d("CustomGsonResponseBodyC", "responsebody");
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value)  {

        JsonReader jsonReader = gson.newJsonReader(value.charStream());
            Log.d("CustomGsonResponseBodyC", "aaaaa");
        try {
            Log.d("CustomGsonResponseBodyC", "解析前");
            T t = adapter.read(jsonReader);
            Log.d("CustomGsonResponseBodyC", "jie");
            return t;
        } catch (Exception e){
            Log.d("CustomGsonResponseBodyC", "数据有问题");
            return null;
        }finally {
            value.close();
        }
    }
}
