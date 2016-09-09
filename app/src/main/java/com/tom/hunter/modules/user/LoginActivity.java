package com.tom.hunter.modules.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.tom.hunter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        toolbar.setTitle("注册");
    }


    @OnClick({R.id.forgotBtn, R.id.loginBtn, R.id.registerBtn, R.id.weiboBtn, R.id.wechatBtn, R.id.qqBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgotBtn:
                gotoForgotFlow();
                break;
            case R.id.loginBtn:
                attemptLogin();
                break;
            case R.id.registerBtn:
                gotoRegisterFlow();
                break;
            case R.id.weiboBtn:
                weiboAuthorized();
                break;
            case R.id.wechatBtn:
                wechatAuthorized();
                break;
            case R.id.qqBtn:
                qqAuthorized();
                break;
        }
    }

    private void gotoRegisterFlow() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void gotoForgotFlow() {
        startActivity(new Intent(this, ForgotActivity.class));
    }

    private void attemptLogin() {
        String name = username.getText().toString();
        String pwd = password.getText().toString();

    }

    private void qqAuthorized() {
    }

    private void wechatAuthorized() {

    }

    private void weiboAuthorized() {

    }
}
