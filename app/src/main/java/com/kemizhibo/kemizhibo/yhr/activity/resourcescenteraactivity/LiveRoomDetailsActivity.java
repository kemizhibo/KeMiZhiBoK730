package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidkun.xtablayout.XTabLayout;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.adapter.HomeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingLookFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingTalkFragment;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveRoomDetailsActivity extends BaseMvpActivity<LiveRoomDetailsVideoPresenterImpl> implements LiveRoomDetailsView {

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
    @BindView(R.id.player)
    DefinitionIjkVideoView ijkVideoView;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    String courseId;
    private DefinitionController controller;
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
        initVideo();
        //接受传来的值
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        //设置适配器
        liveRoomDetailsViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        liveRoomDetailsXTablayout.setupWithViewPager(liveRoomDetailsViewPager);
    }

    private void initVideo() {
        controller = new DefinitionController(this);
        ijkVideoView.setPlayerConfig(new PlayerConfig.Builder()
                .setCustomMediaPlayer(new IjkPlayer(this) {
                    @Override
                    public void setOptions() {
                        //精准seek
                        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
                    }
                })
                .autoRotate()//自动旋转屏幕
                .build());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ijkVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ijkVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkVideoView.release();
    }


    @Override
    public void onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed();
        }
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
        SharedPreferences sp = getSharedPreferences("logintoken", 0);
        String token = sp.getString("token", "");
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoData(this, "Bearer " + token, courseId);
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
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
        //获取不同清晰度的视频路径
        /*LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", str);
        videos.put("高清", str);
        LogUtils.i("0000000000000000000高清路径",str);
        videos.put("超清", str);
        LogUtils.i("0000000000000000000超清",str);
        videos.put("原画", str);
        LogUtils.i("0000000000000000000原画",str);
        if (videos.keySet().equals("高清")){
            //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        }else if (videos.keySet().equals("超清")){
            //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        }else if (videos.keySet().equals("原画")){
            //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        }else {
            ijkVideoView.setDefinitionVideos(videos);
            ijkVideoView.setVideoController(controller);
            ijkVideoView.setTitle("视屏详情");
            ijkVideoView.start();
        }*/
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
