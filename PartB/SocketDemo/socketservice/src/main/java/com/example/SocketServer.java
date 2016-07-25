package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.System.out;

/**
 * Created by ChenFengYao on 16/7/25.
 */
public class SocketServer {
    private ExecutorService mThreadPool;
    private WebConfig mWebConfig;
    private boolean isEnable;
    private ServerSocket mServerSocket;
    private Set<IResourceUriHandler> resourceHandlers;

    public SocketServer(WebConfig webConfig) {
        mWebConfig = webConfig;
        mThreadPool = Executors.newCachedThreadPool();
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
        mServerSocket.close();
        mServerSocket = null;

    }

    private void doProcSycn() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(mWebConfig.getPort());
            mServerSocket = new ServerSocket();
            mServerSocket.bind(socketAddress);

            while (isEnable) {
                final Socket remotePeer = mServerSocket.accept();
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        out.println("一个远程连接已连接:"
                                + remotePeer.getRemoteSocketAddress().toString());

                        //onAcceptRemotePeer(remotePeer);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
            out.println("开始获取请求头");
            InputStreamReader inputStreamReader = new InputStreamReader(nis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String resourceUri = headLine = bufferedReader.readLine().split(" ")[1];
            while ((headLine = bufferedReader.readLine().trim()) != null) {
                if (headLine.equals("")) {
                    break;
                }
                out.println("请求头-"+headLine);
                String[] headers = headLine.split(": ");
                if (headers.length > 1) {
                    httpContext.addRequestHeader(headers[0], headers[1]);
                }
            }

            out.println("请求头获取完成");

            for (IResourceUriHandler handler : resourceHandlers) {
                if(!handler.accept(resourceUri)){

                    continue;
                }
                handler.handler(resourceUri,httpContext);
            }

        } catch (IOException e) {
          e.printStackTrace();
        }
    }

}
