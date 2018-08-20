package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;
import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class TeacherTrainingIteractor {

    private IGetDataDelegate<TeacherTrainingBean> mDelegate;

    @Inject
    public TeacherTrainingIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadTeacherTrainingData(BaseActivity activity, IGetDataDelegate<TeacherTrainingBean> mDelegate,String token,String sellType,String currentPage,String pageSize,String materialEdition,String subjectId,String semester,String courseType,String knowledgeId){
        this.mDelegate = mDelegate;
        TeacherTrainingApi teacherTrainingApi = new TeacherTrainingApi(listener,activity,token,sellType,currentPage,pageSize,materialEdition,subjectId,semester,courseType,knowledgeId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(teacherTrainingApi);
    }

    private HttpOnNextListener<TeacherTrainingBean> listener = new HttpOnNextListener<TeacherTrainingBean>() {
        @Override
        public void onNext(TeacherTrainingBean teacherTrainingBean) {
            mDelegate.getDataSuccess(teacherTrainingBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
