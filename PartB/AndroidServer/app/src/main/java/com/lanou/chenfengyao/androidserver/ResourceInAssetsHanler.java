package com.lanou.chenfengyao.androidserver;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by hasee on 2016/7/24.
 */
public class ResourceInAssetsHanler implements IResourceUriHandler {
    private String acceptPrefix = "/static/";
    private Context context;

    public ResourceInAssetsHanler(Context context) {
        this.context = context;
    }

    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrefix);
    }

    @Override
    public void handler(String uri, HttpContext httpContext) throws IOException {
        int startIndex = acceptPrefix.length();
        String assetsPath = uri.substring(startIndex);
        InputStream is = context.getAssets().open(assetsPath);
        byte[] raw = StreamToolkit.readRawFromStream(is);
        is.close();
        OutputStream nos = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printStream = new PrintStream(nos);
        printStream.println("HTTP/1.1 200 OK");
        printStream.println("Content-Length:"+raw.length);
        if(assetsPath.endsWith(".html")){
            printStream.println("Content-Type:text/html");
        }else if (assetsPath.endsWith(".js")){
            printStream.println("Content-Type:text/js");
        }else if (assetsPath.endsWith(".css")){
            printStream.println("Content-Type:text/css");
        }else if (assetsPath.endsWith(".jpg")){
            printStream.println("Content-Type:text/jpg");
        }else if (assetsPath.endsWith(".png")){
            printStream.println("Content-Type:text/png");
        }
        printStream.println();
        printStream.write(raw);

        printStream.close();
        is.close();
        nos.close();
    }
}
