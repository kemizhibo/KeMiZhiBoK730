package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.UserView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取用户信息
 */
public interface UserPresenter extends BasePresenter<UserView> {
    //获取用户信息
    void userData(BaseActivity activity, String token);
}
