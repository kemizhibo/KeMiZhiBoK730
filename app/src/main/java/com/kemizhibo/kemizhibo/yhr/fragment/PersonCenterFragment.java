package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.PersonCenterBeiShouKeJiLuActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterBianJiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterFanKuiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSheZhiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterShouCangActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSuCaiActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.TakePhotoActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.GetUserPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.DataClearManager;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Date: 2018/4/27
 * Describe: 个人中心
 */

public class PersonCenterFragment extends BaseMvpFragment<GetUserPresenterImpl> implements GetUserView {
    @Inject
    public GetUserPresenterImpl getUserPresenter;
    private BottomSheetDialog dialog;
    @BindView(R.id.person_bianji_butn)
    ImageView personBianjiButn;
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
    private TextView qingchuhuancun_txt;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity,R.layout.fragment_personcenter);
        ButterKnife.bind(this, view);
        TextView school = view.findViewById(R.id.person_school_name);
        TextView grade = view.findViewById(R.id.person_student_grade);
        TextView typr = view.findViewById(R.id.person_student_type);
        qingchuhuancun_txt = view.findViewById(R.id.qingchuhuancun_txt);
        SimpleDraweeView simpleDraweeView = view.findViewById(R.id.person_touxiang);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(userData.getPicImg())
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(controller);
        school.setText(userData.getSchool());
        grade.setText(userData.getGrade());
        typr.setText(userData.getSubject());
        //计算应用缓存大小
        try {
            String totalCacheSize = DataClearManager.getTotalCacheSize(getContext());
            qingchuhuancun_txt.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        getUserPresenter.getUserData(mActivity, "Bearer " + token);
    }
    /*@Override
    public void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(200);
                setState(LoadingPager.LoadResult.success);
                sp = getContext().getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                getUserPresenter.getUserData(mActivity, "Bearer " + token);
            }
        }).start();
    }*/

    @Override
    public void onUserSuccess(GetUserBean getUserBean) {
        setState(LoadingPager.LoadResult.success);
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


    @OnClick({R.id.person_bianji_butn, R.id.person_touxiang, R.id.shoucang_layout, R.id.liulan_layout, R.id.sucai_layout, R.id.jilu_layout, R.id.fankui_layout, R.id.clear_layout, R.id.shehzi_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_bianji_butn:
                startActivity(new Intent(getActivity(), PersonCenterBianJiActivity.class));
                break;
            case R.id.person_touxiang:
                //点击跳转
                startActivity(new Intent(getActivity(), TakePhotoActivity.class));
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
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("确定要清空本地缓存吗？");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DataClearManager.cleanInternalCache(mActivity);
                            qingchuhuancun_txt.setText("0.0KB");
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shehzi_layout:
                startActivity(new Intent(getActivity(), PersonCenterSheZhiActivity.class));
                break;
        }
    }
}
