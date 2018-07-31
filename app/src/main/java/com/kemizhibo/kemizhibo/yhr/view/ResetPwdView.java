package com.kemizhibo.kemizhibo.yhr.view;

import com.kemizhibo.kemizhibo.yhr.bean.ResetPwdBean;

/**
 * Created by 17600 on 2018/5/16.
 */

public interface ResetPwdView {
    void success(ResetPwdBean resetPwdBean);
    void error(String e);
    String token();
    String password();
}
