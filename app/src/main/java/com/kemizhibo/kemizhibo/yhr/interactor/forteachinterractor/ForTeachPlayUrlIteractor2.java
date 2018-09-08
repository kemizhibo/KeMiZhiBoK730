package com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.ForTeachPlayUrlApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   获取一键授课的播放地址
 */
public class ForTeachPlayUrlIteractor2 {

    private IGetDataDelegate<ForTeachPlayUrlBean> mDelegate;

    @Inject
    public ForTeachPlayUrlIteractor2() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadForTeachPlayUrlData(BaseActivity activity, IGetDataDelegate<ForTeachPlayUrlBean> mDelegate,String courseId,String videoType,String encryption,String videoClarity,String kpointId){
        this.mDelegate = mDelegate;
        ForTeachPlayUrlApi forTeachPlayUrlApi = new ForTeachPlayUrlApi(listener,activity,courseId,videoType,encryption,videoClarity,kpointId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(forTeachPlayUrlApi);
    }

    private HttpOnNextListener<ForTeachPlayUrlBean> listener = new HttpOnNextListener<ForTeachPlayUrlBean>() {
        @Override
        public void onNext(ForTeachPlayUrlBean forTeachPlayUrlBean) {
            mDelegate.getDataSuccess(forTeachPlayUrlBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
