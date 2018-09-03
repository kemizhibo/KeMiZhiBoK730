package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.LiveRoomDetailsVideoUrlApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  获取科学观察室视频地址
 */
public class LiveRoomDetailsVideoUrlIteractor3 {

    private IGetDataDelegate<LiveRoomDetailsVideoUrlBean> mDelegate;
    private Context context;

    @Inject
    public LiveRoomDetailsVideoUrlIteractor3() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLiveRoomDetailsVideoUrlData(BaseActivity activity, IGetDataDelegate<LiveRoomDetailsVideoUrlBean> mDelegate,String token,String courseId,String videoType,String encryption,String videoClarity){
        this.mDelegate = mDelegate;
        LiveRoomDetailsVideoUrlApi liveRoomDetailsVideoUrlApi = new LiveRoomDetailsVideoUrlApi(listener,activity,token,courseId,videoType,encryption,videoClarity);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(liveRoomDetailsVideoUrlApi);
    }

    private HttpOnNextListener<LiveRoomDetailsVideoUrlBean> listener = new HttpOnNextListener<LiveRoomDetailsVideoUrlBean>() {
        @Override
        public void onNext(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
            mDelegate.getDataSuccess(liveRoomDetailsVideoUrlBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
