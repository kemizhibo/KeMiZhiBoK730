package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetCollectionService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
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

public class CollectionApi extends BaseApi<CollectionBean> {
    //声明参数
    private String token;
    private String courseId;

    public CollectionApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity,String token,String courseId) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.courseId = courseId ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetCollectionService httpGetCollectionService = retrofit.create(HttpGetCollectionService.class);
        return httpGetCollectionService.getCollectionData(token,courseId);
    }

    @Override
    public CollectionBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, CollectionBean.class);
    }
}
