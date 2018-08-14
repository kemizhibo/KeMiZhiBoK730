package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetCollectionService;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetLikeService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
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

public class GetLikeApi extends BaseApi<GetLikeBean> {
    //声明参数
    private String token;
    private String targetId;
    private String type;

    public GetLikeApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String targetId,String type) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.targetId = targetId ;
        this.type = type ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetLikeService httpGetLikeService = retrofit.create(HttpGetLikeService.class);
        return httpGetLikeService.getLikeData(token,targetId,type);
    }

    @Override
    public GetLikeBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.i("点赞解析",string);
        return JSON.parseObject(string, GetLikeBean.class);
    }
}
