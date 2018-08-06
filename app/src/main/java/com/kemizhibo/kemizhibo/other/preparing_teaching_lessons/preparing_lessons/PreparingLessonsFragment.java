package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.adapter.PreparingLessonsListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.presenter.PreparingLessonsPresenter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.presenter.PreparingLessonsPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.view.PreparingLessonsView;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
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
 * Created by Administrator on 2018/8/1.
 */

public class PreparingLessonsFragment extends Fragment implements PreparingLessonsView{
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.list_view)
    ListView listView;
    private int planStatus = 0;
    private List<PreparingLessonsBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private PreparingLessonsListAdapter adapter;
    private PreparingLessonsPresenter presenter;
    private String startTime = "";
    private String userId = "0";
    private int roleId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        roleId = PreferencesUtils.getIntValue(Constants.ROLE_ID, context);
        presenter = new PreparingLessonsPresenterImp(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.inflate(getActivity(), R.layout.fragment_preparing_teaching_lessons);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                userId = "0";
                startTime = "";
                planStatus = 0;
                presenter.loadMorePreparingLessonsData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                userId = "0";
                startTime = "";
                planStatus = 0;
                presenter.refreshPreparingLessonsData();
            }
        });
        presenter.refreshPreparingLessonsData();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public Context getCustomContext() {
        return getActivity();
    }

    @Override
    public Map getRequestParams() {
        Map params = new HashMap();
        if(roleId == 8){
            params.put(Constants.USER_ID, userId);
            params.put(Constants.START_TIME, startTime);
        }else{
            params.put(Constants.PLAN_STATUS, String.valueOf(planStatus));
        }
        return params;
    }

    @Override
    public void refreshSuccess(PreparingLessonsBean bean) {
        dataBeanList.clear();
        dataBeanList.addAll(bean.getContent().getData());
        setAdapter(false);
    }

    private void setAdapter(final boolean isLoadMore) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isLoadMore){
                    refreshLayout.finishLoadMore();
                }else{
                    refreshLayout.finishRefresh();
                }
                if(adapter == null){
                    adapter = new PreparingLessonsListAdapter(getActivity(), dataBeanList);
                    listView.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void loadMoreSuccess(PreparingLessonsBean bean) {
        dataBeanList.addAll(bean.getContent().getData());
        setAdapter(true);
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

    public void onStatusFilterSelect(int planStatus) {
        this.planStatus = planStatus;
        presenter.refreshPreparingLessonsData();
    }

    public void onManagerFilterSelect(int userId, String time) {
        this.userId = String.valueOf(userId);
        this.startTime = time;
        presenter.refreshPreparingLessonsData();
    }
}
