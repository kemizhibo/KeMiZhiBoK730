package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
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
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.YingXiangDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;
import com.liaoinstan.springview.container.AliFooter;
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
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class YingXinagVideoDetailsActivity extends BaseMvpActivity<YingXiangDetailsVideoPresenterImpl> implements YingXiangDetailsVideoView {

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
    ImageView videoBackButn;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.comment_frame_layout)
    FrameLayout commentFrameLayout;
    private TextView bt_comment;
    private BottomSheetDialog dialog;
    //教师培训直播收藏
    private CollectionBean collectionBeans;
    private String token;
    @BindView(R.id.yingxiang_details_video_title)
    TextView yingxiangDetailsVideoTitle;
    @BindView(R.id.yingxiang_details_shoucang_butn)
    TextView yingxiangDetailsShoucangButn;
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
    //防止快速点击
    private boolean isVisibility = false;
    //视频信息
    private YingXiangDetailsVideoBean.ContentBean contentBean;
    //视频作者信息
    private YingXiangDetailsVideoBean.ContentBean.TeacherBean teacherBean;
    //发表评论
    private CommentDetailBean putCommentDetailBean;
    //评论列表
    private CommentBean.ContentBean.DataBean datalist;
    private EditText commentText;
    private Button bt_comment1;
    //回复评论
    private CommentBean.ContentBean.DataBean replydatalist;
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
    private YingXiangDetailsVideoUrlBean contentUrlBean;
    //定时器
    private Timer timer;
    //视频当前播放时长
    private long currentPosition;
    //记录返回值
    private String oneLookBeanMessage;
    private String watchTime;
    //上或者下拉的状态判断
    int isUp = 1;
    private int page;
    //是否播放完毕的状态判断
    private String isEnd;
    private int p;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_ying_xinag_video_details;
    }

    @Override
    protected void initData() {
        initVideo();
        //播放视频
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
        watchTime = intent.getStringExtra("watchTime");
    }


    @OnClick(R.id.video_back_butn)
    public void onViewClicked() {
        finish();
    }

    private void initVideo() {
        controller = new DefinitionController(this);
        ijkVideoView.setPlayerConfig(new PlayerConfig.Builder()
                .setCustomMediaPlayer(new IjkPlayer(this) {
                    @Override
                    public void setOptions() {
                        //精准seek1
                        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
                        //拿到是否播放完的状态
                        mMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(IMediaPlayer iMediaPlayer) {
                                isEnd = 1 + "";
                            }
                        });
                    }
                })
                .autoRotate()//自动旋转屏幕
                .build());
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
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
        }
        //然后把改评论的ID传给删除接口，判断是不是本人发的，长按时触发
    }

    @Override
    public void onPutCommentError(String msg) {
        Transparent.showErrorMessage(this, "评论失败请重试");
    }

    //回复评论
    @Override
    public void onReplyCommentSuccess(ReplyCommentBean replyCommentBean) {
        //回复评论成功以后，添加到子集合，然后展示，刷新两个适配器
        if (replyCommentBean.getCode() == 0) {
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
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
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
        }
    }

    @Override
    public void onDeleteCommentError(String msg) {
        Transparent.showErrorMessage(this, "删除评论失败请重试");
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
            Transparent.showErrorMessage(this, "登录失效请重新登录");
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
            if (TextUtils.isEmpty((CharSequence) getLikeBean.getContent())) {
                comment_dianzan.setImageResource(R.mipmap.getlike_select_2);
            } else if (getLikeBean.getContent().equals("CANCEL")) {
                comment_dianzan.setImageResource(R.mipmap.dianzan_2);
            }

            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");


        } else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
        }
    }

    @Override
    public void onGetLikeError(String msg) {
        Transparent.showErrorMessage(this, "点赞失败请重试");
    }

    //第一次记录播放时间
    @Override
    public void onGetOneLookSuccess(OneLookBean oneLookBean) {
        if (oneLookBean.getCode() == 0) {
            oneLookBeanMessage = oneLookBean.getMessage();
        } else {
            ToastUtils.showToast("当前网络状态不佳");
            /*Transparent.showErrorMessage(this,"登录失效请重新登录");
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
            }).start();*/
        }
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

    @Override
    protected void getData() {
        super.getData();
        //SystemClock.sleep(200);
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId);
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoUrlData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "HLS", "true", "HD");
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "1", "10", "4");
        isFlag = true;
    }


    @Override
    public void onYingXiangDetailsVideoSuccess(YingXiangDetailsVideoBean yingXiangDetailsVideoBean) {
        LogUtils.i("789",yingXiangDetailsVideoBean.getCode()+"");
        if (yingXiangDetailsVideoBean.getCode() == 0) {
            contentBean = yingXiangDetailsVideoBean.getContent();
            if (!TextUtils.isEmpty(contentBean.toString())) {
                //判断是否收藏过
                isCollection();
                initYingXiangDetailsVideoData();
                teacherBean = contentBean.getTeacher();
            } else {
                if (!TextUtils.isEmpty(teacherBean.toString())) {
                    /*frameLayout.setVisibility(View.GONE);
                    relativelayout.setVisibility(View.VISIBLE);*/
                    initYingXiangDetailsVideoUserData();
                } else {
                    /*frameLayout.setVisibility(View.VISIBLE);
                    relativelayout.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FramgmentEmpty()).commit();*/
                }
            }
        }else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
        }

    }

    private void initYingXiangDetailsVideoUserData() {
        //老师的名字
        yingxiangDetailsTeacherName.setText(teacherBean.getName());
        //老师的职称
        yingxiangDetailsTeacherType.setText(teacherBean.getEducation());
        //老师的介绍
        yingxiangDetailsTeacherJieshao.setText(teacherBean.getCareer());
        //老师的头像
        Glide.with(this).load(teacherBean.getPicPath())
                .error(R.mipmap.milier)
                .placeholder(R.mipmap.milier)
                .into(yingxiangDetailsTeacherTouxiang);//头像
    }

    private void initYingXiangDetailsVideoData() {
        //视频的标题
        yingxiangDetailsVideoTitle.setText(contentBean.getCourseName());
        //Glide.with(this).load("http://192.168.1.101:8080" + contentBean.getTeacher().getPicPath())
        //视频的介绍
        yingxiangDetailsVideoJieshao.setText(contentBean.getContext());
        //评论的数量
        yingxiangDetailsCommentNumTxt.setText("评论(" + contentBean.getPlayCount() + ")");
    }

    private void isCollection() {
        if (contentBean.getFavouriteHistory() == 1) {
            yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_select);
        } else {
            yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_kong);
        }
    }

    @Override
    public void onYingXiangDetailsVideoError(String msg) {
        /*frameLayout.setVisibility(View.VISIBLE);
        relativelayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FramgmentError()).commit();*/
    }

    //获取视频地址
    @Override
    public void onYingXiangDetailsVideoUrlSuccess(YingXiangDetailsVideoUrlBean yingXiangDetailsVideoUrlBean) {
        if (yingXiangDetailsVideoUrlBean.getCode() == 0) {
            contentUrlBean = yingXiangDetailsVideoUrlBean;
            //播放视频
            startVideo();
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
        ijkVideoView.setDefinitionVideos(videos);
        ijkVideoView.setVideoController(controller);
        ijkVideoView.setTitle("视屏详情");

        /*//高级设置
        PlayerConfig playerConfig = new PlayerConfig.Builder()
                .enableCache() //启用边播边缓存功能
                .autoRotate() //启用重力感应自动进入/退出全屏功能---
                .savingProgress() //保存播放进度
                .build();
        ijkVideoView.setPlayerConfig(playerConfig);*/
        //如果接受到的总时长为空说明重新播放，否则，根据时间节点续播
        /*if(contentBean.getWatchTime()!=0){
            ijkVideoView.seekTo(Long.parseLong(watchTime));
            LogUtils.i("播放返回时续播",watchTime);
        }else {
            ijkVideoView.start();
        }*/
        ijkVideoView.start();
        yingXiangDetailsVideoPresenter.getOneLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "", "", courseId, "", "0");
        // 初始化定时器
        timer = new Timer();
        //设置时间
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //获取视频当前播放时长
                currentPosition = ijkVideoView.getCurrentPosition();
                yingXiangDetailsVideoPresenter.getOneLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), "0");
                LogUtils.i("播放已完成", String.valueOf(currentPosition) + "++" + isEnd);
                /*if (isEnd == 1+""){
                    yingXiangDetailsVideoPresenter.getOneLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), isEnd);
                    LogUtils.i("播放已完成",String.valueOf(currentPosition)+"++"+isEnd);
                }else {
                   isEnd = 0+"";
                    yingXiangDetailsVideoPresenter.getOneLookData(YingXinagVideoDetailsActivity.this, "Bearer " + token, "5000", oneLookBeanMessage, courseId, String.valueOf(currentPosition), isEnd);
                    LogUtils.i("播放未完成",String.valueOf(currentPosition)+"++"+isEnd);
                }*/
            }
        }, 0, 5000);
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
        //视频封面图设置失败图片
    }

    //获取评论成功
    @Override
    public void onYingXiangDetailsVideoCommentSuccess(CommentBean commentBean) {
        if (commentBean.getCode() == 0) {
            if (isUp==1){
                commentList.clear();
                commentList.addAll(commentBean.getContent().getData());
                //展示列表
                initComment();
                //点赞
                initCommentGetLike();
                //删除
                initCommentDelete();
            }else if (isUp==2){
                commentList.addAll(commentBean.getContent().getData());
                //展示列表
                initComment();
                //点赞
                initCommentGetLike();
                //删除
                initCommentDelete();
            }

        } else {


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
                if (commentList.get(position).getUserId()==userId){
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
                yingXiangDetailsVideoPresenter.getLikeData(YingXinagVideoDetailsActivity.this, "Bearer " + token, targetId, "4");
                LogUtils.i("点赞1", targetId + "+" + token);
            }
        });
    }

    private void initComment() {
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_item_layout, null);
        comment_text = (TextView) commentView.findViewById(R.id.comment_text);
        comment_dianzan = (ImageView) commentView.findViewById(R.id.comment_dianzan);
        //判断是否点过赞
        if (contentBean.getPraiseHistory() == 1) {
            comment_dianzan.setImageResource(R.mipmap.getlike_select_2);
        } else {
            comment_dianzan.setImageResource(R.mipmap.dianzan_2);
        }
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
                        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, String.valueOf(page), "10", "4");
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
                        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, String.valueOf(page), "10", "4");
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
        //切换控件
        /*frameLayout.setVisibility(View.VISIBLE);
        commentFrameLayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentError()).commit();*/
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
                sp = getSharedPreferences("loginToken", 0);
                token = "Bearer " + sp.getString("token", "");
                if (token != null) {
                    showCommentDialog();
                } else {
                    TeacherTrainingDetailsActivity teacherTrainingDetailsActivity = new TeacherTrainingDetailsActivity();
                    teacherTrainingDetailsActivity.showPopTopWithDarkBg();
                }
                break;
        }
    }

}
