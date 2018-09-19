package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.ViewPagerAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentLoading;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.PicturePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.PictureView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import java.util.ArrayList;
import java.util.List;
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
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.pictrue_details_title)
    TextView pictrueDetailsTitle;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    //图文详情信息
    private PictureBean.ContentBean content;
    private List<PictureBean.ContentBean.ImageTextListBean> listBeanArrayList = new ArrayList<>();
    private String courseId;
    private String token;
    //图文详情收藏
    private CollectionBean collectionBeans;
    private SharedPreferences sp;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pictrue_details;
    }

    @Override
    protected void getData() {
        super.getData();
        frameLayout.setVisibility(View.VISIBLE);
        relativelayout.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FramgmentLoading()).commit();
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
        } else if (pictureBean.getCode() == 401||pictureBean.getCode() == 801){
            initDialogToLogin();
        }
    }

    private void initPicture() {
        //切换控件
        frameLayout.setVisibility(View.GONE);
        relativelayout.setVisibility(View.VISIBLE);
        pictrueDetailsTitle.setText(content.getCourseName());
        viewPagerAdapter.notifyDataSetChanged();
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
                Transparent.showErrorMessage(this, "收藏成功～");
            } else if (collectionBean.getMessage().equals("取消收藏成功")) {
                pictrueDetailsImageview.setBackgroundResource(R.mipmap.dianzan_kong);
                Transparent.showErrorMessage(this, "取消收藏成功～");
            }
        } else if (collectionBean.getCode() == 401||collectionBean.getCode() == 801){
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
                                    Intent intent = new Intent(PictrueDetailsActivity.this, LoginActivity.class);
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

    @Override
    public void onGetCollectionError(String msg) {
        Transparent.showErrorMessage(this, "收藏失败请重试～");
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
        if (NoFastClickUtils.isFastClick()) {
        } else {
            sp = getSharedPreferences("logintoken", 0);
            token = sp.getString("token", "");
            picturePresenter.getCollectionData(this, "Bearer " + token, courseId);
        }
    }

}
