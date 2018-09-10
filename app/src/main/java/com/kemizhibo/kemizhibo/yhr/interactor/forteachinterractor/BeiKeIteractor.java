package com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor;

import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.BeiKeApi;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.InitLectureApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.InitLectureBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   备课列表
 */
public class BeiKeIteractor {

    private IGetDataDelegate<PreparingCenterBean> mDelegate;

    @Inject
    public BeiKeIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadBeiKeData(BaseActivity activity, IGetDataDelegate<PreparingCenterBean> mDelegate,String token, String materialId, String gradeId, String semesterId, String requestSource,String currentPage,String pageSize){
        this.mDelegate = mDelegate;
        BeiKeApi beiKeApi = new BeiKeApi(listener,activity,token,materialId,gradeId,semesterId,requestSource,currentPage,pageSize);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(beiKeApi);
    }

    private HttpOnNextListener<PreparingCenterBean> listener = new HttpOnNextListener<PreparingCenterBean>() {
        @Override
        public void onNext(PreparingCenterBean preparingCenterBean) {
            mDelegate.getDataSuccess(preparingCenterBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
