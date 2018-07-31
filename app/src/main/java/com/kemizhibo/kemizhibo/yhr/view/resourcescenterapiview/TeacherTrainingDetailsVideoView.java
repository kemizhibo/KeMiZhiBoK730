package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  整个直播详情页面
 */
public interface TeacherTrainingDetailsVideoView extends BaseView{

    void onTeacherTrainingDetailsVideoSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean);
    void onTeacherTrainingDetailsVideoError(String msg);

    //获取视频地址
    void onTeacherTrainingDetailsVideoUrlSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean);
    void onTeacherTrainingDetailsVideoUrlError(String msg);

    //收藏视频
    void onGetCollectionSuccess(CollectionBean collectionBean);
    void onGetCollectionError(String msg);

}
