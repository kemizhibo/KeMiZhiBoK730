package com.kemizhibo.kemizhibo.other.preparing_center;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.preparing_online.presenter.PreparingOnlinePresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.PreparingPackageDetailActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.forteachadapter.BeiKeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.forteachimpl.BeiKePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.BeiKeView;
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

/**
 * Author: yhr
 * Date: on 2018/6/19.
 * Describe:备授课的授课中心
 */
public class ForTeanchingFirstFragment extends BaseMvpFragment<BeiKePresenterImpl> implements BeiKeView {

    @Inject
    public BeiKePresenterImpl beiKePresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.spring_view)
    SpringView springView;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private List<PreparingCenterBean.ContentBean.DataBean> dataBeans = new ArrayList<>();
    BeiKeAdapter beiKeAdapter;
    private String materialEdition = "";
    private String subjectId = "";
    private String semester = "";
    //上或者下拉的状态判断
    int isUp = 1;
    private int currentPage = 1;
    private SharedPreferences sp;
    private String token;
    private int itemCount;

    private String headers[] = {"教材", "年级", "学期"};
    private List<View> popupViews = new ArrayList<>();
    private ListMaterialAdapter cityAdapter;
    private ListGradeAdapter ageAdapter;
    private ListSemesterAdapter sexAdapter;
    //筛选条件
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    private ImageView contentView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.forteaching_first_fragment);
        ButterKnife.bind(this, view);
        //展示教师培训的列表数据
        initView();
        initGetBeiKeData();
        return view;
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

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);

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
                    beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                    //beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage + "", "10", materialEdition, subjectId, semester, "", "");
                } else {
                    mDropDownMenu.setTabText(filterMaterialdata.get(position).getSubjectName());
                    materialEdition = filterMaterialdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
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
                    beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                } else {
                    mDropDownMenu.setTabText(filterGradedata.get(position).getSubjectName());
                    subjectId = filterGradedata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
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
                    beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                } else {
                    mDropDownMenu.setTabText(filterSemesterdata.get(position).getSubjectName());
                    semester = filterSemesterdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                }
                mDropDownMenu.closeMenu();
            }
        });
        //好像文字的水印
        contentView = new ImageView(getContext());
        contentView.setImageResource(R.mipmap.zanwusucai);
        contentView.setVisibility(View.GONE);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }


    private void initGetBeiKeData() {
        //设置适配器
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        springView.setType(SpringView.Type.FOLLOW);

        beiKeAdapter = new BeiKeAdapter(R.layout.teachertraining_item, dataBeans);
        beiKeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    /*Intent intent = new Intent(getActivity().getApplicationContext(), YingXinagVideoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    getActivity().startActivity(intent);*/
                    int courseId = dataBeans.get(position).getCourseId();
                    Intent intent = new Intent(getActivity(), PreparingPackageDetailActivity.class);
                    intent.putExtra(Constants.COURSE_ID, courseId);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(beiKeAdapter);
        itemCount = beiKeAdapter.getItemCount();
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialEdition = "";
                        subjectId = "";
                        semester = "";
                        isUp = 1;
                        currentPage = 1;
                        //设置头部为固定的通用样式
                        cityAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[0]);
                        ageAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[1]);
                        sexAdapter.setCheckItem(0);
                        //mDropDownMenu.setTabText(headers[2]);
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                        springView.onFinishFreshAndLoad();
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
                        beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        springView.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new AliFooter(getContext(), true));
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        }
    }

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        isUp=1;
        //筛选条件
        beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
        beiKePresenter.getFilterData(mActivity);
    }

    @Override
    public void onBeiKeSuccess(PreparingCenterBean preparingCenterBean) {
        if (preparingCenterBean.getCode() == 0) {
            if (isUp == 1) {
                dataBeans.clear();
                dataBeans.addAll(preparingCenterBean.getContent().getData());
                if (dataBeans.size() > 0) {
                    contentView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    setState(LoadingPager.LoadResult.success);
                    beiKeAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    contentView.setVisibility(View.VISIBLE);
                    contentView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            materialEdition = "";
                            subjectId = "";
                            semester = "";
                            isUp = 1;
                            currentPage = 1;
                            //mDropDownMenu.setTabText(headers[0]);
                            initialize();
                            //设置头部为固定的通用样式
                            cityAdapter.setCheckItem(0);
                            ageAdapter.setCheckItem(0);
                            sexAdapter.setCheckItem(0);
                            sp = getContext().getSharedPreferences("logintoken", 0);
                            token = sp.getString("token", "");
                            setState(LoadingPager.LoadResult.loading);
                            beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                        }
                    });
                    beiKeAdapter.notifyDataSetChanged();
                }
            } else if (isUp == 2) {
                if (itemCount >= preparingCenterBean.getContent().getTotal()) {
                    ToastUtils.showToast("没有更多数据");
                } else {
                    dataBeans.addAll(preparingCenterBean.getContent().getData());
                    if (dataBeans.size() == 0) {
                        setState(LoadingPager.LoadResult.empty);
                    } else {
                        beiKeAdapter.notifyDataSetChanged();
                        setState(LoadingPager.LoadResult.success);
                    }
                }
            }
        } else if (preparingCenterBean.getCode()==401){
            initDialogToLogin();
        }else {
            setState(LoadingPager.LoadResult.error);
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

    @Override
    public void onBeiKeError(String msg) {
        LogUtils.i("错错错1",msg);
    }

    public void initialize(){
        materialEdition = "";
        subjectId = "";
        semester = "";
        if(null != mDropDownMenu && mDropDownMenu.isShowing()){
            mDropDownMenu.closeMenu();
        }
    }
    //筛选条件
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
    }

    @Override
    public void onFilterError(String msg) {
        LogUtils.i("错错错2",msg);
    }

    @Override
    protected BeiKePresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return beiKePresenter;
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public void onEmptyViewClick() {

    }

    public void onHidden(){
        if(null != mDropDownMenu && mDropDownMenu.isShowing()){
            mDropDownMenu.closeMenu();
        }
    }


//public class ForTeanchingFirstFragment extends BaseFragment implements PreparingCenterView, CommonView {
    /*@BindView(R.id.forteaching_shaixuan_imageview)
    ImageView forteachingShaixuanImageview;
    @BindView(R.id.forteaching_shaixuan_butn)
    RelativeLayout forteachingShaixuanButn;
    @BindView(R.id.spring_view)
    SpringView springView;
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
        presenter.refresh();
    }

    @Override
    public void onEmptyViewClick() {
        initialize();
        show();
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
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new AliHeader(getActivity(), R.drawable.ali, true));
        springView.setFooter(new AliFooter(getActivity(), true));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                initialize();
                presenter.refresh();
            }

            @Override
            public void onLoadmore() {
                presenter.loadMore();
            }
        });
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

    @Override
    public int getEmptyPageLayoutId() {
        return R.layout.beishouke_empty_layout;
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
                //initialize();
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
        dataBeanList.clear();
        dataBeanList.addAll(bean.getContent().getData());
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    springView.onFinishFreshAndLoad();
                    //ToastUtils.showToast("refresh" + dataBeanList.size());
                    if(dataBeanList.size() > 0){
                        setState(LoadingPager.LoadResult.success);
                        springView.setVisibility(View.VISIBLE);
                        setAdapter();
                    }else{
                        setState(LoadingPager.LoadResult.empty);
                        springView.setVisibility(View.INVISIBLE);
                    }
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
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //ToastUtils.showToast("load" + dataBeanList.size());
                    setState(LoadingPager.LoadResult.success);
                    springView.onFinishFreshAndLoad();
                    setAdapter();
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
                    //ToastUtils.showToast(String.valueOf(errorCode));
                    if(null != springView)
                        springView.setVisibility(View.INVISIBLE);
                    if(null != springView)
                        springView.onFinishFreshAndLoad();
                    if(Constants.OTHER_ERROR_CODE == errorCode){
                        setState(LoadingPager.LoadResult.success);
                        if(null != springView)
                            springView.setVisibility(View.VISIBLE);
                        LoadFailUtil.initDialogToLogin(getActivity());
                    }else{
                        if(dataBeanList.size() > 0){
                            setState(LoadingPager.LoadResult.success);
                            if(null != springView)
                                springView.setVisibility(View.VISIBLE);
                            ToastUtils.showToast("网络中断，请检查您的网络状态");
                        }else{
                            setState(LoadingPager.LoadResult.error);
                        }
                    }
                }
            });
        }
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
    public void getCommonFilterError(final int errorCode) {
        if(null != getActivity()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(Constants.OTHER_ERROR_CODE == errorCode){
                        if(Constants.OTHER_ERROR_CODE == errorCode){
                            LoadFailUtil.initDialogToLogin(getActivity());
                        }
                    }
                }
            });
        }
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
    }*/
}
