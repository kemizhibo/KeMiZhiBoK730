package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.SearchActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter.CollectionBoxAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.SearchAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.CollectionBoxPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.CollectionBoxView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonCenterShouCangActivity extends BaseMvpActivity<CollectionBoxPresenterImpl> implements CollectionBoxView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;


    @Inject
    public CollectionBoxPresenterImpl collectionBoxPresenter;
    CollectionBoxAdapter collectionBoxAdapter;
    @BindView(R.id.collection_box_recyclerview)
    RecyclerView collectionBoxRecyclerview;
    @BindView(R.id.collection_box_springview)
    SpringView collectionBoxSpringview;
    private List<CollectionBoxBean.ContentBean.DataBean.CourseBean> dataBeans;
    private int page;
    //上或者下拉的状态判断
    int isUp = 1;
    private SharedPreferences sp;
    private String token;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_shou_cang;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    @Override
    protected void getData() {
        super.getData();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        collectionBoxPresenter.getCollectionBoxData(this,"Bearer "+ token,"1","12");
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("收藏夹");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    public void onCollectionBoxSuccess(CollectionBoxBean collectionBoxBean) {
        dataBeans = new ArrayList<>();
        dataBeans.add(collectionBoxBean.getContent().getData().get(0).getCourse());
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        collectionBoxRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        collectionBoxRecyclerview.setItemAnimator(new DefaultItemAnimator());
        collectionBoxSpringview.setType(SpringView.Type.FOLLOW);
        collectionBoxAdapter = new CollectionBoxAdapter(R.layout.collection_box_item_layout, dataBeans);
        collectionBoxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {

                } else {
                    if (dataBeans.get(position).getIsImageText()==0) {
                        Intent intent = new Intent(PersonCenterShouCangActivity.this, YingXinagVideoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        PersonCenterShouCangActivity.this.startActivity(intent);
                    } /*else if (dataBeans.get(position).getFileType().equals("LIVE")) {
                        Intent intent = new Intent(PersonCenterShouCangActivity.this, TeacherTrainingDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        PersonCenterShouCangActivity.this.startActivity(intent);
                    } */else {
                        Intent intent = new Intent(PersonCenterShouCangActivity.this, PictrueDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        PersonCenterShouCangActivity.this.startActivity(intent);
                    }
                }
            }
        });
        collectionBoxRecyclerview.setAdapter(collectionBoxAdapter);
        collectionBoxSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        page = 1;
                        collectionBoxPresenter.getCollectionBoxData(PersonCenterShouCangActivity.this,"Bearer "+token,"1","12");
                        collectionBoxSpringview.onFinishFreshAndLoad();
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
                        collectionBoxPresenter.getCollectionBoxData(PersonCenterShouCangActivity.this,"Bearer "+token,"1","12");
                        collectionBoxSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        collectionBoxSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        collectionBoxSpringview.setFooter(new AliFooter(this, true));

    }

    @Override
    public void onCollectionBoxError(String msg) {

    }

    @Override
    protected CollectionBoxPresenterImpl initInject() {
        activityComponent.inject(this);
        return collectionBoxPresenter;
    }

}
