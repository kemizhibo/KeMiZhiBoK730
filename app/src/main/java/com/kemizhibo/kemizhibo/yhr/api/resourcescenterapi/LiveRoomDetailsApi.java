package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
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

public class LiveRoomDetailsApi extends BaseApi<LiveRoomDetailsBean> {
    //声明参数
    private String token;
    private String courseId;
    public LiveRoomDetailsApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token,String courseId) {
        super(listener, rxAppCompatActivity);
        this.courseId = courseId ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getYingXinagVideoDetailsData(token,courseId);
    }

    @Override
    public LiveRoomDetailsBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e(string);
        return JSON.parseObject(string, LiveRoomDetailsBean.class);
    }
}
