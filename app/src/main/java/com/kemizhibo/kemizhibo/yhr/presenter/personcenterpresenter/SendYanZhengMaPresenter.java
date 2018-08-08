package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SendYanZhengMaView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  发送验证码
 */
public interface SendYanZhengMaPresenter extends BasePresenter<SendYanZhengMaView> {
    //获取验证码
    void getSendYanZhengMaData(BaseActivity activity, String flag,String token,String mobile);
    //验证旧手机号
    void getOldPhoneData(BaseActivity activity,String token,String mobile, String code);
    //验证新手机号
    void getNewPhoneData(BaseActivity activity,String token,String mobile, String code);
}
