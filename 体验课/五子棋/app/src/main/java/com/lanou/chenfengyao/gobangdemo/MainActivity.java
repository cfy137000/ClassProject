package com.lanou.chenfengyao.gobangdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.lanou.chenfengyao.gobangdemo.base.BaseActivity;
import com.lanou.chenfengyao.gobangdemo.utils.BindContent;
import com.lanou.chenfengyao.gobangdemo.utils.BindView;
import com.lanou.chenfengyao.gobangdemo.weiget.CheckerBoard;

@BindContent(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_restart)
    private Button restartBtn;
    @BindView(R.id.checker_broad)
    private CheckerBoard checkerBoard;
    @Override
    public void initData() {
        restartBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        checkerBoard.restart();
    }
}
