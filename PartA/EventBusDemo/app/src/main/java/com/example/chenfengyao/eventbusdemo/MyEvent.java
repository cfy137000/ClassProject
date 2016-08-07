package com.example.chenfengyao.eventbusdemo;

/**
 * Created by ChenFengYao on 15/10/28.
 * 所有的事件
 */
public class MyEvent {
    public static class FirstEvent{
        String text;

        public FirstEvent(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    //用于服务的类
    public static class ServiceEvent{
        int what;

        public ServiceEvent(int what) {
            this.what = what;
        }

        public int getWhat() {
            return what;
        }
    }
}
