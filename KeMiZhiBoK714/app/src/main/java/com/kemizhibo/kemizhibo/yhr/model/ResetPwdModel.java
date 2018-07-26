package com.kemizhibo.kemizhibo.yhr.model;



import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.retrofitUtils.BaseObserver;
import com.kemizhibo.kemizhibo.yhr.retrofitUtils.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 17600 on 2018/5/18.
 */

public class ResetPwdModel {
    //创建一个Map集合
    Map<String,String> map = new HashMap<>();
    //获取数据的方法
    public void getData(String token,String password,final LoginCallBack loginCallBack){
        //把参数添加到集合中
        map.put("token",token);
        map.put("password",password);
        //使用封装进行请求数据，第一个参数为接口域名以后的部分，第二参数为map集合，第三个参数为回调接口
        RetrofitManager.get("kemiapi/user/school/resetPwd", map, new BaseObserver<LoginBean>() {
            @Override
            public void success(LoginBean loginBean) {
                loginCallBack.success(loginBean);
            }

            @Override
            public void failure(int code) {

            }

        });
    }
    //创建model层的回调接口
    public interface LoginCallBack{
        void success(LoginBean loginBean);
        void error(String e);
    }
}
