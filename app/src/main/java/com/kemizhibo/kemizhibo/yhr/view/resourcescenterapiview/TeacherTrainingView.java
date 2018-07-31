package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;


import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;

/**
 * Author: yhr
 * Date: on 2018/7/9.
 * Describe:  教师培训
 */

public interface TeacherTrainingView extends BaseView {
    //筛选
    void onFilterSuccess(FilterBean filterBean);
    void onFilterError(String msg);


    void onTeacherTrainingSuccess(TeacherTrainingBean teacherTrainingBean);
    void onTeacherTrainingError(String msg);
}
