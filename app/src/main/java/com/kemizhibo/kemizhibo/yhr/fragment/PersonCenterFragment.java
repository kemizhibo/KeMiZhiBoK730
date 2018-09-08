package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.PersonCenterBeiShouKeJiLuActivity;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.MainActivity;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterBianJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterFanKuiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSheZhiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterShouCangActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.TakePhotoActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.GetUserPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
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
    @BindView(R.id.person_bianji_butn)
    ImageView personBianjiButn;
    @BindView(R.id.shoucang_layout)
    LinearLayout shoucangLayout;
    @BindView(R.id.liulan_layout)
    LinearLayout liulanLayout;
    @BindView(R.id.jilu_layout)
    LinearLayout jiluLayout;
    @BindView(R.id.fankui_layout)
    LinearLayout fankuiLayout;
    @BindView(R.id.shehzi_layout)
    LinearLayout shehziLayout;
    @BindView(R.id.person_touxiang)
    SimpleDraweeView personTouxiang;
    @BindView(R.id.person_school_name)
    TextView personSchoolName;
    @BindView(R.id.person_student_grade)
    TextView personStudentGrade;
    @BindView(R.id.person_student_type)
    TextView personStudentType;
    private SharedPreferences sp;
    private String token;
    private GetUserBean.ContentBean userData;
    private Intent intent;
    private Bundle bundle;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(mActivity, "Bearer " + token);
        LogUtils.i("走这里了没有1", "00000000000000000");
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity, R.layout.fragment_personcenter);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(mActivity, "Bearer " + token);
    }

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void onUserSuccess(GetUserBean getUserBean) {
        if (getUserBean.getCode() == 0) {
            setState(LoadingPager.LoadResult.success);
            userData = getUserBean.getContent();
            if (!TextUtils.isEmpty(userData.getSchool())){
                personSchoolName.setText(userData.getSchool());
            }if (!TextUtils.isEmpty(userData.getGrade())){
                personStudentGrade.setText(userData.getGrade());
            }if (!TextUtils.isEmpty(userData.getSubject())){
                personStudentType.setText(userData.getSubject());
            }if (!TextUtils.isEmpty(userData.getPicImg())){
                personTouxiang.setImageURI(userData.getPicImg());
            }
            int roleId = userData.getRoleId();
            if (roleId!=0&&!TextUtils.isEmpty(roleId+"")){
                if (roleId == 8) {
                    guanliLayout.setVisibility(View.VISIBLE);
                }else {
                    guanliLayout.setVisibility(View.GONE);
                }
            }
        } else {
            initDialogToLogin();
        }
    }

    @Override
    public void onUserError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onChangeUserSuccess(ChangeUserBean changeUserBean) {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onChangeUserError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected GetUserPresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return getUserPresenter;
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getContext());
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                } else {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
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

    @OnClick({R.id.person_bianji_butn, R.id.person_touxiang, R.id.shoucang_layout, R.id.liulan_layout, R.id.jilu_layout, R.id.fankui_layout, R.id.shehzi_layout, R.id.guanli_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_bianji_butn:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    startActivity(new Intent(getActivity(), PersonCenterBianJiActivity.class));
                }
                break;
            case R.id.person_touxiang:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    //点击跳转
                    intent = new Intent(getActivity().getApplicationContext(), TakePhotoActivity.class);
                    bundle = new Bundle();
                    bundle.putString("photo", String.valueOf(userData.getPicImg()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    getActivity().startActivity(intent);
                }
                break;
            case R.id.shoucang_layout:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    startActivity(new Intent(getActivity(), PersonCenterShouCangActivity.class));
                }
                break;
            case R.id.liulan_layout:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    startActivity(new Intent(getActivity(), PersonCenterLiuLanActivity.class));
                }
                break;
            /*case R.id.sucai_layout:
                startActivity(new Intent(getActivity(), PersonCenterSuCaiActivity.class));
                break;*/
            case R.id.jilu_layout:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    startActivity(new Intent(getActivity(), PersonCenterBeiShouKeJiLuActivity.class));
                    PreferencesUtils.saveIntValue(Constants.ROLE_ID, Constants.CHILD_ROLE_ID, getActivity());
                }
                break;
            case R.id.guanli_layout:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    startActivity(new Intent(getActivity(), PersonCenterBeiShouKeJiLuActivity.class));
                    PreferencesUtils.saveIntValue(Constants.ROLE_ID, Constants.MANAGER_ROLE_ID, getActivity());
                }
                break;
            case R.id.fankui_layout:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    startActivity(new Intent(getActivity(), PersonCenterFanKuiActivity.class));
                }
                break;
            case R.id.shehzi_layout:
                if (NoFastClickUtils.isFastClick()) {
                } else {
                   // startActivity(new Intent(getActivity(), PersonCenterSheZhiActivity.class));
                    startActivityForResult(new Intent(getActivity(), PersonCenterSheZhiActivity.class),MyApplication.YINGXIANG_TO_PICK_req);
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MyApplication.YINGXIANG_TO_PICK_req&&resultCode==MyApplication.YINGXIANG_TO_PICK_res){
            /*Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            finish();*/
            try{
                MainActivity activity = (MainActivity) getActivity();
                activity.exit();
            }catch (Exception e){}
        }
    }
}
