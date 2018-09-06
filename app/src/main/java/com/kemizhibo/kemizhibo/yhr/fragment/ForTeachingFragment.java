package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_center.ForTeanchingFirstFragment;
import com.kemizhibo.kemizhibo.other.preparing_online.ForTeanchingSecondFragment;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.ForTeachSearchActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.SearchActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.HomeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: 备授课
 */

public class ForTeachingFragment extends BaseFragment {
    @BindView(R.id.forteaching_tab_layout)
    XTabLayout forteachingTabLayout;
    Unbinder unbinder;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    @BindView(R.id.forteaching_sousuo)
    ImageView forteachingSousuo;
    @BindView(R.id.forteaching_view_pager)
    ViewPager forteachingViewPager;

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
        File file = getContext().getExternalFilesDir(null);//注意该方法是Context的方法,故需要调用的地方如果不在Activity中,需要传入Context调用
        Debug.startMethodTracing(file + "/dmtrace.trace");
        View view = UIUtils.inflate(mActivity, R.layout.fragment_forteaching);
        ButterKnife.bind(this, view);
        //添加标题
        initTitile();
        //添加fragment
        initFragment();
        //设置适配器
        forteachingViewPager.setAdapter(new HomeAdapter(getChildFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        forteachingTabLayout.setupWithViewPager(forteachingViewPager);
        Debug.stopMethodTracing();
        return view;
    }

    @Override
    public void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(200);
                setState(LoadingPager.LoadResult.success);
            }
        }).start();
    }

    @Override
    public void onEmptyViewClick() {

    }

    private void initTitile() {
        mTitleList = new ArrayList<>();
        mTitleList.add("备课中心");
        mTitleList.add("在线授课");
        //设置tablayout模式
        //tablayout获取集合中的名称
        forteachingTabLayout.addTab(forteachingTabLayout.newTab().setText(mTitleList.get(0)));
        forteachingTabLayout.addTab(forteachingTabLayout.newTab().setText(mTitleList.get(1)));
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new ForTeanchingFirstFragment());
        mFragmentList.add(new ForTeanchingSecondFragment());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            List<Fragment> fragments = getChildFragmentManager().getFragments();
            try {
                ForTeanchingFirstFragment firstFragment = (ForTeanchingFirstFragment) fragments.get(0);
                ForTeanchingSecondFragment secondFragment = (ForTeanchingSecondFragment) fragments.get(1);
                firstFragment.onHidden();
                secondFragment.onHidden();
            } catch (Exception e) {

            }
        }
    }

    @OnClick(R.id.forteaching_sousuo)
    public void onViewClicked() {
        if (NoFastClickUtils.isFastClick()) {
        }else {
            startActivity(new Intent(getContext(), ForTeachSearchActivity.class));
        }
    }
}
