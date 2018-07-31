package com.kemizhibo.kemizhibo.other.common.presenter;

import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/30.
 */

public class CommonFilterPresenterImp implements CommonFilterPresenter{
    private CommonView commonView;

    public CommonFilterPresenterImp(CommonView commonView) {
        this.commonView = commonView;
    }

    @Override
    public void getCommonFilterData() {
        OkHttpRequest.doGet(Constants.COMMON_FILTER_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                commonView.getCommonFilterError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                CommonFilterBean bean = GsonUtils.getBean(response.body().string(), CommonFilterBean.class);
                if(null != bean && 0 == bean.getCode()){
                    commonView.getCommonFilterSuccess(bean);
                }else{
                    commonView.getCommonFilterError();
                }
            }
        });
    }
}
