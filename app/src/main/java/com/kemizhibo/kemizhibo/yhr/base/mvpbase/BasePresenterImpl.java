package com.kemizhibo.kemizhibo.yhr.base.mvpbase;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: 所有Presenter都有绑定和解绑View的操作
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mPresenterView ;

    @Override
    public void attachView(T t) {
        this.mPresenterView = t ;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null ;
    }
}
