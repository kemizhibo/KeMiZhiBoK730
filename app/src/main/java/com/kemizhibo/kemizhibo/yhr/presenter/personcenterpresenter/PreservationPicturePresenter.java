package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取用户信息
 */
public interface GetUserPresenter extends BasePresenter<GetUserView> {
    //获取用户信息
    void getUserData(BaseActivity activity,String token);
    //更改用户信息
    void getChangeUserData(BaseActivity activity,String token,String school,String realName,String grade,String subject,String idCardNo,String email,String address);
}
