package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.ChangePwdPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.SendYanZhengMaPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.ChangePwdView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;
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
    private SharedPreferences sp;
    private String token;
    private String oldpwd;
    private String newpwd;

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
    protected ChangePwdPresenterImpl initInject() {
        activityComponent.inject(this);
        return changePwdPresenter;
    }

    @OnClick(R.id.finish_butn)
    public void onViewClicked() {
        oldpwd = oldPwdEdittext.getText().toString();
        newpwd = newPwdEdittext.getText().toString();
        if (TextUtils.isEmpty(oldpwd)){
            oldPwdEdittext.setError("旧密码不能为空");
        }else if (TextUtils.isEmpty(newpwd)){
            newPwdEdittext.setError("新密码不能为空");
        }
        else if (!isPwd(newpwd)){
            newPwdEdittext.setError("密码必须由6-20位字母和数字组成");
        }
        else{
            sp = getSharedPreferences("logintoken", 0);
            token = sp.getString("token", "");
            changePwdPresenter.getChangePwdData(this,"Bearer "+ token,oldpwd,newpwd);
        }

    }

    @Override
    public void onChangePwdSuccess(ChangePwdBean changePwdBean) {
         if (changePwdBean.getCode()==0){
             //更改sp中的密码
             SharedPreferences sp = getSharedPreferences("logintoken", 0);
             SharedPreferences.Editor edit = sp.edit();
             edit.putString("pwd",newpwd).commit();
             Intent intent = new Intent(ChangePwdActivity.this, LoginActivity.class);
             startActivity(intent);
             finish();
         }else {
             oldPwdEdittext.setError("旧密码错误");
         }
    }

    @Override
    public void onChangePwdError(String msg) {

    }
}
