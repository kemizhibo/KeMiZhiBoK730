package com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor;

import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.ForTeachPlayUrlApi;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.InitLectureApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.InitLectureBean;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   预览一件授课
 */
public class InitLectureIteractor {

    private IGetDataDelegate<InitLectureBean> mDelegate;

    @Inject
    public InitLectureIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadInitLectureData(BaseActivity activity, IGetDataDelegate<InitLectureBean> mDelegate,String token,String moduleId){
        this.mDelegate = mDelegate;
        InitLectureApi initLectureApi = new InitLectureApi(listener,activity,token,moduleId);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(initLectureApi);
    }

    private HttpOnNextListener<InitLectureBean> listener = new HttpOnNextListener<InitLectureBean>() {
        @Override
        public void onNext(InitLectureBean initLectureBean) {
            mDelegate.getDataSuccess(initLectureBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
