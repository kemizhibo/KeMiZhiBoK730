package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.LiveRoomDetailsApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.TeacherTrainingDetailsVideoApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class LiveRoomDetailsVideoIteractor {

    private IGetDataDelegate<LiveRoomDetailsBean> mDelegate;

    @Inject
    public LiveRoomDetailsVideoIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLiveRoomDetailsVideoData(BaseActivity activity, IGetDataDelegate<LiveRoomDetailsBean> mDelegate,String token,String courseId){
        this.mDelegate = mDelegate;
        LiveRoomDetailsApi liveRoomDetailsApi = new LiveRoomDetailsApi(listener,activity,token,courseId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(liveRoomDetailsApi);
    }

    private HttpOnNextListener<LiveRoomDetailsBean> listener = new HttpOnNextListener<LiveRoomDetailsBean>() {
        @Override
        public void onNext(LiveRoomDetailsBean liveRoomDetailsBean) {
            mDelegate.getDataSuccess(liveRoomDetailsBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
