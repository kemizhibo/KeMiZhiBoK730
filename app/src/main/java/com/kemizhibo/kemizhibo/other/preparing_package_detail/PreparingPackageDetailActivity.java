package com.kemizhibo.kemizhibo.other.preparing_package_detail;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter.PreparingPackageDetailPresenter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter.PreparingPackageDetailPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingPackageDetailView;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PreparingPackageDetailActivity extends BaseActivity implements PreparingPackageDetailView {
    @BindView(R.id.back_img)
    ImageView back;
    @BindView(R.id.list_view)
    ListView listView;

    private PreparingPackageDetailPresenter detailPresenter;
    private int courseId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preparing_package_detail;
    }

    @Override
    protected void initData() {
        detailPresenter = new PreparingPackageDetailPresenterImp(this);
        Intent intent = getIntent();
        courseId = intent.getIntExtra(Constants.COURSE_ID, 0);
        detailPresenter.getPreparingPackageDetailData();
    }

    @OnClick(R.id.back_img)
    public void onClick(View view){
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
    public void getPreparingPackageDetailDataSuccess(PreparingPackageDetailBean bean) {
        Log.d("PreparingPackageDetailA", "bean.getCode():" + bean.getCode());
    }

    @Override
    public void error(String operate, String errorCode) {

    }
}
