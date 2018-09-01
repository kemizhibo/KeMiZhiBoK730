package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.SearchActivity;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.TabUtils;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe: 资源库
 */

public class ResourceLibraryFragment extends BaseFragment {

    @BindView(R.id.ziyuanku_sousuo)
    ImageView ziyuankuSousuo;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ziyuanku_tab_layout)
    TabLayout ziyuankuTabLayout;
    @BindView(R.id.ziyuanku_view_pager)
    ViewPager ziyuankuViewPager;
    List<Fragment> list_fragment;
    List<String> list_title;
    FragmentPagerAdapter adapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        //如果依附于activity需要依附于mactivity
        View view = UIUtils.inflate(mActivity, R.layout.fragment_main_resource_library);
        ButterKnife.bind(this, view);

        ziyuankuTabLayout.addTab(ziyuankuTabLayout.newTab().setText("影像素材"), true);//添加 Tab,默认选中
        //tab可滚动
        ziyuankuTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //tab居中显示
        ziyuankuTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        //tab的字体选择器,默认,选择时
        ziyuankuTabLayout.setTabTextColors(getResources().getColor(R.color.huise_tab), getResources().getColor(R.color.lvse_tab));
        //tab的下划线颜色,默认是
        ziyuankuTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.lvse_tab));
        //下划线宽度、单元格间距
        final TabUtils tabUtils = new TabUtils();
        ziyuankuTabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabUtils.setIndicator(ziyuankuTabLayout, 10, 10);
            }
        });

        YingXiangFragment f1 = new YingXiangFragment();
        PeiXunFragment f2 = new PeiXunFragment();
        TalkClassFragment f3 = new TalkClassFragment();
        LiveRoomFragment f4 = new LiveRoomFragment();
        ZouJinFragment f5 = new ZouJinFragment();

        list_title = new ArrayList<>();
        list_title.add("影像素材");
        list_title.add("教师培训");
        list_title.add("科学观察室");
        list_title.add("科学进校园");
        list_title.add("探秘科学馆");


        list_fragment = new ArrayList<>();
        list_fragment.add(f1);
        list_fragment.add(f2);
        list_fragment.add(f4);
        list_fragment.add(f3);
        list_fragment.add(f5);
        ziyuankuTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        adapter = new NewsAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        ziyuankuViewPager.setAdapter(adapter);
        //预加载
        ziyuankuViewPager.setOffscreenPageLimit(4);
        ziyuankuTabLayout.setupWithViewPager(ziyuankuViewPager);
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


    @OnClick({R.id.ziyuanku_sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ziyuanku_sousuo:
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    startActivity(new Intent(getContext(), SearchActivity.class));
                }
                break;
        }
    }
    //适配器
    public class NewsAdapter extends FragmentPagerAdapter {

        private List<Fragment> list_fragment;                         //fragment列表
        private List<String> list_Title;                              //tab名的列表

        public NewsAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
            super(fm);
            this.list_fragment = list_fragment;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_Title.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return list_Title.get(position % list_Title.size());
        }
    }


}
