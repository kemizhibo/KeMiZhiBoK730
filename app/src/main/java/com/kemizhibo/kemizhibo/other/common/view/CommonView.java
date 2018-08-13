package com.kemizhibo.kemizhibo.other.common.view;

import android.content.Context;

import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonTeacherBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;

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
    void getCommonTeacherSuccess(CommonTeacherBean bean);
    void getCommonTeacherError(int errorCode);
    void getCommonUserTeachPlanSuccess(CommonUserTeachPlanBean bean);
    void getCommonUserTeachPlanError(int errorCode);
}
