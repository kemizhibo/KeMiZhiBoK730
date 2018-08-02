package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.CommentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CommentDetailBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.DeleteCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.GetLikeBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ReplyCommentBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.YingXiangDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionController;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.YingXiangDetailsVideoView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
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
    //private android.support.v7.widget.Toolbar toolbar;
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
    @BindView(R.id.detail_page_focus)
    ImageView detailPageFocus;
    @BindView(R.id.detail_page_title)
    TextView detailPageTitle;
    @BindView(R.id.detail_page_comment_container)
    LinearLayout detailPageCommentContainer;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.detail_page_do_comment)
    TextView detailPageDoComment;
    @BindView(R.id.comment_recyclerview)
    RecyclerView commentRecyclerview;
    private String courseId;
    private String content;

    //评论列表项
    CommentAdapter commentAdapter;
    private List<CommentBean.ContentBean.DataBean> commentList;
    //防止快速点击
    private boolean isVisibility = false;
    private EditText commentText;
    //用户和视频信息
    private YingXiangDetailsVideoBean.ContentBean contentBean;
    //发表评论
    private CommentDetailBean putCommentDetailBean;
    //评论列表
    private CommentBean.ContentBean.DataBean datalist;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ying_xinag_video_details;
    }

    @Override
    protected void initData() {
        initView();
        initVideo();
        //播放视频
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
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


    private void initView() {
        bt_comment = (TextView) findViewById(R.id.detail_page_do_comment);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("科米直播");
    }

    //发表评论成功
    @Override
    public void onPutCommentSuccess(CommentDetailBean commentDetailBean) {
        //发表成功以后吧数据添加到评论列表中然后刷新适配器，刷新列表数据
        if (commentDetailBean.getCode() == 0) {
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            showCollectionDialog();
        }
        //然后把改评论的ID传给删除接口，判断是不是本人发的，长按时触发
    }

    @Override
    public void onPutCommentError(String msg) {

    }

    //回复评论
    @Override
    public void onReplyCommentSuccess(ReplyCommentBean replyCommentBean) {
        //回复评论成功以后，添加到子集合，然后展示，刷新两个适配器
        if (replyCommentBean.getCode() == 0) {
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(this, "Bearer " + token, courseId, "1", "10", "4");
        } else {
            showCollectionDialog();
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
            showCollectionDialog();
        }
    }

    @Override
    public void onDeleteCommentError(String msg) {

    }

    // 收藏
    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        collectionBeans = collectionBean;
        if (collectionBean.getMessage().equals("收藏成功")) {
            LogUtils.i("00000000000000000", contentBean.getFavouriteHistory() + "");
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_select);
            ToastUtils.showToast(collectionBean.getMessage());
        } else if (collectionBean.getMessage().equals("取消收藏成功")) {
            LogUtils.i("00000000000000000", contentBean.getFavouriteHistory() + "");
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_kong);
            ToastUtils.showToast(collectionBean.getMessage());
        } else {
            showCollectionDialog();
        }
    }

    private void showCollectionDialog() {
        dialog = new BottomSheetDialog(this);
        View collectiondialogview = LayoutInflater.from(this).inflate(R.layout.collection_dialog_layout, null);
        Button toLoginButn = (Button) collectiondialogview.findViewById(R.id.tologin_butn);
        dialog.setContentView(collectiondialogview);
        View parent = (View) collectiondialogview.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        collectiondialogview.measure(0, 0);
        behavior.setPeekHeight(collectiondialogview.getMeasuredHeight());
        toLoginButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YingXinagVideoDetailsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onGetCollectionError(String msg) {

    }

    //点赞
    @Override
    public void onGetLikeSuccess(GetLikeBean getLikeBean) {
        getLikeData = getLikeBean;
        LogUtils.i("0000000000000000000", getLikeData.getCode() + "");
        if (getLikeBean.getCode() == 0) {
            comment_dianzan.setImageResource(R.mipmap.getlike_select_2);
        } else if (getLikeBean.getCode() != 0) {
            comment_dianzan.setImageResource(R.mipmap.dianzan_2);
        } else {
            showCollectionDialog();
        }
    }

    @Override
    public void onGetLikeError(String msg) {

    }

    //第一次记录播放时间
    @Override
    public void onGetOneLookSuccess(LookBean lookBean) {

    }

    @Override
    public void onGetOneLookError(String msg) {

    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog() {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout, null);
        commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {
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
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
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
        SystemClock.sleep(200);
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId);
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoUrlData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "HLS", "true", "HD");
        yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoCommentData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "1", "10", "4");
        isFlag = true;
    }


    @Override
    public void onYingXiangDetailsVideoSuccess(YingXiangDetailsVideoBean yingXiangDetailsVideoBean) {
        contentBean = yingXiangDetailsVideoBean.getContent();
        //判断是否收藏过
        if (contentBean.getFavouriteHistory() == 1) {
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_select);
        } else {
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_kong);
        }
        //判断是否点过赞
        if (contentBean.getPraiseCount() == 1) {
            comment_dianzan.setBackgroundResource(R.mipmap.getlike_select_2);
        } else {
            comment_dianzan.setBackgroundResource(R.mipmap.dianzan_2);
        }
        //视频的标题
        yingxiangDetailsVideoTitle.setText(contentBean.getCourseName());


        Glide.with(this).load("http://192.168.1.101:8080" + contentBean.getTeacher().getPicPath())
                .error(R.mipmap.milier)
                .placeholder(R.mipmap.milier)
                .into(yingxiangDetailsTeacherTouxiang);//头像
        LogUtils.i("===http://192.168.1.101:8080" + contentBean.getTeacher().getPicPath());
        //yingxiangDetailsTeacherName.setText(contentBean.getTeacherName().toString());
        //老师的名字
        yingxiangDetailsTeacherName.setText(contentBean.getTeacher().getName());
        LogUtils.i("00000000000000", contentBean.getTeacher().getName());
        //老师的职称
        yingxiangDetailsTeacherType.setText(contentBean.getTeacher().getEducation());
        //老师的介绍
        yingxiangDetailsTeacherJieshao.setText(contentBean.getTeacher().getCareer());
        //视频的介绍
        yingxiangDetailsVideoJieshao.setText(contentBean.getContext());
        //评论的数量
        yingxiangDetailsCommentNumTxt.setText("评论(" + contentBean.getPlayCount() + ")");

    }

    @Override
    public void onYingXiangDetailsVideoError(String msg) {

    }


    @Override
    public void onYingXiangDetailsVideoUrlSuccess(YingXiangDetailsVideoUrlBean yingXiangDetailsVideoUrlBean) {
        YingXiangDetailsVideoUrlBean contentUrlBean = yingXiangDetailsVideoUrlBean;
        String str = contentUrlBean.getContent();
        //视频清晰度切换按钮获取不通路径
        //LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        //获取不同清晰度的视频路径
        LinkedHashMap<String, String> videos = new LinkedHashMap<>();
        videos.put("标清", str);
        videos.put("高清", str);
        LogUtils.i("0000000000000000000高清路径",str);
        videos.put("超清", str);
        LogUtils.i("0000000000000000000超清",str);
        videos.put("原画", str);
        LogUtils.i("0000000000000000000原画",str);
        if (videos.keySet().equals("高清")){
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoUrlData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "HLS", "true", "SD");
        }else if (videos.keySet().equals("超清")){
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoUrlData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "HLS", "true", "LD");
        }else if (videos.keySet().equals("原画")){
            yingXiangDetailsVideoPresenter.getYingXiangDetailsVideoUrlData(YingXinagVideoDetailsActivity.this, "Bearer " + token, courseId, "HLS", "true", "UD");
        }else {
            ijkVideoView.setDefinitionVideos(videos);
            ijkVideoView.setVideoController(controller);
            ijkVideoView.setTitle("视屏详情");
            ijkVideoView.start();
        }

    }

    @Override
    public void onYingXiangDetailsVideoUrlError(String msg) {

    }


    @Override
    public void onYingXiangDetailsVideoCommentSuccess(CommentBean commentBean) {
        commentList = new ArrayList<>();
        commentList.addAll(commentBean.getContent().getData());
        //获取评论列表
        getCommentData();
        //获取回复的集合
        //获取回复列表
    }

    @Override
    public void onYingXiangDetailsVideoCommentError(String msg) {

    }


    private void getCommentData() {
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_item_layout, null);
        //评论列表条目初始化
        //初始化评论列表控件
        comment_text = (TextView) commentView.findViewById(R.id.comment_text);
        comment_dianzan = (ImageView) commentView.findViewById(R.id.comment_dianzan);
        commentRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new CommentAdapter(R.layout.comment_item_layout, commentList);
        commentRecyclerview.setAdapter(commentAdapter);
        commentRecyclerview.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                String targetId = String.valueOf(commentList.get(position).getCommentId());
                LogUtils.i("0000000000000000000000", targetId);
                LogUtils.i("0000000000000000000000", token);
                yingXiangDetailsVideoPresenter.getLikeData(YingXinagVideoDetailsActivity.this, "Bearer " + token, targetId, "4");
            }
        });
        //长按点击子条目
        commentAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                commentId = String.valueOf(commentList.get(position).getCommentId());
                showDeleteComentDialog();
                return true;
            }
        });
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
