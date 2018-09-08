package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.web.MyLiveRoomWebActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListImgScienceAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.LiveRoomFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LiveRoomFragment extends BaseMvpFragment<LiveRoomPresenterImpl> implements LiveRoomView {

    @BindView(R.id.live_room_recyclerview)
    RecyclerView liveRoomRecyclerview;
    @BindView(R.id.live_room_spring)
    SpringView liveRoomSpring;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    @Inject
    public LiveRoomPresenterImpl liveRoomPresenter;
    //申明presenterImpl对象,教材筛选条件
    LiveRoomFragmentAdapter liveRoomFragmentAdapter;
    private List<LiveRoomBean.ContentBean.DataBean> liveDataBean = new ArrayList<>();
    private String materialEdition = "";
    private String knowledgeId = "";
    private String semester = "";
    private String subjectId = "";
    private SharedPreferences sp;
    private String token;
    //上或者下拉的状态判断
    int isUp = 1;
    private Intent intent;
    private Bundle bundle;
    private int currentPage = 1;
    private int itemCount = 0;
    private String headers[] = {"教材", "年级", "学期", "分类"};
    private List<View> popupViews = new ArrayList<>();
    private ListMaterialAdapter cityAdapter;
    private ListGradeAdapter ageAdapter;
    private ListSemesterAdapter sexAdapter;
    private ListImgScienceAdapter constellationAdapter;
    //筛选条件
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    private List<FilterBean.ContentBean.ImgScienceBean> filterImgSciencedata;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.kexue_fragment);
        ButterKnife.bind(this, view);
        initView();
        //科学观察室展示列表的方法
        initLiveRoomFragmentData();
        return view;
    }

    @Override
    public void load() {
        //筛选条件
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        isUp = 1;
        liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
        liveRoomPresenter.getFilterData(mActivity);
    }

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void onFilterSuccess(FilterBean filterBean) {
        setState(LoadingPager.LoadResult.success);
        //教材版本
        filterMaterialdata = new ArrayList<>();
        FilterBean.ContentBean.MaterialBean materialBean = new FilterBean.ContentBean.MaterialBean();
        materialBean.setSubjectName("通用");
        filterMaterialdata.add(materialBean);
        filterMaterialdata.addAll(filterBean.getContent().getMaterial());
        //年级
        filterGradedata = new ArrayList<>();
        FilterBean.ContentBean.GradeBean gradeBean = new FilterBean.ContentBean.GradeBean();
        gradeBean.setSubjectName("通用");
        filterGradedata.add(gradeBean);
        filterGradedata.addAll(filterBean.getContent().getGrade());
        //学期
        filterSemesterdata = new ArrayList<>();
        FilterBean.ContentBean.SemesterBean semesterBean = new FilterBean.ContentBean.SemesterBean();
        semesterBean.setSubjectName("通用");
        filterSemesterdata.add(semesterBean);
        filterSemesterdata.addAll(filterBean.getContent().getSemester());
        //分类
        filterImgSciencedata = new ArrayList<>();
        FilterBean.ContentBean.ImgScienceBean imgScienceBean = new FilterBean.ContentBean.ImgScienceBean();
        imgScienceBean.setSubjectName("通用");
        filterImgSciencedata.add(imgScienceBean);
        filterImgSciencedata.addAll(filterBean.getContent().getImgScience());
    }

    @Override
    public void onFilterError(String msg) {

    }

    @Override
    public void onLiveRoomSuccess(LiveRoomBean liveRoomBean) {
        if (liveRoomBean.getCode() == 0) {
            if (isUp == 1) {
                liveDataBean.clear();
                liveDataBean.addAll(liveRoomBean.getContent().getData());
                LogUtils.i("数量1", liveDataBean.size() + "");
                if (liveDataBean.size() == 0) {
                    liveRoomFragmentAdapter.notifyDataSetChanged();
                    setState(LoadingPager.LoadResult.empty);
                } else {
                    liveRoomFragmentAdapter.notifyDataSetChanged();
                    setState(LoadingPager.LoadResult.success);
                }
            } else if (isUp == 2) {
                if (itemCount >= liveRoomBean.getContent().getTotal()) {
                    LogUtils.i("数量3", liveDataBean.size() + "");
                    ToastUtils.showToast("没有更多数据");
                } else {
                    liveDataBean.addAll(liveRoomBean.getContent().getData());
                    LogUtils.i("数量2", liveDataBean.size() + "");
                    if (liveDataBean.size() == 0) {
                        setState(LoadingPager.LoadResult.empty);
                    } else {
                        liveRoomFragmentAdapter.notifyDataSetChanged();
                        setState(LoadingPager.LoadResult.success);
                    }
                }
            }
        } else if (liveRoomBean.getCode() == 401) {
            initDialogToLogin();
        } else {
            setState(LoadingPager.LoadResult.error);
        }
    }

    @Override
    public void onLiveRoomError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected LiveRoomPresenterImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        //this需要去di注册
        fragmentComponent.inject(this);
        return liveRoomPresenter;
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    private void initLiveRoomFragmentData() {
        //设置适配器
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        liveRoomRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        liveRoomRecyclerview.setItemAnimator(new DefaultItemAnimator());
        liveRoomSpring.setType(SpringView.Type.FOLLOW);
        liveRoomFragmentAdapter = new LiveRoomFragmentAdapter(R.layout.liveroom_item, liveDataBean);
        liveRoomFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    //点击跳转web页面，并且传值
                    Intent intent = new Intent(getActivity().getApplicationContext(), MyLiveRoomWebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(liveDataBean.get(position).getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    getActivity().startActivity(intent);
                }
            }
        });
        liveRoomRecyclerview.setAdapter(liveRoomFragmentAdapter);
        itemCount = liveRoomFragmentAdapter.getItemCount();
        liveRoomSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialEdition = "";
                        subjectId = "";
                        semester = "";
                        knowledgeId = "";
                        isUp = 1;
                        currentPage = 1;
                        cityAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[0]);
                        ageAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[1]);
                        sexAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[2]);
                        constellationAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[3]);
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                        liveRoomSpring.onFinishFreshAndLoad();
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
                        liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                        liveRoomSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        liveRoomSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        liveRoomSpring.setFooter(new AliFooter(getContext(), true));
    }

    private void initView() {
        //init city menu
        final ListView cityView = new ListView(getContext());
        cityAdapter = new ListMaterialAdapter(getContext(), filterMaterialdata);
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        final ListView ageView = new ListView(getContext());
        ageView.setDividerHeight(0);
        ageAdapter = new ListGradeAdapter(getContext(), filterGradedata);
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(getContext());
        sexView.setDividerHeight(0);
        sexAdapter = new ListSemesterAdapter(getContext(), filterSemesterdata);
        sexView.setAdapter(sexAdapter);

        //init constellation
        final ListView constellationView = new ListView(getContext());
        constellationView.setDividerHeight(0);
        constellationAdapter = new ListImgScienceAdapter(getContext(), filterImgSciencedata);
        constellationView.setAdapter(constellationAdapter);

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                cityAdapter.setCheckItem(position);
                if (position == 0) {
                    mDropDownMenu.setTabText(headers[0]);
                    materialEdition = "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                } else {
                    mDropDownMenu.setTabText(filterMaterialdata.get(position).getSubjectName());
                    materialEdition = filterMaterialdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                ageAdapter.setCheckItem(position);
                if (position == 0) {
                    mDropDownMenu.setTabText(headers[1]);
                    subjectId = "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                } else {
                    mDropDownMenu.setTabText(filterGradedata.get(position).getSubjectName());
                    subjectId = filterGradedata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                sexAdapter.setCheckItem(position);
                if (position == 0) {
                    mDropDownMenu.setTabText(headers[2]);
                    semester = "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                } else {
                    mDropDownMenu.setTabText(filterSemesterdata.get(position).getSubjectName());
                    semester = filterSemesterdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        constellationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                constellationAdapter.setCheckItem(position);
                if (position == 0) {
                    mDropDownMenu.setTabText(headers[3]);
                    knowledgeId = "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                } else {
                    mDropDownMenu.setTabText(filterImgSciencedata.get(position).getSubjectName());
                    knowledgeId = filterImgSciencedata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    liveRoomPresenter.getLiveRoomData(mActivity, "Bearer " + token, "SCIENCEROOM", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        //好像文字的水印
        TextView contentView = new TextView(getContext());
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

   /* @Override
    public void onDestroy() {
        super.onDestroy();
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        }
    }*/
   @Override
   public void onHiddenChanged(boolean hidden) {
       super.onHiddenChanged(hidden);
       //退出activity前关闭菜单
       if (mDropDownMenu.isShowing()) {
           mDropDownMenu.closeMenu();
       }
   }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getContext());
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                } else {
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
