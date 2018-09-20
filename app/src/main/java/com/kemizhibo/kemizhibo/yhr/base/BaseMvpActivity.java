package com.kemizhibo.kemizhibo.yhr.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.di.component.ActivityComponent;
import com.kemizhibo.kemizhibo.yhr.di.component.DaggerActivityComponent;
import com.kemizhibo.kemizhibo.yhr.model.modules.ActivityModule;

/**
 * Created by yhr on 2018/5/3.
 * Activity实现MVP的基类
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected ActivityComponent activityComponent;
    protected  T mPresenter;

    //通过Dagger2注入的是 Presenter

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInject();
        mPresenter.attachView(this);
        getData();
    }

    public  void initActivityComponent(){
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((MyApplication)getApplication()).getAppComponent())
                .build();
    }

    /**
     * 完成注入并返回注入的Presenter对象
     * @return
     */
    protected abstract T initInject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
}
