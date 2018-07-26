package com.kemizhibo.kemizhibo.other.preparing_online.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface PreparingOnlineView {
    Context getCustomContext();
    Map getRequestParams();
    void refreshSuccess(PreparingOnlineBean bean);
    void loadMoreSuccess(PreparingOnlineBean bean);
    void error(int errorCode, boolean isLoadMore);
}
