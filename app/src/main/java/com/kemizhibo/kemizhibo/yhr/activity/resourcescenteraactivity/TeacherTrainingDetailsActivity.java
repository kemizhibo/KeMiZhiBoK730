package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zhouwei.library.CustomPopWindow;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.HomeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingLookFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment.TeacherTrainingTalkFragment;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.TeacherTrainingDetailsVideoPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingDetailsVideoView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class TeacherTrainingDetailsActivity extends BaseMvpActivity<TeacherTrainingDetailsVideoPresenterImpl> implements TeacherTrainingDetailsVideoView {

    @BindView(R.id.teacher_training_details_video)
    JZVideoPlayerStandard teacherTrainingDetailsVideo;
    @BindView(R.id.teacher_training_toolbar)
    Toolbar teacherTrainingToolbar;
    @BindView(R.id.teacher_training_collapsing_toolbar)
    CollapsingToolbarLayout teacherTrainingCollapsingToolbar;
    @BindView(R.id.teacher_training_appbar)
    AppBarLayout teacherTrainingAppbar;
    @BindView(R.id.teacher_training_details_video_title)
    TextView teacherTrainingDetailsVideoTitle;
    @BindView(R.id.teacher_training_details_shoucang_butn)
    TextView teacherTrainingDetailsShoucangButn;
    @BindView(R.id.teacher_training_detail_page_title)
    TextView teacherTrainingDetailPageTitle;
    @BindView(R.id.teacher_training_details_video_jieshao)
    TextView teacherTrainingDetailsVideoJieshao;
    @BindView(R.id.teacher_training_xTablayout)
    XTabLayout teacherTrainingXTablayout;
    @BindView(R.id.teacher_training_viewPager)
    ViewPager teacherTrainingViewPager;
    @BindView(R.id.teacher_training_detail_page_above_container)
    LinearLayout teacherTrainingDetailPageAboveContainer;
    @BindView(R.id.teacher_training_content)
    CoordinatorLayout teacherTrainingContent;
    @BindView(R.id.relativelayout_fragment)
    RelativeLayout relativelayoutFragment;
    @BindView(R.id.collection_imageview)
    ImageView collectionImageview;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    String courseId;
    private CustomPopWindow mCustomPopWindow;


    @Inject
    public TeacherTrainingDetailsVideoPresenterImpl teacherTrainingDetailsVideoPresenter;
    //教师培训详情页的用户和视频信息
    private TeacherTrainingDetailsVideoBean.ContentBean contentBean;
    //教师培训直播收藏
    private CollectionBean collectionBeans;
    private String token;
    private SharedPreferences sp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_training_details;
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
        teacherTrainingViewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        teacherTrainingXTablayout.setupWithViewPager(teacherTrainingViewPager);
    }

    @Override
    protected void getData() {
        super.getData();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        teacherTrainingDetailsVideoPresenter.getTeacherTrainingDetailsVideoData(this, "Bearer "+token,courseId);
        teacherTrainingDetailsVideoPresenter.getTeacherTrainingDetailsVideoUrlData(this, "Bearer "+token,courseId, "HLS", "true", "HD");
        teacherTrainingDetailsVideoPresenter.getTeacherTrainingDetailsVideoData(this, "Bearer "+token,courseId);
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
        teacherTrainingXTablayout.addTab(teacherTrainingXTablayout.newTab().setText(mTitleList.get(0)));
        teacherTrainingXTablayout.addTab(teacherTrainingXTablayout.newTab().setText(mTitleList.get(1)));
    }

    @Override
    public void onTeacherTrainingDetailsVideoSuccess(TeacherTrainingDetailsVideoBean teacherTrainingDetailsVideoBean) {
        contentBean = teacherTrainingDetailsVideoBean.getContent();
        //视频的标题
        teacherTrainingDetailsVideoTitle.setText(contentBean.getCourseName());
        //视频介绍
        teacherTrainingDetailsVideoJieshao.setText(contentBean.getContext());
    }

    @Override
    public void onTeacherTrainingDetailsVideoError(String msg) {

    }

    @Override
    public void onTeacherTrainingDetailsVideoUrlSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean) {
        TeacherTrainingDetailsVideoUrlBean contentUrlBean = teacherTrainingDetailsVideoUrlBean;
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
    public void onTeacherTrainingDetailsVideoUrlError(String msg) {

    }

    //收藏和取消收藏,成功请求，失败弹框
    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        collectionBeans = collectionBean;
        if (collectionBeans.getCode()==0){
            collectionImageview.setBackgroundResource(R.mipmap.dianzan_select);
        }else{
            showPopTopWithDarkBg();
        }
    }

    @Override
    public void onGetCollectionError(String msg) {

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected TeacherTrainingDetailsVideoPresenterImpl initInject() {
        activityComponent.inject(this);
        return teacherTrainingDetailsVideoPresenter;
    }

    @OnClick(R.id.teacher_training_details_shoucang_butn)
    public void onViewClicked() {
        showPopTopWithDarkBg();
        //点击以后请求收藏的接口
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        teacherTrainingDetailsVideoPresenter.getCollectionData(this,courseId,"Bearer "+token);
    }
    /**
     * 显示PopupWindow 同时背景变暗
     */
    public void showPopTopWithDarkBg() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                //.setBgDarkAlpha(0.7f)// 控制亮度
                .enableOutsideTouchableDissmiss(false)// 设置点击PopupWindow之外的地方，popWindow不关闭，如果不设置这个属性或者为true，则关闭
                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e("TAG", "onDismiss");
                    }
                })
                .create()
                .showAsDropDown(teacherTrainingDetailsShoucangButn, 0, 20);
    }

    private void handleLogic(View contentView) {
        View viewbutn = contentView.findViewById(R.id.pop_butn);
        viewbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherTrainingDetailsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}