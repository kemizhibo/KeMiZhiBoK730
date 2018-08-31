package com.kemizhibo.kemizhibo.yhr.view.findpwdview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.BeforLoginValiDatePhoneBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.ResetPwdBean;
import com.kemizhibo.kemizhibo.yhr.bean.findPwdbean.SendValidateBeforLoginBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface ResetPwdView extends BaseView {
    //重置密码
    void onResetPwdSuccess(ResetPwdBean resetPwdBean);
    void onResetPwdError(String msg);
}
