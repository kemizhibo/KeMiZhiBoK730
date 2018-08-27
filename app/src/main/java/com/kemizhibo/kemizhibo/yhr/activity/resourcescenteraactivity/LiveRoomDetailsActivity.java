package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LiveRoomDetailsActivity extends BaseMvpActivity<LiveRoomDetailsVideoPresenterImpl> implements LiveRoomDetailsView {


    String courseId;
    @BindView(R.id.video_back_butn)
    ImageView videoBackButn;
    @BindView(R.id.player)
    DefinitionIjkVideoView player;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.finish_butn)
    Button finishButn;
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
        initVideo();
        //接受传来的值
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
    }

    private void initVideo() {
        controller = new DefinitionController(this);
        player.setPlayerConfig(new PlayerConfig.Builder()
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
        player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }


    @Override
    public void onBackPressed() {
        if (!player.onBackPressed()) {
            super.onBackPressed();
        }
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
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", str);
        videos.put("高清", str);
        LogUtils.i("0000000000000000000高清路径", str);
        videos.put("超清", str);
        LogUtils.i("0000000000000000000超清", str);
        videos.put("原画", str);
        LogUtils.i("0000000000000000000原画", str);
        if (videos.keySet().equals("高清")) {
            //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        } else if (videos.keySet().equals("超清")) {
            //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        } else if (videos.keySet().equals("原画")) {
            //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        } else {
            player.setDefinitionVideos(videos);
            player.setVideoController(controller);
            player.setTitle("视屏详情");
            player.start();
        }
    }

    @Override
    public void onLiveRoomDetailsVideoUrlError(String msg) {

    }

    @Override
    protected LiveRoomDetailsVideoPresenterImpl initInject() {
        activityComponent.inject(this);
        return liveRoomDetailsVideoPresenter;
    }

    @OnClick(R.id.finish_butn)
    public void onViewClicked() {

    }
}
