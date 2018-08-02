package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.FeedBackView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  反馈
 */
public interface FeedBackPresenter extends BasePresenter<FeedBackView> {
    //反馈
    void getFeedBackData(BaseActivity activity, String token, String content, String type);
}
