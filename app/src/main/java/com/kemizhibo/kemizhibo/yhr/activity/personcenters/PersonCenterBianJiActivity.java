package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentLoading;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.GetUserPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.Validator;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class PersonCenterBianJiActivity extends BaseMvpActivity<GetUserPresenterImpl> implements GetUserView {

    @Inject
    public GetUserPresenterImpl getUserPresenter;

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.person_change_phone)
    TextView personChangePhone;
    @BindView(R.id.person_school_name_edittext)
    EditText personSchoolNameEdittext;
    @BindView(R.id.person_lianxiren_edittext)
    EditText personLianxirenEdittext;
    @BindView(R.id.person_type_edittext)
    EditText personTypeEdittext;
    @BindView(R.id.person_gradle_edittext)
    EditText personGradleEdittext;
    @BindView(R.id.person_address_edittext)
    EditText personAddressEdittext;
    @BindView(R.id.person_idcard_edittext)
    EditText personIdcardEdittext;
    @BindView(R.id.person_email_edittext)
    EditText personEmailEdittext;
    @BindView(R.id.person_phone_edittext)
    TextView personPhoneEdittext;
    @BindView(R.id.finish_butn)
    Button finishButn;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;


    private GetUserBean.ContentBean userBean;
    private String token;
    private String school;
    private String realname;
    private String subject;
    private String grade;
    private String address;
    private String idcard;
    private String email;
    private SharedPreferences sp;
    private String mobile;
    private boolean isIdCard;
    private boolean isemail;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                PersonCenterBianJiActivity.this.finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bian_ji;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    @Override
    protected void getData() {
        super.getData();
        frameLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FramgmentLoading()).commit();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(this, "Bearer " + token);
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("修改资料");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    public void onUserSuccess(GetUserBean getUserBean) {
        if (getUserBean.getCode()==0){
            userBean = getUserBean.getContent();
            //g给输入框设置默认值
            if (TextUtils.isEmpty(getUserBean.getContent().getSchool())||getUserBean.getContent().getSchool()==null){
                personSchoolNameEdittext.setText("");
            }else {
                school = (String) getUserBean.getContent().getSchool();
                //学校
                personSchoolNameEdittext.setText(school);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getRealName())||getUserBean.getContent().getRealName()==null){
                personLianxirenEdittext.setText("");
            }else {
                realname = getUserBean.getContent().getRealName();
                //名字
                personLianxirenEdittext.setText(realname);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getSubject())||getUserBean.getContent().getSubject()==null){
                personTypeEdittext.setText("");
            }else {
                subject = (String) getUserBean.getContent().getSubject();
                //学科
                personTypeEdittext.setText(subject);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getGrade())||getUserBean.getContent().getGrade()==null){
                personGradleEdittext.setText("");
            }else {
                grade = getUserBean.getContent().getGrade();
                //年级
                personGradleEdittext.setText(grade);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getAddress())||getUserBean.getContent().getAddress()==null){
                personAddressEdittext.setText("");
            }else {
                address = getUserBean.getContent().getAddress();
                //地址
                personAddressEdittext.setText(address);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getIdCardNo())||getUserBean.getContent().getIdCardNo()==null){
                personIdcardEdittext.setText("");
            }else {
                idcard = (String) getUserBean.getContent().getIdCardNo();
                //身份证号码
                personIdcardEdittext.setText(idcard);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getEmail())||getUserBean.getContent().getEmail()==null){
                personEmailEdittext.setText("");
            }else {
                email = (String) getUserBean.getContent().getEmail();
                //邮箱
                personEmailEdittext.setText(email);
            }
            if (TextUtils.isEmpty(getUserBean.getContent().getMobile())||getUserBean.getContent().getMobile()==null){
                personPhoneEdittext.setText("");
            }else {
                mobile = getUserBean.getContent().getMobile();
                //手机号码
                personPhoneEdittext.setText(mobile);
            }
            frameLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }else if (getUserBean.getCode()==401||getUserBean.getCode()==801){
            initDialogToLogin();
        }else {

        }
        //Transparent.showErrorMessage(this, "短信发送失败，请重试");
    }

    @Override
    public void onUserError(String msg) {
        LogUtils.i("个人信息",msg);
    }

    //更改用户信息
    @Override
    public void onChangeUserSuccess(ChangeUserBean changeUserBean) {
        if (changeUserBean.getCode() == 0) {
            Transparent.showSuccessMessage(this, "修改成功!");
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
        } else if(changeUserBean.getCode() == 401||changeUserBean.getCode() == 801){
            initDialogToLogin();
        }else {
            ToastUtils.showToast("网络连接中断，请检查您的网络状态");
        }
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                } else {
                                    Intent intent = new Intent(PersonCenterBianJiActivity.this, LoginActivity.class);
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
    public void onChangeUserError(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    protected GetUserPresenterImpl initInject() {
        activityComponent.inject(this);
        return getUserPresenter;
    }


    @OnClick({R.id.person_change_phone, R.id.finish_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_change_phone:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    //回传值
                    Intent intent = new Intent(this, ChangePhoneActivity.class);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                    //startActivityForResult(intent,1000);
                    //startActivity(new Intent(this, ChangePhoneActivity.class));
                }
                break;
            case R.id.finish_butn:
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                school = personSchoolNameEdittext.getText().toString();
                realname = personLianxirenEdittext.getText().toString();
                subject = personTypeEdittext.getText().toString();
                grade = personGradleEdittext.getText().toString();
                address = personAddressEdittext.getText().toString();
                if (!TextUtils.isEmpty(personIdcardEdittext.getText().toString().trim())){
                    isIdCard = Validator.isIDCard(personIdcardEdittext.getText().toString().trim());
                }else {
                    personIdcardEdittext.setError("请在此输入身份证号码");
                    personIdcardEdittext.requestFocus();
                }
                if (TextUtils.isEmpty(personIdcardEdittext.getText().toString().trim())) {
                    personIdcardEdittext.setError("请输入身份证号码");
                    personIdcardEdittext.requestFocus();
                } else if (isIdCard == false) {
                    personIdcardEdittext.setError("请输入正确的身份证号码");
                    personIdcardEdittext.requestFocus();
                } else {
                    idcard = personIdcardEdittext.getText().toString().trim();
                    if (!TextUtils.isEmpty(personEmailEdittext.getText().toString().trim())){
                        isemail = Validator.isEmail(personEmailEdittext.getText().toString().trim());
                    }else {
                        personEmailEdittext.setError("请输入邮箱");
                        personEmailEdittext.requestFocus();
                    }
                    if (TextUtils.isEmpty(personEmailEdittext.getText().toString().trim())) {
                        personEmailEdittext.setError("请在此输入邮箱");
                        personEmailEdittext.requestFocus();
                    } else if (isemail == false) {
                        personEmailEdittext.setError("请输入正确的邮箱");
                        personEmailEdittext.requestFocus();
                    } else {
                        this.email = personEmailEdittext.getText().toString().trim();
                    }
                }

                if (isIdCard == true && isemail == true) {
                    getUserPresenter.getChangeUserData(this, "Bearer " + token, school, realname, grade, subject, idcard, this.email, address);
                }
                break;
        }
    }

}
