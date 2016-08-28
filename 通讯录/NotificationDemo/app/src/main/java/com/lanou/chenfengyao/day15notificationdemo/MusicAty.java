package com.lanou.chenfengyao.day15notificationdemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * Created by ChenFengYao on 16/4/27.
 */
public class MusicAty extends AppCompatActivity implements View.OnClickListener {
    private Button playMusicBtn;
    private Button pauseMusicBtn;
    private Button nextBtn;
    private MediaPlayer player;//播放音乐用到的对象
    private SeekBar seekBar;
    //是否正在拖动
    private boolean isSeeking = false;
    private MusicPauseReceiver musicPauseReceiver;
    private RemoteViews mRemoteViews;

    private String musicUrl = "http://yinyueshiting.baidu.com/data2/music/122873158/49046814400128.mp3?xcode=edb2d83e8fb02c81794b86d808232fc4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        musicPauseReceiver = new MusicPauseReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(getPackageName() + ".MUSIC_PAUSE");
        registerReceiver(musicPauseReceiver, filter);

        playMusicBtn = (Button) findViewById(R.id.play_music_btn);
        pauseMusicBtn = (Button) findViewById(R.id.pause_music_btn);
        nextBtn = (Button) findViewById(R.id.next_btn);
        pauseMusicBtn.setOnClickListener(this);
        playMusicBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.music_seek_bar);
        //创建MediaPlayer
        //player = MediaPlayer.create(this,R.raw.lcfycjk);
//        player = MediaPlayer.create(this, Uri.parse("http://yinyueshiting.baidu.com/data2/music/122873158/49046814400128.mp3?xcode=edb2d83e8fb02c81794b86d808232fc4"));

        player = new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse(musicUrl));
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //开始拖动
                isSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //当拖动结束
                int progress = seekBar.getProgress();
                //让歌曲定位到某个位置
                player.seekTo(progress);
                isSeeking = false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_music_btn:
                //播放
                player.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            seekBar.setMax(player.getDuration());
                            if (!isSeeking) {
                                //只有不在拖动的时候,才去更新seekBar的进度
                                // seekBar.setProgress(player.getCurrentPosition());
                            }
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

                //在通知栏放一个Notification
                showMusicNotify(false);
                break;
            case R.id.pause_music_btn:
                //暂停
                player.pause();
                break;

            case R.id.next_btn://下一曲
                showMusicNotify(true);//改变Notification里的内容必须要重新弹出Notification
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(musicPauseReceiver);
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showMusicNotify(boolean change) {
        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //为Notification设置自定义布局
        mRemoteViews = new RemoteViews(getPackageName()
                , R.layout.music_notify);
        //设置ImageView里的图片
        mRemoteViews.setImageViewResource(R.id.music_icon_iv
                , R.mipmap.ic_launcher);
        //设置文字
        if (change) {
            mRemoteViews.setTextViewText(R.id.music_name_tv,
                    "下一曲");
        } else {
            mRemoteViews.setTextViewText(R.id.music_name_tv,
                    "独角戏");
        }
        if (change) {
            mRemoteViews.setTextViewText(R.id.music_singer_tv,
                    "下一曲");
        } else {
            mRemoteViews.setTextViewText(R.id.music_singer_tv,
                    "许茹芸");
        }

        Intent pauseIntent = new Intent(getPackageName()
                + ".MUSIC_PAUSE");
        PendingIntent pausePendingIntent
                = PendingIntent.getBroadcast(this, 100, pauseIntent
                , PendingIntent.FLAG_CANCEL_CURRENT);
        //设置监听
        mRemoteViews.setOnClickPendingIntent(R.id.music_pause_btn
                , pausePendingIntent);
        builder.setContent(mRemoteViews);

        Notification notification = builder.build();
        manager.notify(0, notification);

    }

    class MusicPauseReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            player.pause();
        }
    }
}
