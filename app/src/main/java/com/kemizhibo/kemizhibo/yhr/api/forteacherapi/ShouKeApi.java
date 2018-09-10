package com.kemizhibo.kemizhibo.yhr.api.forteacherapi;


import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetService;
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

public class ShouKeApi extends BaseApi<TeachingLessonsBean> {
    String token;
    String materialId;
    String gradeId;
    String semesterId;
    String pageSize;
    String currentPage;
    String type;
    public ShouKeApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String token, String materialId, String gradeId, String semesterId, String pageSize,String currentPage,String type) {
        super(listener, rxAppCompatActivity);
        this.token = token;
        this.materialId = materialId;
        this.gradeId = gradeId;
        this.semesterId = semesterId;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.type = type;
    }


    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getShouKeData(token,materialId,gradeId,semesterId,pageSize,currentPage,type);
    }

    @Override
    public TeachingLessonsBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.i("于杰666",string);
        return JSON.parseObject(string, TeachingLessonsBean.class);
    }
}
