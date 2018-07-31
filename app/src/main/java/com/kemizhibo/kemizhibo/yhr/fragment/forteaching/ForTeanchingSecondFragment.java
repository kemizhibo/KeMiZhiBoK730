package com.kemizhibo.kemizhibo.yhr.fragment.forteaching;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

/**
 * Author: yhr
 * Date: on 2018/6/19.
 * Describe:
 */

public class ForTeanchingSecondFragment extends BaseFragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.forteaching_second_fragment);
        return view;
    }

}
