package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterBeiShouKeJiLuActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterBianJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterFanKuiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSheZhiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterShouCangActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSuCaiActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.GetUserPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Date: 2018/4/27
 * Describe: 个人中心
 */

public class PersonalCenterFragment extends BaseMvpFragment<GetUserPresenterImpl> implements GetUserView {
    @Inject
    public GetUserPresenterImpl getUserPresenter;


    @BindView(R.id.person_bianji_butn)
    ImageView personBianjiButn;
    @BindView(R.id.person_touxiang)
    SimpleDraweeView personTouxiang;
    @BindView(R.id.person_school_name)
    TextView personSchoolName;
    @BindView(R.id.person_student_grade)
    TextView personStudentGrade;
    @BindView(R.id.person_student_type)
    TextView personStudentType;
    @BindView(R.id.shoucang_layout)
    LinearLayout shoucangLayout;
    @BindView(R.id.liulan_layout)
    LinearLayout liulanLayout;
    @BindView(R.id.sucai_layout)
    LinearLayout sucaiLayout;
    @BindView(R.id.jilu_layout)
    LinearLayout jiluLayout;
    @BindView(R.id.fankui_layout)
    LinearLayout fankuiLayout;
    @BindView(R.id.clear_layout)
    LinearLayout clearLayout;
    @BindView(R.id.shehzi_layout)
    LinearLayout shehziLayout;
    private SharedPreferences sp;
    private String token;
    private GetUserBean.ContentBean userData;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_personcenter);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void load() {
        sp = mActivity.getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(mActivity, "Bearer " + token);
    }


    @Override
    public void onUserSuccess(GetUserBean getUserBean) {
        setState(LoadingPager.LoadResult.success);
        //TextView personSchoolName = getActivity().findViewById(R.id.person_school_name);
        userData = getUserBean.getContent();
        //text.setText(userData.getIdCardNo());
        LogUtils.i("00000000000000", "123"+userData.getIdCardNo());
        //LogUtils.i("00000000000000", "123"+userData.toString());
    }

    @Override
    public void onUserError(String msg) {
        setState(LoadingPager.LoadResult.error);
        LogUtils.i("0000000000000000", "456" + msg);
    }

    @Override
    public void onChangeUserSuccess(ChangeUserBean changeUserBean) {

    }

    @Override
    public void onChangeUserError(String msg) {

    }

    @Override
    protected GetUserPresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return getUserPresenter;
    }


    @OnClick({R.id.person_bianji_butn, R.id.person_touxiang, R.id.shoucang_layout, R.id.liulan_layout, R.id.sucai_layout, R.id.jilu_layout, R.id.fankui_layout, R.id.clear_layout, R.id.shehzi_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_bianji_butn:
                startActivity(new Intent(getActivity(), PersonCenterBianJiActivity.class));
                break;
            case R.id.person_touxiang:
                break;
            case R.id.shoucang_layout:
                startActivity(new Intent(getActivity(), PersonCenterShouCangActivity.class));
                break;
            case R.id.liulan_layout:
                startActivity(new Intent(getActivity(), PersonCenterLiuLanActivity.class));
                break;
            case R.id.sucai_layout:
                startActivity(new Intent(getActivity(), PersonCenterSuCaiActivity.class));
                break;
            case R.id.jilu_layout:
                startActivity(new Intent(getActivity(), PersonCenterBeiShouKeJiLuActivity.class));
                break;
            case R.id.fankui_layout:
                startActivity(new Intent(getActivity(), PersonCenterFanKuiActivity.class));
                break;
            case R.id.clear_layout:
                break;
            case R.id.shehzi_layout:
                startActivity(new Intent(getActivity(), PersonCenterSheZhiActivity.class));
                break;
        }
    }
}
