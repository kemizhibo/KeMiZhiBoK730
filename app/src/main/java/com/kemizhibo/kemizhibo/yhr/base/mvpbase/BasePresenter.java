package com.kemizhibo.kemizhibo.yhr.base.mvpbase;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: 抽取绑定解除，引入泛型
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T t);
    void detachView();
}
