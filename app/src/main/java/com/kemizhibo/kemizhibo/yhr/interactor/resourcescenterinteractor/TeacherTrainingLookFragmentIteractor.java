package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingLookFragmentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingLookBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class TeacherTrainingLookFragmentIteractor {

    private IGetDataDelegate<TeacherTrainingLookBean> mDelegate;
    private Context context;

    @Inject
    public TeacherTrainingLookFragmentIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadTeacherTrainingLookFragmentData(BaseActivity activity, IGetDataDelegate<TeacherTrainingLookBean> mDelegate,String sellType,String subjectId,String currentPage,String pageSize){
        this.mDelegate = mDelegate;
        TeacherTrainingLookFragmentApi teacherTrainingLookFragmentApi = new TeacherTrainingLookFragmentApi(listener,activity,sellType,subjectId,currentPage,pageSize);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(teacherTrainingLookFragmentApi);
    }

    private HttpOnNextListener<TeacherTrainingLookBean> listener = new HttpOnNextListener<TeacherTrainingLookBean>() {
        @Override
        public void onNext(TeacherTrainingLookBean teacherTrainingLookBean) {
            mDelegate.getDataSuccess(teacherTrainingLookBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
