package com.kemizhibo.kemizhibo.other.preparing_center.presenter;

import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_center.view.PreparingCenterView;
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

public class PreparingCenterPresenterImp implements PreparingCenterPresenter {

    private PreparingCenterView preparingCenterView;
    public final static int DEFAULT_PAGE_SIZE = 10;
    public final static int DEFAULT_PAGE_NUM = 1;

    private int pageSize;
    private int pageNum;

    public PreparingCenterPresenterImp(PreparingCenterView preparingCenterView) {
        pageSize = DEFAULT_PAGE_SIZE;
        pageNum = DEFAULT_PAGE_NUM;
        this.preparingCenterView = preparingCenterView;
    }

    @Override
    public void refresh() {
        if (!NetUtils.isConnected(preparingCenterView.getCustomContext())){
            preparingCenterView.error(Constants.NET_ERROR_CODE, false);
            return;
        }
        Map requestParams = preparingCenterView.getRequestParams();
        requestParams.put(Constants.REQUEST_SOURCE, "app");
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(DEFAULT_PAGE_NUM));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doPost(preparingCenterView.getCustomContext(), Constants.PREPARING_CENTER_URL, requestParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                preparingCenterView.error(Constants.REQUEST_ERROR_CODE, false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingCenterBean bean = GsonUtils.getBean(response.body().string(), PreparingCenterBean.class);
                if(bean != null && 0 == (bean.getCode())){
                    pageNum = DEFAULT_PAGE_NUM;
                    preparingCenterView.refreshSuccess(bean);
                }else if(0 != bean.getCode()){
                    preparingCenterView.error(Constants.OTHER_ERROR_CODE, false);
                }else{
                    preparingCenterView.error(Constants.REQUEST_ERROR_CODE, false);
                }
            }
        });
    }

    @Override
    public void loadMore() {
        if (!NetUtils.isConnected(preparingCenterView.getCustomContext())){
            preparingCenterView.error(Constants.NET_ERROR_CODE, true);
            return;
        }
        Map requestParams = preparingCenterView.getRequestParams();
        requestParams.put(Constants.REQUEST_SOURCE, "app");
        requestParams.put(Constants.CURRENT_PAGE, String.valueOf(++pageNum));
        requestParams.put(Constants.PAGE_SIZE, String.valueOf(pageSize));
        OkHttpRequest.doPost(preparingCenterView.getCustomContext(), Constants.PREPARING_CENTER_URL, requestParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                preparingCenterView.error(Constants.REQUEST_ERROR_CODE, true);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                PreparingCenterBean bean = GsonUtils.getBean(response.body().string(), PreparingCenterBean.class);
                if(null != bean && 0 == bean.getCode()){
                    preparingCenterView.loadMoreSuccess(bean);
                }else if(null != bean && 0 != bean.getCode()){
                    preparingCenterView.error(Constants.OTHER_ERROR_CODE, true);
                }else{
                    preparingCenterView.error(Constants.REQUEST_ERROR_CODE, true);
                }
            }
        });
    }
}
