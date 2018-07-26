package com.kemizhibo.kemizhibo.yhr.activity.logins;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.GetMssBean;
import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;
import com.kemizhibo.kemizhibo.yhr.presenter.GetMssPresenter;
import com.kemizhibo.kemizhibo.yhr.presenter.MessagePresenter;
import com.kemizhibo.kemizhibo.yhr.utils.PhoneFormatCheckUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TimerUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.GetMssView;
import com.kemizhibo.kemizhibo.yhr.view.MessageIView;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class WangJiActivity extends BaseActivity implements GetMssView,MessageIView {

    @BindView(R.id.yanzheng_back)
    ImageView yanzhengBack;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.yanzhengma)
    EditText yanzhengma;
    @BindView(R.id.huoqv_yanzhengma)
    Button huoqvYanzhengma;
    @BindView(R.id.next)
    Button next;
    private GetMssPresenter getMssPresenter;
    private MessagePresenter messagePresenter;
    private TimerUtils timerUtils;
    private String token;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wnag_ji;
    }

    @Override
    protected void initData() {
        getMssPresenter = new GetMssPresenter(this);
        messagePresenter = new MessagePresenter(this);
    }

    @Override
    public void success(GetMssBean getMssBean) {
        timerUtils = new TimerUtils(huoqvYanzhengma,60000,1000);
        timerUtils.start();
    }

    @Override
    public void error(String e) {
        ToastUtils.showToast(e);
    }

    @OnClick({R.id.yanzheng_back, R.id.huoqv_yanzhengma, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yanzheng_back:
                startActivity(new Intent(WangJiActivity.this, LoginActivity.class));
                break;
            case R.id.huoqv_yanzhengma:
                if (TextUtils.isEmpty(phoneNumber.getText().toString())){
                    phoneNumber.setError("请先输入您的手机号码");
                    //ToastUtils.showToast("请先输入您的手机号码");
                }
                else if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phoneNumber.getText().toString())&&!PhoneFormatCheckUtils.isHKPhoneLegal(phoneNumber.getText().toString())&&!PhoneFormatCheckUtils.isPhoneLegal(phoneNumber.getText().toString())){
                    phoneNumber.setError("请输入正确的手机号码");
                    //ToastUtils.showToast("请输入正确的手机号码");
                }
                else{
                    getMssPresenter.getDatas();
                }
                break;
            case R.id.next:
                if (TextUtils.isEmpty(yanzhengma.getText().toString().trim())){
                    ToastUtils.showToast("验证码不能为空");
                }else{
                    messagePresenter.getDatas();
                }
                break;
        }
    }

    @Override
    public void successMess(MessageBean messageBean) {
        token = messageBean.getContent().toString();
        ToastUtils.showToast(messageBean.getMessage());
        Intent intent = new Intent(WangJiActivity.this, XiuGaiActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    @Override
    public void errorMess(String e) {
        ToastUtils.showToast(e);
    }

    @Override
    public String mobile() {
        return phoneNumber.getText().toString();
    }

    @Override
    public String mobileCode() {
        return yanzhengma.getText().toString();
    }

    @Override
    public String getPhone() {
        return phoneNumber.getText().toString();
    }
}
