package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;


import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.InitLectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;

/**
 * Author: yhr
 * Date: on 2018/7/9.
 * Describe:科学观察室详情页
 */

public interface LiveRoomDetailsView extends BaseView {

    //完成一键授课
    void onLectureSuccess(LectureBean lectureBean);
    void onLectureError(String msg);

    //预览一键授课
    void onInitLectureSuccess(InitLectureBean initLectureBean);
    void onInitLectureError(String msg);

    //获取一键授课播放地址
    void onForTeachPlayUrlSuccess(ForTeachPlayUrlBean forTeachPlayUrlBean);
    void onForTeachPlayUrlError(String msg);
    //获取一键授课播放地址
    void onForTeachPlayUrl2Success(ForTeachPlayUrlBean forTeachPlayUrlBean);
    void onForTeachPlayUrl2Error(String msg);
    //获取一键授课播放地址
    void onForTeachPlayUrl3Success(ForTeachPlayUrlBean forTeachPlayUrlBean);
    void onForTeachPlayUrl3Error(String msg);
    //获取一键授课播放地址
    void onForTeachPlayUrl4Success(ForTeachPlayUrlBean forTeachPlayUrlBean);
    void onForTeachPlayUrl4Error(String msg);

}
