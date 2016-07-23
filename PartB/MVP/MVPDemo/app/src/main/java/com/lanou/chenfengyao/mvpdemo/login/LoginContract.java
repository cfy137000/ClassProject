package com.lanou.chenfengyao.mvpdemo.login;


import com.lanou.chenfengyao.mvpdemo.base.BasePresenter;
import com.lanou.chenfengyao.mvpdemo.base.BaseView;

/**
 * Created by ChenFengYao on 16/7/17.
 */
public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void loginSuccess();
        void loginError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void login(String userName,String psw);
        void loginError(String errorMessage);
        void loginSuccess();
    }
}
