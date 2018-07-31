package com.kemizhibo.kemizhibo.yhr.api;

/**
 * Author: 闫浩然
 * Date: 2018/5/8
 * Describe: 网络请求回调
 */

public interface IGetDataDelegate<T> {

    void getDataSuccess(T t);
    void getDataError(String errmsg);

}
