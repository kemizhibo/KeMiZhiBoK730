package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.LiuLanView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SignOutView;

import java.util.List;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  浏览记录
 */
public interface LiuLanPresenter extends BasePresenter<LiuLanView> {
    //浏览记录
    void getLiuLanData(BaseActivity activity, String token,String page,String size);

    //清空浏览记录
    void getClearLiuLanData(BaseActivity activity, String token);

    //删除一个或者多个浏览记录
    void getClearOneOrMoreLiuLanData(BaseActivity activity, String token, String array);

}
