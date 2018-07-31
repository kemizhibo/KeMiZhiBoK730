package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.adapter.HomeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingLookFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingTalkFragment;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

public class LiveRoomDetailsActivity extends BaseMvpActivity<LiveRoomDetailsVideoPresenterImpl> implements LiveRoomDetailsView {

    @BindView(R.id.teacher_training_details_video)
    JZVideoPlayerStandard teacherTrainingDetailsVideo;
    @BindView(R.id.teacher_training_toolbar)
    Toolbar teacherTrainingToolbar;
    @BindView(R.id.teacher_training_collapsing_toolbar)
    CollapsingToolbarLayout teacherTrainingCollapsingToolbar;
    @BindView(R.id.teacher_training_appbar)
    AppBarLayout teacherTrainingAppbar;
    @BindView(R.id.live_room_details_video_title)
    TextView liveRoomDetailsVideoTitle;
    @BindView(R.id.live_room_details_shoucang_butn)
    TextView liveRoomDetailsShoucangButn;
    @BindView(R.id.live_room_details_page_title)
    TextView liveRoomDetailsPageTitle;
    @BindView(R.id.live_room_details_video_jieshao)
    TextView liveRoomDetailsVideoJieshao;
    @BindView(R.id.live_room_details_xTablayout)
    XTabLayout liveRoomDetailsXTablayout;
    @BindView(R.id.live_room_details_viewPager)
    ViewPager liveRoomDetailsViewPager;
    @BindView(R.id.teacher_training_detail_page_above_container)
    LinearLayout teacherTrainingDetailPageAboveContainer;
    @BindView(R.id.teacher_training_content)
    CoordinatorLayout teacherTrainingContent;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    String courseId;

    @Inject
    public LiveRoomDetailsVideoPresenterImpl liveRoomDetailsVideoPresenter;
    //科学观察室详情页的用户和视频信息
    private LiveRoomDetailsBean.ContentBean contentBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_room_details;
    }

    @Override
    protected void initData() {
        //添加标题
        initTitile();
        //添加fragment
        initFragment();
        //接受传来的值
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        //设置适配器
        liveRoomDetailsViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        liveRoomDetailsXTablayout.setupWithViewPager(liveRoomDetailsViewPager);
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new TeacherTrainingLookFragment());
        mFragmentList.add(new TeacherTrainingTalkFragment());
    }

    private void initTitile() {
        mTitleList = new ArrayList<>();
        mTitleList.add("看讲解");
        mTitleList.add("讨论区");
        liveRoomDetailsXTablayout.addTab(liveRoomDetailsXTablayout.newTab().setText(mTitleList.get(0)));
        liveRoomDetailsXTablayout.addTab(liveRoomDetailsXTablayout.newTab().setText(mTitleList.get(1)));
    }

    @Override
    protected void getData() {
        super.getData();
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoData(this, courseId);
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, courseId, "HLS", "true", "HD");
    }

    @Override
    public void onLiveRoomDetailsSuccess(LiveRoomDetailsBean liveRoomDetailsBean) {
        contentBean = liveRoomDetailsBean.getContent();
        //视频的标题
        liveRoomDetailsVideoTitle.setText(contentBean.getCourseName());
        //视频介绍
        liveRoomDetailsVideoJieshao.setText(contentBean.getContext());
    }

    @Override
    public void onLiveRoomDetailsError(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrlSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        LiveRoomDetailsVideoUrlBean contentUrlBean = liveRoomDetailsVideoUrlBean;
        //视频地址
        String str = contentUrlBean.getContent();
        teacherTrainingDetailsVideo.setUp(str,
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                "");
        Glide.with(this)
                .load(contentBean.getLogo())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(teacherTrainingDetailsVideo.thumbImageView);
        //LogUtils.d("str",str);
    }

    @Override
    public void onLiveRoomDetailsVideoUrlError(String msg) {

    }

    @Override
    protected LiveRoomDetailsVideoPresenterImpl initInject() {
        activityComponent.inject(this);
        return liveRoomDetailsVideoPresenter;
    }
}
