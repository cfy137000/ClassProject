package com.lanou.chenfengyao.androidserver;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ChenFengYao on 16/7/23.
 */
public class SimpleHttpServer {

    private final WebConfiguration webConfiguration;
    private boolean isEnable;
    private ServerSocket socket;
    private final ExecutorService threadPool;
    private Set<IResourceUriHandler> resourceHandlers;

    public SimpleHttpServer(WebConfiguration webConfiguration) {
        this.webConfiguration = webConfiguration;
        threadPool = Executors.newCachedThreadPool();
        resourceHandlers = new HashSet<>();
    }

    /**
     * 启动server(异步)
     */
    public void startAsync() {
        isEnable = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProcSycn();
            }
        }).start();
    }


    /**
     * 停止Server(异步)
     */
    public void stopAsync() throws IOException {
        if (isEnable) {
            return;
        }
        isEnable = false;
        socket.close();
        socket = null;

    }

    private void doProcSycn() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(webConfiguration.getPort());
            socket = new ServerSocket();
            socket.bind(socketAddress);

            while (isEnable) {
                final Socket remotePeer = socket.accept();
                threadPool.submit(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("SimpleHttpServer", "a remote peer accepted..."
                                + remotePeer.getRemoteSocketAddress().toString());
                        onAcceptRemotePeer(remotePeer);
                    }
                });
            }
        } catch (IOException e) {
            Log.d("SimpleHttpServer",
                    e.toString());
        }

    }

    private final String TAG = "SimpleHttpServer";

    public void registerResourceHandler(IResourceUriHandler handler){
        resourceHandlers.add(handler);
    }

    private void onAcceptRemotePeer(Socket remotePeer) {
        try {
//            remotePeer.getOutputStream()
//                    .write("congratulations,connected successful".getBytes());
            HttpContext httpContext = new HttpContext();
            httpContext.setUnderlySocket(remotePeer);
            InputStream nis = remotePeer.getInputStream();
            String headLine = null;
            Log.d("SimpleHttpServer", "准备开始打印头");
            InputStreamReader inputStreamReader = new InputStreamReader(nis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String resourceUri = headLine = bufferedReader.readLine().split(" ")[1];
            Log.d(TAG, "onAcceptRemotePeer: resourceUri:"+resourceUri);
            while ((headLine = bufferedReader.readLine().trim()) != null) {
                if (headLine.equals("")) {
                    break;
                }
                Log.d(TAG, "onAcceptRemotePeer: " + headLine);
                String[] headers = headLine.split(": ");
                if (headers.length > 1) {
                    httpContext.addRequestHeader(headers[0], headers[1]);
                }
            }

            Log.d("SimpleHttpServer", "打印结束");

            for (IResourceUriHandler handler : resourceHandlers) {
                if(!handler.accept(resourceUri)){

                    continue;
                }
                handler.handler(resourceUri,httpContext);
            }

        } catch (IOException e) {
            Log.d("SimpleHttpServer", e.toString());
        }
    }
}
