package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.PicturePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.PictureView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

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
    LinearLayout pictrueDetailsCollection;
    @BindView(R.id.pictrue_details_txt)
    TextView pictrueDetailsTxt;
    @BindView(R.id.pictrue_details_viewpager)
    Banner pictrueDetailsViewpager;

    @Inject
    public PicturePresenterImpl picturePresenter;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    //图文详情信息
    private PictureBean.ContentBean content;
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
    private SharedPreferences sp;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    startActivity(new Intent(PictrueDetailsActivity.this, LoginActivity.class));
                    break;
                }
                case 1: {

                    break;
                }
                default: {

                    break;
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pictrue_details;
    }

    @Override
    protected void getData() {
        super.getData();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        picturePresenter.getPictureData(this, "Bearer " + token, courseId);
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
        publicTitleBarRoot.changeTitleBar("影像素材");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    public void onPictureSuccess(PictureBean pictureBean) {
        LogUtils.i("456789",pictureBean.getContent().getImageText());
        if (pictureBean.getCode() == 0) {
            content = pictureBean.getContent();
            //判断是否收藏过
            if (content.getFavouriteHistory() == 1) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_select);
            } else {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_kong);
            }
            if (TextUtils.isEmpty(content.getImageText().toString())) {
                //切换控件
                frameLayout.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentEmpty()).commit();
            } else {
                //切换控件
                frameLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                //解析数据
                initJsonData();
                //填充数据
                initPicture();
            }
        }else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
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
        }
    }

    private void initPicture() {
        //切换控件
        frameLayout.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        //图片标题
        pictrueDetailsTitle.setText(content.getContext());
        //图片介绍
        pictrueDetailsTxt.setText(text);
        //设置内置样式
        pictrueDetailsViewpager.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                //设置指示器的位置，小点点，左中右。
                .setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器，图片加载器在下方
        pictrueDetailsViewpager.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        pictrueDetailsViewpager.setImages(l);
        pictrueDetailsViewpager.setBannerAnimation(Transformer.Accordion);
        pictrueDetailsViewpager.isAutoPlay(false).start();
    }

    private void initJsonData() {
        List<Map> list = JSON.parseArray(content.getImageText(), Map.class);
        Map map = list.get(0);
        text = (String) map.get("text");
        l = (List) map.get("imgList");
        for (String s : l) {
            LogUtils.i("1=========================================" + s);
        }
    }

    @Override
    public void onPictureError(String msg) {
        frameLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentError()).commit();
    }

    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        if (collectionBean.getCode()==0){
            collectionBeans = collectionBean;
            if (collectionBean.getMessage().equals("添加收藏成功")) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_select);
                Transparent.showSuccessMessage(this, "添加收藏成功");
            } else if (collectionBean.getMessage().equals("取消收藏成功")) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_kong);
                Transparent.showInfoMessage(this, "已取消收藏");
            }
        }
       else {
            Transparent.showErrorMessage(this, "登录失效请重新登录");
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
        }
    }


    @Override
    public void onGetCollectionError(String msg) {
        Transparent.showErrorMessage(this, "收藏失败请重试");
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
            Glide.with(context).load((String) path)
                    .error(R.mipmap.milier)
                    .placeholder(R.mipmap.milier)
                    .into(imageView);
        }
    }


    @OnClick(R.id.pictrue_details_collection)
    public void onViewClicked() {
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        picturePresenter.getCollectionData(this, "Bearer " + token, courseId);
    }
}
