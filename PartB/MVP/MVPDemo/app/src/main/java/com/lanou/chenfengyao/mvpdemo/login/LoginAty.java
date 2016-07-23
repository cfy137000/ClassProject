package com.lanou.chenfengyao.mvpdemo.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ChenFengYao on 16/7/17.
 */
public class LoginAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content,loginFragment,"login")
                .show(loginFragment).commit();
        new LoginPresenter(loginFragment);
    }
}
