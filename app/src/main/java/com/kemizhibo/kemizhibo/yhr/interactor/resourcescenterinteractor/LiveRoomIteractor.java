package com.kemizhibo.kemizhibo.yhr.interactor.resourcescenterinteractor;

import android.content.Context;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.LiveRoomApi;
import com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi.YingXiangFragmentApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe
 */
public class LiveRoomIteractor {

    private IGetDataDelegate<LiveRoomBean> mDelegate;
    private Context context;

    @Inject
    public LiveRoomIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadLiveRoomData(BaseActivity activity, IGetDataDelegate<LiveRoomBean> mDelegate,String sellType,String currentPage,String pageSize,String materialEdition,String subjectId,String semester,String knowledgeId){
        this.mDelegate = mDelegate;
        LiveRoomApi liveRoomApi = new LiveRoomApi(listener,activity,sellType,currentPage,pageSize,materialEdition,subjectId,semester,knowledgeId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(liveRoomApi);
    }

    private HttpOnNextListener<LiveRoomBean> listener = new HttpOnNextListener<LiveRoomBean>() {
        @Override
        public void onNext(LiveRoomBean liveRoomBean) {
            mDelegate.getDataSuccess(liveRoomBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
