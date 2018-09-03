package com.kemizhibo.kemizhibo.yhr.fragment.home;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

/**
 * Author: yhr
 * Date: on 2018/6/14.
 * Describe:科学进校园
 */

public class SixFragment extends BaseFragment{
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
        View view = UIUtils.inflate(R.layout.home_six_fragment);
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
    }
}
