package com.lanou.chenfengyao.bmobdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn,upLoadIconBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化 建议初始化放到Application里
        Bmob.initialize(this, "738e0e0144a73a1a212a3a57d8a3664c");

        loginBtn = (Button) findViewById(R.id.login_btn);
        upLoadIconBtn = (Button) findViewById(R.id.up_load_icon);

        loginBtn.setOnClickListener(this);
        upLoadIconBtn.setOnClickListener(this);

        //创建用户
//        BmobUser bmobUser = new BmobUser();
//        bmobUser.setUsername("aaaabbb");//用户名
//        bmobUser.setPassword("111111");//密码
//        bmobUser.signUp(new SaveListener<BmobUser>() {
//            @Override
//            public void done(BmobUser bmobUser, BmobException e) {
//                if(e == null){
//                    Toast.makeText(MainActivity.this,
//                            "注册成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    //注册失败
//                    Toast.makeText(MainActivity.this,
//                            e.getMessage(), Toast.LENGTH_SHORT).show();
//                    Log.d("MainActivity", e.getMessage());
//                }
//            }
//        });
        //尝试自动登录
//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if(bmobUser != null){
//            Toast.makeText(this,
//                    "已经登录过了", Toast.LENGTH_SHORT).show();
//        } else {
//            bmobUser = new BmobUser();
//            bmobUser.setUsername("aaaabbb");
//            bmobUser.setPassword("111111");
//            bmobUser.login(new SaveListener<BmobUser>() {
//                @Override
//                public void done(BmobUser bmobUser, BmobException e) {
//                    if(e == null){
//                        Toast.makeText(MainActivity.this,
//                                "登录成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(MainActivity.this,
//                                e.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.d("MainActivity", e.getMessage());
//                    }
//                }
//            });
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                login();
                break;
            case R.id.up_load_icon:
                upLoadIcon();
                break;
        }
    }

    private void upLoadIcon(){
        final MyUser myUser = MyUser.getCurrentUser(MyUser.class);
        if(myUser == null){
            Toast.makeText(this,
                    "先登录", Toast.LENGTH_SHORT).show();
        }else {
            //已经登录过了
            //上传头像
            //拿到图片的bitmap
            Bitmap bitmap = BitmapFactory
                    .decodeResource(getResources(),R.mipmap.ic_launcher);
            //getCacheDir是Android提供的缓存路径
            //位置 是包名/cache
            //该方法 是Context的方法,可以使用Application的Context
            File cacheDir = getCacheDir();
            if(!cacheDir.exists()){
                //如果这个路径不存在
                cacheDir.mkdir();//就创建这个文件夹
            }
            //文件名加上时间 为了 防止 文件名重复
            long time = System.currentTimeMillis();
            File iconFile = new File(cacheDir
                    ,myUser.getUsername()+time+".png");
            if(!iconFile.exists()){
                //如果文件不存在
                try {
                    //创建文件
                    iconFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream = null;
            try {
                //创建一个文件输出流

                      fileOutputStream  = new FileOutputStream(iconFile);

                bitmap.compress(Bitmap.CompressFormat.PNG,
                        100,fileOutputStream);
                fileOutputStream.close();
                //图片就存到了File里面了
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtil.closeIO(fileOutputStream);

            }
            //上传File
            final BmobFile bmobFile = new BmobFile(iconFile);
            //上传
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if(e == null){
                        Toast.makeText(MainActivity.this,
                                "上传成功", Toast.LENGTH_SHORT).show();
                        //拿到图片的URL
                        String fileUrl = bmobFile.getFileUrl();
                        Log.d("MainActivity", fileUrl);
                        //把图片的url存储到 用户表里
                        myUser.setIcon(fileUrl);
                        myUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e == null){
                                    Toast.makeText(MainActivity.this,
                                            "存储url成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "存储url失败", Toast.LENGTH_SHORT).show();
                                    Log.d("MainActivity", e.getMessage());
                                }
                            }
                        });

                    } else {
                        Toast.makeText(MainActivity.this,
                                "上传失败", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", e.getMessage());
                    }
                }
            });
        }
    }

    //登录
    private void login(){
        MyUser myUser = new MyUser();
        myUser.setUsername("aaaabbb");
        myUser.setPassword("111111");
        myUser.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    Log.d("MainActivity", "登录成功");
                }else {
                    Log.d("MainActivity", "登录失败");
                    Log.d("MainActivity", e.getMessage());
                }
            }
        });
    }


}
class IOUtil{
    public static void closeIO(Closeable ... closeables){
        for (Closeable closeable : closeables) {
            if(closeable != null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
