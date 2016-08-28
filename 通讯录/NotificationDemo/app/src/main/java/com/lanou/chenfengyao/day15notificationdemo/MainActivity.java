package com.lanou.chenfengyao.day15notificationdemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBtn = (Button) findViewById(R.id.show_notification_btn);
        showBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_notification_btn:
                //显示一个Notification
                showNotification();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification() {
        //第一步:获得Manager对象
        NotificationManager manager
                = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        //第二步,通过Builder来设置Notification
        //2.1获得Builder对象
        Notification.Builder builder =
                new Notification.Builder(this);
        //2.2设置各个属性
        Bitmap largeIcon = BitmapFactory
                .decodeResource(getResources(), R.mipmap.wbh);
        builder.setLargeIcon(largeIcon);//设置大图
        builder.setContentTitle("隔壁老吴");//设置标题
        builder.setContentText("今晚8点");
        //设置小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);

        //设置PendingIntent
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        //PendingIntent 相当于将Intent包装起来
        //在未来的某个时候,再执行该意图
        //PendingIntent 首先需要使用静态方法来生成
        //如果想要跳转Activity,就使用
        //  getActivity的方法
        //  它接收4个参数
        //  Context,结果码,Intent和Flag
        //  结果码和Flag需要配合考虑
        //Flag代表,PendingIntent 在多次生成时,所使用的策略
        // 常见的有FLAG_ONE_SHOT:当PendingIntent的意图被执行后
        //                      所有的相同Pending都失效
        //FLAG_CANCEL_CURRENT: 后生成的Pending会更新之前所有相同
        //                      的Pending,并且取消前者的Pending
        //FLAG_UPDATE_CURRENT:更新Pending,不取消之前的
        //其中FLAG_CANCEL_CURRENT是 我们通常使用的Flag
        //它会保证所有的意图是最新的
        //如果确定两个PendingIntent是否是相同的Pending
        //Android 使用PendingIntent的结果码和intent来确定
        //当两个PendingIntent具有相同的结果码和intent时
        //这两个PendingIntent就是相同的
        //注意:intent中所携带的值并不参与匹配
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 100, intent
                        , PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        //显示Notification的数量
        builder.setNumber(5);
        //2.3 通过Builder生成Notification
        Notification notification = builder.build();
        //让Notification显示出来
        //第一个参数是id 如果id相同,则会显示一条
        //如果id不同才能显示多条

        //让Notification点击消失
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(i++, notification);

    }


}
