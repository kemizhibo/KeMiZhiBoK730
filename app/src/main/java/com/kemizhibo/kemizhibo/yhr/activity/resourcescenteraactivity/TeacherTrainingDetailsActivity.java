package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.TeacherTrainingDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
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
import butterknife.OnClick;
import static com.dueeeke.videoplayer.player.IjkVideoView.SCREEN_SCALE_MATCH_PARENT;

public class TeacherTrainingDetailsActivity extends BaseMvpActivity<TeacherTrainingDetailsVideoPresenterImpl> implements TeacherTrainingDetailsVideoView {

    @Inject
    public TeacherTrainingDetailsVideoPresenterImpl teacherTrainingDetailsVideoPresenter;
    @BindView(R.id.video_back_butn)
    RelativeLayout videoBackButn;
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
    private BottomSheetDialog dialog;
    //教师培训直播收藏
    private CollectionBean collectionBeans;
    private String token;
    private String courseId;
    private String trim;
    //评论列表项
    CommentAdapter commentAdapter;
    private List<CommentBean.ContentBean.DataBean> commentList = new ArrayList<>();
    //视频信息
    private EditText commentText;
    private Button bt_comment1;
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
    private CommentBean.ContentBean tatle;
    private String str2;
    private String str3;
    private String str4;

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
            isUp=1;
            teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
            Transparent.showErrorMessage(this,"评论成功～");
        } else {
            initDialogToLogin();
        }
        //然后把改评论的ID传给删除接口，判断是不是本人发的，长按时触发
    }

    @Override
    public void onPutCommentError(String msg) {
        Transparent.showErrorMessage(this,"评论失败请重试～");
    }


    //删除
    @Override
    public void onDeleteCommentSuccess(DeleteCommentBean deleteCommentBean) {
        //如果数据返回成功，就删除,刷新适配器
        if (deleteCommentBean.getCode() == 0) {
            Transparent.showErrorMessage(this,"删除评论成功～");
            teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            initDialogToLogin();
        }
    }

    @Override
    public void onDeleteCommentError(String msg) {
        Transparent.showErrorMessage(this, "删除评论失败请重试～");
    }
    //获取视屏详情信息
    @Override
    public void onTeacherTrainingDetailsVideoSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean) {
        if (teacherTrainingDetailsVideoBean.getCode() == 0) {
            content = teacherTrainingDetailsVideoBean.getContent();
            initYingXiangDetailsVideoData();
            //判断是否收藏过
            isCollection();
            //展示列表
            initComment();
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
                Transparent.showErrorMessage(this,"收藏成功～");
            } else if (collectionBean.getMessage().equals("取消收藏成功")) {
                yingxiangDetailsShoucangImageview.setImageResource(R.mipmap.dianzan_kong);
                Transparent.showErrorMessage(this,"收藏已取消～");
            }
        } else {
            initDialogToLogin();
        }
    }


    @Override
    public void onGetCollectionError(String msg) {
        Transparent.showErrorMessage(this,"收藏失败请重试～");
    }

    //点赞
    @Override
    public void onGetLikeSuccess(GetLikeBean getLikeBean) {
        if (getLikeBean.getCode() == 0) {
            if ("CANCEL".equals(getLikeBean.getContent())) {
                comment_dianzan.setBackgroundResource(R.mipmap.dianzan_2);
            } else  {
                comment_dianzan.setBackgroundResource(R.mipmap.getlike_select_2);
            }
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
        dialog = new BottomSheetDialog(this,R.style.BottomSheetEdit);
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
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    //正常点击的逻辑
                    trim = commentText.getText().toString().trim();
                    if (!TextUtils.isEmpty(trim)) {
                        dialog.dismiss();
                        sp = getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        teacherTrainingDetailsVideoPresenter.getPutCommentData(TeacherTrainingDetailsActivity.this, "Bearer " + token, courseId, trim, "");
                    } else {
                        Toast.makeText(TeacherTrainingDetailsActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    }
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
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        teacherTrainingDetailsVideoPresenter.getTeacherTrainingDetailsVideoData(this, "Bearer " + token, courseId);
        teacherTrainingDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        teacherTrainingDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData2(this, "Bearer " + token, courseId, "HLS", "true", "SD");
        teacherTrainingDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData3(this, "Bearer " + token, courseId, "HLS", "true", "LD");
        teacherTrainingDetailsVideoPresenter.getLiveRoomDetailsVideoUrlData4(this, "Bearer " + token, courseId, "HLS", "true", "UD");
        isFlag = true;
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                }else {
                                    Intent intent = new Intent(TeacherTrainingDetailsActivity.this, LoginActivity.class);
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
        if (0==tatle.getTotal()){
            yingxiangDetailsCommentNumTxt.setText("暂无评论");
        }else {
            yingxiangDetailsCommentNumTxt.setText("评论(" + tatle.getTotal() + ")");
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
        videos.put("高清", str2);
        videos.put("原画", str3);
        videos.put("超清", str4);
        player.setDefinitionVideos(videos);
        player.setScreenScale(SCREEN_SCALE_MATCH_PARENT);
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
            stopTimer();
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
        tatle = commentBean.getContent();
            if (isUp == 1) {
                commentList.clear();
                commentList.addAll(commentBean.getContent().getData());
                if (commentList.size() > 0) {
                    if (isFlag) {
                        commentAdapter.notifyDataSetChanged();
                    }
            } else if (isUp == 2) {
                commentList.addAll(commentBean.getContent().getData());
                    if (isFlag) {
                        commentAdapter.notifyDataSetChanged();
                    }
            }
        }
    }

    private void initComment() {
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_item_layout, null);
        commentRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        //上拉下拉动画效果
        commentRecyclerview.setItemAnimator(new DefaultItemAnimator());
        //滑动卡顿
        commentRecyclerview.setHasFixedSize(true);
        commentRecyclerview.setNestedScrollingEnabled(false);
        commentAdapter = new CommentAdapter(R.layout.comment_item_layout, commentList);
        //点赞
        commentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                    ToastUtils.showToast("点击太快了，请休息休息您的手指");
                }else {
                    comment_dianzan = (ImageView) view.findViewById(R.id.comment_dianzan);
                    p = position;
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    String targetId = String.valueOf(commentList.get(position).getCommentId());
                    teacherTrainingDetailsVideoPresenter.getLikeData(TeacherTrainingDetailsActivity.this, "Bearer " + token, targetId, "4");
                }
            }
        });
        //删除
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
                        isFlag=true;
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
                        isFlag=true;
                        commentSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        commentSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
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
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    //请求删除接口
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    teacherTrainingDetailsVideoPresenter.getDeleteCommentData(TeacherTrainingDetailsActivity.this, "Bearer " + token, commentId, "4");
                    dialog.dismiss();
                }
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
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    //点击收藏判断是否登录，登录成功改变图片，失败弹出popwindow
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    teacherTrainingDetailsVideoPresenter.getCollectionData(this, "Bearer " + token, courseId);
                    //如果成功拿到数据，就正常收藏，否则就弹框
                }
                break;
            case R.id.detail_page_do_comment:
                showCommentDialog();
                break;
        }
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

}
