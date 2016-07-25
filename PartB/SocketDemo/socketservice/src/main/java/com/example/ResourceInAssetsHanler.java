package com.example;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hasee on 2016/7/24.
 */
public class ResourceInAssetsHanler implements IResourceUriHandler {
    private String acceptPrefix = "/static/";


    @Override
    public boolean accept(String uri) {
        return uri.startsWith(acceptPrefix);
    }

    @Override
    public void handler(String uri, HttpContext httpContext) throws IOException {
        Socket underlySocket = httpContext.getUnderlySocket();
        OutputStream outputStream = underlySocket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println("这是服务端");
        writer.close();
        outputStream.close();
    }
}
