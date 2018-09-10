package com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor;

import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.ShouKeApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;
import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   授课列表
 */
public class ShouKeIteractor {

    private IGetDataDelegate<TeachingLessonsBean> mDelegate;

    @Inject
    public ShouKeIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadShouKeData(BaseActivity activity, IGetDataDelegate<TeachingLessonsBean> mDelegate,String token, String materialId, String gradeId, String semesterId,String pageSize, String currentPage,String type){
        this.mDelegate = mDelegate;
        ShouKeApi shouKeApi = new ShouKeApi(listener,activity,token,materialId,gradeId,semesterId,pageSize,currentPage,type);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(shouKeApi);
    }

    private HttpOnNextListener<TeachingLessonsBean> listener = new HttpOnNextListener<TeachingLessonsBean>() {
        @Override
        public void onNext(TeachingLessonsBean teachingLessonsBean) {
            mDelegate.getDataSuccess(teachingLessonsBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
