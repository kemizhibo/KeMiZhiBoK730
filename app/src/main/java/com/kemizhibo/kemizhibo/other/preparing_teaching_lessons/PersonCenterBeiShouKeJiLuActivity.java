package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenter;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.PreparingLessonsFragment;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.TeachingLessonsFragment;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PersonCenterBeiShouKeJiLuActivity extends BaseActivity implements CommonView{

    @BindView(R.id.filter_ll)
    LinearLayout filterLl;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.filter_text)
    TextView filterText;
    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int currentIndex = 0;
    private List<Fragment> fragments;
    private List<String> titles;
    private CommonPresenter userInfoPresenter;
    private int roleId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_bei_shou_ke_ji_lu;
    }

    @Override
    protected void initData() {
        userInfoPresenter = new CommonPresenterImp(this);
        userInfoPresenter.getUserInfo();
    }

    private void initializeData() {
        fragments = new ArrayList();
        fragments.add(new PreparingLessonsFragment());
        fragments.add(new TeachingLessonsFragment());
        titles = new ArrayList<>();
        if(8 == roleId){
            titles.add("备课情况");
            titles.add("授课情况");
            filterText.setText("筛选");
        }else if(9 == roleId){
            titles.add("备课记录");
            titles.add("授课记录");
            filterText.setText("状态");
        }

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                if(9 == roleId){
                    filterText.setText(currentIndex == 0 ? "状态" : "日期");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public Context getCommonCustomContext() {
        return this;
    }

    @Override
    public Map getCommonRequestParams() {
        return null;
    }

    @Override
    public void getCommonFilterSuccess(CommonFilterBean bean) {

    }

    @Override
    public void getCommonFilterError(int errorCode) {

    }

    @Override
    public void getCommonUserInfoSuccess(CommonUserInfoBean bean) {
        roleId = bean.getContent().getRoleId();
        initializeData();
    }

    @Override
    public void getCommonUserInfoError(int errorCode) {

    }
}
