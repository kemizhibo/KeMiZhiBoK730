package com.kemizhibo.kemizhibo.yhr.interactor.forteachinterractor;

import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.yhr.api.IGetDataDelegate;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.BeiKeApi;
import com.kemizhibo.kemizhibo.yhr.api.forteacherapi.UserPlanApi;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe   授课方案列表
 */
public class UserPlanIteractor {

    private IGetDataDelegate<CommonUserTeachPlanBean> mDelegate;

    @Inject
    public UserPlanIteractor() {}

    /**
     * 执行网络操作，获取数据
     */
    public void loadUserPlanData(BaseActivity activity, IGetDataDelegate<CommonUserTeachPlanBean> mDelegate,String token, String courseId, String lastPlan){
        this.mDelegate = mDelegate;
        UserPlanApi userPlanApi = new UserPlanApi(listener,activity,token,courseId,lastPlan);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(userPlanApi);
    }

    private HttpOnNextListener<CommonUserTeachPlanBean> listener = new HttpOnNextListener<CommonUserTeachPlanBean>() {
        @Override
        public void onNext(CommonUserTeachPlanBean commonUserTeachPlanBean) {
            mDelegate.getDataSuccess(commonUserTeachPlanBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
