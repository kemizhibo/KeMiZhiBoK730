package com.kemizhibo.kemizhibo.yhr.api.forteacherapi;


import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.InitLectureBean;
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

public class InitLectureApi extends BaseApi<InitLectureBean> {
    String token;
    String moduleId;
    public InitLectureApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String moduleId) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.moduleId = moduleId;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getInitLectureData(token,moduleId);
    }

    @Override
    public InitLectureBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, InitLectureBean.class);
    }
}
