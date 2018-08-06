package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetPutCommentService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
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

public class PutCommentApi extends BaseApi<CommentDetailBean> {
    //声明参数
    private String token;
    private String courseId;
    private String content;
    private String pCommentId;
    public PutCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity,String token, String courseId, String content, String pCommentId) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.courseId = courseId ;
        this.content = content ;
        this.pCommentId = pCommentId ;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetPutCommentService httpGetPutCommentService = retrofit.create(HttpGetPutCommentService.class);
        return httpGetPutCommentService.getPutCommentData(token,courseId,content,pCommentId);
    }

    @Override
    public CommentDetailBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.e(string);
        return JSON.parseObject(string, CommentDetailBean.class);
    }
}