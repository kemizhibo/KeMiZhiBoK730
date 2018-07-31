package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
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

public class LiveRoomDetailsVideoUrlApi extends BaseApi<LiveRoomDetailsVideoUrlBean> {
    //声明参数
    private String token;
    private String courseId;
    private String videoType;
    private String encryption;
    private String videoClarity;

    public LiveRoomDetailsVideoUrlApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token,String courseId, String videoType, String encryption, String videoClarity) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.courseId = courseId ;
        this.videoType = videoType ;
        this.encryption = encryption ;
        this.videoClarity = videoClarity ;

    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getYingXinagVideoDetailsUrlData(token,courseId,videoType,encryption,videoClarity);
    }

    @Override
    public LiveRoomDetailsVideoUrlBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e(string);
        return JSON.parseObject(string, LiveRoomDetailsVideoUrlBean.class);
    }
}
