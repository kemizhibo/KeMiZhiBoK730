package com.kemizhibo.kemizhibo.yhr.view.findpwdview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SendYanZhengMaBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface SendValidateBeforLoginView extends BaseView {
    //发送验证码
    void onSendYanZhengMaSuccess(SendValidateBeforLoginBean sendValidateBeforLoginBean);
    void onSendYanZhengMaError(String msg);

    //验证手机号
    void onBeforLoginValiDatePhoneBeforLoginValiDatePhoneSuccess(BeforLoginValiDatePhoneBean beforLoginValiDatePhoneBean);
    void onBeforLoginValiDatePhoneError(String msg);

}
