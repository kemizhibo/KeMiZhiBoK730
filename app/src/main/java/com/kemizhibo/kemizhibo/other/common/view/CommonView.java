package com.kemizhibo.kemizhibo.other.common.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;

import java.util.Map;

/**
 * Created by Administrator on 2018/7/30.
 */

public interface CommonView {
    Context getCommonCustomContext();
    Map getCommonRequestParams();
    void getCommonFilterSuccess(CommonFilterBean bean);
    void getCommonFilterError(int errorCode);
    void getCommonUserInfoSuccess(CommonUserInfoBean bean);
    void getCommonUserInfoError(int errorCode);
}
