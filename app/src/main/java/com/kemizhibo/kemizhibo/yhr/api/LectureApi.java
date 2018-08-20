package com.kemizhibo.kemizhibo.yhr.api;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangePwdBean;
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

public class LectureApi extends BaseApi<LectureBean> {
    String token;
    String moduleId;
    String kemiVideoPlan;
    public LectureApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String moduleId, String kemiVideoPlan) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.moduleId = moduleId;
        this.kemiVideoPlan = kemiVideoPlan;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getLectureData(token,moduleId,kemiVideoPlan);
    }

    @Override
    public LectureBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, LectureBean.class);
    }
}
