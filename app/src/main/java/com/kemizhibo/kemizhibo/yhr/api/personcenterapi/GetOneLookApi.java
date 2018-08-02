package com.kemizhibo.kemizhibo.yhr.api.personcenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetUserService;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LookBean;
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

public class GetOneLookApi extends BaseApi<LookBean> {

    String token;
    String playPosition;
    String courseId;
    String watchTime;
    String isEnd;

    public GetOneLookApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token,String playPosition,String courseId,String watchTime,String isEnd) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.playPosition = playPosition;
        this.courseId = courseId;
        this.watchTime = watchTime;
        this.isEnd = isEnd;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getOneLookData(token,playPosition,courseId,watchTime,isEnd);
    }

    @Override
    public LookBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e("00000000000000000000000000",string);
        return JSON.parseObject(string, LookBean.class);
    }
}
