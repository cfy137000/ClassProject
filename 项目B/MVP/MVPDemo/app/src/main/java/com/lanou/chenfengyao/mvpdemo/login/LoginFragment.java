package com.lanou.chenfengyao.mvpdemo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanou.chenfengyao.mvpdemo.MainActivity;
import com.lanou.chenfengyao.mvpdemo.R;

/**
 * Created by ChenFengYao on 16/7/17.
 */
public class LoginFragment extends Fragment implements LoginContract.View {
    private EditText userName, psw;
    private Button loginBtn;
    private LoginContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userName = (EditText) view.findViewById(R.id.user_name);
        psw = (EditText) view.findViewById(R.id.psw);
        loginBtn = (Button) view.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressDialog == null) {
                    initProgressDialog();
                } else {
                    progressDialog.show();

                }
                presenter.login(userName.getText().toString(), psw.getText().toString());

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initProgressDialog();
        presenter.start();
    }

    private void initProgressDialog() {
        progressDialog = ProgressDialog.show(getActivity(), "正在登陆", "请稍后", true, false);
    }

    @Override
    public void loginSuccess() {
        progressDialog.dismiss();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void loginError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
