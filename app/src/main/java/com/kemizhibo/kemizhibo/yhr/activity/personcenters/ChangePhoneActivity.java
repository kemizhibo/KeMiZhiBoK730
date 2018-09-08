package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.SendYanZhengMaPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TimerUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
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
    }

    @Override
    protected void getData() {
        super.getData();
        personChangeOldPhone.setText("您绑定的手机号码是" + mobile + "，请点击获取验证码");
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
                if (NoFastClickUtils.isFastClick()) {
                }{
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                sendYanZhengMaPresenter.getSendYanZhengMaData(this, "1", "Bearer " + token, mobile);
            }

                break;
            case R.id.person_change_next_butn:
                if (TextUtils.isEmpty(personChangeEdittext.getText().toString().trim())) {
                    personChangeEdittext.setError("请输入您收到的验证码");
                    personChangeEdittext.requestFocus();
                } else {
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    sendYanZhengMaPresenter.getOldPhoneData(this, "Bearer " + token, mobile, personChangeEdittext.getText().toString());
                }
                break;
        }
    }

    @Override
    public void onSendYanZhengMaSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
        LogUtils.i("验证码",sendYanZhengMaBean.getCode()+"");
        if (sendYanZhengMaBean.getCode() == 0) {
            timerUtils = new TimerUtils(personChangeSendYanzhengma, 60000, 1000);
            timerUtils.start();
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
                                    Intent intent = new Intent(ChangePhoneActivity.this, LoginActivity.class);
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
    public void onSendYanZhengMaError(String msg) {
        ToastUtils.showToast("短信发送失败，请重试");
    }

    //验证旧手机号
    @Override
    public void onOldPhoneSuccess(SendYanZhengMaBean sendYanZhengMaBean) {
        if (sendYanZhengMaBean.getCode() == 0) {
            Intent intent = new Intent(ChangePhoneActivity.this, SetNewPhoneActivity.class);
            startActivity(intent);
        } else if (sendYanZhengMaBean.getCode()==500){
            personChangeEdittext.setError("验证码错误，请重新输入");
            personChangeEdittext.requestFocus();
        }else {
            initDialogToLogin();
        }
    }

    @Override
    public void onOldPhoneError(String msg) {
        ToastUtils.showToast("验证码错误，请重新输入");
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
