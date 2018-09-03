package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.presenter.PreparingLessonsPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.adapter.TeachingLessonsListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.presenter.TeachingLessonsPresenter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.presenter.TeachingLessonsPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.view.TeachingLessonsView;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
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
public class TeachingLessonsFragment extends BaseFragment implements TeachingLessonsView{
    @BindView(R.id.spring_view)
    SpringView springView;
    @BindView(R.id.list_view)
    ListView listView;
    /*@BindView(R.id.frame_layout)
    FrameLayout frameLayout;*/
    private List<TeachingLessonsBean.ContentBean.DataBean> dataBeanList = new ArrayList<>();
    private TeachingLessonsListAdapter adapter;
    private TeachingLessonsPresenter presenter;
    private String startTime = "";
    private String userId = "0";
    private int roleId;
    private LoadingFragment loadingFragment;
    private LoadingEmptyFragment loadingEmptyFragment;
    private LoadingErrorFragment loadingErrorFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        roleId = PreferencesUtils.getIntValue(Constants.ROLE_ID, getActivity());
        presenter = new TeachingLessonsPresenterImp(this);
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity, R.layout.fragment_preparing_teaching_lessons);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        loadingFragment = new LoadingFragment();
        loadingEmptyFragment = new LoadingEmptyFragment();
        loadingErrorFragment = new LoadingErrorFragment();
        loadingErrorFragment.setListener(new LoadingErrorFragment.OnErrorPageCickListener() {
            @Override
            public void onErrorPageClick() {
                if(null != presenter){
                    springView.setVisibility(View.INVISIBLE);
                    //frameLayout.setVisibility(View.VISIBLE);
                    //getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
                    presenter.refreshTeachingLessonsData();
                }
            }
        });

        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new AliHeader(getActivity(), R.drawable.ali, true));
        springView.setFooter(new AliFooter(getActivity(), true));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                userId = "0";
                startTime = "";
                presenter.refreshTeachingLessonsData();
            }

            @Override
            public void onLoadmore() {
                presenter.loadMoreTeachingLessonsData();
            }
        });

        //frameLayout.setVisibility(View.VISIBLE);
        //getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CommonWebActivity.class);
                intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.TEACH);
                intent.putExtra(Constants.MODULE_ID, dataBeanList.get(position).getModuleId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
        presenter.refreshTeachingLessonsData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
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
        params.put(Constants.START_TIME, startTime);
        if(roleId == 8){
            params.put(Constants.USER_ID, userId);
        }
        return params;
    }

    @Override
    public void refreshSuccess(TeachingLessonsBean bean) {
        dataBeanList.clear();
        dataBeanList.addAll(bean.getContent().getData());
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(dataBeanList.size() > 0){
                        //frameLayout.setVisibility(View.GONE);
                        springView.setVisibility(View.VISIBLE);
                        //frameLayout.setVisibility(View.GONE);
                        setState(LoadingPager.LoadResult.success);
                        setAdapter(false);
                    }else{
                        springView.setVisibility(View.INVISIBLE);
                        //frameLayout.setVisibility(View.VISIBLE);
                        setState(LoadingPager.LoadResult.empty);
                        //getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingEmptyFragment).commit();
                    }
                }
            });
        }
    }

    private void setAdapter(final boolean isLoadMore) {
        springView.onFinishFreshAndLoad();
        if(adapter == null){
            adapter = new TeachingLessonsListAdapter(getActivity(), dataBeanList);
            listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreSuccess(TeachingLessonsBean bean) {
        dataBeanList.addAll(bean.getContent().getData());
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setState(LoadingPager.LoadResult.success);
                    setAdapter(true);
                }
            });
        }
    }

    @Override
    public void error(final int errorCode, final boolean isLoadMore) {
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(null != springView)
                        springView.onFinishFreshAndLoad();
                    if(Constants.OTHER_ERROR_CODE == errorCode){
                        if(null != springView)
                            springView.setVisibility(View.VISIBLE);
                        //frameLayout.setVisibility(View.GONE);
                        setState(LoadingPager.LoadResult.success);
                        LoadFailUtil.initDialogToLogin(getActivity());
                    }else{
                        if(dataBeanList.size() > 0){
                            if(null != springView)
                                springView.setVisibility(View.VISIBLE);
                            //frameLayout.setVisibility(View.GONE);
                            ToastUtils.showToast("网络中断，请检查您的网络状态");
                        }else{
                            if(null != springView)
                                springView.setVisibility(View.INVISIBLE);
                            setState(LoadingPager.LoadResult.error);
                            //frameLayout.setVisibility(View.VISIBLE);
                            //getChildFragmentManager().openTransaction().replace(R.id.frame_layout, loadingErrorFragment).commit();
                        }
                    }
                }
            });
        }
    }

    public void onDateFilterSelect(String startTime){
        this.startTime = startTime;
        if(null != presenter)
            presenter.refreshTeachingLessonsData();
    }

    public void onManagerFilterSelect(int userId, String time) {
        this.userId = String.valueOf(userId);
        this.startTime = time;
        if(null != presenter)
            presenter.refreshTeachingLessonsData();
    }
}
