package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
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

public class LiveRoomApi extends BaseApi<LiveRoomBean> {
    //声明参数
    private String sellType;
    private String page;
    private String pageSize;
    private String materialEdition;
    private String subjectId;
    private String semester;
    private String knowledgeId;
    public LiveRoomApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String sellType, String page, String pageSize, String materialEdition, String subjectId, String semester, String knowledgeId) {
        super(listener, rxAppCompatActivity);
        this.sellType = sellType ;
        this.page = page ;
        this.pageSize = pageSize ;
        this.materialEdition = materialEdition ;
        this.subjectId = subjectId ;
        this.semester = semester ;
        this.knowledgeId = knowledgeId ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getYingXinagFragmentData(sellType,page,pageSize,materialEdition,subjectId,semester,knowledgeId);
    }

    @Override
    public LiveRoomBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e(string);
        return JSON.parseObject(string, LiveRoomBean.class);
    }
}
