package com.kemizhibo.kemizhibo.other.preparing_online.presenter;

import android.app.Activity;

import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.load.LoadFailUtil;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_online.view.PreparingOnlineView;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.utils.NetUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/24.
 */

public class PreparingOnlinePresenterImp implements PreparingOnlinePresenter {

    private PreparingOnlineView preparingOnlineView;
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NUM = 1;

    private int pageSize;
    private int pageNum;

    public PreparingOnlinePresenterImp(PreparingOnlineView preparingOnlineView) {
        pageNum = DEFAULT_PAGE_NUM;
        pageSize = DEFAULT_PAGE_SIZE;
        this.preparingOnlineView = preparingOnlineView;
    }

    @Override
    public void refresh() {
        if (!NetUtils.isConnected(preparingOnlineView.getCustomContext())){
            preparingOnlineView.error(Constants.NET_ERROR_CODE, false);
            return;
        }
        Map requestParams = preparingOnlineView.getRequestParams();
        requestParams.put(Constants.TYPE, "app");
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(DEFAULT_PAGE_NUM));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doGet(preparingOnlineView.getCustomContext(), OkHttpRequest.attachHttpGetParams(Constants.PREPARING_ONLINE_URL, requestParams), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                preparingOnlineView.error(Constants.REQUEST_ERROR_CODE, false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingOnlineBean bean = GsonUtils.getBean(response.body().string(), PreparingOnlineBean.class);
                if(bean != null && 0 == bean.getCode()){
                    preparingOnlineView.refreshSuccess(bean);
                    pageNum = DEFAULT_PAGE_NUM;
                }else if(0 != bean.getCode()){
                    preparingOnlineView.error(Constants.OTHER_ERROR_CODE, false);
                }else{
                    preparingOnlineView.error(Constants.REQUEST_ERROR_CODE, false);
                }
            }
        });
    }

    @Override
    public void loadMore() {
        if (!NetUtils.isConnected(preparingOnlineView.getCustomContext())){
            preparingOnlineView.error(Constants.NET_ERROR_CODE, false);
            return;
        }
        Map requestParams = preparingOnlineView.getRequestParams();
        requestParams.put(Constants.TYPE, "app");
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(++pageNum));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doGet(preparingOnlineView.getCustomContext(), OkHttpRequest.attachHttpGetParams(Constants.PREPARING_ONLINE_URL, requestParams), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                preparingOnlineView.error(Constants.REQUEST_ERROR_CODE, true);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingOnlineBean bean = GsonUtils.getBean(response.body().string(), PreparingOnlineBean.class);
                if(null != bean && 0 == bean.getCode()){
                    preparingOnlineView.loadMoreSuccess(bean);
                }else if(null != bean && 0 != bean.getCode()){
                    preparingOnlineView.error(Constants.OTHER_ERROR_CODE, true);
                }else{
                    preparingOnlineView.error(Constants.REQUEST_ERROR_CODE, true);
                }
            }
        });
    }
}
