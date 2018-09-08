package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.ForTeachPlayUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.forteachbean.InitLectureBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomDetailsView;
import java.util.LinkedHashMap;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import static com.dueeeke.videoplayer.player.IjkVideoView.SCREEN_SCALE_MATCH_PARENT;
public class LiveRoomDetailsActivity extends BaseMvpActivity<LiveRoomDetailsVideoPresenterImpl> implements LiveRoomDetailsView {

    String moduleId;
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
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                finish();
            }
        }
    };
    private String str2;
    private String str3;
    private String str4;
    private String courseId;
    private String kpointId;
    private SharedPreferences sp;
    private String token;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_room_details_finish;
    }

    @Override
    protected void initData() {
        initVideo();
        //接受传来的值
        Intent intent = getIntent();
        moduleId = intent.getStringExtra(Constants.MODULE_ID);
        LogUtils.i("传过去的22",moduleId);
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
                //.autoRotate()//自动旋转屏幕
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
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        //liveRoomDetailsVideoPresenter.getLiveRoomDetailsVideoData(this, "Bearer " + token, moduleId);
        liveRoomDetailsVideoPresenter.getInitLectureData(this,"Bearer "+ token,moduleId);
    }


    //保存授课记录
    @Override
    public void onLectureSuccess(LectureBean lectureBean) {

    }

    @Override
    public void onLectureError(String msg) {

    }
    //预览一键授课
    @Override
    public void onInitLectureSuccess(InitLectureBean initLectureBean) {
        if (initLectureBean.getCode() == 0) {
            if (TextUtils.isEmpty(initLectureBean.getContent().getFileName())) {
                yingxiangDetailsVideoTitle.setText("");
            } else {
                yingxiangDetailsVideoTitle.setText(initLectureBean.getContent().getFileName());
            }
            courseId = String.valueOf(initLectureBean.getContent().getCourseId());
            kpointId = String.valueOf(initLectureBean.getContent().getKpointId());
            if(!TextUtils.isEmpty(courseId)&&!TextUtils.isEmpty(kpointId)){
                liveRoomDetailsVideoPresenter.getForTeachPlayUrlData(this,courseId,"HLS","true","HD",kpointId);
                liveRoomDetailsVideoPresenter.getForTeachPlayUrlData2(this,courseId,"HLS","true","SD",kpointId);
                liveRoomDetailsVideoPresenter.getForTeachPlayUrlData3(this,courseId,"HLS","true","LD",kpointId);
                liveRoomDetailsVideoPresenter.getForTeachPlayUrlData4(this,courseId,"HLS","true","UD",kpointId);
            }
        }
    }

    @Override
    public void onInitLectureError(String msg) {

    }

    //h获取一件授课的视频播放地址
    @Override
    public void onForTeachPlayUrlSuccess(ForTeachPlayUrlBean forTeachPlayUrlBean) {
        if (forTeachPlayUrlBean.getCode()==0){
            String str1 = forTeachPlayUrlBean.getContent();
            //视频地址
            //获取不同清晰度的视频路径
            LinkedHashMap<String, String> videos = new LinkedHashMap<>();
            videos.put("标清", str1);
            /*videos.put("高清", str2);
            videos.put("超清", str3);
            videos.put("原画", str4);*/
            player.setDefinitionVideos(videos);
            player.setScreenScale(SCREEN_SCALE_MATCH_PARENT);
            player.setVideoController(controller);
            player.setTitle("视屏详情");
            player.start();
        }
    }

    @Override
    public void onForTeachPlayUrlError(String msg) {

    }

    @Override
    public void onForTeachPlayUrl2Success(ForTeachPlayUrlBean forTeachPlayUrlBean) {
        str2 = forTeachPlayUrlBean.getContent();
        LogUtils.i("地址2",str2);
    }

    @Override
    public void onForTeachPlayUrl2Error(String msg) {

    }

    @Override
    public void onForTeachPlayUrl3Success(ForTeachPlayUrlBean forTeachPlayUrlBean) {
        str3 = forTeachPlayUrlBean.getContent();
        LogUtils.i("地址3",str3);
    }

    @Override
    public void onForTeachPlayUrl3Error(String msg) {

    }

    @Override
    public void onForTeachPlayUrl4Success(ForTeachPlayUrlBean forTeachPlayUrlBean) {
        str4 = forTeachPlayUrlBean.getContent();
        LogUtils.i("地址4",str4);
    }

    @Override
    public void onForTeachPlayUrl4Error(String msg) {

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
                SharedPreferences sp = getSharedPreferences("logintoken", 0);
                String token = sp.getString("token", "");
                liveRoomDetailsVideoPresenter.getLectureData(LiveRoomDetailsActivity.this,"Bearer "+token,moduleId,"3");
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
