package com.kemizhibo.kemizhibo.yhr.fragment.stateFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.ForTeachingFragment;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

/**
 * Author: 闫浩然
 * Date: on 2018/8/18.
 * Describe:activity中状态显示页
 */

public class FramgmentNext extends BaseFragment{

    private RelativeLayout relativeLayout;

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
        View view = UIUtils.inflate(mActivity,R.layout.loading_nextpager_page);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.loading_next);
        return view;
    }

    @Override
    public void load() {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                .replace(R.layout.loading_nextpager_page,new ForTeachingFragment()).commit();
            }
        });
    }

    @Override
    public void onEmptyViewClick() {

    }
}
