package com.kemizhibo.kemizhibo.yhr.presenter;

import com.kemizhibo.kemizhibo.yhr.bean.GetMssBean;
import com.kemizhibo.kemizhibo.yhr.model.GetMssModel;
import com.kemizhibo.kemizhibo.yhr.view.GetMssView;


/**
 * Created by 17600 on 2018/5/18.
 */

public class GetMssPresenter {
    //定义出view层接口和model层类
    GetMssModel getMssModel;
    GetMssView getMssView;

    //给构造器
    public GetMssPresenter(GetMssView getMssView) {
        this.getMssView = getMssView;
        this.getMssModel = new GetMssModel();
    }
    //创建获取接口数据的方法
    public void getDatas(){
       /* final String account = getMssView.getAccount();
        final String pwd = getMssView.getPwd();*/
        final String mobile = getMssView.getPhone();
        //调用model层获取数据的方法，将两个参数传进去
        getMssModel.getData(mobile,new GetMssModel.GetMssCallBack(){
            @Override
            public void success(GetMssBean getMssBean) {
                //这里进行了判断
                getMssView.success(getMssBean);
            }

            @Override
            public void error(String e) {

            }
        });
    }
}
