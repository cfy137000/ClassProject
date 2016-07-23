package com.lanou.chenfengyao.mvpdemo.login;

import java.util.Random;

/**
 * Created by ChenFengYao on 16/7/17.
 */
public class LoginModel {
    private LoginContract.Presenter presenter;

    public LoginModel(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void checkLoginFromNet(final String userName, final String psw){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if(userName.equals("userName")&&psw.equals("psw")){
                        presenter.loginSuccess();
                    }else {
                        presenter.loginError("用户名或密码错误");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
