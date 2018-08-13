package com.kemizhibo.kemizhibo.yhr.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonTeacherBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenter;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.PreparingPackageDetailActivity;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.MyClassAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterImgScienceAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl.HomePagePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Author: yhr
 * Date: on 2018/6/14.
 * Describe:我的备课
 */

public class MyClassFragment extends BaseMvpFragment<HomePagePresenterImpl> implements HomePageView, CommonView {


    @BindView(R.id.myclass_recyclerview)
    RecyclerView myclassRecyclerview;
    @BindView(R.id.myclass_spring)
    SpringView myclassSpring;
    private int currentPage;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;
    private CommonPresenter commonPresenter;

    @Inject
    public HomePagePresenterImpl homePagePresenter;
    //申明presenterImpl对象,我的备课列表
    private List<HomePageBean.ContentBean.ReturnPrepareBean> myclassBean;
    MyClassAdapter myClassAdapter;
    private int courseId;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        commonPresenter = new CommonPresenterImp(this);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.home_first_fragment);
        ButterKnife.bind(this, view);
        //展示我的备课数据
        initMyClassData();
        return view;

    }

    private void initMyClassData() {
        //设置适配器
        LinearLayoutManager myClassManage = new LinearLayoutManager(getContext());
        myclassRecyclerview.setLayoutManager(myClassManage);
        myclassSpring.setType(SpringView.Type.FOLLOW);
        myClassAdapter = new MyClassAdapter(R.layout.myclass_fragment, myclassBean);
        //子条目点击事件
        myClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                courseId = myclassBean.get(position).getCourseId();
                if(1 == myclassBean.get(position).getPrepareStatus()){
                    Intent intent = new Intent(getActivity(), PreparingPackageDetailActivity.class);
                    intent.putExtra(Constants.COURSE_ID, courseId);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), CommonWebActivity.class);
                    intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.PREVIEW);
                    intent.putExtra(Constants.MODULE_ID, myclassBean.get(position).getModuleId());
                    startActivity(intent);
                    /*commonPresenter.getUserTeachPlan();
                    show();*/
                }
            }
        });
        myclassRecyclerview.setAdapter(myClassAdapter);
        //上拉下拉
        myclassSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        currentPage = 1;
                        homePagePresenter.getHomePageData(mActivity);
                        myclassSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 2;
                        currentPage++;
                        homePagePresenter.getHomePageData(mActivity);
                        myclassSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        myclassSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        myclassSpring.setFooter(new AliFooter(getContext(), true));
    }

    @Override
    public void load() {
        homePagePresenter.getHomePageData(mActivity);
    }

    @Override
    public void onHomePageSuccess(HomePageBean searchBean) {
        //成功的状态显示UI操作,添加数据
        setState(LoadingPager.LoadResult.success);
        myclassBean = new ArrayList<>();
        myclassBean.addAll(searchBean.getContent().getReturnPrepare());
    }

    @Override
    public void onHomePageError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected HomePagePresenterImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        //this需要去di注册
        fragmentComponent.inject(this);
        return homePagePresenter;
    }

    @Override
    public Context getCommonCustomContext() {
        return getActivity();
    }

    @Override
    public Map getCommonRequestParams() {
        Map params = new HashMap();
        params.put(Constants.COURSE_ID, courseId);
        params.put(Constants.LASTPLAN, true);
        return params;
    }

    @Override
    public void getCommonFilterSuccess(CommonFilterBean bean) {

    }

    @Override
    public void getCommonFilterError(int errorCode) {

    }

    @Override
    public void getCommonUserInfoSuccess(CommonUserInfoBean bean) {

    }

    @Override
    public void getCommonUserInfoError(int errorCode) {

    }

    @Override
    public void getCommonTeacherSuccess(CommonTeacherBean bean) {

    }

    @Override
    public void getCommonTeacherError(int errorCode) {

    }

    @Override
    public void getCommonUserTeachPlanSuccess(CommonUserTeachPlanBean bean) {
        /*setState(LoadingPager.LoadResult.success);
        int docType = bean.getContent().get(0).getDocType();*/
    }

    @Override
    public void getCommonUserTeachPlanError(int errorCode) {

    }
}
