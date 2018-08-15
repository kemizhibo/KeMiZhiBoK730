package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearCollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
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

public class ClearCollectionBoxApi extends BaseApi<ClearCollectionBoxBean> {
    String token;
    public ClearCollectionBoxApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token) {
        super(listener, rxAppCompatActivity);
        this.token = token;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getClearCollectionBoxData(token);
    }

    @Override
    public ClearCollectionBoxBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(string, ClearCollectionBoxBean.class);
    }
}
