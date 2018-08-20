package com.kemizhibo.kemizhibo.yhr.interactor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.LectureApi;
import com.kemizhibo.kemizhibo.yhr.api.tokenapi.GetLoginApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   完成一键授课
 */
public class LectureIteractor {

    private IGetDataDelegate<LectureBean> mDelegate;

    @Inject
    public LectureIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLectureData(BaseActivity activity, IGetDataDelegate<LectureBean> mDelegate,String token,String moduleId,String kemiVideoPlan){
        this.mDelegate = mDelegate;
        LectureApi lectureApi = new LectureApi(listener,activity,token,moduleId,kemiVideoPlan);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(lectureApi);
    }

    private HttpOnNextListener<LectureBean> listener = new HttpOnNextListener<LectureBean>() {
        @Override
        public void onNext(LectureBean lectureBean) {
            mDelegate.getDataSuccess(lectureBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
