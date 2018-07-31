package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;

/**
 * Created by Administrator on 2018/7/31.
 */

public interface PreparingPackageDetailView {
    String getCourseId();
    Context getCustomContext();
    void getPreparingPackageDetailDataSuccess(PreparingPackageDetailBean bean);
    void error(String operate, String errorCode);
}
