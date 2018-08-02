package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.presenter;

import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.view.PreparingLessonsView;
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

public class PreparingLessonsPresenterImp implements PreparingLessonsPresenter{
    private PreparingLessonsView preparingLessonsView;
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NUM = 1;

    private int pageSize;
    private int pageNum;

    public PreparingLessonsPresenterImp(PreparingLessonsView preparingLessonsView) {
        this.preparingLessonsView = preparingLessonsView;
        pageSize = DEFAULT_PAGE_SIZE;
        pageNum = DEFAULT_PAGE_NUM;
    }

    @Override
    public void refreshPreparingLessonsData() {
        if (!NetUtils.isConnected(preparingLessonsView.getCustomContext())){
            preparingLessonsView.error(Constants.NET_ERROR_CODE, false);
            return;
        }
        Map requestParams = preparingLessonsView.getRequestParams();
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(DEFAULT_PAGE_NUM));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doPost(Constants.PREPARING_LESSONS_RECORD_URL, requestParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                preparingLessonsView.error(Constants.REQUEST_ERROR_CODE, false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingLessonsBean bean = GsonUtils.getBean(response.body().string(), PreparingLessonsBean.class);
                if(bean != null && 0 == (bean.getCode())){
                    pageNum = DEFAULT_PAGE_NUM;
                    preparingLessonsView.refreshSuccess(bean);
                }else{
                    preparingLessonsView.error(Constants.REQUEST_ERROR_CODE, false);
                }
            }
        });
    }

    @Override
    public void loadMorePreparingLessonsData() {
        if (!NetUtils.isConnected(preparingLessonsView.getCustomContext())){
            preparingLessonsView.error(Constants.NET_ERROR_CODE, true);
            return;
        }
        Map requestParams = preparingLessonsView.getRequestParams();
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(++pageNum));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doPost(Constants.PREPARING_LESSONS_RECORD_URL, requestParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                preparingLessonsView.error(Constants.REQUEST_ERROR_CODE, true);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingLessonsBean bean = GsonUtils.getBean(response.body().string(), PreparingLessonsBean.class);
                if(bean != null && "0".equals(bean.getCode())){
                    preparingLessonsView.refreshSuccess(bean);
                }else{
                    preparingLessonsView.error(Constants.REQUEST_ERROR_CODE, true);
                }
            }
        });
    }
}
