package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingDetailsVideoUrlApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangDetailsVideoUrlApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class TeacherTrainingDetailsVideoUrlIteractor {

    private IGetDataDelegate<TeacherTrainingDetailsVideoUrlBean> mDelegate;

    @Inject
    public TeacherTrainingDetailsVideoUrlIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadYingXiangDetailsVideoUrlData(BaseActivity activity, IGetDataDelegate<TeacherTrainingDetailsVideoUrlBean> mDelegate,String token,String courseId,String videoType,String encryption,String videoClarity){
        this.mDelegate = mDelegate;
        TeacherTrainingDetailsVideoUrlApi teacherTrainingDetailsVideoUrlApi = new TeacherTrainingDetailsVideoUrlApi(listener,activity,token,courseId,videoType,encryption,videoClarity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(teacherTrainingDetailsVideoUrlApi);
    }

    private HttpOnNextListener<TeacherTrainingDetailsVideoUrlBean> listener = new HttpOnNextListener<TeacherTrainingDetailsVideoUrlBean>() {
        @Override
        public void onNext(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean) {
            mDelegate.getDataSuccess(teacherTrainingDetailsVideoUrlBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
