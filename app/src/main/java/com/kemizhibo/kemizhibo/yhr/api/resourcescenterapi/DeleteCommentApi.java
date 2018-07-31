package com.kemizhibo.kemizhibo.yhr.api.resourcescenterapi;

import com.alibaba.fastjson.JSON;
import com.kemizhibo.kemizhibo.yhr.api.httpservice.HttpGetDeleteCommentService;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
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

public class DeleteCommentApi extends BaseApi<DeleteCommentBean> {
    //声明参数
    private String token;
    private String commentId;
    private String type;

    public DeleteCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity,String token, String commentId, String type) {
        super(listener, rxAppCompatActivity);
        this.token = token ;
        this.commentId = commentId ;
        this.type = type ;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetDeleteCommentService httpGetDeleteCommentService = retrofit.create(HttpGetDeleteCommentService.class);
        return httpGetDeleteCommentService.getDeleteCommentData(token,commentId,type);
    }

    @Override
    public DeleteCommentBean call(ResponseBody responseBody) {
        //解析
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtils.e(string);
        return JSON.parseObject(string, DeleteCommentBean.class);
    }
}
