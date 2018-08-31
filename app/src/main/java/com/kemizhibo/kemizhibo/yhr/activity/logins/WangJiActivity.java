package com.kemizhibo.kemizhibo.yhr.activity.logins;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;
import com.kemizhibo.kemizhibo.yhr.presenter.GetMssPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.MessagePresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.findpwdimpl.SendValidateBeforLoginPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.PhoneFormatCheckUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TimerUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.SendValidateBeforLoginView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class WangJiActivity extends BaseMvpActivity<SendValidateBeforLoginPresenterImpl> implements SendValidateBeforLoginView {

    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.yanzhengma)
    EditText yanzhengma;
    @BindView(R.id.huoqv_yanzhengma)
    Button huoqvYanzhengma;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    private GetMssPresenter getMssPresenter;
    private MessagePresenter messagePresenter;
    private TimerUtils timerUtils;
    private String content;
    private String mobile;

    @Inject
    public SendValidateBeforLoginPresenterImpl sendYanZhengMaPresenter;
    private String yanzhengcode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wnag_ji;
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
        publicTitleBarRoot.changeTitleBar("找回密码");
        publicTitleBarRoot.buildFinish();
    }

    @OnClick({R.id.huoqv_yanzhengma, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.huoqv_yanzhengma:
                mobile = phoneNumber.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNumber.getText().toString())) {
                    phoneNumber.setError("请先输入您的手机号码");
                    phoneNumber.requestFocus();
                    //ToastUtils.showToast("请先输入您的手机号码");
                } else if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phoneNumber.getText().toString()) && !PhoneFormatCheckUtils.isHKPhoneLegal(phoneNumber.getText().toString()) && !PhoneFormatCheckUtils.isPhoneLegal(phoneNumber.getText().toString())) {
                    phoneNumber.setError("请输入正确的手机号码");
                    phoneNumber.requestFocus();
                    //ToastUtils.showToast("请输入正确的手机号码");
                } else {
                    sendYanZhengMaPresenter.getSendYanZhengMaData(this, "1", mobile);
                }
                break;
            case R.id.next:
                yanzhengcode = yanzhengma.getText().toString().trim();
                if (TextUtils.isEmpty(yanzhengma.getText().toString().trim())) {
                    yanzhengma.setError("请先输入您的验证码");
                    yanzhengma.requestFocus();
                } else if (TextUtils.isEmpty(phoneNumber.getText().toString())){
                    phoneNumber.setError("请先输入您的手机号码");
                    phoneNumber.requestFocus();
                }else {
                    sendYanZhengMaPresenter.getBeforLoginValiDatePhone(this,mobile,yanzhengcode);
                }
                break;
        }
    }


    @Override
    public void onSendYanZhengMaSuccess(SendValidateBeforLoginBean sendValidateBeforLoginBean) {
        if (sendValidateBeforLoginBean.getCode() == 0) {
            LogUtils.i("验证码",sendValidateBeforLoginBean.getCode()+"");
            timerUtils = new TimerUtils(huoqvYanzhengma, 60000, 1000);
            timerUtils.start();
        } else {
            phoneNumber.setError("手机号码不存在，请重新输入");
            phoneNumber.requestFocus();
        }
    }

    @Override
    public void onSendYanZhengMaError(String msg) {
        ToastUtils.showToast("验证码发送失败，请稍后重试");
    }

    @Override
    public void onBeforLoginValiDatePhoneBeforLoginValiDatePhoneSuccess(BeforLoginValiDatePhoneBean beforLoginValiDatePhoneBean) {
        if (beforLoginValiDatePhoneBean.getCode() == 0) {
            content = beforLoginValiDatePhoneBean.getContent().toString();
            Intent intent = new Intent(WangJiActivity.this, XiuGaiActivity.class);
            intent.putExtra("content", content);
            startActivity(intent);
        } else {
            yanzhengma.setError("验证码错误，请重新输入");
            yanzhengma.requestFocus();
        }
    }

    @Override
    public void onBeforLoginValiDatePhoneError(String msg) {
        ToastUtils.showToast("验证码获取失败，请稍后重新获取");
    }


    @Override
    protected SendValidateBeforLoginPresenterImpl initInject() {
        activityComponent.inject(this);
        return sendYanZhengMaPresenter;
    }

}
