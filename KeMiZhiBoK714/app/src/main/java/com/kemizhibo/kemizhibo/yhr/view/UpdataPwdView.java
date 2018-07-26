package com.kemizhibo.kemizhibo.yhr.view;

import com.kemizhibo.kemizhibo.yhr.bean.UpdataPwdBean;

/**
 * Created by 17600 on 2018/5/21.
 */

public interface UpdataPwdView {
    void success(UpdataPwdBean updataPwdBean);
    void error(String e);
    //String getToken(UpdataPwdBean updataPwdBean);
    String getPassword();
}
