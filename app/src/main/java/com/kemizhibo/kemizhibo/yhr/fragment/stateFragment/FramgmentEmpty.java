package com.kemizhibo.kemizhibo.yhr.fragment.stateFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

/**
 * Author: 闫浩然
 * Date: on 2018/8/18.
 * Describe:activity中状态显示页
 */

public class FramgmentEmpty extends BaseFragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity,R.layout.loading_empty_page);
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.empty);
    }
}
