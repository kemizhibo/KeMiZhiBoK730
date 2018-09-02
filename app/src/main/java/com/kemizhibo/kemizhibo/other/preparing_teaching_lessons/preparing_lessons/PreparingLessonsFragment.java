package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.load.LoadFailUtil;
import com.kemizhibo.kemizhibo.other.load.LoadingEmptyFragment;
import com.kemizhibo.kemizhibo.other.load.LoadingErrorFragment;
import com.kemizhibo.kemizhibo.other.load.LoadingFragment;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.adapter.PreparingLessonsListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.presenter.PreparingLessonsPresenter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.presenter.PreparingLessonsPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.view.PreparingLessonsView;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/1.
 */
@SuppressLint("RestrictedApi")
public class PreparingLessonsFragment extends Fragment implements PreparingLessonsView{
    @BindView(R.id.spring_view)
    SpringView springView;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private int planStatus = 0;
    private List<PreparingLessonsBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private PreparingLessonsListAdapter adapter;
    private PreparingLessonsPresenter presenter;
    private String startTime = "";
    private String userId = "0";
    private int roleId;
    private LoadingEmptyFragment loadingEmptyFragment;
    private LoadingErrorFragment loadingErrorFragment;
    private LoadingFragment loadingFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = UIUtils.inflate(getActivity(), R.layout.fragment_preparing_teaching_lessons);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadingFragment = new LoadingFragment();
        loadingEmptyFragment = new LoadingEmptyFragment();
        loadingErrorFragment = new LoadingErrorFragment();
        loadingErrorFragment.setListener(new LoadingErrorFragment.OnErrorPageCickListener() {
            @Override
            public void onErrorPageClick() {
                if(null != presenter){
                    springView.setVisibility(View.INVISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);
                    getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
                    presenter.refreshPreparingLessonsData();
                }
            }
        });
        roleId = PreferencesUtils.getIntValue(Constants.ROLE_ID, getActivity());
        presenter = new PreparingLessonsPresenterImp(this);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new AliHeader(getActivity(), R.drawable.ali, true));
        springView.setFooter(new AliFooter(getActivity(), true));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                userId = "0";
                startTime = "";
                planStatus = 0;
                presenter.refreshPreparingLessonsData();
            }

            @Override
            public void onLoadmore() {
                presenter.loadMorePreparingLessonsData();
            }
        });
        presenter.refreshPreparingLessonsData();
        frameLayout.setVisibility(View.VISIBLE);
        getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CommonWebActivity.class);
                if(roleId == Constants.MANAGER_ROLE_ID){
                    intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.PREVIEW);
                }else{
                    //子账号跳转授课页面，H5会自己获取备课状态
                    intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.TEACH);
                }
                intent.putExtra(Constants.MODULE_ID, dataBeanList.get(position).getModuleId());
                startActivity(intent);
            }
        });
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
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(dataBeanList.size() > 0){
                        springView.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.GONE);
                        setAdapter(false);
                    }else{
                        springView.setVisibility(View.INVISIBLE);
                        frameLayout.setVisibility(View.VISIBLE);
                        getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingEmptyFragment).commit();
                    }
                }
            });
        }
    }

    private void setAdapter(final boolean isLoadMore) {
        springView.onFinishFreshAndLoad();
        if(adapter == null){
            adapter = new PreparingLessonsListAdapter(getActivity(), dataBeanList);
            listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreSuccess(PreparingLessonsBean bean) {
        dataBeanList.addAll(bean.getContent().getData());
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setAdapter(true);
                }
            });
        }
    }

    @Override
    public void error(final int errorCode, final boolean isLoadMore) {
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @SuppressLint("RestrictedApi")
                @Override
                public void run() {
                    springView.onFinishFreshAndLoad();
                    if(Constants.OTHER_ERROR_CODE == errorCode){
                        springView.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.GONE);
                        LoadFailUtil.initDialogToLogin(getActivity());
                    }else{
                        if(dataBeanList.size() > 0 ){
                            springView.setVisibility(View.VISIBLE);
                            frameLayout.setVisibility(View.GONE);
                            ToastUtils.showToast("网络中断，请检查您的网络状态");
                        }else{
                            springView.setVisibility(View.GONE);
                            frameLayout.setVisibility(View.VISIBLE);
                            getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingErrorFragment).commit();
                        }
                    }
                }
            });
        }
    }

    public void onStatusFilterSelect(int planStatus) {
        this.planStatus = planStatus;
        if(null != presenter)
            presenter.refreshPreparingLessonsData();
    }

    public void onManagerFilterSelect(int userId, String time) {
        this.userId = String.valueOf(userId);
        this.startTime = time;
        if(null != presenter)
            presenter.refreshPreparingLessonsData();
    }
}
