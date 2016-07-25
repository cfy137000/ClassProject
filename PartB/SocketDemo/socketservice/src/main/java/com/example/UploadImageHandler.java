package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by hasee on 2016/7/24.
 */
public class UploadImageHandler implements IResourceUriHandler {
    private String acceptPrefix = "/upload_image/";

    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrefix);
    }

    @Override
    public void handler(String uri, HttpContext httpContext) throws IOException {
        String tempPath = "/mnt/sdcard/test_puload.jpg";
        long totalLength = Long.parseLong(httpContext.getRequestHeaderValue("Content-Length"));
        FileOutputStream fos = new FileOutputStream(tempPath);
        InputStream nis = httpContext.getUnderlySocket().getInputStream();
        byte[] buffer = new byte[10240];
        int nReaded = 0;
        long nLeftLength = totalLength;
        while ((nReaded = nis.read(buffer))>0 && nLeftLength > 0){
            fos.write(buffer,0,nReaded);
            nLeftLength -= nReaded;
        }
        fos.close();
        nis.close();

        OutputStream nos = httpContext.getUnderlySocket().getOutputStream();
        PrintStream printer = new PrintStream(nos);
        printer.println("HTTP/1.1 200 OK");
        printer.println();
        printer.close();
        nos.close();

        onImageLoaded(tempPath);
    }

    protected void onImageLoaded(String path){

    }
}
