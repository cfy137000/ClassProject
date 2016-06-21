package com.lanou.chenfengyao.volleydemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String URL = "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("MainActivity", response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        List<TestBean> testBeen;
//        Gson gson = new Gson();
//        Type type = new TypeToken<TestBean>(){}.getType();
//        testBeen = gson.fromJson("aaa",type);
//        requestQueue.add(stringRequest);
//        GsonRequest<TestBean> testBeanGsonRequest
//                = new GsonRequest<TestBean>(URL, new Response.Listener<TestBean>() {
//            @Override
//            public void onResponse(TestBean response) {
//                Log.d("MainActivity", "response.getCode():" + response.getCode());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }, TestBean.class);
//        requestQueue.add(testBeanGsonRequest);
//        imageView = (ImageView) findViewById(R.id.main_iv);
//        ImageLoader imageLoader = new ImageLoader(requestQueue, new DoubleCache());
//        imageLoader.get("http://c.hiphotos.baidu.com/image/pic/item/a8ec8a13632762d045aee6cea3ec08fa513dc62b.jpg"
//                , new MyImageListener());

        StringRequest request = new StringRequest("http://apis.baidu.com/apistore/weatherservice/citylist?cityname=大连", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response);
                WetherBean wetherBean = new Gson().fromJson(response,WetherBean.class);
                Toast.makeText(MainActivity.this, wetherBean.getRetData().get(0).getName_cn(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("apikey","b915afe12f997bfa32f93016b37a025f");
                return hashMap;
            }
        };
        requestQueue.add(request);
    }

    class MyImageListener implements ImageLoader.ImageListener {

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            if (response.getBitmap() != null) {
                imageView.setImageBitmap(response.getBitmap());
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,
                        "alpha", 0, 1);
                objectAnimator.setDuration(5000);
                objectAnimator.start();
            } else {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }



}
