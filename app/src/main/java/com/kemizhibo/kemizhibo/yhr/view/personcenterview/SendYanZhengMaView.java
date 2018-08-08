package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface SendYanZhengMaView extends BaseView {
    //发送验证码
    void onSendYanZhengMaSuccess(SendYanZhengMaBean sendYanZhengMaBean);
    void onSendYanZhengMaError(String msg);
    //验证旧手机号
    void onOldPhoneSuccess(SendYanZhengMaBean sendYanZhengMaBean);
    void onOldPhoneError(String msg);
    //验证新手机号
    void onNewPhoneSuccess(SendYanZhengMaBean sendYanZhengMaBean);
    void onNewPhoneError(String msg);
}
