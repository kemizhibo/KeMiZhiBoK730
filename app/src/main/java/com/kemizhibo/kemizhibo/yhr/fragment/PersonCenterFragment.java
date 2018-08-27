package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.PersonCenterBeiShouKeJiLuActivity;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.ChangePhoneActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterBianJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterFanKuiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterGuanLiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSheZhiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterShouCangActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.TakePhotoActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.GetUserPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
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

public class PersonCenterFragment extends BaseMvpFragment<GetUserPresenterImpl> implements GetUserView {
    @Inject
    public GetUserPresenterImpl getUserPresenter;
    @BindView(R.id.guanli_layout)
    LinearLayout guanliLayout;
    private BottomSheetDialog dialog;
    @BindView(R.id.person_bianji_butn)
    ImageView personBianjiButn;
    @BindView(R.id.shoucang_layout)
    LinearLayout shoucangLayout;
    @BindView(R.id.liulan_layout)
    LinearLayout liulanLayout;
    /*@BindView(R.id.sucai_layout)
    LinearLayout sucaiLayout;*/
    @BindView(R.id.jilu_layout)
    LinearLayout jiluLayout;
    @BindView(R.id.fankui_layout)
    LinearLayout fankuiLayout;
    /* @BindView(R.id.clear_layout)
     LinearLayout clearLayout;*/
    @BindView(R.id.shehzi_layout)
    LinearLayout shehziLayout;
    private SharedPreferences sp;
    private String token;
    private GetUserBean.ContentBean userData;
    private TextView qingchuhuancun_txt;
    private TextView school;
    private TextView grade;
    private TextView typr;
    private SimpleDraweeView simpleDraweeView;
    private Intent intent;
    private Bundle bundle;
    private int code;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public void onResume() {
        super.onResume();
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(mActivity, "Bearer " + token);
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity, R.layout.fragment_personcenter);
        ButterKnife.bind(this, view);
        school = view.findViewById(R.id.person_school_name);
        grade = view.findViewById(R.id.person_student_grade);
        typr = view.findViewById(R.id.person_student_type);
        simpleDraweeView = view.findViewById(R.id.person_touxiang);
        /*DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(userData.getPicImg())
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(controller);
        if (TextUtils.isEmpty(userData.getSchool().toString())){
            school.setText("");
        }else {
            school.setText(userData.getSchool().toString());
        }
        if (TextUtils.isEmpty(userData.getGrade().toString())){
            grade.setText("");
        }else {
            grade.setText(userData.getGrade().toString());
        }
        if (TextUtils.isEmpty(userData.getSubject().toString())){
            typr.setText("");
        }else {
            typr.setText(userData.getSubject().toString());
        }*/
        return view;
    }


    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(mActivity, "Bearer " + token);
    }


    @Override
    public void onUserSuccess(GetUserBean getUserBean) {
        setState(LoadingPager.LoadResult.success);
        code = getUserBean.getCode();
        userData = getUserBean.getContent();
    }

    @Override
    public void onUserError(String msg) {
        setState(LoadingPager.LoadResult.error);
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

    private void initDialogToLogin() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        AlertDialog dialog=builder
                .setView(R.layout.alertdialog_login)
                .setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

    @OnClick({R.id.person_bianji_butn, R.id.person_touxiang, R.id.shoucang_layout, R.id.liulan_layout, R.id.jilu_layout, R.id.fankui_layout, R.id.shehzi_layout,R.id.guanli_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_bianji_butn:
                if (code==0){
                    startActivity(new Intent(getActivity(), PersonCenterBianJiActivity.class));
                }else {
                    initDialogToLogin();
                }
                break;
            case R.id.person_touxiang:
                //点击跳转
                intent = new Intent(getActivity().getApplicationContext(), TakePhotoActivity.class);
                bundle = new Bundle();
                bundle.putString("photo", String.valueOf(userData.getPicImg()));
                intent.putExtras(bundle);
                //这里一定要获取到所在Activity再startActivity()；
                getActivity().startActivity(intent);
                break;
            case R.id.shoucang_layout:
                startActivity(new Intent(getActivity(), PersonCenterShouCangActivity.class));
                break;
            case R.id.liulan_layout:
                startActivity(new Intent(getActivity(), PersonCenterLiuLanActivity.class));
                break;
            /*case R.id.sucai_layout:
                startActivity(new Intent(getActivity(), PersonCenterSuCaiActivity.class));
                break;*/
            case R.id.jilu_layout:
                startActivity(new Intent(getActivity(), PersonCenterBeiShouKeJiLuActivity.class));
                PreferencesUtils.saveIntValue(Constants.ROLE_ID, Constants.CHILD_ROLE_ID, getActivity());
                break;
            case R.id.guanli_layout:
                startActivity(new Intent(getActivity(), PersonCenterBeiShouKeJiLuActivity.class));
                PreferencesUtils.saveIntValue(Constants.ROLE_ID, Constants.MANAGER_ROLE_ID, getActivity());
                break;
            case R.id.fankui_layout:
                startActivity(new Intent(getActivity(), PersonCenterFanKuiActivity.class));
                break;
            case R.id.shehzi_layout:
                startActivity(new Intent(getActivity(), PersonCenterSheZhiActivity.class));
                break;
        }
    }
}
