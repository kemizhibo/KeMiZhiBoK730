package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
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

import static com.dueeeke.videoplayer.player.IjkVideoView.SCREEN_SCALE_MATCH_PARENT;

public class LiveRoomDetailsActivity extends BaseMvpActivity<LiveRoomDetailsVideoPresenterImpl> implements LiveRoomDetailsView {

    String courseId;
    @BindView(R.id.player)
    DefinitionIjkVideoView player;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.finish_butn)
    Button finishButn;
    @BindView(R.id.yingxiang_details_video_title)
    TextView yingxiangDetailsVideoTitle;
    @BindView(R.id.video_back_butn)
    RelativeLayout videoBackButn;
    private DefinitionController controller;
    @Inject
    public LiveRoomDetailsVideoPresenterImpl liveRoomDetailsVideoPresenter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0)
                finish();
        }
    };
    private String str2;
    private String str3;
    private String str4;

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
        handler.removeMessages(0);
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
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData2(this, "Bearer " + token, courseId, "HLS", "true", "SD");
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData3(this, "Bearer " + token, courseId, "HLS", "true", "LD");
        liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData4(this, "Bearer " + token, courseId, "HLS", "true", "UD");
    }

    @Override
    public void onLiveRoomDetailsSuccess(LiveRoomDetailsBean liveRoomDetailsBean) {
        if (liveRoomDetailsBean.getCode() == 0) {
            if (TextUtils.isEmpty(liveRoomDetailsBean.getContent().getCourseName())) {
                yingxiangDetailsVideoTitle.setText("");
            } else {
                yingxiangDetailsVideoTitle.setText(liveRoomDetailsBean.getContent().getCourseName());
            }
        }
    }

    @Override
    public void onLiveRoomDetailsError(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrlSuccess(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        LiveRoomDetailsVideoUrlBean contentUrlBean = liveRoomDetailsVideoUrlBean;
        //视频地址
        String str1 = contentUrlBean.getContent();
        LogUtils.i("请求到的地址1", str1);
        //获取不同清晰度的视频路径
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", str1);
        videos.put("高清", str2);
        videos.put("超清", str3);
        videos.put("原画", str4);
        player.setDefinitionVideos(videos);
        player.setScreenScale(SCREEN_SCALE_MATCH_PARENT);
        player.setVideoController(controller);
        player.setTitle("视屏详情");
        player.start();
    }

    @Override
    public void onLiveRoomDetailsVideoUrlError(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrl2Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        LiveRoomDetailsVideoUrlBean contentUrlBean = liveRoomDetailsVideoUrlBean;
        str2 = contentUrlBean.getContent();
        LogUtils.i("请求到的地址2", str2);

    }

    @Override
    public void onLiveRoomDetailsVideoUrl2Error(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrl3Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        LiveRoomDetailsVideoUrlBean contentUrlBean = liveRoomDetailsVideoUrlBean;
        str3 = contentUrlBean.getContent();
        LogUtils.i("请求到的地址3", str3);

    }

    @Override
    public void onLiveRoomDetailsVideoUrl3Error(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrl4Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        LiveRoomDetailsVideoUrlBean contentUrlBean = liveRoomDetailsVideoUrlBean;
        str4 = contentUrlBean.getContent();
        LogUtils.i("请求到的地址4", str4);

    }

    @Override
    public void onLiveRoomDetailsVideoUrl4Error(String msg) {

    }

    @Override
    protected LiveRoomDetailsVideoPresenterImpl initInject() {
        activityComponent.inject(this);
        return liveRoomDetailsVideoPresenter;
    }

    @OnClick({R.id.video_back_butn, R.id.finish_butn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video_back_butn:
                finish();
                break;
            case R.id.finish_butn:
                player.release();
                initMessage();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }

    private void initMessage() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.initmessage)
                        .build();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

}
