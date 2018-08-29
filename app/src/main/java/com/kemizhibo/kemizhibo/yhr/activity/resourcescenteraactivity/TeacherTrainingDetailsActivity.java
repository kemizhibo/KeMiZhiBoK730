package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
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
import android.widget.ScrollView;
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
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.TeacherTrainingDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.YingXiangDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class TeacherTrainingDetailsActivity extends BaseMvpActivity<TeacherTrainingDetailsVideoPresenterImpl> implements TeacherTrainingDetailsVideoView {

    @Inject
    public TeacherTrainingDetailsVideoPresenterImpl teacherTrainingDetailsVideoPresenter;
    @BindView(R.id.video_back_butn)
    ImageView videoBackButn;
    @BindView(R.id.player)
    DefinitionIjkVideoView player;
    @BindView(R.id.yingxiang_details_video_title)
    TextView yingxiangDetailsVideoTitle;
    @BindView(R.id.yingxiang_details_shoucang_imageview)
    ImageView yingxiangDetailsShoucangImageview;
    @BindView(R.id.yingxiang_details_shoucang_butn)
    LinearLayout yingxiangDetailsShoucangButn;
    @BindView(R.id.detail_page_title)
    TextView detailPageTitle;
    @BindView(R.id.yingxiang_details_video_jieshao)
    TextView yingxiangDetailsVideoJieshao;
    @BindView(R.id.yingxiang_details_comment_num_txt)
    TextView yingxiangDetailsCommentNumTxt;
    @BindView(R.id.detail_page_above_container)
    LinearLayout detailPageAboveContainer;
    @BindView(R.id.comment_frame_layout)
    FrameLayout commentFrameLayout;
    @BindView(R.id.comment_recyclerview)
    RecyclerView commentRecyclerview;
    @BindView(R.id.comment_springview)
    SpringView commentSpringview;
    @BindView(R.id.jieshao_relativelayout)
    RelativeLayout jieshaoRelativelayout;
    @BindView(R.id.teacher_training_screview)
    ScrollView teacherTrainingScreview;
    @BindView(R.id.detail_page_do_comment)
    TextView detailPageDoComment;
    @BindView(R.id.pinglun_layout)
    RelativeLayout pinglunLayout;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;

    private TextView bt_comment;
    private BottomSheetDialog dialog;
    //教师培训直播收藏
    private CollectionBean collectionBeans;
    private String token;
    private String courseId;
    private String trim;

    //评论列表项
    CommentAdapter commentAdapter;
    private List<CommentBean.ContentBean.DataBean> commentList = new ArrayList<>();
    //防止快速点击
    private boolean isVisibility = false;
    //视频信息
    //private TeacherTrainingDetailsVideoBean.ContentBean.DataBean content;
    //发表评论
    private CommentDetailBean putCommentDetailBean;
    //评论列表
    private CommentBean.ContentBean.DataBean datalist;
    private EditText commentText;
    private Button bt_comment1;
    //点赞
    private GetLikeBean getLikeData;
    //token
    private SharedPreferences sp;
    //评论ID
    private String commentId;
    //刷新适配器的判断
    private boolean isFlag;
    private ImageView comment_dianzan;
    private TextView comment_text;
    private DefinitionController controller;
    //播放地址
    private TeacherTrainingDetailsVideoUrlBean contentUrlBean;
    //定时器
    private Timer timer;
    //视频当前播放时长
    private long currentPosition;
    //记录返回值
    private String oneLookBeanMessage;
    //private String watchTime;
    //上或者下拉的状态判断
    int isUp = 1;
    private int page;
    private int p;
    private List<CommentBean.ContentBean.DataBean> data;
    private TeacherTrainingDetailsVideoBean.ContentBean content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_training_details;
    }

    @Override
    protected void initData() {
        //initVideo();
        controller = new DefinitionController(this);
        //播放视频
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        //watchTime = intent.getStringExtra("watchTime");
        //LogUtils.i("播放判断从浏览记录传回来的毫秒值",watchTime);
    }


    @OnClick(R.id.video_back_butn)
    public void onViewClicked() {
        finish();
    }

    /*private void initVideo() {
        player.setPlayerConfig(new PlayerConfig.Builder()
                .setCustomMediaPlayer(new IjkPlayer(this) {
                    @Override
                    public void setOptions() {
                        //精准seek1
                        LogUtils.i("播放视屏时常", String.valueOf(mMediaPlayer.getDuration()));
                        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mMediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
                            @Override
                            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                                iMediaPlayer.start();
                            }
                        });
                    }
                })
                .autoRotate()//自动旋转屏幕
                .build());
    }*/


    @Override
    public void onBackPressed() {
        if (!player.onBackPressed()) {
            super.onBackPressed();
        }
    }

    //发表评论成功
    @Override
    public void onPutCommentSuccess(CommentDetailBean commentDetailBean) {
        //发表成功以后吧数据添加到评论列表中然后刷新适配器，刷新列表数据
        if (commentDetailBean.getCode() == 0) {
            teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            initDialogToLogin();
        }
        //然后把改评论的ID传给删除接口，判断是不是本人发的，长按时触发
    }

    @Override
    public void onPutCommentError(String msg) {
        Transparent.showErrorMessage(this, "评论失败请重试");
    }


    //删除
    @Override
    public void onDeleteCommentSuccess(DeleteCommentBean deleteCommentBean) {
        //如果数据返回成功，就删除,刷新适配器
        if (deleteCommentBean.getCode() == 0) {
            teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            initDialogToLogin();
        }
    }

    @Override
    public void onDeleteCommentError(String msg) {
        Transparent.showErrorMessage(this, "删除评论失败请重试");
    }
    //获取视屏详情信息
    @Override
    public void onTeacherTrainingDetailsVideoSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean) {
        if (teacherTrainingDetailsVideoBean.getCode() == 0) {
            content = teacherTrainingDetailsVideoBean.getContent();
            initYingXiangDetailsVideoData();
            //判断是否收藏过
            isCollection();
            teacherTrainingDetailsVideoPresenter.getTeacherTrainingDetailsVideoUrlData(this, "Bearer " + token, courseId, "HLS", "true", "HD");
        } else {
            initDialogToLogin();
        }
    }

    @Override
    public void onTeacherTrainingDetailsVideoError(String msg) {

    }
    //获取视频播放地址
    @Override
    public void onTeacherTrainingDetailsVideoUrlSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean) {
        if (teacherTrainingDetailsVideoUrlBean.getCode() == 0) {
            contentUrlBean = teacherTrainingDetailsVideoUrlBean;
            //播放视频
            startVideo();
            //ijkVideoView.setOnHierarchyChangeListener();
        }
    }

    @Override
    public void onTeacherTrainingDetailsVideoUrlError(String msg) {

    }

    // 收藏
    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        if (collectionBean.getCode() == 0) {
            collectionBeans = collectionBean;
            if (collectionBean.getMessage().equals("添加收藏成功")) {
                yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_select);
                Transparent.showSuccessMessage(this, "添加收藏成功");
            } else if (collectionBean.getMessage().equals("取消收藏成功")) {
                yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_kong);
                Transparent.showInfoMessage(this, "已取消收藏");
            }
        } else {
            initDialogToLogin();
        }
    }


    @Override
    public void onGetCollectionError(String msg) {
        Transparent.showErrorMessage(this, "收藏失败请重试");
    }

    //点赞
    @Override
    public void onGetLikeSuccess(GetLikeBean getLikeBean) {
        if (getLikeBean.getCode() == 0) {
            getLikeData = getLikeBean;
            /*if (commentList.get(p).getPraiseHistory()==1){
                commentList.get(p).setPraiseHistory(0);
            }else {
                commentList.get(p).setPraiseHistory(1);
            }
            //更改数据源的方法
            commentList.set(p,commentList.get(p));
            commentAdapter.notifyDataSetChanged();*/
            if (getLikeBean.getContent() == null) {
                comment_dianzan.setImageResource(R.mipmap.getlike_select_2);
            } else if (getLikeBean.getContent().equals("CANCEL")) {
                comment_dianzan.setImageResource(R.mipmap.dianzan_2);
            }
            //commentAdapter.notifyDataSetChanged();
            teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");

        } else {
            initDialogToLogin();
        }
    }

    @Override
    public void onGetLikeError(String msg) {
        Transparent.showErrorMessage(this, "点赞失败请重试");
    }

    @Override
    public void onGetOneLookError(String msg) {
        ToastUtils.showToast("浏览失败");
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
        dialog = new BottomSheetDialog(this);
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
                trim = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    teacherTrainingDetailsVideoPresenter.getPutCommentData(TeacherTrainingDetailsActivity.this, "Bearer " + token, courseId, trim, "");
                } else {
                    Toast.makeText(TeacherTrainingDetailsActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void getData() {
        super.getData();
        //SystemClock.sleep(200);
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        teacherTrainingDetailsVideoPresenter.getTeacherTrainingDetailsVideoData(this, "Bearer " + token, courseId);
        teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        LogUtils.i("视频介绍",token+"++"+courseId);
        isFlag = true;
    }



    private void initDialogToLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder
                .setView(R.layout.alertdialog_login)
                .setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TeacherTrainingDetailsActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

    private void initYingXiangDetailsVideoData() {
        //视频的标题
        if (TextUtils.isEmpty(content.getCourseName().toString().trim())) {
            yingxiangDetailsVideoTitle.setText("暂无标题");
        } else {
            yingxiangDetailsVideoTitle.setText(content.getCourseName().toString().trim());
        }
        //视频的介绍
        if (TextUtils.isEmpty(content.getContext().toString().trim())) {
            yingxiangDetailsVideoJieshao.setText("暂无介绍");
        } else {
            yingxiangDetailsVideoJieshao.setText(content.getContext().toString().trim());
        }
        //评论的数量
        if (content.getCommentnum()==null||content.getCommentnum().toString().trim()==""||(int)content.getCommentnum()==0){
            yingxiangDetailsCommentNumTxt.setText("暂无评论");
        }else {
            yingxiangDetailsCommentNumTxt.setText("评论(" + content.getCommentnum() + ")");
        }
    }

    private void isCollection() {
        if (content.getFavouriteHistory() == 1) {
            yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_select);
        } else {
            yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_kong);
        }
    }

    private void startVideo() {
        String str = contentUrlBean.getContent();
        //获取不同清晰度的视频路径
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", str);
        videos.put("高清", str);
        videos.put("原画", str);
        videos.put("超清", str);
        player.setDefinitionVideos(videos);
        player.setVideoController(controller);
        player.setTitle("视屏详情");

        if (content.getWatchTime()==0){
            player.skipPositionWhenPlay(0);
            player.start();
            teacherTrainingDetailsVideoPresenter.getOneLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "", "", courseId, "", "0");
        }else {
            player.skipPositionWhenPlay(content.getWatchTime());
            player.start();
            //设置时间
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    //获取视频当前播放时长
                    currentPosition = player.getCurrentPosition();
                    teacherTrainingDetailsVideoPresenter.getMoreLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), "0");
                }
            }, 0, 5000);
            //设置时间
           /* new IjkMediaPlayer().setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    teacherTrainingDetailsVideoPresenter.getOneLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "", "", courseId, "", "1");
                }
            });*/
        }
    }

    //第一次记录播放时间
    @Override
    public void onGetOneLookSuccess(OneLookBean oneLookBean) {
        if (oneLookBean.getCode() == 0) {
            oneLookBeanMessage = oneLookBean.getMessage();
                // 初始化定时器
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //获取视频当前播放时长
                        currentPosition = player.getCurrentPosition();
                        teacherTrainingDetailsVideoPresenter.getMoreLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), "0");

                       /* if (player.getDuration()-content.getWatchTime()<=10000){
                            teacherTrainingDetailsVideoPresenter.getMoreLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "", "", courseId, String.valueOf(currentPosition), "1");
                        }else {
                            teacherTrainingDetailsVideoPresenter.getMoreLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), "0");
                        }*/
                    }
                }, 0, 5000);

        } else {
            ToastUtils.showToast("当前网络状态不佳");
        }
    }

    @Override
    public void onGetMoreLookSuccess(OneLookBean oneLookBean) {
        //视频总时长减去当前时长
        if (player.getDuration()-content.getWatchTime()<=10000){
            LogUtils.i("总时长",player.getDuration()+"");
            teacherTrainingDetailsVideoPresenter.getMoreLookData(TeacherTrainingDetailsActivity.this, "Bearer " + token, "5000", oneLookBean.getMessage(), courseId, String.valueOf(currentPosition), "1");
        }
    }

    @Override
    public void onGetMoreLookError(String msg) {

    }
    //、、、、、、、、、、、视频播放的各种方法,第一次播放时记录位置，暂停时，看完时

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
        player.pause();
        stopTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
        stopTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.resume();
    }

    @Override
    protected TeacherTrainingDetailsVideoPresenterImpl initInject() {
        activityComponent.inject(this);
        return teacherTrainingDetailsVideoPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
        stopTimer();
    }


    //获取评论成功
    @Override
    public void onYingXiangDetailsVideoCommentSuccess(CommentBean commentBean) {
        data = commentBean.getContent().getData();
        LogUtils.i("评论列表数据",data.toString());
            /*//判断是否点过赞
            if (data.get(0).getPraiseHistory() == 1) {
                comment_dianzan.setImageResource(R.mipmap.getlike_select_2);
            } else {
                comment_dianzan.setImageResource(R.mipmap.dianzan_2);
            }*/
            if (isUp == 1) {
                commentList.clear();
                commentList.addAll(commentBean.getContent().getData());
                if (commentList.size() > 0) {
                    //展示列表
                    initComment();
                    //点赞
                    initCommentGetLike();
                    //删除
                    initCommentDelete();
            } else if (isUp == 2) {
                commentAdapter.addData(commentBean.getContent().getData());
                //commentList.addAll(commentBean.getContent().getData());
                //展示列表
                initComment();
                //点赞
                initCommentGetLike();
                //删除
                initCommentDelete();
            }
        }
        //解决滑动冲突
        /*appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    commentSpringview.setEnable(true);
                } else {
                    commentSpringview.setEnable(false);
                }
            }
        });*/
    }


    private void initCommentDelete() {
        commentAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                commentId = String.valueOf(commentList.get(position).getCommentId());
                //取出用户ID
                sp = getSharedPreferences("logintoken", 0);
                int userId = sp.getInt("userId", 0);
                if (commentList.get(position).getUserId() == userId) {
                    showDeleteComentDialog();
                }
                return true;
            }
        });
    }

    private void initCommentGetLike() {
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                p = position;
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                String targetId = String.valueOf(commentList.get(position).getCommentId());
                teacherTrainingDetailsVideoPresenter.getLikeData(TeacherTrainingDetailsActivity.this, "Bearer " + token, targetId, "4");
                LogUtils.i("点赞1", targetId + "+" + token);
            }
        });
    }

    private void initComment() {
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_item_layout, null);
        comment_text = (TextView) commentView.findViewById(R.id.comment_text);
        comment_dianzan = (ImageView) commentView.findViewById(R.id.comment_dianzan);
        commentRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        //上拉下拉动画效果
        commentRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //滑动卡顿
        commentRecyclerview.setHasFixedSize(true);
        commentRecyclerview.setNestedScrollingEnabled(false);
        commentAdapter = new CommentAdapter(R.layout.comment_item_layout, commentList);
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
                        teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(TeacherTrainingDetailsActivity.this, "Bearer " + token, courseId, String.valueOf(page), "10", "4");
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
                        teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(TeacherTrainingDetailsActivity.this, "Bearer " + token, courseId, String.valueOf(page), "10", "4");
                        commentSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        //commentSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        commentSpringview.setFooter(new AliFooter(this, true));
    }

    @Override
    public void onYingXiangDetailsVideoCommentError(String msg) {
        /*commentFrameLayout.setVisibility(View.VISIBLE);
        commentSpringview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.comment_frame_layout, new FramgmentError()).commit();*/
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
                teacherTrainingDetailsVideoPresenter.getDeleteCommentData(TeacherTrainingDetailsActivity.this, "Bearer " + token, commentId, "4");
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



    @OnClick({R.id.yingxiang_details_shoucang_butn, R.id.detail_page_do_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yingxiang_details_shoucang_butn:
                //点击收藏判断是否登录，登录成功改变图片，失败弹出popwindow
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                teacherTrainingDetailsVideoPresenter.getCollectionData(this, "Bearer " + token, courseId);
                //如果成功拿到数据，就正常收藏，否则就弹框
                break;
            case R.id.detail_page_do_comment:
                showCommentDialog();
                break;
        }
    }

}
