package com.kemizhibo.kemizhibo.yhr.view.homepagerview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.TokenBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;


/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public interface SowingMapView extends BaseView{

    void onSowingMapSuccess(SowingMapBean loginBean);
    void onSowingMapError(String msg);

}
