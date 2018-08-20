package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by yhr on 2018/7/1.
 */

public class ClearOneOrMoreLiuLanApi extends BaseApi<ClearLiuLanBean> {
    String token;
    String[] array;
    public ClearOneOrMoreLiuLanApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String[] array) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.array = array;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getClearOneOrMoreLiuLanData(token,array);
    }

    @Override
    public ClearLiuLanBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ClearLiuLanBean.class);
    }
}
