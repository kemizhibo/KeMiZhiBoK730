package com.kemizhibo.kemizhibo.yhr.model;



import com.kemizhibo.kemizhibo.yhr.bean.GetMssBean;
import com.kemizhibo.kemizhibo.yhr.retrofitUtils.BaseObserver;
import com.kemizhibo.kemizhibo.yhr.retrofitUtils.RetrofitManager;
import com.kemizhibo.kemizhibo.yhr.utils.TimerUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 17600 on 2018/5/18.
 */

public class GetMssModel {
    //创建一个Map集合
    Map<String,String> map = new HashMap<>();
    //获取数据的方法
    public void getData(String mobile,final GetMssCallBack getMssCallBack){
        //把参数添加到集合中
        map.put("mobile",mobile);
        //使用封装进行请求数据，第一个参数为接口域名以后的部分，第二参数为map集合，第三个参数为回调接口
        RetrofitManager.get("kemiapi/user/school/smsCode/retrievePwd", map, new BaseObserver<GetMssBean>() {
            @Override
            public void success(GetMssBean getMssBean) {
                if (getMssBean.isFlag()){
                    getMssCallBack.success(getMssBean);
                    ToastUtils.showToast("验证码已发送至您的手机，请注意查收");
                }else
                {
                    ToastUtils.showToast(getMssBean.getMessage().toString());
                }

            }
            @Override
            public void failure(int code) {

            }

        });
    }


    //创建model层的回调接口
    public interface GetMssCallBack{
        void success(GetMssBean getMssBean);
        void error(String e);
    }
}
