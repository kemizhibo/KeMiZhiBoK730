package com.kemizhibo.kemizhibo.yhr.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

import butterknife.ButterKnife;

public class TalkClassFragment extends BaseFragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.guancha_fragment);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onEmptyViewClick() {

    }

}
