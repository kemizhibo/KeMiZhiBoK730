package com.kemizhibo.kemizhibo.yhr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.di.component.DaggerFragmentComponent;
import com.kemizhibo.kemizhibo.yhr.di.component.FragmentComponent;
import com.kemizhibo.kemizhibo.yhr.model.modules.FragmentModule;

/**
 * Created by yhr on 2018/5/3.
 *
 * 将Dagger2依赖注入和绑定View的操作提取出来
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    protected FragmentComponent fragmentComponent;
    protected T mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
    }

    private void initFragmentComponent(){
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(((MyApplication)getActivity().getApplication()).getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    /**
     * 完成依赖注入并返回注入的Presenter
     * @return T
     */
    protected abstract T initInjector();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
}
