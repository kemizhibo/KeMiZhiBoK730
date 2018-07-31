package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;


import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingLookBean;

/**
 * Author: yhr
 * Date: on 2018/7/9.
 * Describe:
 */

public interface TeacherTrainingLookFragmentView extends BaseView {
    void onTeacherTrainingLookFragmentSuccess(TeacherTrainingLookBean teacherTrainingLookBean);
    void onTeacherTrainingLookFragmentError(String msg);

    //获取看讲解列表的视频地址
    //获取视频地址
    void onTeacherTrainingDetailsVideoUrlSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean);
    void onTeacherTrainingDetailsVideoUrlError(String msg);
}
