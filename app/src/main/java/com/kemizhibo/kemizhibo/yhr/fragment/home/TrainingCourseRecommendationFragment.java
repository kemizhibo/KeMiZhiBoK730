package com.kemizhibo.kemizhibo.yhr.fragment.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.MyClassAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.TrainingCourseRecommendationAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl.HomePagePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
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
 * Describe:培训课推荐
 */

public class TrainingCourseRecommendationFragment extends BaseMvpFragment<HomePagePresenterImpl> implements HomePageView {

    @Inject
    public HomePagePresenterImpl homePagePresenter;
    @BindView(R.id.training_course_recommendation_recyclerview)
    RecyclerView trainingCourseRecommendationRecyclerview;
    @BindView(R.id.training_course_recommendation_spring)
    SpringView trainingCourseRecommendationSpring;

    private int currentPage;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;

    //申明presenterImpl对象,我的备课列表
    private List<HomePageBean.ContentBean.ReturnTrainBean> trainBean = new ArrayList<>();
    TrainingCourseRecommendationAdapter trainingCourseRecommendationAdapter;
    private SharedPreferences sp;
    private String token;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.training_course_recommendation_fragment);
        ButterKnife.bind(this, view);
        //展示我的备课数据
        initTrainingCourseRecommendationData();
        return view;
    }

    private void initTrainingCourseRecommendationData() {
        //设置适配器
        LinearLayoutManager trainingCourseRecommendationManage = new LinearLayoutManager(getContext());
        trainingCourseRecommendationRecyclerview.setLayoutManager(trainingCourseRecommendationManage);
        trainingCourseRecommendationSpring.setType(SpringView.Type.FOLLOW);
        trainingCourseRecommendationAdapter = new TrainingCourseRecommendationAdapter(R.layout.training_course_recommendation_adapter, trainBean);
        trainingCourseRecommendationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), YingXinagVideoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(trainBean.get(position).getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    getActivity().startActivity(intent);
                }
            }
        });
        trainingCourseRecommendationRecyclerview.setAdapter(trainingCourseRecommendationAdapter);
        //上拉下拉
        trainingCourseRecommendationSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        currentPage = 1;
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        homePagePresenter.getHomePageData(mActivity,"Bearer "+token);
                        trainingCourseRecommendationSpring.onFinishFreshAndLoad();
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
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        homePagePresenter.getHomePageData(mActivity,"Bearer "+token);
                        trainingCourseRecommendationSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        trainingCourseRecommendationSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        if (trainBean==null){
            trainingCourseRecommendationSpring.setFooter(new AliFooter(getContext(), R.drawable.ali,false));
        }else {
            trainingCourseRecommendationSpring.setFooter(new AliFooter(getContext(), true));
        }
    }

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        homePagePresenter.getHomePageData(mActivity,"Bearer "+ token);
    }

    @Override
    public void onHomePageSuccess(HomePageBean searchBean) {
        if (searchBean.getCode()==0){
            trainBean.clear();
            trainBean.addAll(searchBean.getContent().getReturnTrain());
            if (trainBean==null){
                setState(LoadingPager.LoadResult.empty);
            }else {
                setState(LoadingPager.LoadResult.success);
            }
        }else {
            Transparent.showErrorMessage(getContext(),"登录失效请重新登录");
        }
    }

    @Override
    public void onHomePageError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected HomePagePresenterImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        //this需要去di注册
        fragmentComponent.inject(this);
        return homePagePresenter;
    }

}
