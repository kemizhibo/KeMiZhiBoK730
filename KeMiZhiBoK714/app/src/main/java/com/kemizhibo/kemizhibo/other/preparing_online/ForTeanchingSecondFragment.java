package com.kemizhibo.kemizhibo.other.preparing_online;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_online.adapter.PreparingOnlineListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;
import com.kemizhibo.kemizhibo.other.preparing_online.presenter.PreparingOnlinePresenter;
import com.kemizhibo.kemizhibo.other.preparing_online.presenter.PreparingOnlinePresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_online.view.PreparingOnlineView;
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

/**
 * Author: yhr
 * Date: on 2018/6/19.
 * Describe:
 */

public class ForTeanchingSecondFragment extends BaseFragment implements PreparingOnlineView{
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.list_view)
    ListView listView;

    private PreparingOnlinePresenter presenter;
    private String materialId = "";
    private String gradeId = "";
    private String semesterId = "";
    private String chapterId = "";
    private List<PreparingOnlineBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private PreparingOnlineListAdapter  adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new PreparingOnlinePresenterImp(this);
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
    public void error(int errorCode, boolean isLoadMore) {
        if(isLoadMore){
            refreshLayout.finishLoadMore();
        }else{
            refreshLayout.finishRefresh();
        }
    }
}
