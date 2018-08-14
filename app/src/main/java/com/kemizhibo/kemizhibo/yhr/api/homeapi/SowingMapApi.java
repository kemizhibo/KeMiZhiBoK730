package com.kemizhibo.kemizhibo.yhr.api.homeapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
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

public class SowingMapApi extends BaseApi<SowingMapBean> {
    //声明参数
    private String token;
    private String device;

    public SowingMapApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity,String token, String device) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.device = device ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getSowingMapData(token,device);
    }

    @Override
    public SowingMapBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.e("12346"+string);
        return JSON.parseObject(string, SowingMapBean.class);
    }
}
