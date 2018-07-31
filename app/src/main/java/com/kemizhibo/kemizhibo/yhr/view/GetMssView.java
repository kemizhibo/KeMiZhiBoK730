package com.kemizhibo.kemizhibo.yhr.view;


import com.kemizhibo.kemizhibo.yhr.bean.GetMssBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface GetMssView {
    void success(GetMssBean getMssBean);
    void error(String e);
    String getPhone();
}
