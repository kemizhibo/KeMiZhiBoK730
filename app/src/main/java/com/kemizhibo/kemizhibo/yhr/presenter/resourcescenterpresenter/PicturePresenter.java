package com.kemizhibo.kemizhibo.yhr.presenter.resourcescenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.PictureView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;

/**
 * Author: yhr
 * Date: on 2018/7/3.
 * Describe:教师培训详情页
 */

public interface PicturePresenter extends BasePresenter<PictureView> {
    void getPictureData(BaseActivity activity, String courseId);
    //收藏
    void getCollectionData(BaseActivity activity, String courseId,String token);
}
