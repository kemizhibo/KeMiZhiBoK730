package com.kemizhibo.kemizhibo.yhr.model;


import com.kemizhibo.kemizhibo.yhr.bean.MessageBean;
import com.kemizhibo.kemizhibo.yhr.retrofitUtils.BaseObserver;
import com.kemizhibo.kemizhibo.yhr.retrofitUtils.RetrofitManager;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 17600 on 2018/5/18.
 */

public class MessageModel {
    //创建一个Map集合
    Map<String,String> map = new HashMap<>();
    //获取数据的方法
    public void getData(String mobile,String mobileCode,final MessageCallBack messageCallBack){
        //把参数添加到集合中
        map.put("mobile",mobile);
        map.put("mobileCode",mobileCode);
        //使用封装进行请求数据，第一个参数为接口域名以后的部分，第二参数为map集合，第三个参数为回调接口
        RetrofitManager.get("kemiapi/user/school/smsCode/validate", map, new BaseObserver<MessageBean>() {
            @Override
            public void success(MessageBean messageBean) {
                messageCallBack.success(messageBean);
            }

            @Override
            public void failure(int code) {

            }

        });
    }
    //创建model层的回调接口
    public interface MessageCallBack{
        void success(MessageBean messageBean);
        void error(String e);
    }
}
