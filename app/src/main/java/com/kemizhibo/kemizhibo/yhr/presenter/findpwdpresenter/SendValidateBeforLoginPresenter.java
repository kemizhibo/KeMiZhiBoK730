package com.kemizhibo.kemizhibo.yhr.presenter.findpwdpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.findpwdview.SendValidateBeforLoginView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  登录之前发送验证码
 */
public interface SendValidateBeforLoginPresenter extends BasePresenter<SendValidateBeforLoginView> {
    //获取验证码
    void getSendYanZhengMaData(BaseActivity activity, String flag,String mobile);

    //验证手机号
    void getBeforLoginValiDatePhone(BaseActivity activity,String mobile, String code);

}
