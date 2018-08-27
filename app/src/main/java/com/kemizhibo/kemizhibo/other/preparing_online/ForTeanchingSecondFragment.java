package com.kemizhibo.kemizhibo.other.preparing_online;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonTeacherBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenter;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_online.adapter.PlanListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_online.adapter.PreparingOnlineListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_online.presenter.PreparingOnlinePresenter;
import com.kemizhibo.kemizhibo.other.preparing_online.presenter.PreparingOnlinePresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_online.view.PreparingOnlineView;
import com.kemizhibo.kemizhibo.other.utils.FilterPopUtils;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.LiveRoomDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
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
 * Describe:
 */

public class ForTeanchingSecondFragment extends BaseFragment implements PreparingOnlineView, CommonView {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.forteaching_shaixuan_imageview)
    ImageView forteachingShaixuanImageview;
    @BindView(R.id.forteaching_shaixuan_butn)
    RelativeLayout forteachingShaixuanButn;

    private PreparingOnlinePresenter presenter;
    private CommonPresenter commonPresenter;
    private String materialId = "";
    private String gradeId = "";
    private String semesterId = "";
    private String chapterId = "";
    private List<PreparingOnlineBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private PreparingOnlineListAdapter adapter;

    private PopupWindow filterPop = null;
    private String[] materials = null;
    private String[] grades = null;
    private String[] semesters = null;
    private int materialSelectI = -1;
    private int gradeSelectI = -1;
    private int semesterSelectI = -1;
    private CommonFilterBean filterBean;

    private List<CommonUserTeachPlanBean.ContentBean> planBeanList = new ArrayList<>();
    private int courseId;
    private int selectIndex = -1;
    private View parent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new PreparingOnlinePresenterImp(this);
        commonPresenter = new CommonPresenterImp(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
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
                commonPresenter.getCommonFilterData();
            }else{
                showFilterPop();
            }
        }
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
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.forteaching_second_fragment);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initialize();
                presenter.refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.loadMore();
            }
        });
        presenter.refresh();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseId = dataBeanList.get(position).getCourseId();
                commonPresenter.getUserTeachPlan();
                show();
            }
        });
    }

    private void showPlanPop() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_plan, null, false);
        final ListView planList = view.findViewById(R.id.plan_list);
        final PlanListAdapter planListAdapter = new PlanListAdapter(getActivity(), planBeanList);
        planList.setAdapter(planListAdapter);
        planListAdapter.setPlanListAdapterCallBack(new PlanListAdapter.PlanListAdapterCallBack() {
            @Override
            public void onCheckBoxClick(boolean isChecked, int position) {
                if(isChecked){
                    selectIndex = position;
                    for (int i = 0; i < planBeanList.size(); i++) {
                        if(i == position){
                            planBeanList.get(i).setChecked(true);
                        }else{
                            planBeanList.get(i).setChecked(false);
                        }
                    }
                }else{
                    planBeanList.get(position).setChecked(false);
                }
                planListAdapter.notifyDataSetChanged();
            }
        });
        RelativeLayout goTeach = view.findViewById(R.id.go_teach_rl);
        final PopupWindow planPop = new PopupWindow(getActivity());
        goTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = isChecked();
                if(isChecked){
                    planPop.dismiss();
                    int docType = planBeanList.get(selectIndex).getDocType();
                    if(docType == 5){
                        //视频
                        Intent intent = new Intent(getActivity(), LiveRoomDetailsActivity.class);
                        intent.putExtra(Constants.COURSE_ID, String.valueOf(planBeanList.get(selectIndex).getCourseId()));
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getActivity(), CommonWebActivity.class);
                        intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.TEACH);
                        intent.putExtra(Constants.MODULE_ID, planBeanList.get(selectIndex).getModuleId());
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(mActivity, "请选择您的授课方案", Toast.LENGTH_SHORT).show();
                }
            }
        });
        planPop.setContentView(view);
        planPop.setWidth(733);
        planPop.setHeight(400);
        planPop.setFocusable(true);
        planPop.setOutsideTouchable(true);
        planPop.setBackgroundDrawable(new BitmapDrawable());
        /*backgroundAlpha(0.8f);
        parent = getActivity().getWindow().getDecorView();*/
        planPop.showAtLocation(getActivity().getCurrentFocus(), Gravity.CENTER, 0, 0);
        planPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                initialize();
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }
    private boolean isChecked() {
        for (int i = 0; i < planBeanList.size(); i++) {
            if(planBeanList.get(i).isChecked()){
                selectIndex = i;
                return true;
            }
        }
        return false;
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
    public Context getCustomContext() {
        return getContext();
    }

    @Override
    public Map getRequestParams() {
        Map requestParams = new HashMap();
        requestParams.put(Constants.METERIAL_ID, materialId);
        requestParams.put(Constants.GRADE_ID, gradeId);
        requestParams.put(Constants.SEMESTER_ID, semesterId);
        requestParams.put(Constants.CHAPTER_ID, chapterId);
        return requestParams;
    }

    @Override
    public void refreshSuccess(PreparingOnlineBean bean) {
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
            adapter = new PreparingOnlineListAdapter(getActivity(), dataBeanList);
            listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreSuccess(PreparingOnlineBean bean) {
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
        Map params = new HashMap();
        params.put(Constants.COURSE_ID, String.valueOf(courseId));
        return params;
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
         planBeanList.clear();
         planBeanList.addAll(bean.getContent());
         getActivity().runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 showPlanPop();
             }
         });
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
