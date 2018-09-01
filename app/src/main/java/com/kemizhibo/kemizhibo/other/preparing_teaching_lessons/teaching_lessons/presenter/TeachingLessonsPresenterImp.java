package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.presenter;

import android.app.Activity;

import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.load.LoadFailUtil;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.view.TeachingLessonsView;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.utils.NetUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/1.
 */

public class TeachingLessonsPresenterImp implements TeachingLessonsPresenter {
    private TeachingLessonsView teachingLessonsView;
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NUM = 1;

    private int pageSize;
    private int pageNum;

    public TeachingLessonsPresenterImp(TeachingLessonsView teachingLessonsView) {
        this.teachingLessonsView = teachingLessonsView;
        pageSize = DEFAULT_PAGE_SIZE;
        pageNum = DEFAULT_PAGE_NUM;
    }

    @Override
    public void refreshTeachingLessonsData() {
        if (!NetUtils.isConnected(teachingLessonsView.getCustomContext())){
            teachingLessonsView.error(Constants.NET_ERROR_CODE, false);
            return;
        }
        Map requestParams = teachingLessonsView.getRequestParams();
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(DEFAULT_PAGE_NUM));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doPost(teachingLessonsView.getCustomContext(), Constants.TEACHING_LESSONS_RECORD_URL, requestParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                teachingLessonsView.error(Constants.REQUEST_ERROR_CODE, false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TeachingLessonsBean bean = GsonUtils.getBean(response.body().string(), TeachingLessonsBean.class);
                if(bean != null && 0 == (bean.getCode())){
                    pageNum = DEFAULT_PAGE_NUM;
                    teachingLessonsView.refreshSuccess(bean);
                }else if(0 != bean.getCode()){
                    teachingLessonsView.error(Constants.OTHER_ERROR_CODE, false);
                }else{
                    teachingLessonsView.error(Constants.REQUEST_ERROR_CODE, false);
                }
            }
        });
    }

    @Override
    public void loadMoreTeachingLessonsData() {
        if (!NetUtils.isConnected(teachingLessonsView.getCustomContext())){
            teachingLessonsView.error(Constants.NET_ERROR_CODE, true);
            return;
        }
        Map requestParams = teachingLessonsView.getRequestParams();
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(++pageNum));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doPost(teachingLessonsView.getCustomContext(), Constants.TEACHING_LESSONS_RECORD_URL, requestParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                teachingLessonsView.error(Constants.REQUEST_ERROR_CODE, true);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TeachingLessonsBean bean = GsonUtils.getBean(response.body().string(), TeachingLessonsBean.class);
                if(bean != null && "0".equals(bean.getCode())){
                    teachingLessonsView.refreshSuccess(bean);
                }else if(0 != bean.getCode()){
                    teachingLessonsView.error(Constants.OTHER_ERROR_CODE, true);
                }else{
                    teachingLessonsView.error(Constants.REQUEST_ERROR_CODE, true);
                }
            }
        });
    }
}
