package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingDetailsVideoApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangDetailsVideoApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   影响素材详情
 */
public class TeacherTrainingDetailsVideoIteractor {

    private IGetDataDelegate<TeacherTrainingDetailsVideoBean> mDelegate;

    @Inject
    public TeacherTrainingDetailsVideoIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadTeacherTrainingDetailsVideoData(BaseActivity activity, IGetDataDelegate<TeacherTrainingDetailsVideoBean> mDelegate,String token,String courseId){
        this.mDelegate = mDelegate;
        TeacherTrainingDetailsVideoApi teacherTrainingDetailsVideoApi = new TeacherTrainingDetailsVideoApi(listener,activity,token,courseId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(teacherTrainingDetailsVideoApi);
    }

    private HttpOnNextListener<TeacherTrainingDetailsVideoBean> listener = new HttpOnNextListener<TeacherTrainingDetailsVideoBean>() {
        @Override
        public void onNext(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean) {
            mDelegate.getDataSuccess(teacherTrainingDetailsVideoBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
