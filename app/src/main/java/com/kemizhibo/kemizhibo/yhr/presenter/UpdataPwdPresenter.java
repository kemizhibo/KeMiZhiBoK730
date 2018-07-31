package com.kemizhibo.kemizhibo.yhr.presenter;

import android.text.TextUtils;

import com.kemizhibo.kemizhibo.yhr.bean.UpdataPwdBean;
import com.kemizhibo.kemizhibo.yhr.model.UpdataPwdModel;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.UpdataPwdView;

/**
 * Created by 17600 on 2018/5/21.
 */

public class UpdataPwdPresenter {
    //定义出view层接口和model层类
    UpdataPwdModel updataPwdModel;
    UpdataPwdView updataPwdView;

    //给构造器
    public UpdataPwdPresenter(UpdataPwdView updataPwdView) {
        this.updataPwdView = updataPwdView;
        this.updataPwdModel = new UpdataPwdModel();
    }
    //创建获取接口数据的方法
    public void getDatas(String token){
        String password = updataPwdView.getPassword();
        //final String token = updataPwdView.getToken();
        //调用model层获取数据的方法，将两个参数传进去
        updataPwdModel.getData(token,password,new UpdataPwdModel.UpdataPwdCallBack(){
            @Override
            public void success(UpdataPwdBean updataPwdBean) {
                if (updataPwdBean.isFlag()){
                    updataPwdView.success(updataPwdBean);
                }else {
                    ToastUtils.showToast(updataPwdBean.getMessage().toString());
                }
            }

            @Override
            public void error(String e) {

            }
        });
    }

}
