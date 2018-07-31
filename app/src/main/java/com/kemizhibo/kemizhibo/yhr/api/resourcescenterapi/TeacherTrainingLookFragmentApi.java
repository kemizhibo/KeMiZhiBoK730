package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingLookBean;
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

public class TeacherTrainingLookFragmentApi extends BaseApi<TeacherTrainingLookBean> {
    //声明参数
    private String sellType;
    private String subjectId;
    private String page;
    private String pageSize;

    public TeacherTrainingLookFragmentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String sellType,String subjectId,String page, String pageSize) {
        super(listener, rxAppCompatActivity);
        this.sellType = sellType ;
        this.subjectId = subjectId ;
        this.page = page ;
        this.pageSize = pageSize ;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getTeacherTrainingLookFragmnetData(sellType,subjectId,page,pageSize);
    }

    @Override
    public TeacherTrainingLookBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //LogUtils.e(string+"++++++++++++++++++++++++++++++++++++++++++");
        return JSON.parseObject(string, TeacherTrainingLookBean.class);
    }
}
