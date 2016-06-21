package com.lanou.chenfengyao.asyncandthreadpooldemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ChenFengYao on 16/3/31.
 */
public class DeskCache {
    static String cacheDir = MyApplication.context.getCacheDir() + "/Image/";
    public Bitmap getBitmap(String url) {
        url = MD5Util.getMD5String(url);
        return BitmapFactory.decodeFile(cacheDir + url + ".png");
    }

    public void putBitmap(String url, Bitmap bitmap) {
        File file = new File(cacheDir);
        if (!file.exists()) {
            file.mkdir();
        }
        url = MD5Util.getMD5String(url);
        Log.d("Sysout",cacheDir);
        File imageFile = new File(cacheDir, url+".png");
        if (!imageFile.exists()) {
            FileOutputStream fileOutputStream = null;
            try {
                imageFile.createNewFile();
                fileOutputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
