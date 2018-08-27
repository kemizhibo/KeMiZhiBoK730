package com.kemizhibo.kemizhibo.other.preparing_center;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonTeacherBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenter;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_center.adapter.PreparingCenterGridAdapter;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_center.presenter.PreparingCenterPresenter;
import com.kemizhibo.kemizhibo.other.preparing_center.presenter.PreparingCenterPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_center.view.PreparingCenterView;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.PreparingPackageDetailActivity;
import com.kemizhibo.kemizhibo.other.utils.FilterPopUtils;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: yhr
 * Date: on 2018/6/19.
 * Describe:备授课的授课中心
 */

public class ForTeanchingFirstFragment extends BaseFragment implements PreparingCenterView, CommonView {
    @BindView(R.id.forteaching_shaixuan_imageview)
    ImageView forteachingShaixuanImageview;
    @BindView(R.id.forteaching_shaixuan_butn)
    RelativeLayout forteachingShaixuanButn;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.grid_view)
    GridView gridView;
    //隐藏和显示布局模块
    private boolean isOpen = false;
    ValueAnimator valueAnimator = null;
    private ViewGroup.LayoutParams layoutParams;
    private PreparingCenterPresenter presenter;
    private CommonPresenter filterPresenter;
    private String materialId = "";//教材版本id
    private String gradeId = "";//年级id
    private String semesterId = "";//学期id
    private List<PreparingCenterBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private PreparingCenterGridAdapter adapter;

    private PopupWindow filterPop = null;
    private String[] materials = null;
    private String[] grades = null;
    private String[] semesters = null;
    private int materialSelectI = -1;
    private int gradeSelectI = -1;
    private int semesterSelectI = -1;
    private CommonFilterBean filterBean;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new PreparingCenterPresenterImp(this);
        filterPresenter = new CommonPresenterImp(this);
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.forteaching_first_fragment);
        ButterKnife.bind(this, view);
        //隐藏和显示布局
        initView();
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
    }


    @OnClick(R.id.forteaching_shaixuan_butn)
    public void onViewClicked() {
        if(null != filterPop && filterPop.isShowing()){
            filterPop.dismiss();
        }else{
            if(null == materials){
                filterPresenter.getCommonFilterData();
            }else{
                showFilterPop();
            }
        }
    }

    private void initView() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int courseId = dataBeanList.get(position).getCourseId();
                Intent intent = new Intent(getActivity(), PreparingPackageDetailActivity.class);
                intent.putExtra(Constants.COURSE_ID, courseId);
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initialize();
                presenter.refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initialize();
                presenter.loadMore();
            }
        });
        presenter.refresh();
    }

    public void initialize(){
        materialId = "";
        gradeId = "";
        semesterId = "";
        materialSelectI = -1;
        gradeSelectI = -1;
        semesterSelectI = -1;
        if(null != filterPop && filterPop.isShowing()){
            filterPop.dismiss();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    private void showFilterPop() {
        filterPop = FilterPopUtils.show(getActivity(), materials, grades, semesters, materialSelectI, gradeSelectI, semesterSelectI, new FilterPopUtils.OnPopIndicatorClickListener() {
            @Override
            public void onClick(String category, int position) {
                //filterPop.dismiss();
                switch (category) {
                    case FilterPopUtils.CATEGORY_MATERIAL:
                        materialId = String.valueOf(filterBean.getContent().getMaterial().get(position).getSubjectId());
                        materialSelectI = position;
                        presenter.refresh();
                        break;
                    case FilterPopUtils.CATEGORY_GRADE:
                        gradeId = String.valueOf(filterBean.getContent().getGrade().get(position).getSubjectId());
                        gradeSelectI = position;
                        presenter.refresh();
                        break;
                    case FilterPopUtils.CATEGORY_SEMESTER:
                        semesterId = String.valueOf(filterBean.getContent().getSemester().get(position).getSubjectId());
                        semesterSelectI = position;
                        presenter.refresh();
                        break;
                    default:
                        break;
                }
            }
        });
        filterPop.showAsDropDown(forteachingShaixuanButn, 0, 0, Gravity.BOTTOM);
        filterPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                initialize();
            }
        });
    }

    @Override
    public Context getCustomContext() {
        return getContext();
    }

    @Override
    public Map getRequestParams() {
        Map requestParams = new HashMap();
        requestParams.put(Constants.METERIAL_ID, materialId);
        requestParams.put(Constants.GRADE_ID, gradeId);
        requestParams.put(Constants.SEMESTER_ID, semesterId);
        return requestParams;
    }

    @Override
    public void refreshSuccess(PreparingCenterBean bean) {
        if(bean.getContent().getData().size() > 0){
            dataBeanList.clear();
            dataBeanList.addAll(bean.getContent().getData());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setState(LoadingPager.LoadResult.success);
                    refreshLayout.finishRefresh();
                    setAdapter();
                }
            });
        }else{
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setState(LoadingPager.LoadResult.empty);
                }
            });
        }
    }

    private void setAdapter() {
        if(adapter == null){
            adapter = new PreparingCenterGridAdapter(getActivity(), dataBeanList);
            gridView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreSuccess(PreparingCenterBean bean) {
        dataBeanList.addAll(bean.getContent().getData());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishLoadMore();
                setAdapter();
            }
        });
    }

    @Override
    public void error(int errorCode, final boolean isLoadMore) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setState(LoadingPager.LoadResult.error);
                if(isLoadMore){
                    refreshLayout.finishLoadMore();
                }else{
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    @Override
    public Context getCommonCustomContext() {
        return getActivity();
    }

    @Override
    public Map getCommonRequestParams() {
        return null;
    }

    @Override
    public void getCommonFilterSuccess(CommonFilterBean bean) {
        filterBean = bean;
        List<CommonFilterBean.ContentBean.MaterialBean> material = bean.getContent().getMaterial();
        materials = new String[material.size()];
        for (int i = 0; i < material.size(); i++) {
            materials[i] = material.get(i).getSubjectName();
        }
        List<CommonFilterBean.ContentBean.GradeBean> grade = bean.getContent().getGrade();
        grades = new String[grade.size()];
        for (int i = 0; i < grade.size(); i++) {
            grades[i] = grade.get(i).getSubjectName();
        }
        List<CommonFilterBean.ContentBean.SemesterBean> semester = bean.getContent().getSemester();
        semesters = new String[semester.size()];
        for (int i = 0; i < semester.size(); i++) {
            semesters[i] = semester.get(i).getSubjectName();
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showFilterPop();
            }
        });
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

    }

    @Override
    public void getCommonUserTeachPlanError(int errorCode) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {

        }else {
            if(null != filterPop && filterPop.isShowing()){
                filterPop.dismiss();
            }
        }
    }

    public void onHidden(){
        if(null != filterPop && filterPop.isShowing()){
            filterPop.dismiss();
        }
    }
}
