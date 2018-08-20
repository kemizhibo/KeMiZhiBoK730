package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public interface TeacherTrainingPresenter extends BasePresenter<TeacherTrainingView> {
    /**
     * 获取教师培训页数据
     */
    void getFilterData(BaseActivity activity);
    void getTeacherTrainingData(BaseActivity activity,String token, String sellType, String currentPage, String pageSize, String materialEdition, String subjectId, String semester,String courseType, String knowledgeId);
}
