package com.lanou.chenfengyao.volleyuse.nettool;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lanou.chenfengyao.volleyuse.R;

/**
 * Created by ChenFengYao on 16/8/16.
 * 网络请求的工具类
 * 在进行网络请求的时候,都使用该工具类
 */
public class NetTool {
    private Object tag;//

    //在netTool初始化的时候,设置tag
    //方便在netTool在销毁的时候可以通过该tag来取消网络请求
    public NetTool(Object tag) {
        this.tag = tag;
    }

    public NetTool() {
    }

    /**
     * 用来请求网络图片
     *
     * @param url       图片的url
     * @param imageView 图片需要设置给的ImageView
     */
    public void getImage(String url, ImageView imageView) {
        ImageLoader imageLoader = VolleySingleton
                .getInstance().getImageLoader();

        imageView.setTag(url);
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher,//默认图片
                R.mipmap.ic_launcher));//错误图片
    }

    class MyImgListener implements ImageLoader.ImageListener {
        private String url;
        private ImageView mImageView;

        public MyImgListener(ImageView imageView, String url) {
            mImageView = imageView;
            this.url = url;
        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if (bitmap != null) {
                if(url.equals(mImageView.getTag())){
                    mImageView.setImageBitmap(bitmap);
                }else {
                    getImage(url,mImageView);
                }
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    /**
     * 根据Url获得网络数据
     * 注意:此方法只能解析JsonObject类型的数据,无法解析JsonArray类型的数据
     * 利用Gson 解析JsonArray类型的数据,需要自行百度
     *
     * @param url         网络请求的url
     * @param tClass      要解析的实体类 类型
     * @param netListener 回调的接口
     * @param <T>         要解析的实体类类型
     */
    public <T> void getNetData(String url, Class<T> tClass, final NetListener<T> netListener) {
        GsonRequest<T> gsonRequest = new GsonRequest<T>(url, tClass, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                //将解析后的数据 返回给Activity,Fragment等调用的地方
                netListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onError(error.getMessage());

            }
        });

        //将Request加入到RequestQueue里
        VolleySingleton.getInstance().addRequest(gsonRequest, tag);
    }

    //netTool销毁的方法,会取消和这个工具类绑定的所有Tag
    public void onDestroy() {
        if (tag != null) {
            VolleySingleton.getInstance().removeRequest(tag);
        }
    }

    public interface NetListener<T> {
        void onSuccess(T t);//请求成功

        void onError(String errorMsg);//请求失败,返回错误信息
    }
}
