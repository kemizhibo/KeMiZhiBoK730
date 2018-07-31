package com.kemizhibo.kemizhibo.other.common.view;

import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;

/**
 * Created by Administrator on 2018/7/30.
 */

public interface CommonView {
    void getCommonFilterSuccess(CommonFilterBean bean);
    void getCommonFilterError();
}
