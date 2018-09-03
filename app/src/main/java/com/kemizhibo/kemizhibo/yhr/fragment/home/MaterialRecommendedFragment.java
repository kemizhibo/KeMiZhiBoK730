package com.kemizhibo.kemizhibo.yhr.fragment.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterLiuLanActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.MaterialRecommendedAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.homepageadapter.MyClassAdapter;
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
 * Describe:素材推荐
 */

public class MaterialRecommendedFragment extends BaseMvpFragment<HomePagePresenterImpl> implements HomePageView {

    @BindView(R.id.material_recommended_recyclerview)
    RecyclerView materialRecommendedRecyclerview;
    @BindView(R.id.material_recommended_spring)
    SpringView materialRecommendedSpring;

    private Intent intent;
    private Bundle bundle;
    private int currentPage;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;

    //申明presenterImpl对象,推荐素材列表
    private List<HomePageBean.ContentBean.ReturnMaterialBean> materialBean = new ArrayList<>();;
    @Inject
    public HomePagePresenterImpl homePagePresenter;
    MaterialRecommendedAdapter materialRecommendedAdapter;
    private SharedPreferences sp;
    private String token;

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
        materialRecommendedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    switch (materialBean.get(position).getFileType()) {
                        case "VIDEO":
                            intent = new Intent(getActivity().getApplicationContext(), TeacherTrainingDetailsActivity.class);
                            bundle = new Bundle();
                            bundle.putString("courseId", String.valueOf(materialBean.get(position).getCourseId()));
                            intent.putExtras(bundle);
                            //这里一定要获取到所在Activity再startActivity()；
                            getActivity().startActivity(intent);
                            break;
                        default:
                            intent = new Intent(getActivity().getApplicationContext(), PictrueDetailsActivity.class);
                            bundle = new Bundle();
                            bundle.putString("courseId", String.valueOf(materialBean.get(position).getCourseId()));
                            intent.putExtras(bundle);
                            //这里一定要获取到所在Activity再startActivity()；
                            getActivity().startActivityForResult(intent, MyApplication.YINGXIANG_TO_PICK_req);
                            break;
                    }
                }
            }
        });
        materialRecommendedRecyclerview.setAdapter(materialRecommendedAdapter);
        //上拉下拉
        materialRecommendedSpring.setListener(new SpringView.OnFreshListener() {
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
                        materialRecommendedSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {

            }
        });
        materialRecommendedSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字

    }

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        homePagePresenter.getHomePageData(mActivity,"Bearer "+token);
    }

    @Override
    public void onHomePageSuccess(HomePageBean searchBean) {
        if (searchBean.getCode()==0){
            if (isUp == 1) {
                materialBean.clear();
                materialBean.addAll(searchBean.getContent().getReturnMaterial());
                if (materialBean==null){
                    setState(LoadingPager.LoadResult.empty);
                }else {
                    setState(LoadingPager.LoadResult.success);
                    if (isFlag) {
                        materialRecommendedAdapter.notifyDataSetChanged();
                    }
                }
            }
        }else {
            initDialogToLogin();
        }
    }

    private void initDialogToLogin() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        AlertDialog dialog=builder
                .setView(R.layout.alertdialog_login)
                .setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (NoFastClickUtils.isFastClick()) {
                        }else {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
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
