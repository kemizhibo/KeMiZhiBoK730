package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.LiuLanView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SignOutView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  浏览记录
 */
public interface LiuLanPresenter extends BasePresenter<LiuLanView> {

    void getLiuLanData(BaseActivity activity, String token,String page,String size);

}
