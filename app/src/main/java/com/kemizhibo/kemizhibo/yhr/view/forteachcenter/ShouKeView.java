package com.kemizhibo.kemizhibo.yhr.view.forteachcenter;

import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    备课中心
 */
public interface ShouKeView extends BaseView{
    void onShouKeSuccess(TeachingLessonsBean teachingLessonsBean);
    void onShouKeError(String msg);

    //筛选
    void onFilterSuccess(FilterBean filterBean);
    void onFilterError(String msg);

    //授课方案
    void onUserPlanSuccess(CommonUserTeachPlanBean commonUserTeachPlanBean);
    void onUserPlanError(String msg);
}
