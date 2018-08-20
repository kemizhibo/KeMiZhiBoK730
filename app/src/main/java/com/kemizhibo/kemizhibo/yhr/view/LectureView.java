package com.kemizhibo.kemizhibo.yhr.view;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;


/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   完成一键授课
 */
public interface LectureView extends BaseView{
    //是否登录成功
    void onLectureSuccess(LectureBean lectureBean);
    void onLectureError(String msg);
}
