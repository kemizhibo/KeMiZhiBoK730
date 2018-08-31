package com.kemizhibo.kemizhibo.yhr.presenter.findpwdpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.ResetPwdView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  重置密码
 */
public interface ResetPwdPresenter extends BasePresenter<ResetPwdView> {
    //获取验证码
    void getResetPwdData(BaseActivity activity, String token, String password);
}
