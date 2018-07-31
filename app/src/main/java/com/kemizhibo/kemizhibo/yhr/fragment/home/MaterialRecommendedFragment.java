package com.kemizhibo.kemizhibo.yhr.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.MaterialRecommendedAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.MyClassAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl.HomePagePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: yhr
 * Date: on 2018/6/14.
 * Describe:素材推荐
 */

public class MaterialRecommendedFragment extends BaseMvpFragment<HomePagePresenterImpl> implements HomePageView {

    @BindView(R.id.material_recommended_recyclerview)
    RecyclerView materialRecommendedRecyclerview;
    @BindView(R.id.material_recommended_spring)
    SpringView materialRecommendedSpring;

    //申明presenterImpl对象,推荐素材列表
    private List<HomePageBean.ContentBean.ReturnMaterialBean> materialBean;
    @Inject
    public HomePagePresenterImpl homePagePresenter;
    MaterialRecommendedAdapter materialRecommendedAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.material_recommended_fragment);
        ButterKnife.bind(this, view);
        //展示素材推荐数据
        initMaterialRecommendedData();
        return view;
    }

    private void initMaterialRecommendedData() {
        //设置适配器
        LinearLayoutManager myClassManage = new LinearLayoutManager(getContext());
        materialRecommendedRecyclerview.setLayoutManager(myClassManage);
        materialRecommendedSpring.setType(SpringView.Type.FOLLOW);
        materialRecommendedAdapter = new MaterialRecommendedAdapter(R.layout.material_recommended_adapter, materialBean);
       /* myClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                knowledgeId = String.valueOf(myclassBean.get(position).getSubjectId());
                ToastUtils.showToast(materialEdition);
            }
        });*/
        materialRecommendedRecyclerview.setAdapter(materialRecommendedAdapter);
        //上拉下拉
        materialRecommendedSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRecommendedSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRecommendedSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        materialRecommendedSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        materialRecommendedSpring.setFooter(new AliFooter(getContext(), true));
    }

    @Override
    public void load() {
        homePagePresenter.getHomePageData(mActivity);
    }

    @Override
    public void onHomePageSuccess(HomePageBean searchBean) {
        //成功的状态显示UI操作,添加数据
        setState(LoadingPager.LoadResult.success);
        materialBean = new ArrayList<>();
        materialBean.addAll(searchBean.getContent().getReturnMaterial());
    }

    @Override
    public void onHomePageError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected HomePagePresenterImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        fragmentComponent.inject(this);
        return homePagePresenter;
    }

}
