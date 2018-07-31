package com.kemizhibo.kemizhibo.other.preparing_center.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface PreparingCenterView {
    Context getCustomContext();
    Map getRequestParams();
    void refreshSuccess(PreparingCenterBean bean);
    void loadMoreSuccess(PreparingCenterBean bean);
    void error(int errorCode, boolean isLoadMore);
}
