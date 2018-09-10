package com.kemizhibo.kemizhibo.yhr.api.forteacherapi;


import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by yhr on 2018/7/1.
 */

public class UserPlanApi extends BaseApi<CommonUserTeachPlanBean> {
    String token;
    String courseId;
    String lastPlan;

    public UserPlanApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String courseId, String lastPlan) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.courseId = courseId;
        this.lastPlan = lastPlan;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getUserPlanData(token,courseId,lastPlan);
    }

    @Override
    public CommonUserTeachPlanBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, CommonUserTeachPlanBean.class);
    }
}
