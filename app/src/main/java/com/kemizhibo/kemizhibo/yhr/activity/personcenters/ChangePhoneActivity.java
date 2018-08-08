package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.WangJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.logins.XiuGaiActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.FeedBackPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.SendYanZhengMaPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.PhoneFormatCheckUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TimerUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.FeedBackView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseMvpActivity<SendYanZhengMaPresenterImpl> implements SendYanZhengMaView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.person_change_old_phone)
    TextView personChangeOldPhone;
    @BindView(R.id.person_change_edittext)
    EditText personChangeEdittext;
    @BindView(R.id.person_change_send_yanzhengma)
    Button personChangeSendYanzhengma;
    @BindView(R.id.person_change_next_butn)
    Button personChangeNextButn;

    @Inject
    public SendYanZhengMaPresenterImpl sendYanZhengMaPresenter;
    private SharedPreferences sp;
    private String token;
    private String mobile;
    private TimerUtils timerUtils;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        //获取传过来的值
        mobile = getIntent().getStringExtra("mobile");
        /*String res =editText2.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("result",res);
        setResult(1001,intent1);
        finish();*/
    }

    @Override
    protected void getData() {
        super.getData();
        personChangeOldPhone.setText("您绑定的手机号码是"+mobile+"，请点击获取验证码");
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

    @OnClick({R.id.person_change_send_yanzhengma, R.id.person_change_next_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_change_send_yanzhengma:
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                sendYanZhengMaPresenter.getSendYanZhengMaData(this,"1","Bearer "+ token,mobile);
                break;
            case R.id.person_change_next_butn:
                if (TextUtils.isEmpty(personChangeEdittext.getText().toString().trim())){
                    ToastUtils.showToast("验证码不能为空");
                }else{
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    sendYanZhengMaPresenter.getOldPhoneData(this,"Bearer "+token,mobile,personChangeEdittext.getText().toString());
                }
                break;
        }
    }

    @Override
    public void onSendYanZhengMaSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
        if (sendYanZhengMaBean.getCode()==0){
            timerUtils = new TimerUtils(personChangeSendYanzhengma,60000,1000);
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
        if (sendYanZhengMaBean.getCode()==0){
            Intent intent=new Intent(ChangePhoneActivity.this, SetNewPhoneActivity.class);
            startActivity(intent);
        }else {
            ToastUtils.showToast("验证码错误，请重新输入");
        }
    }

    @Override
    public void onOldPhoneError(String msg) {

    }
    //验证新手机号
    @Override
    public void onNewPhoneSuccess(SendYanZhengMaBean sendYanZhengMaBean) {

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
