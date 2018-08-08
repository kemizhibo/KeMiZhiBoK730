package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SignOutView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  退出登录
 */
public interface SignOutPresenter extends BasePresenter<SignOutView> {

    void getSignOutData(BaseActivity activity,String token);

}
