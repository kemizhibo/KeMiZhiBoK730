package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.ChangePwdPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.DataClearManager;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.ChangePwdView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kemizhibo.kemizhibo.yhr.activity.logins.XiuGaiActivity.isPwd;

public class ChangePwdActivity extends BaseMvpActivity<ChangePwdPresenterImpl> implements ChangePwdView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.old_pwd_edittext)
    EditText oldPwdEdittext;
    @BindView(R.id.new_pwd_edittext)
    EditText newPwdEdittext;
    @BindView(R.id.finish_butn)
    Button finishButn;
    @Inject
    public ChangePwdPresenterImpl changePwdPresenter;
    @BindView(R.id.new_pwd_edittext_agin)
    EditText newPwdEdittextAgin;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){
                Intent intent = new Intent(ChangePwdActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
    };
    private SharedPreferences sp;
    private String token;
    private String oldpwd;
    private String newpwd;
    private String aginpwd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("修改密码");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    @Override
    protected ChangePwdPresenterImpl initInject() {
        activityComponent.inject(this);
        return changePwdPresenter;
    }

    @OnClick(R.id.finish_butn)
    public void onViewClicked() {
        if (NoFastClickUtils.isFastClick()) {
        }else {
            oldpwd = oldPwdEdittext.getText().toString();
            newpwd = newPwdEdittext.getText().toString();
            aginpwd = newPwdEdittextAgin.getText().toString();
            sp = getSharedPreferences("logintoken", 0);
            String pwd = sp.getString("pwd", "");
            if (TextUtils.isEmpty(oldpwd)) {
                oldPwdEdittext.setError("请输入旧密码");
                oldPwdEdittext.requestFocus();
            } else if (TextUtils.isEmpty(newpwd)) {
                newPwdEdittext.setError("请输入新密码");
                newPwdEdittext.requestFocus();
            } else if (TextUtils.isEmpty(aginpwd)){
                newPwdEdittextAgin.setError("请输入新密码");
                newPwdEdittextAgin.requestFocus();
            }else if (!isPwd(newpwd)) {
                newPwdEdittext.setError("密码必须由6-20位字母和数字组成");
                newPwdEdittext.requestFocus();
            } else if (!newpwd.equals(aginpwd)){
                newPwdEdittextAgin.setError("两次密码不一致，请重新输入");
                newPwdEdittextAgin.requestFocus();
            }else if (!oldpwd.equals(pwd)){
                oldPwdEdittext.setError("旧密码错误");
                oldPwdEdittext.requestFocus();
            }
            else {
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                changePwdPresenter.getChangePwdData(this, "Bearer " + token, oldpwd, newpwd);
            }
        }
    }


    @Override
    public void onChangePwdSuccess(ChangePwdBean changePwdBean) {
        if (changePwdBean.getCode() == 0) {
            //更改sp中的密码
            SharedPreferences sp = getSharedPreferences("logintoken", 0);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("pwd", newpwd).commit();
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setCancelable(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setContentView(R.layout.alertdialog_style);
            //WindowManager windowManager = this.getWindowManager();
            //Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = 520;
            lp.height = 260;
            window.setAttributes(lp);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1500);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            initDialogToLogin();
        }
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                }else {
                                    Intent intent = new Intent(ChangePwdActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        })
                        .build();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

    @Override
    public void onChangePwdError(String msg) {
        ToastUtils.showToast(msg);
    }
}
