package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.MainActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.GetUserPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

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
    private String personSchoolNameEdittextHint;
    private String personLianxirenEdittextHint;
    private String personTypeEdittextHint;
    private String personGradleEdittextHint;
    private String personAddressEdittextHint;
    private String personIdcardEdittextHint;
    private String personEmailEdittextHint;
    private String mobile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bian_ji;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    @Override
    protected void getData() {
        super.getData();
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
        userBean = getUserBean.getContent();
        //g给输入框设置默认值
        school = (String) getUserBean.getContent().getSchool();
        realname = getUserBean.getContent().getRealName();
        subject = (String) getUserBean.getContent().getSubject();
        grade = getUserBean.getContent().getGrade();
        address = getUserBean.getContent().getAddress();
        idcard = (String) getUserBean.getContent().getIdCardNo();
        email = (String) getUserBean.getContent().getEmail();
        mobile = getUserBean.getContent().getMobile();
        //手机号码
        personPhoneEdittext.setText(mobile);
        //学校
        if (school.equals(null) || school.equals("")) {
            school = "";
        } else {
            personSchoolNameEdittext.setHint(school);
        }
        //名字
        if (realname.equals(null) || realname.equals("")) {
            realname = "";
        } else {
            personLianxirenEdittext.setHint(realname);
        }
        //学科
        if (subject.equals(null) || subject.equals("")) {
            subject = "";
        } else {
            personTypeEdittext.setHint(subject);
        }
        //年级
        if (grade.equals(null) || grade.equals("")) {
            grade = "";
        } else {
            personGradleEdittext.setHint(grade);
        }
        //地址
        if (address.equals(null) || address.equals("")) {
            address = "";
        } else {
            personAddressEdittext.setHint(address);
        }
        //身份证号码
        if (idcard.equals(null) || idcard.equals("")) {
            idcard = "";
        } else {
            personIdcardEdittext.setHint(idcard);
        }
        //邮箱
        if (email.equals(null) || email.equals("")) {
            email = "";
        } else {
            personEmailEdittext.setHint(email);
        }
    }

    @Override
    public void onUserError(String msg) {

    }

    //更改用户信息
    @Override
    public void onChangeUserSuccess(ChangeUserBean changeUserBean) {
        if (changeUserBean.getCode() == 0) {
            ToastUtils.showToast(changeUserBean.getMessage() + 0);
            PersonCenterBianJiActivity.this.finish();
        } else {
            ToastUtils.showToast(changeUserBean.getMessage() + 1);
        }
    }

    @Override
    public void onChangeUserError(String msg) {

    }

    @Override
    protected GetUserPresenterImpl initInject() {
        activityComponent.inject(this);
        return getUserPresenter;
    }

    /*@OnFocusChange({R.id.person_school_name_edittext, R.id.person_lianxiren_edittext, R.id.person_type_edittext, R.id.person_gradle_edittext, R.id.person_address_edittext, R.id.person_idcard_edittext, R.id.person_email_edittext, R.id.person_phone_edittext, R.id.person_change_phone})
    public void onViewClicked(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.person_school_name_edittext:
                if (hasFocus) {
                    personSchoolNameEdittext.setHint("");
                    personSchoolNameEdittext.setCursorVisible(true);
                } else {
                    personSchoolNameEdittext.setHint(school);
                }
                break;
            case R.id.person_lianxiren_edittext:
                if (hasFocus) {
                    personLianxirenEdittext.setHint("");
                    personLianxirenEdittext.setCursorVisible(true);
                    personLianxirenEdittextHint = realname;
                } else {
                    personLianxirenEdittext.setHint(realname);
                }
                break;
            case R.id.person_type_edittext:
                if (hasFocus) {
                    personTypeEdittext.setHint("");
                    personTypeEdittext.setCursorVisible(true);
                    personTypeEdittextHint = subject;
                } else {
                    personTypeEdittext.setHint(subject);
                }
                break;
            case R.id.person_gradle_edittext:
                if (hasFocus) {
                    personGradleEdittext.setHint("");
                    personGradleEdittext.setCursorVisible(true);
                    personGradleEdittextHint = grade;
                } else {
                    personGradleEdittext.setHint(grade);
                }
                break;
            case R.id.person_address_edittext:
                if (hasFocus) {
                    personAddressEdittext.setHint("");
                    personAddressEdittext.setCursorVisible(true);
                    personAddressEdittextHint = address;
                } else {
                    personAddressEdittext.setHint(address);
                }
                break;
            case R.id.person_idcard_edittext:
                if (hasFocus) {
                    personIdcardEdittext.setHint("");
                    personIdcardEdittext.setCursorVisible(true);
                    personIdcardEdittextHint = idcard;
                } else {
                    personIdcardEdittext.setHint(idcard);
                }
                break;
            case R.id.person_email_edittext:
                if (hasFocus) {
                    personEmailEdittext.setHint("");
                    personEmailEdittext.setCursorVisible(true);
                } else {
                    personEmailEdittext.setHint(email);
                }
                break;
        }
    }*/

    @OnClick({R.id.person_change_phone, R.id.finish_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_change_phone:
                //回传值
                Intent intent = new Intent(this,ChangePhoneActivity.class);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
                //startActivityForResult(intent,1000);
                //startActivity(new Intent(this, ChangePhoneActivity.class));
                break;
            case R.id.finish_butn:
                /*if (personSchoolNameEdittextHint==null){
                    personSchoolNameEdittextHint = school;
                }else {
                    personSchoolNameEdittextHint = personSchoolNameEdittext.getText().toString();
                }
                if (personLianxirenEdittextHint==null){
                    personLianxirenEdittextHint = realname;
                }else {
                    personLianxirenEdittextHint = personLianxirenEdittext.getText().toString();
                }
                if (personTypeEdittextHint==null){
                    personTypeEdittextHint = subject;
                }else {
                    personTypeEdittextHint = personTypeEdittext.getText().toString();
                }
                if (personGradleEdittextHint==null){
                    personGradleEdittextHint = grade;
                }else {
                    personGradleEdittextHint = personGradleEdittext.getHint().toString();
                }
                if (personAddressEdittextHint==null){
                    personAddressEdittextHint = address;
                }else {
                    personAddressEdittextHint = personAddressEdittext.getText().toString();
                }
                if (personIdcardEdittextHint==null){
                    personIdcardEdittextHint = idcard;
                }else {
                    personIdcardEdittextHint = personIdcardEdittext.getText().toString();
                }
                if (personEmailEdittextHint==null){
                    personEmailEdittextHint = email;
                }else {
                    personEmailEdittextHint = personEmailEdittext.getText().toString();
                }*/
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                //getUserPresenter.getChangeUserData(this, "Bearer " + token, personSchoolNameEdittextHint, personLianxirenEdittextHint, personGradleEdittextHint, personTypeEdittextHint, personIdcardEdittextHint, personEmailEdittextHint, personAddressEdittextHint);
                getUserPresenter.getChangeUserData(this, "Bearer " + token, school, realname, grade,subject, idcard, email, address);
                break;
        }
    }

}
