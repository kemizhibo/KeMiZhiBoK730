package com.kemizhibo.kemizhibo.yhr.view.homepagerview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.TokenBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;


/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public interface SowingMapView extends BaseView{

    void onSowingMapSuccess(SowingMapBean sowingMapBean);
    void onSowingMapError(String msg);

    //获取版本信息
    void onVersionInformationSuccess( VersionInformationBean versionInformationBean);
    void onVersionInformationError(String msg);
}
