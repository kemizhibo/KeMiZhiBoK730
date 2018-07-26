package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.PicturePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.PictureView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PictrueDetailsActivity extends BaseMvpActivity<PicturePresenterImpl> implements PictureView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.pictrue_details_title)
    TextView pictrueDetailsTitle;
    @BindView(R.id.pictrue_details_imageview)
    ImageView pictrueDetailsImageview;
    @BindView(R.id.pictrue_details_collection)
    TextView pictrueDetailsCollection;
    @BindView(R.id.pictrue_details_txt)
    TextView pictrueDetailsTxt;
    @BindView(R.id.pictrue_details_viewpager)
    Banner pictrueDetailsViewpager;

    @Inject
    public PicturePresenterImpl picturePresenter;
    //图文详情信息
    private PictureBean.ContentBean picBean;
    //初始化popwindow
    private CustomPopWindow mCustomPopWindow;
    //json解析出来的标题和图片集合
    private String text;
    private List<String> l;
    private String courseId;
    private String token;
    private BottomSheetDialog dialog;
    //图文详情收藏
    private CollectionBean collectionBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pictrue_details;
    }

    @Override
    protected void getData() {
        super.getData();
        picturePresenter.getPictureData(this, courseId);
        LogUtils.i("55555555555555555",courseId);
    }

    @Override
    protected void initData() {
        bindTitleBar();
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");

    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("备课包");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    public void onPictureSuccess(PictureBean pictureBean) {
        //解析json串
        picBean = pictureBean.getContent();
        List<Map> list = JSON.parseArray(picBean.getImageText(),Map.class);
        Map map = list.get(0);
        text = (String)map.get("text");
        Object images = map.get("imgList");
        l = (List) images;

        //图片标题
        pictrueDetailsTitle.setText(picBean.getContext());
        //图片介绍
        pictrueDetailsTxt.setText(text);
        LogUtils.i("6666666666666666666666",l.toString());
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        pictrueDetailsViewpager.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                //设置指示器的位置，小点点，左中右。
                .setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器，图片加载器在下方
        pictrueDetailsViewpager.setImageLoader(new PictrueDetailsActivity.MyLoader());
        //设置图片网址或地址的集合
        pictrueDetailsViewpager.setImages(l);
        pictrueDetailsViewpager.setBannerAnimation(Transformer.Accordion);
        pictrueDetailsViewpager.isAutoPlay(false).start();
    }

    @Override
    public void onPictureError(String msg) {
        LogUtils.i("555555555555555555555555",msg);
    }

    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        collectionBeans = collectionBean;
        /*if (collectionBeans.getCode()==0){
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_select);
        }else {
            showCollectionDialog();
        }*/
       /* if (contentBean.getFavouriteHistory()==1){
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_select);
        }else {
            yingxiangDetailsShoucangImageview.setBackgroundResource(R.mipmap.dianzan_kong);
        }*/
        if (collectionBean.getMessage().equals("收藏成功")){
            pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_select);
            ToastUtils.showToast(collectionBean.getMessage());
        }else if (collectionBean.getMessage().equals("取消收藏成功")){
            pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_kong);
            ToastUtils.showToast(collectionBean.getMessage());
        }else {
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
                Intent intent = new Intent(PictrueDetailsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onGetCollectionError(String msg) {

    }

    @Override
    protected PicturePresenterImpl initInject() {
        activityComponent.inject(this);
        return picturePresenter;
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }


    @OnClick(R.id.pictrue_details_collection)
    public void onViewClicked() {
        //点击收藏判断是否登录，登录成功改变图片，失败弹出popwindow
        SharedPreferences sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        picturePresenter.getCollectionData(this, courseId, "Bearer " + token);
        //如果成功拿到数据，就正常收藏，否则就弹框
    }


}
