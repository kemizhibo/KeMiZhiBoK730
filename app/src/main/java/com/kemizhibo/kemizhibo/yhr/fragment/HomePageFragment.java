package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.HomeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.SowingMapBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;
import com.kemizhibo.kemizhibo.yhr.fragment.home.MaterialRecommendedFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.home.MyClassFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.home.FourFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.home.TrainingCourseRecommendationFragment;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl.SowingMapPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.SowingMapView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: 首页
 */

public class HomePageFragment extends BaseMvpFragment<SowingMapPresenterImpl> implements OnBannerListener,SowingMapView {
    @BindView(R.id.home_viewpager)
    ViewPager homeViewpager;
    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.home_tablayout)
    XTabLayout homeTablayout;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    @Inject
    public SowingMapPresenterImpl sowingMapPresenter;
    //轮播数据
    private List<SowingMapBean.ContentBean> sowingData;
    //图片的集合
    List<String> imageList = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity, R.layout.fragment_homepage);
        ButterKnife.bind(this, view);
        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER);
        homeBanner.setImageLoader(new MyLoader());
        for(int i = 0;i < sowingData.size(); i ++){
            imageList.add(sowingData.get(i).getImagesUrl());
        }

        homeBanner.setImages(imageList);
        homeBanner.setBannerAnimation(Transformer.Default);
        homeBanner.setDelayTime(3000);
        homeBanner.isAutoPlay(true)
                .setOnBannerListener(this)
                .start();
        //设置透明状态栏
        //StatusBarCompat.translucentStatusBar(mActivity);

        //添加标题
        initTitile();
        //添加fragment
        initFragment();
        //设置适配器
        homeViewpager.setAdapter(new HomeAdapter(getChildFragmentManager(), mFragmentList, mTitleList));
        homeViewpager.setOffscreenPageLimit(4);
        //将tablayout与fragment关联
        homeTablayout.setupWithViewPager(homeViewpager);
        return view;
    }

    @Override
    public void load() {
        SharedPreferences sp = getContext().getSharedPreferences("logintoken", 0);
        String token = sp.getString("token", "");
        sowingMapPresenter.getSowingMapData(mActivity,"Bearer "+token,"app-2");
        sowingMapPresenter.getVersionInformationData(mActivity);
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    private void initTitile() {
        mTitleList = new ArrayList<>();
        mTitleList.add("我的备课");
        mTitleList.add("素材推荐");
        mTitleList.add("培训课推荐");
        //mTitleList.add("科学观察室");
        /*mTitleList.add("探秘科学馆");
        mTitleList.add("科学进校园");*/

        homeTablayout.addTab(homeTablayout.newTab().setText(mTitleList.get(0)));
        homeTablayout.addTab(homeTablayout.newTab().setText(mTitleList.get(1)));
        homeTablayout.addTab(homeTablayout.newTab().setText(mTitleList.get(2)));
        //homeTablayout.addTab(homeTablayout.newTab().setText(mTitleList.get(3)));
       /* homeTablayout.addTab(homeTablayout.newTab().setText(mTitleList.get(4)));
        homeTablayout.addTab(homeTablayout.newTab().setText(mTitleList.get(5)));*/
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MyClassFragment());
        mFragmentList.add(new MaterialRecommendedFragment());
        mFragmentList.add(new TrainingCourseRecommendationFragment());
        //mFragmentList.add(new FourFragment());
        /*mFragmentList.add(new FiveFragment());
        mFragmentList.add(new SixFragment());*/
    }

    @Override
    public void onSowingMapSuccess(SowingMapBean loginBean) {
        setState(LoadingPager.LoadResult.success);
        sowingData = new ArrayList<>();
        sowingData.addAll(loginBean.getContent());
    }

    @Override
    public void onSowingMapError(String msg) {
        //setState(LoadingPager.LoadResult.error);
    }
    //版本更新
    @Override
    public void onVersionInformationSuccess(VersionInformationBean versionInformationBean) {
        /*if (versionInformationBean.getCode()==0&&versionInformationBean.getContent().getVersionNo()!="现在的版本号"){
            //比较，如果有新版本，弹出提示，有新版本,确定以后调用下载最新apk接口接口
        }*/
    }

    @Override
    public void onVersionInformationError(String msg) {

    }

    @Override
    protected SowingMapPresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return sowingMapPresenter;
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //imageView.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(context).load((String) path)
                    .placeholder(R.mipmap.milier)
                    .error(R.mipmap.milier)
                    .fallback(R.mipmap.milier)
                    .into(imageView);
        }
    }

}

