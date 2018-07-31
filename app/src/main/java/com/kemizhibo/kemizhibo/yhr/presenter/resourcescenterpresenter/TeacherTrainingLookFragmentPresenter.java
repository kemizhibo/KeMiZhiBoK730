package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingLookFragmentView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public interface TeacherTrainingLookFragmentPresenter extends BasePresenter<TeacherTrainingLookFragmentView> {

    void getTeacherTrainingLookFragmentData(BaseActivity activity, String sellType, String subjectId,String currentPage, String pageSize);
    //获取视频地址
    void getTeacherTrainingDetailsVideoUrlData(BaseActivity activity,String token,String courseId,String videoType,String encryption,String videoClarity);
}
