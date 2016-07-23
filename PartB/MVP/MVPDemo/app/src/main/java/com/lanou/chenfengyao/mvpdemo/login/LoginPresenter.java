package com.lanou.chenfengyao.mvpdemo.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by ChenFengYao on 16/7/17.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LoginModel loginModel;
    private Handler mMainHandler;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(String userName, String psw) {
        if (userName == null || psw == null || userName.length() * psw.length() ==0) {
            view.loginError("用户名或密码为空");
        } else {
            loginModel.checkLoginFromNet(userName, psw);
        }
    }

    @Override
    public void loginError(String errorMessage) {
        Message message = mMainHandler.obtainMessage();
        message.what = 2;
        message.obj = errorMessage;
        mMainHandler.sendMessage(message);
    }

    @Override
    public void loginSuccess() {
        mMainHandler.sendEmptyMessage(1);

    }

    @Override
    public void start() {
        loginModel = new LoginModel(this);
        mMainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        view.loginSuccess();
                        break;
                    default:
                        view.loginError(msg.obj.toString());
                        break;
                }
            }
        };
    }
}
