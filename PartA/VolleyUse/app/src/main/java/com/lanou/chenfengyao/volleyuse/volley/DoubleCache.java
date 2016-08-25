package com.lanou3g.lesson.volley;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Risky on 15/10/20.
 */
public class DoubleCache implements ImageLoader.ImageCache {
    private ImageLoader.ImageCache memoryCache ;
    private ImageLoader.ImageCache diskCache;

    public DoubleCache() {
        memoryCache = new MemoryCache();
        diskCache   = new DiskCache();
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bmp = memoryCache.getBitmap(url);
        if (bmp == null) {
            bmp = diskCache.getBitmap(url);
        }
        return bmp;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        memoryCache.putBitmap(url,bitmap);
        diskCache.putBitmap(url,bitmap);
    }
}
