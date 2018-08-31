package com.kemizhibo.kemizhibo.yhr.activity.logins;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;
import com.kemizhibo.kemizhibo.yhr.bean.UpdataPwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.ResetPwdBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.findpwdimpl.ResetPwdPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.ResetPwdView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class XiuGaiActivity extends BaseMvpActivity<ResetPwdPresenterImpl> implements ResetPwdView {

    @BindView(R.id.xiugai_pwd)
    EditText xiugaiPwd;
    @BindView(R.id.queren_pwd)
    EditText querenPwd;
    @BindView(R.id.finish_next)
    Button finishnext;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    private String content;
    @Inject
    public ResetPwdPresenterImpl resetPwdPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiu_gai;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        Intent intent = getIntent();
        content = intent.getStringExtra("content");
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("找回密码");
        publicTitleBarRoot.buildFinish();
    }

    @OnClick({R.id.finish_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish_next:
                if (TextUtils.isEmpty(xiugaiPwd.getText().toString().trim())) {
                    xiugaiPwd.setError("密码不能为空");
                    xiugaiPwd.requestFocus();
                } else if (!isPwd(xiugaiPwd.getText().toString().trim())) {
                    xiugaiPwd.setError("密码必须由6-20位字母和数字组成");
                    xiugaiPwd.requestFocus();
                } else if (TextUtils.isEmpty(querenPwd.getText().toString().trim()) || !querenPwd.getText().toString().trim().equals(xiugaiPwd.getText().toString().trim())) {
                    querenPwd.setError("两次密码输入不一致");
                    querenPwd.requestFocus();
                } else {
                    resetPwdPresenter.getResetPwdData(this,content,querenPwd.getText().toString().trim());
                }
                break;
        }
    }


    //密码规则
    public static boolean isPwd(String str) {
        String strpwd = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        Pattern p = Pattern.compile(strpwd);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @Override
    public void onResetPwdSuccess(ResetPwdBean resetPwdBean) {
        //Transparent.showSuccessMessage(this,"登录成功!");
        //更改sp中的密码
        SharedPreferences sp = getSharedPreferences("logintoken", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("pwd", xiugaiPwd.getText().toString()).commit();
        startActivity(new Intent(XiuGaiActivity.this, FinishActivity.class));
        XiuGaiActivity.this.finish();
    }

    @Override
    public void onResetPwdError(String msg) {
        Transparent.showSuccessMessage(this,"重置密码失败请重试～");
    }

    @Override
    protected ResetPwdPresenterImpl initInject() {
        activityComponent.inject(this);
        return resetPwdPresenter;
    }
}
