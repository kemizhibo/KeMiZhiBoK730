package com.kemizhibo.kemizhibo.yhr.view;


import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;

/**
 * Created by 17600 on 2018/5/16.
 */

public interface MessageIView {
    void successMess(MessageBean messageBean);
    void errorMess(String e);
    String mobile();
    String mobileCode();
}
