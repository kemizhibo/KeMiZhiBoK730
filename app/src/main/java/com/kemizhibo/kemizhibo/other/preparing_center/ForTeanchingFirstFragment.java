package com.kemizhibo.kemizhibo.other.preparing_center;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_center.adapter.PreparingCenterGridAdapter;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_center.presenter.PreparingCenterPresenter;
import com.kemizhibo.kemizhibo.other.preparing_center.presenter.PreparingCenterPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_center.view.PreparingCenterView;
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

public class ForTeanchingFirstFragment extends BaseFragment implements PreparingCenterView{
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
    private String materialId = "";//教材版本id
    private String gradeId = "";//年级id
    private String semesterId = "";//学期id
    private List<PreparingCenterBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private PreparingCenterGridAdapter adapter;
    private boolean autoRefresh = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new PreparingCenterPresenterImp(this);
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
        expend();
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.refresh();
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.loadMore();
            }
        });
        presenter.refresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    private void expend() {

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
        dataBeanList.clear();
        dataBeanList.addAll(bean.getContent().getData());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishRefresh();
                setAdapter();
            }
        });
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
                if(isLoadMore){
                    refreshLayout.finishLoadMore();
                }else{
                    refreshLayout.finishRefresh();
                }
            }
        });
    }
}
