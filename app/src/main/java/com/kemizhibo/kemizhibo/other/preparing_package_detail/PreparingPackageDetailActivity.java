package com.kemizhibo.kemizhibo.other.preparing_package_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class PreparingPackageDetailActivity extends BaseActivity implements PreparingPackageDetailView {
    @BindView(R.id.back_img)
    ImageView back;
    @BindView(R.id.list_view_one)
    ListView listViewone;
    @BindView(R.id.list_view_su)
    MyListView listViewsu;
    @BindView(R.id.list_view_shou)
    ListView listViewshou;
    @BindView(R.id.list_view_qi)
    ListView listViewqi;
    @BindView(R.id.none)
    TextView mnone;
    private PreparingPackageDetailPresenter detailPresenter;
    private int courseId;
    Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preparing_package_detail;
    }

    @Override
    protected void initData() {
        detailPresenter = new PreparingPackageDetailPresenterImp(this);
        Intent intent = getIntent();
        courseId = intent.getIntExtra(Constants.COURSE_ID, 0);
        courseId = 2832;
        detailPresenter.getPreparingPackageDetailData();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }

    @OnClick(R.id.back_img)
    public void onClick(View view) {
        finish();
    }

    @Override
    public String getCourseId() {
        return String.valueOf(courseId);
    }

    @Override
    public Context getCustomContext() {
        return this;
    }

    @Override
    public void getPreparingPackageDetailDataSuccess(final PreparingPackageDetailBean bean) {
        Log.d("PreparingPackageDetailA", "bean.getCode():" + bean.getCode());
        if (bean.getCode() == 0) {
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    PreparingDetailOneAdapter preparingDetailOneAdapter = new PreparingDetailOneAdapter(PreparingPackageDetailActivity.this, bean.getContent().getOneKey());
                    Log.i("---onekey-", bean.getContent().getOneKey().size() + "");
                    listViewone.setAdapter(preparingDetailOneAdapter);
                    Log.i("---getMaterial-", bean.getContent().getMaterial().size() + "");
                    PreparingDetailAdapter preparingDetailAdapter = new PreparingDetailAdapter(PreparingPackageDetailActivity.this, bean.getContent().getMaterial());
                    listViewsu.setAdapter(preparingDetailAdapter);
                    Log.i("---plansize-", bean.getContent().getPlan().size() + "");
                    PreparingDetailPlanAdapter preparingDetailPlanAdapter = new PreparingDetailPlanAdapter(PreparingPackageDetailActivity.this, bean.getContent().getPlan());
                    listViewshou.setAdapter(preparingDetailPlanAdapter);
                    List<PreparingPackageDetailBean.ContentBean.OtherBean> other = bean.getContent().getOther();
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

}
