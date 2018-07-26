package com.kemizhibo.kemizhibo.yhr.presenter;

import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;
import com.kemizhibo.kemizhibo.yhr.model.MessageModel;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.MessageIView;

/**
 * Created by 17600 on 2018/5/16.
 */

public class MessagePresenter {
    //定义出view层接口和model层类
    MessageModel messageModel;
    MessageIView messageIView;

    //给构造器
    public MessagePresenter(MessageIView messageIView) {
        this.messageIView = messageIView;
        this.messageModel = new MessageModel();
    }
    //创建获取接口数据的方法
    public void getDatas(){
        final String mobile = messageIView.mobile();
        final String mobileCode = messageIView.mobileCode();
        //调用model层获取数据的方法，将两个参数传进去
        messageModel.getData(mobile,mobileCode,new MessageModel.MessageCallBack(){
            @Override
            public void success(MessageBean messageBean) {
                if (messageBean.isFlag()){
                    messageIView.successMess(messageBean);
                }else {
                    ToastUtils.showToast(messageBean.getMessage().toString());
                }
            }

            @Override
            public void error(String e) {

            }
        });
    }
}
