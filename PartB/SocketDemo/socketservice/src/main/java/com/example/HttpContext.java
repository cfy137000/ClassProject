package com.example;

import java.net.Socket;
import java.util.HashMap;

/**
 * Created by hasee on 2016/7/24.
 */
public class HttpContext {
    private Socket underlySocket;
    private HashMap<String,String> requestHeaders;


    public HttpContext() {
        requestHeaders = new HashMap<>();
    }

    public void setUnderlySocket(Socket socket) {
        this.underlySocket = socket;
    }

    public Socket getUnderlySocket() {
        return underlySocket;
    }

    public void addRequestHeader(String headerName,String headerValue){
        requestHeaders.put(headerName,headerValue);
    }

    public String getRequestHeaderValue(String headerName){
        return requestHeaders.get(headerName);
    }


}
