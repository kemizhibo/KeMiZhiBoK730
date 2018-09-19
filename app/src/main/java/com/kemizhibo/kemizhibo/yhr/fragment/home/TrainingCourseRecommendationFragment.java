package com.kemizhibo.kemizhibo.yhr.fragment.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.TrainingCourseRecommendationAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.HomePageBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.homeimpl.HomePagePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.homepagerview.HomePageView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


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
    protected boolean isCreated = false;

    //申明presenterImpl对象,我的备课列表
    private List<HomePageBean.ContentBean.ReturnTrainBean> trainBean = new ArrayList<>();
    TrainingCourseRecommendationAdapter trainingCourseRecommendationAdapter;
    private SharedPreferences sp;
    private String token;
    private Intent intent;
    private Bundle bundle;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 标记
        isCreated = true;
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
        trainingCourseRecommendationAdapter = new TrainingCourseRecommendationAdapter(R.layout.training_course_recommendation_adapter, trainBean);
        trainingCourseRecommendationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    intent = new Intent(getActivity().getApplicationContext(), YingXinagVideoDetailsActivity.class);
                    bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(trainBean.get(position).getCourseId()));
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            }
        });
        trainingCourseRecommendationRecyclerview.setAdapter(trainingCourseRecommendationAdapter);
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        homePagePresenter.getHomePageData(mActivity,"Bearer "+ token);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated) {
            return;
        }
        if (isVisibleToUser) {
            setState(LoadingPager.LoadResult.success);
            sp = getContext().getSharedPreferences("logintoken", 0);
            token = sp.getString("token", "");
            homePagePresenter.getHomePageData(mActivity,"Bearer "+ token);
            LogUtils.i("1111111","1111111111111111111");
        }
    }

    @Override
    public void onEmptyViewClick() {

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
            trainingCourseRecommendationAdapter.notifyDataSetChanged();
        }else if (searchBean.getCode()==401||searchBean.getCode()==801){
            initDialogToLogin();
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

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getContext());
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                }else {
                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
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
}
