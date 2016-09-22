package com.lanou.chenfengyao.dialogdemo;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.quick_option_dialog);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
        builder.setView(view);
        //builder.setMessage("asdf");
        AlertDialog alertDialog = builder.create();
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);

//        lp.x = 100; // 新位置X坐标
//        //lp.y = -100; // 新位置Y坐标
//        lp.width = 300; // 宽度
//        lp.height = 300; // 高度
//        lp.alpha = 0.7f; // 透明度

        alertDialog.setCanceledOnTouchOutside(true);

        dialogWindow.getDecorView().setBottom(0);
        dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

      //  dialogWindow.setAttributes(lp);
        /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);


        alertDialog.show();

    }
}
