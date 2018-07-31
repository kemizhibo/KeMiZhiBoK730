package com.kemizhibo.kemizhibo.yhr.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.GetLoginPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: 启动页
 */

public class SplashActivity extends BaseMvpActivity<GetLoginPresenterImpl> implements LoginView {
    private final long SPLASH_LENGTH = 3000;
    Handler handler = new Handler();
    //private String token;
    @Inject
    public GetLoginPresenterImpl getTokenPresenter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
            public void run() {
               SharedPreferences sp = getSharedPreferences("logintoken", 0);
//                token = sp.getString("token", "");
                String token = sp.getString("token", "");

                if (!token.equals("")){
                    //刷新token
                    String name = sp.getString("name", "");
                    String pwd = sp.getString("pwd", "");
                    getTokenPresenter.getLoginData(SplashActivity.this, name,pwd);

                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_LENGTH);//3秒后跳转至应用主界面MainActivity
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        if (loginBean.getCode()==0){
            SharedPreferences sp = getSharedPreferences("logintoken", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("token",loginBean.getContent()).commit();
            LogUtils.e("111111111111111",loginBean.getContent());
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onLoginError(String msg) {

    }

    @Override
    protected GetLoginPresenterImpl initInject() {
        activityComponent.inject(this);
        return getTokenPresenter;
    }
}
