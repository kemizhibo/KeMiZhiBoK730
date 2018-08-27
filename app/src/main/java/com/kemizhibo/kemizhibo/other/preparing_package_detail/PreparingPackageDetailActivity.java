package com.kemizhibo.kemizhibo.other.preparing_package_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.kemizhibo.kemizhibo.BuildConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter.PreparingPackageDetailPresenter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter.PreparingPackageDetailPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.MyListView;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailNewAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailOneAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailOtherAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailPlanAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingPackageDetailView;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class PreparingPackageDetailActivity extends BaseActivity implements PreparingPackageDetailView {
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.list_view_one)
    MyListView listViewone;
    @BindView(R.id.list_view_su)
    MyListView listViewsu;
    @BindView(R.id.list_view_shou)
    ListView listViewshou;
    @BindView(R.id.list_view_qi)
    ListView listViewqi;
    @BindView(R.id.none)
    TextView mnone;
    @BindView(R.id.detaile_scrollView)
    ScrollView mdetailescrollView;
    private PreparingPackageDetailPresenter detailPresenter;
    private int courseId;
    Handler mHandler = new Handler();
    //private PreparingDetailAdapter preparingDetailAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preparing_package_detail;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        detailPresenter = new PreparingPackageDetailPresenterImp(this);
       // detailPresenter.getPreparingPackageDetailData();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        Intent intent = getIntent();
        courseId = intent.getIntExtra(Constants.COURSE_ID, 0);
        //courseId = 2832;
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("备课包");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    public String getCourseId() {
        return String.valueOf(courseId);
    }

    @Override
    public Context getCustomContext() {
        return this;
    }

    @OnClick(R.id.make_img)
    public void make(View view) {
        Intent intent = new Intent(this, CommonWebActivity.class);
        intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
        intent.putExtra(Constants.COURSE_ID, courseId);
        startActivity(intent);
    }

    @Override
    public void getPreparingPackageDetailDataSuccess(final PreparingPackageDetailBean bean) {
       /* LogUtils.i("detail log", "name" + bean.getContent().getAppMaterial().getKemiPic().get(0).getDocName());
        Log.d("PreparingPackageDetailA", "bean.getCode():" + bean.getCode());*/
        if (bean.getCode() == 0) {
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    PreparingDetailOneAdapter preparingDetailOneAdapter = new PreparingDetailOneAdapter(PreparingPackageDetailActivity.this, bean.getContent().getOneKey());
                    Log.i("---onekey-", bean.getContent().getOneKey().size() + "");
                    listViewone.setAdapter(preparingDetailOneAdapter);
                    Log.i("---getMaterial-", bean.getContent().getAppMaterial().getKemiPic().size() + "");
                     PreparingDetailAdapter preparingDetailAdapter = new PreparingDetailAdapter(PreparingPackageDetailActivity.this, bean.getContent().getAppMaterial(), getSupportFragmentManager());
                    listViewsu.setAdapter(preparingDetailAdapter);
                    Log.i("---plansize-", bean.getContent().getPlan().size() + "");
                    PreparingDetailPlanAdapter preparingDetailPlanAdapter = new PreparingDetailPlanAdapter(PreparingPackageDetailActivity.this, bean.getContent().getPlan(), mHandler);
                    listViewshou.setAdapter(preparingDetailPlanAdapter);
                    List<PreparingPackageDetailBean.ContentBean.DataBean> other = bean.getContent().getOther();
                    if (other.size() > 0) {
                        mnone.setVisibility(View.GONE);
                        listViewqi.setAdapter(new PreparingDetailOtherAdapter(PreparingPackageDetailActivity.this, other));
                    }


                }
            });
        }
    }


    @Override
    public void error(String operate, String errorCode) {

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("--hhhhh--", "====hhhhhh");
        detailPresenter.getPreparingPackageDetailData();
    }
}

