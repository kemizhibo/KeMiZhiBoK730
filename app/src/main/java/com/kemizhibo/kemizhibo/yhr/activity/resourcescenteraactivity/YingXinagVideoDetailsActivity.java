package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.CommentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentCommentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentLoading;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.YingXiangDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import static com.dueeeke.videoplayer.player.IjkVideoView.SCREEN_SCALE_MATCH_PARENT;

public class YingXinagVideoDetailsActivity extends BaseMvpActivity<YingXiangDetailsVideoPresenterImpl> implements YingXiangDetailsVideoView{

    @Inject
    public YingXiangDetailsVideoPresenterImpl yingXiangDetailsVideoPresenter;
    @BindView(R.id.yingxiang_details_shoucang_imageview)
    ImageView yingxiangDetailsShoucangImageview;
    @BindView(R.id.yingxiang_details_teacher_touxiang)
    CircleImageView yingxiangDetailsTeacherTouxiang;
    @BindView(R.id.player)
    DefinitionIjkVideoView ijkVideoView;
    @BindView(R.id.comment_springview)
    SpringView commentSpringview;
    @BindView(R.id.video_back_butn)
    RelativeLayout videoBackButn;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.comment_frame_layout)
    FrameLayout commentFrameLayout;
    @BindView(R.id.detail_framlayout)
    FrameLayout detailFramlayout;
    private BottomSheetDialog dialog;
    //教师培训直播收藏
    private CollectionBean collectionBeans;
    private String token;
    @BindView(R.id.yingxiang_details_video_title)
    TextView yingxiangDetailsVideoTitle;
    @BindView(R.id.yingxiang_details_shoucang_butn)
    LinearLayout yingxiangDetailsShoucangButn;
    @BindView(R.id.yingxiang_details_teacher_name)
    TextView yingxiangDetailsTeacherName;
    @BindView(R.id.yingxiang_details_teacher_type)
    TextView yingxiangDetailsTeacherType;
    @BindView(R.id.yingxiang_details_teacher_jieshao)
    TextView yingxiangDetailsTeacherJieshao;
    @BindView(R.id.yingxiang_details_video_jieshao)
    TextView yingxiangDetailsVideoJieshao;
    @BindView(R.id.yingxiang_details_comment_num_txt)
    TextView yingxiangDetailsCommentNumTxt;
    @BindView(R.id.detail_page_time)
    TextView detailPageTime;
    @BindView(R.id.detail_page_title)
    TextView detailPageTitle;
    @BindView(R.id.detail_page_do_comment)
    TextView detailPageDoComment;
    @BindView(R.id.comment_recyclerview)
    RecyclerView commentRecyclerview;
    private String courseId;
    private String content;
    //评论列表项
    CommentAdapter commentAdapter;
    private List<CommentBean.ContentBean.DataBean> commentList = new ArrayList<>();
    private EditText commentText;
    private Button bt_comment1;
    //token
    private SharedPreferences sp;
    //评论ID
    private String commentId;
    private ImageView comment_dianzan;
    private DefinitionController controller;
    //定时器
    private Timer timer;
    //视频当前播放时长
    private long currentPosition;
    //记录返回值
    private String oneLookBeanMessage;
    //上或者下拉的状态判断
    int isUp = 1;
    private int page = 1;
    private List<CommentBean.ContentBean.DataBean> data;
    private YingXiangDetailsVideoBean.ContentBean dataBeans;
    private CommentBean.ContentBean tatle;
    private String targetId;
    private String str2;
    private String str3;
    private String str4;
    private String str;
    private long duration;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ying_xinag_video_details;
    }

    @Override
    protected void initData() {
        //展示列表
        controller = new DefinitionController(this);
        //播放视频
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        //评论列表
        initComment();
    }

    @Override
    protected void getData() {
        super.getData();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        detailFramlayout.setVisibility(View.VISIBLE);
        relativelayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_framlayout, new FramgmentLoading()).commit();
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId);
        yingXiangDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData2(this, "Bearer " + token, courseId, "HLS", "true", "SD");
        yingXiangDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData3(this, "Bearer " + token, courseId, "HLS", "true", "LD");
        yingXiangDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData4(this, "Bearer " + token, courseId, "HLS", "true", "UD");
        //展示加载图片
        commentFrameLayout.setVisibility(View.VISIBLE);
        commentRecyclerview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.comment_frame_layout, new FramgmentLoading()).commit();
        isUp = 1;
        page = 1;
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, page + "", "10", "4");
        LogUtils.i("啊哈2","我已经在全速前进了");
    }


    @SuppressLint("NewApi")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @OnClick(R.id.video_back_butn)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (!ijkVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    //发表评论成功
    @Override
    public void onPutCommentSuccess(CommentDetailBean commentDetailBean) {
        //发表成功以后吧数据添加到评论列表中然后刷新适配器，刷新列表数据
        if (commentDetailBean.getCode() == 0) {
            isUp = 1;
            page = 1;
            if (!TextUtils.isEmpty(courseId)){
                yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, page + "", "10", "4");
                Transparent.showErrorMessage(this, "评论成功～");
            }
        }
        if (commentDetailBean.getCode() == 401 || commentDetailBean.getCode() == 801) {
            initDialogToLogin();
        } else {
            //ToastUtils.showToast("当前不支持表情添加，敬请期待");
        }
    }

    @Override
    public void onPutCommentError(String msg) {
        Transparent.showErrorMessage(this, "评论失败");
    }

    //回复评论
    @Override
    public void onReplyCommentSuccess(ReplyCommentBean replyCommentBean) {
        //回复评论成功以后，添加到子集合，然后展示，刷新两个适配器
        if (replyCommentBean.getCode() == 0) {
            if (!TextUtils.isEmpty(courseId)){
                yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
            }
        } else if (replyCommentBean.getCode() == 401 || replyCommentBean.getCode() == 801) {
            //Transparent.showErrorMessage(this, "登录失效请重新登录");
        }
    }

    @Override
    public void onReplyCommentError(String msg) {

    }

    //删除
    @Override
    public void onDeleteCommentSuccess(DeleteCommentBean deleteCommentBean) {
        //如果数据返回成功，就删除,刷新适配器
        if (deleteCommentBean.getCode() == 0) {
            //commentList.clear();
            isUp = 1;
            page = 1;
            if (!TextUtils.isEmpty(courseId)){
                yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, page + "", "10", "4");
            }
            //commentAdapter.notifyDataSetChanged();
            Transparent.showErrorMessage(this, "评论删除成功～");
        } else if (deleteCommentBean.getCode() == 401 || deleteCommentBean.getCode() == 801) {
            initDialogToLogin();
        }
    }

    @Override
    public void onDeleteCommentError(String msg) {
        Transparent.showErrorMessage(this, "删除失败");
    }

    // 收藏
    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        if (collectionBean.getCode() == 0) {
            collectionBeans = collectionBean;
            if (collectionBean.getMessage().equals("添加收藏成功")) {
                yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_select);
                Transparent.showErrorMessage(this, "收藏成功～");
            } else if (collectionBean.getMessage().equals("取消收藏成功")) {
                yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_kong);
                Transparent.showErrorMessage(this, "取消收藏成功～");
            }
        } else if (collectionBean.getCode() == 401 || collectionBean.getCode() == 801) {
            initDialogToLogin();
        }
    }

    @Override
    public void onGetCollectionError(String msg) {
        Transparent.showErrorMessage(this, "收藏失败请重试～");
    }

    //点赞
    @Override
    public void onGetLikeSuccess(GetLikeBean getLikeBean) {
        if (getLikeBean.getCode() == 0) {
            if ("CANCEL".equals(getLikeBean.getContent())) {
                comment_dianzan.setBackgroundResource(R.mipmap.dianzan_2);
            } else {
                comment_dianzan.setBackgroundResource(R.mipmap.getlike_select_2);
            }
        } else if (getLikeBean.getCode() == 401 || getLikeBean.getCode() == 801) {
            initDialogToLogin();
        }
    }

    @Override
    public void onGetLikeError(String msg) {
        Transparent.showErrorMessage(this, "点赞失败");
    }

    @Override
    public void onGetOneLookError(String msg) {
        ToastUtils.showToast("添加浏览记录失败");
    }

    // 停止定时器
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            // 一定设置为null，否则定时器不会被回收
            timer = null;
        }
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog() {
        dialog = new BottomSheetDialog(this, R.style.BottomSheetEdit);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout, null);
        commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        bt_comment1 = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        bt_comment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //正常点击的逻辑
                content = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    yingXiangDetailsVideoPresenter.getPutCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, content, "");
                   /* CommentDetailBean detailBean = new CommentDetailBean("小明", commentContent,"刚刚");
                    adapter.addTheCommentData(detailBean);
                    Toast.makeText(MainActivity.this,"评论成功",Toast.LENGTH_SHORT).show();*/
                } else {
                    Toast.makeText(YingXinagVideoDetailsActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length() > 2) {
                    bt_comment1.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment1.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    //视屏详情
    @Override
    public void onYingXiangDetailsVideoSuccess(YingXiangDetailsVideoBean yingXiangDetailsVideoBean) {
        if (yingXiangDetailsVideoBean.getCode() == 0) {
            dataBeans = yingXiangDetailsVideoBean.getContent();
            if (dataBeans.getVideoDuration() != 0) {
                duration = (long) dataBeans.getVideoDuration();
            }
            initYingXiangDetailsVideoData();
            initYingXiangDetailsVideoUserData();
            //判断是否收藏过
            isCollection();
            detailFramlayout.setVisibility(View.GONE);
            relativelayout.setVisibility(View.VISIBLE);
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoUrlData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "HLS", "true", "HD");
        } else if (yingXiangDetailsVideoBean.getCode() == 401 || yingXiangDetailsVideoBean.getCode() == 801) {
            initDialogToLogin();
        }
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                } else {
                                    Intent intent = new Intent(YingXinagVideoDetailsActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        })
                        .build();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

    private void initYingXiangDetailsVideoUserData() {
        //老师的名字
        if (TextUtils.isEmpty(dataBeans.getTeacher().getName().toString().trim())) {
            yingxiangDetailsTeacherName.setText("佚名");
        } else {
            yingxiangDetailsTeacherName.setText(dataBeans.getTeacher().getName().toString().trim());
        }
        //老师的职称
        if (TextUtils.isEmpty(dataBeans.getTeacher().getEducation().toString().trim())) {
            yingxiangDetailsTeacherType.setText("暂无职称");
        } else {
            yingxiangDetailsTeacherType.setText(dataBeans.getTeacher().getEducation().toString().trim());
        }
        //老师的介绍
        if (TextUtils.isEmpty(dataBeans.getTeacher().getCareer().toString().trim())) {
            yingxiangDetailsTeacherJieshao.setText("暂无介绍");
        } else {
            yingxiangDetailsTeacherJieshao.setText(dataBeans.getTeacher().getCareer().toString().trim());
        }
        //老师的头像
        Glide.with(this).load(dataBeans.getTeacher().getPicPath())
                .error(R.mipmap.milier)
                .placeholder(R.mipmap.milier)
                .into(yingxiangDetailsTeacherTouxiang);//头像
    }

    private void initYingXiangDetailsVideoData() {
        //视频的标题
        if (TextUtils.isEmpty(dataBeans.getCourseName().toString().trim())) {
            yingxiangDetailsVideoTitle.setText("暂无标题");
        } else {
            yingxiangDetailsVideoTitle.setText(dataBeans.getCourseName().toString().trim());
        }
        //老师头像
        Glide.with(this).load(dataBeans.getTeacher().getPicPath());
        //视频的介绍
        if (TextUtils.isEmpty(dataBeans.getContext().toString().trim())) {
            yingxiangDetailsVideoJieshao.setText("暂无介绍");
        } else {
            yingxiangDetailsVideoJieshao.setText(dataBeans.getContext().toString().trim());
        }
    }

    private void isCollection() {
        if (dataBeans.getFavouriteHistory() == 1) {
            yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_select);
        } else {
            yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_kong);
        }
    }

    @Override
    public void onYingXiangDetailsVideoError(String msg) {

    }

    //获取视频地址
    @Override
    public void onYingXiangDetailsVideoUrlSuccess(YingXiangDetailsVideoUrlBean yingXiangDetailsVideoUrlBean) {
        if (yingXiangDetailsVideoUrlBean.getCode() == 0) {
           if (!TextUtils.isEmpty(yingXiangDetailsVideoUrlBean.getContent())||yingXiangDetailsVideoUrlBean.getContent()!=null){
               str = yingXiangDetailsVideoUrlBean.getContent();
               ijkVideoView.setPlayerConfig(new PlayerConfig.Builder()
                       .setCustomMediaPlayer(new IjkPlayer(this) {
                           @Override
                           public void setOptions() {
                               //精准seek
                               mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
                           }
                       })
                       //.autoRotate()//自动旋转屏幕
                       .build());
               startVideo();
           }
        }
    }

    private void startVideo() {
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", str);
        videos.put("高清", str2);
        videos.put("原画", str3);
        videos.put("超清", str4);
        ijkVideoView.setDefinitionVideos(videos);
        ijkVideoView.setScreenScale(SCREEN_SCALE_MATCH_PARENT);
        ijkVideoView.setVideoController(controller);
        ijkVideoView.setTitle("视屏详情");
        if (dataBeans.getWatchTime() == 0) {
            ijkVideoView.skipPositionWhenPlay(0);
            ijkVideoView.start();
            yingXiangDetailsVideoPresenter.getOneLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "", "", courseId, "", "0");
        } else {
            ijkVideoView.skipPositionWhenPlay(dataBeans.getWatchTime());
            ijkVideoView.start();
            //设置时间
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //获取视频当前播放时长
                    currentPosition = ijkVideoView.getCurrentPosition();
                    yingXiangDetailsVideoPresenter.getMoreLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), "0");
                }
            }, 0, 5000);
        }
    }

    //第一次记录播放时间
    @Override
    public void onGetOneLookSuccess(OneLookBean oneLookBean) {
        if (oneLookBean.getCode() == 0) {
                // 初始化定时器
                oneLookBeanMessage = oneLookBean.getMessage();
                // 初始化定时器
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //获取视频当前播放时长
                        currentPosition = ijkVideoView.getCurrentPosition();
                        yingXiangDetailsVideoPresenter.getMoreLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), "0");
                    }
                }, 0, 5000);
        }
    }

    @Override
    public void onGetMoreLookSuccess(final OneLookBean oneLookBean) {
        if (oneLookBean.getCode() == 0) {
            //视频总时长减去当前时长
            //ijkVideoView.release();
            if (duration - currentPosition <= 2000) {
                yingXiangDetailsVideoPresenter.getLastLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "0", "", courseId, "0", "1");
            }
        }
    }

    @Override
    public void onGetMoreLookError(String msg) {

    }

    //播放完毕
    @Override
    public void onGetLastLookSuccess(OneLookBean oneLookBean) {
        stopTimer();
    }

    @Override
    public void onGetLastLookError(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrl2Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        if (!TextUtils.isEmpty(liveRoomDetailsVideoUrlBean.getContent())||liveRoomDetailsVideoUrlBean.getContent()!=null){
            str2 = liveRoomDetailsVideoUrlBean.getContent();
        }
    }

    @Override
    public void onLiveRoomDetailsVideoUrl2Error(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrl3Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        if (!TextUtils.isEmpty(liveRoomDetailsVideoUrlBean.getContent())||liveRoomDetailsVideoUrlBean.getContent()!=null){
            str3 = liveRoomDetailsVideoUrlBean.getContent();
        }
    }

    @Override
    public void onLiveRoomDetailsVideoUrl3Error(String msg) {

    }

    @Override
    public void onLiveRoomDetailsVideoUrl4Success(LiveRoomDetailsVideoUrlBean liveRoomDetailsVideoUrlBean) {
        /*LiveRoomDetailsVideoUrlBean contentUrlBean = liveRoomDetailsVideoUrlBean;
        str4 = contentUrlBean.getContent();*/
        if (!TextUtils.isEmpty(liveRoomDetailsVideoUrlBean.getContent())||liveRoomDetailsVideoUrlBean.getContent()!=null){
            str4 = liveRoomDetailsVideoUrlBean.getContent();
        }
    }

    @Override
    public void onLiveRoomDetailsVideoUrl4Error(String msg) {

    }

    @Override
    protected void onStart() {
        super.onStart();

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
        stopTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ijkVideoView.release();
        stopTimer();
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
        stopTimer();
    }

    @Override
    public void onYingXiangDetailsVideoUrlError(String msg) {

    }

    //获取评论成功
    @Override
    public void onYingXiangDetailsVideoCommentSuccess(CommentBean commentBean) {
        if (commentBean.getCode()==0){
            data = commentBean.getContent().getData();
            tatle = commentBean.getContent();
            if (isUp == 1) {
                LogUtils.i("啊哈1","我已经在全速前进了");
                commentList.clear();
                commentList.addAll(data);
                if (commentList.size() > 0) {
                    //评论的数量
                    if (0 == tatle.getTotal()) {
                        yingxiangDetailsCommentNumTxt.setText("暂无评论");
                    } else {
                        yingxiangDetailsCommentNumTxt.setText("评论(" + tatle.getTotal() + ")");
                    }
                    commentFrameLayout.setVisibility(View.GONE);
                    commentRecyclerview.setVisibility(View.VISIBLE);
                    commentAdapter.notifyDataSetChanged();
                } else {
                    //展示为空图片
                    commentFrameLayout.setVisibility(View.VISIBLE);
                    commentRecyclerview.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.comment_frame_layout, new FramgmentCommentEmpty()).commit();
                    yingxiangDetailsCommentNumTxt.setText("暂无评论");
                    commentAdapter.notifyDataSetChanged();
                }
            } else if (isUp == 2) {
                commentList.addAll(data);
                commentAdapter.notifyDataSetChanged();
            }
        }else if (commentBean.getCode()==401||commentBean.getCode()==801){
            initDialogToLogin();
        }
    }

    private void initComment() {
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_item_layout, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        commentRecyclerview.setLayoutManager(layoutManager);
        //上拉下拉动画效果
        commentRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //滑动卡顿
        commentRecyclerview.setHasFixedSize(true);
        commentRecyclerview.setNestedScrollingEnabled(false);
        commentAdapter = new CommentAdapter(R.layout.comment_item_layout, commentList);
        //点赞11
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                    ToastUtils.showToast("点击太快了，请休息休息您的手指");
                } else {
                    comment_dianzan = (ImageView) view.findViewById(R.id.comment_dianzan);
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    targetId = String.valueOf(commentList.get(position).getCommentId());
                    //改变点赞状态，并且刷新数据
                    if (commentList.get(position).getPraiseHistory() == 1) {
                        commentList.get(position).setPraiseHistory(0);
                        commentList.set(position, commentList.get(position));
                    } else {
                        commentList.get(position).setPraiseHistory(1);
                        commentList.set(position, commentList.get(position));
                    }
                    //commentAdapter.notifyDataSetChanged();
                    yingXiangDetailsVideoPresenter.getLikeData(YingXinagVideoDetailsActivity.this, "Bearer " + token, targetId, "4");
                }
            }
        });
        //删除
        commentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                commentId = String.valueOf(commentList.get(position).getCommentId());
                //取出用户ID
                sp = getSharedPreferences("logintoken", 0);
                int userId = sp.getInt("userId", 0);
                if (commentList.get(position).getUserId() == userId) {
                    showDeleteComentDialog();
                }
            }
        });
        /*commentAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                return true;
            }
        });*/
        commentRecyclerview.setAdapter(commentAdapter);
        commentSpringview.setType(SpringView.Type.FOLLOW);
        commentSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        page = 1;
                        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, page+"", "10", "4");
                        commentSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 2;
                        page++;
                        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, page+"", "10", "4");
                        commentSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        commentSpringview.setHeader(new AliHeader(this, true));   //参数为：logo图片资源，是否显示文字
        commentSpringview.setFooter(new AliFooter(this, true));
    }

    @Override
    public void onYingXiangDetailsVideoCommentError(String msg) {
        /*commentFrameLayout.setVisibility(View.VISIBLE);
        commentSpringview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.comment_frame_layout,new FramgmentError()).commit();*/
    }

    private void showDeleteComentDialog() {
        dialog = new BottomSheetDialog(this);
        View collectiondialogview = LayoutInflater.from(this).inflate(R.layout.delete_dialog_layout, null);
        Button yesDelete = (Button) collectiondialogview.findViewById(R.id.delete_yes_butn);
        Button noDelete = (Button) collectiondialogview.findViewById(R.id.delete_no_butn);
        dialog.setContentView(collectiondialogview);
        View parent = (View) collectiondialogview.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        collectiondialogview.measure(0, 0);
        behavior.setPeekHeight(collectiondialogview.getMeasuredHeight());
        yesDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求删除接口
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                yingXiangDetailsVideoPresenter.getDeleteCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, commentId, "4");
                dialog.dismiss();
            }
        });
        noDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected YingXiangDetailsVideoPresenterImpl initInject() {
        activityComponent.inject(this);
        return yingXiangDetailsVideoPresenter;
    }


    @OnClick({R.id.yingxiang_details_shoucang_butn, R.id.detail_page_do_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yingxiang_details_shoucang_butn:
                //点击收藏判断是否登录，登录成功改变图片，失败弹出popwindow
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                yingXiangDetailsVideoPresenter.getCollectionData(this, "Bearer " + token, courseId);
                //如果成功拿到数据，就正常收藏，否则就弹框
                break;
            case R.id.detail_page_do_comment:
                showCommentDialog();
                break;
        }
    }
}
