package com.kemizhibo.kemizhibo.yhr.activity.logins;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;
import com.kemizhibo.kemizhibo.yhr.bean.UpdataPwdBean;
import com.kemizhibo.kemizhibo.yhr.presenter.UpdataPwdPresenter;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.UpdataPwdView;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class XiuGaiActivity extends BaseActivity implements UpdataPwdView {

    @BindView(R.id.xiugai_back)
    ImageView xiugaiBack;
    @BindView(R.id.xiugai_pwd)
    EditText xiugaiPwd;
    @BindView(R.id.queren_pwd)
    EditText querenPwd;
    @BindView(R.id.finish_next)
    Button finishnext;
    private UpdataPwdPresenter updataPwdPresenter;
    private MessageBean messageBean;
    private String token;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiu_gai;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
       //实例化P
        updataPwdPresenter = new UpdataPwdPresenter(this);
        messageBean = new MessageBean();
    }

    @OnClick({R.id.xiugai_back, R.id.finish_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xiugai_back:
                startActivity(new Intent(XiuGaiActivity.this, WangJiActivity.class));
                break;
            case R.id.finish_next:
                if (TextUtils.isEmpty(xiugaiPwd.getText().toString().trim())){
                    xiugaiPwd.setError("密码不能为空");
                }else if (!isPwd(xiugaiPwd.getText().toString().trim())){
                    xiugaiPwd.setError("密码必须由6-16位字母和数字组成");
                }else if (TextUtils.isEmpty(querenPwd.getText().toString().trim())||!querenPwd.getText().toString().trim().equals(xiugaiPwd.getText().toString().trim())){
                    querenPwd.setError("两次密码输入不一致");
                }else{
                    updataPwdPresenter.getDatas(token);
                }
                break;
        }
    }

    @Override
    public void success(UpdataPwdBean updataPwdBean) {
        //更改sp中的密码
        SharedPreferences sp = getSharedPreferences("logintoken", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("pwd",xiugaiPwd.getText().toString()).commit();
        startActivity(new Intent(XiuGaiActivity.this, FinishActivity.class));
        XiuGaiActivity.this.finish();
    }
    //密码规则
    public static boolean isPwd(String str) {
        //String strpwd = "/^(?=.{6,16})(?=.*[a-z])(?=.*[0-9])[0-9a-z]*$/";
        //^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$
        //  /^[a-zA-Z0-9]{6,16}$/
        String strpwd = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        Pattern p = Pattern.compile(strpwd);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @Override
    public void error(String e) {
        ToastUtils.showToast(e);
    }

    @Override
    public String getPassword() {
        return querenPwd.getText().toString();
    }

}
