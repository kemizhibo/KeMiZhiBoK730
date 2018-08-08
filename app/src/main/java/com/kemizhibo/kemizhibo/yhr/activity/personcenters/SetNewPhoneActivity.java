package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.FinishActivity;
import com.kemizhibo.kemizhibo.yhr.activity.logins.WangJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.logins.XiuGaiActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.SendYanZhengMaPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.PhoneFormatCheckUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TimerUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SetNewPhoneActivity extends BaseMvpActivity<SendYanZhengMaPresenterImpl> implements SendYanZhengMaView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.put_phone_num)
    EditText putPhoneNum;
    @BindView(R.id.put_yanzhengma)
    EditText putYanzhengma;
    @BindView(R.id.get_yanzhengma)
    Button getYanzhengma;
    @BindView(R.id.commit_butn)
    Button commitButn;
    private TimerUtils timerUtils;
    @Inject
    public SendYanZhengMaPresenterImpl sendYanZhengMaPresenter;
    private SharedPreferences sp;
    private String token;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_new_phone;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    @Override
    protected void getData() {
        super.getData();
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("修改手机号");
        publicTitleBarRoot.buildFinish();
    }


    @OnClick({R.id.get_yanzhengma, R.id.commit_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_yanzhengma:
                if (TextUtils.isEmpty(putPhoneNum.getText().toString())){
                    putPhoneNum.setError("请先输入您的手机号码");
                    //ToastUtils.showToast("请先输入您的手机号码");
                }
                else if (!PhoneFormatCheckUtils.isChinaPhoneLegal(putPhoneNum.getText().toString())&&!PhoneFormatCheckUtils.isHKPhoneLegal(putPhoneNum.getText().toString())&&!PhoneFormatCheckUtils.isPhoneLegal(putPhoneNum.getText().toString())){
                    putPhoneNum.setError("请输入正确的手机号码");
                    //ToastUtils.showToast("请输入正确的手机号码");
                }
                else{
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    sendYanZhengMaPresenter.getSendYanZhengMaData(this,"2","Bearer "+ token,putPhoneNum.getText().toString());
                }
                break;
            case R.id.commit_butn:
                if (TextUtils.isEmpty(putYanzhengma.getText().toString().trim())){
                    ToastUtils.showToast("验证码不能为空");
                }else{
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    sendYanZhengMaPresenter.getNewPhoneData(this,"Bearer "+token,putPhoneNum.getText().toString().trim(),putYanzhengma.getText().toString().trim());
                    LogUtils.i("0000000000000",token+"++++"+putPhoneNum.getText().toString()+"++++"+putYanzhengma.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void onSendYanZhengMaSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
        if (sendYanZhengMaBean.getCode()==0){
            timerUtils = new TimerUtils(getYanzhengma,60000,1000);
            timerUtils.start();
        }else {
            ToastUtils.showToast("短信发送失败，请重试");
        }
    }

    @Override
    public void onSendYanZhengMaError(String msg) {

    }
    //验证旧手机号
    @Override
    public void onOldPhoneSuccess(SendYanZhengMaBean sendYanZhengMaBean) {

    }

    @Override
    public void onOldPhoneError(String msg) {

    }
    //验证新手机号
    @Override
    public void onNewPhoneSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
        if (sendYanZhengMaBean.getCode()==0){
            Intent intent = new Intent(this, ChangePhoneFinishActivity.class);
            startActivity(intent);
        }else {
            ToastUtils.showToast("验证码错误，请重新输入");
        }
    }

    @Override
    public void onNewPhoneError(String msg) {

    }

    @Override
    protected SendYanZhengMaPresenterImpl initInject() {
        activityComponent.inject(this);
        return sendYanZhengMaPresenter;
    }
}
