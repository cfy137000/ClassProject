package com.example;

public class Main {
    public static void main(String[] args){
        WebConfig webConfig = new WebConfig();
        webConfig.setPort(8088);
        //设置最大连接数
        webConfig.setMaxParallels(5);
        SocketServer simpleHttpServer;
        simpleHttpServer = new SocketServer(webConfig);

        simpleHttpServer.registerResourceHandler(new ResourceInAssetsHanler());
        simpleHttpServer.registerResourceHandler(new UploadImageHandler() {
        });

        simpleHttpServer.startAsync();

    }
}
