package com.kemizhibo.kemizhibo.yhr.activity.logins;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.MainActivity;
import com.kemizhibo.kemizhibo.yhr.activity.SplashActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.TokenBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.GetLoginPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<GetLoginPresenterImpl> implements LoginView {

    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_wangji)
    TextView loginWangji;
    @BindView(R.id.login_butn)
    Button loginButn;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0)
                startActivity(intent);
                finish();
        };
    };


    @Inject
    public GetLoginPresenterImpl getTokenPresenter;

    //登陆
    private LoginBean loginContentBean;
    private String name;
    private String pwd;
    private Intent intent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.login_wangji, R.id.login_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_wangji:
                startActivity(new Intent(LoginActivity.this, WangJiActivity.class));
                break;
            case R.id.login_butn:
                if (TextUtils.isEmpty(loginName.getText().toString().trim())){
                    loginName.setError("用户名不能为空");
                }else if (TextUtils.isEmpty(loginPwd.getText().toString().trim())){
                    loginPwd.setError("密码不能为空");
                }else {
                    name = loginName.getText().toString().trim();
                    pwd = loginPwd.getText().toString().trim();
                    getTokenPresenter.getLoginData(this, name,pwd);
                }
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        if(loginBean.getCode()==0){
            SharedPreferences sp = getSharedPreferences("logintoken", 0);
            SharedPreferences.Editor edit = sp.edit();
            LogUtils.i("LoginActivity", "token" + loginBean.getContent());
            edit.putString("token",loginBean.getContent()).
                    putString("name",name).putString("pwd",pwd).commit();
            getTokenPresenter.getUserData(this,"Bearer "+loginBean.getContent().toString());
        }else {
            ToastUtils.showToast("账号或密码错误");
        }

    }

    @Override
    public void onLoginError(String msg) {
        Transparent.showErrorMessage(this,"无网络!");
    }

    //获取用户信息
    @Override
    public void onUserSuccess(GetUserBean getUserBean) {
        if (getUserBean.getCode()==0){
            GetUserBean.ContentBean content = getUserBean.getContent();
            //存用户ID
            SharedPreferences sp = getSharedPreferences("logintoken", 0);
            SharedPreferences.Editor edit = sp.edit();
            edit.putInt("userId", content.getUserId()).commit();
            intent = new Intent(LoginActivity.this, MainActivity.class);
            Transparent.showSuccessMessage(this,"登录成功!");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void onUserError(String msg) {

    }

    @Override
    protected GetLoginPresenterImpl initInject() {
        activityComponent.inject(this);
        return getTokenPresenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MyApplication.YINGXIANG_TO_PICK_req&&resultCode==MyApplication.YINGXIANG_TO_PICK_res){
            finish();
        }
    }
}
