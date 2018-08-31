package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.ViewPagerAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.PicturePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.PictureView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PictrueDetailsActivity extends BaseMvpActivity<PicturePresenterImpl> implements PictureView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.pictrue_details_imageview)
    ImageView pictrueDetailsImageview;
    @BindView(R.id.pictrue_details_collection)
    LinearLayout pictrueDetailsCollection;

    @Inject
    public PicturePresenterImpl picturePresenter;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    /*@BindView(R.id.linear_layout)
    LinearLayout linearLayout;*/
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.pictrue_details_title)
    TextView pictrueDetailsTitle;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    //图文详情信息
    private PictureBean.ContentBean content;
    private List<PictureBean.ContentBean.ImageTextListBean> listBeanArrayList = new ArrayList<>();
    //初始化popwindow
    private CustomPopWindow mCustomPopWindow;
    //json解析出来的标题和图片集合
    private String text;
    private String l;
    private String courseId;
    private String token;
    private BottomSheetDialog dialog;
    //图文详情收藏
    private CollectionBean collectionBeans;
    private SharedPreferences sp;
    private ViewPagerAdapter viewPagerAdapter;
    private AlertDialog dialogOk;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){
                dialogOk.dismiss();
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
        viewPagerAdapter = new ViewPagerAdapter(this, listBeanArrayList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void initData() {
        bindTitleBar();
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseId");
    }

    /*private void initJsonData() {
        List<Map> list = JSON.parseArray(content.getImageText(), Map.class);
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            pictureAndTextList.add((PictureAndText) map.get("text"));
            pictureAndTextList.add((PictureAndText) map.get("imgList"));
        }
        LogUtils.i("几何中",pictureAndTextList.toString());
    }*/


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
        LogUtils.i("456789", pictureBean.getContent().getImageText());
        if (pictureBean.getCode() == 0) {
            picturePresenter.getOneLookData(this, "Bearer " + token, "", "", courseId, "", "0");
            content = pictureBean.getContent();
            listBeanArrayList.addAll(pictureBean.getContent().getImageTextList());
            //判断是否收藏过
            if (content.getFavouriteHistory() == 1) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_select);
            } else {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_kong);
            }
            if (listBeanArrayList == null) {
                //切换控件
                frameLayout.setVisibility(View.VISIBLE);
                relativelayout.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FramgmentEmpty()).commit();
            } else {
                //切换控件
                frameLayout.setVisibility(View.GONE);
                relativelayout.setVisibility(View.VISIBLE);
                //填充数据
                initPicture();
            }
        } else {
            initDialogToLogin();
        }
    }

    private void initPicture() {
        //切换控件
        frameLayout.setVisibility(View.GONE);
        relativelayout.setVisibility(View.VISIBLE);
        pictrueDetailsTitle.setText(content.getCourseName());
        //图片标题
        /*for (int i = 0;i<listBeanArrayList.size();i++){
            String text = listBeanArrayList.get(i).getText();
            pictrueDetailsTitle.setText(text);
            List<String> imgList = listBeanArrayList.get(i).getImgList();
            for (int j = 0;j<imgList.size();j++){
                String s = imgList.get(j).toString();
                Glide.with(this).load(s)
                        .error(R.mipmap.milier)
                        .placeholder(R.mipmap.milier)
                        .into(pictrueDetailsImageview);
            }
        }*/
        viewPagerAdapter.notifyDataSetChanged();



        /*//设置内置样式
        pictrueDetailsViewpager.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                //设置指示器的位置，小点点，左中右。
                .setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器，图片加载器在下方
        pictrueDetailsViewpager.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        pictrueDetailsViewpager.setImages(l);
        pictrueDetailsViewpager.setBannerAnimation(Transformer.Accordion);
        pictrueDetailsViewpager.isAutoPlay(false).start();*/
    }

    @Override
    public void onPictureError(String msg) {
        frameLayout.setVisibility(View.VISIBLE);
        relativelayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FramgmentError()).commit();
    }

    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        if (collectionBean.getCode() == 0) {
            collectionBeans = collectionBean;
            if (collectionBean.getMessage().equals("添加收藏成功")) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_select);
                Transparent.showErrorMessage(this,"收藏成功～");
            } else if (collectionBean.getMessage().equals("取消收藏成功")) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_kong);
                Transparent.showErrorMessage(this,"取消收藏成功～");
            }
        } else {
            initDialogToLogin();
        }
    }

    private void initDialogToLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder
                .setView(R.layout.alertdialog_login)
                .setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PictrueDetailsActivity.this, LoginActivity.class);
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

    @Override
    public void onGetCollectionError(String msg) {
        Transparent.showErrorMessage(this,"收藏失败请重试～");
    }

    @Override
    public void onGetOneLookSuccess(OneLookBean oneLookBean) {

    }

    @Override
    public void onGetOneLookError(String msg) {

    }

    @Override
    protected PicturePresenterImpl initInject() {
        activityComponent.inject(this);
        return picturePresenter;
    }


    @OnClick(R.id.pictrue_details_collection)
    public void onViewClicked() {
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        picturePresenter.getCollectionData(this, "Bearer " + token, courseId);
    }

}
