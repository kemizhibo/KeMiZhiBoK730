package com.kemizhibo.kemizhibo.other.preparing_online;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_online.adapter.PlanListAdapter;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.LiveRoomDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.forteachadapter.ShouKeAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.forteachimpl.ShouKePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.forteachcenter.ShouKeView;
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

/**
 * Author: yhr
 * Date: on 2018/6/19.
 * Describe:
 */

public class ForTeanchingSecondFragment extends BaseMvpFragment<ShouKePresenterImpl> implements ShouKeView {

    @Inject
    public ShouKePresenterImpl shouKePresenter;
    @BindView(R.id.recycler_View)
    RecyclerView recyclerView;
    @BindView(R.id.spring_view)
    SpringView springView;
    /*@BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;*/

    private List<TeachingLessonsBean.ContentBean.DataBean> dataBeans = new ArrayList<>();
    ShouKeAdapter shouKeAdapter;
    private String materialEdition = "";
    private String subjectId = "";
    private String semester = "";
    //上或者下拉的状态判断
    private int selectIndex = -1;
    int isUp = 1;
    private int currentPage = 1;
    private SharedPreferences sp;
    private String token;
    private int itemCount;
    private String courseId;

    private String headers[] = {"教材", "年级", "学期"};
    private List<View> popupViews = new ArrayList<>();
    private ListMaterialAdapter cityAdapter;
    private ListGradeAdapter ageAdapter;
    private ListSemesterAdapter sexAdapter;
    //筛选条件
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    private List<CommonUserTeachPlanBean.ContentBean> planBeanList = new ArrayList<>();
    private ImageView contentView;
    private PopupWindow planPop;
    private DropDownMenu mDropDownMenu;
    /* private int materialSelectI = -1;
    private int gradeSelectI = -1;
    private int semesterSelectI = -1;*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.forteaching_second_fragment);
        mDropDownMenu = view.findViewById(R.id.dropDownMenu);
        ButterKnife.bind(this, view);
        //展示教师培训的列表数据
        initGetShouKeData();
        initView();
        return view;
    }

    private void initGetShouKeData() {
        //设置适配器
        LinearLayoutManager layoutManage = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        springView.setType(SpringView.Type.FOLLOW);
        shouKeAdapter = new ShouKeAdapter(R.layout.item_preparing_online_list, dataBeans);
        shouKeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            courseId = String.valueOf(dataBeans.get(position).getCourseId());
                            shouKePresenter.getUserPlanData(mActivity,"Bearer "+token,courseId,"false");
                        }
                    });
                }
            }
        });
        recyclerView.setAdapter(shouKeAdapter);
        itemCount = shouKeAdapter.getItemCount();
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
                        shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
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
                        shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
                        //shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "app", currentPage + "", "10");
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        springView.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        springView.setFooter(new AliFooter(getContext(), true));
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
                    shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
                    //beiKePresenter.getBeiKeData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage + "", "10", materialEdition, subjectId, semester, "", "");
                } else {
                    //mDropDownMenu.setTabText(filterMaterialdata.get(position).getSubjectName());
                    materialEdition = filterMaterialdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
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
                    shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
                } else {
                    //mDropDownMenu.setTabText(filterGradedata.get(position).getSubjectName());
                    subjectId = filterGradedata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
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
                    shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
                } else {
                    //mDropDownMenu.setTabText(filterSemesterdata.get(position).getSubjectName());
                    semester = filterSemesterdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
                }
                mDropDownMenu.closeMenu();
            }
        });
        //好像文字的水印
        contentView = new ImageView(getContext());
        LogUtils.i("报错2","123");
        contentView.setImageResource(R.mipmap.zanwusucai);
        LogUtils.i("报错3","123");
        contentView.setVisibility(View.INVISIBLE);
        LogUtils.i("报错4","123");
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        LogUtils.i("报错5","123");
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        }
        //生命周期中获取的弹窗的影藏和显示
        if(null != planPop && planPop.isShowing()){
            planPop.dismiss();
        }

    }*/

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        isUp=1;
        //筛选条件
        shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
        shouKePresenter.getFilterData(mActivity);
    }

    @Override
    public void onShouKeSuccess(TeachingLessonsBean teachingLessonsBean) {
        if (teachingLessonsBean.getCode() == 0) {
            if (isUp == 1) {
                dataBeans.clear();
                dataBeans.addAll(teachingLessonsBean.getContent().getData());
                if (dataBeans.size() > 0) {
                    contentView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    setState(LoadingPager.LoadResult.success);
                    shouKeAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    contentView.setVisibility(View.VISIBLE);
                    LogUtils.i("报错6","123");
                    shouKeAdapter.notifyDataSetChanged();
                    contentView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LogUtils.i("报错7","123");
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
                            shouKePresenter.getShouKeData(mActivity, "Bearer " + token, materialEdition, subjectId, semester, "10", currentPage + "", "app");
                        }
                    });
                    //shouKeAdapter.notifyDataSetChanged();
                }
            } else if (isUp == 2) {
                if (itemCount >= teachingLessonsBean.getContent().getTotal()) {
                    ToastUtils.showToast("没有更多数据");
                } else {
                    dataBeans.addAll(teachingLessonsBean.getContent().getData());
                    if (dataBeans.size() == 0) {
                        setState(LoadingPager.LoadResult.empty);
                    } else {
                        shouKeAdapter.notifyDataSetChanged();
                        setState(LoadingPager.LoadResult.success);
                    }
                }
            }
        } else if (teachingLessonsBean.getCode()==401){
            initDialogToLogin();
        }else {
            setState(LoadingPager.LoadResult.error);
        }
    }

    @Override
    public void onShouKeError(String msg) {
        LogUtils.i("报错1",msg);
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
    }

    @Override
    public void onFilterError(String msg) {
    }

    //获取用户方案列表
    @Override
    public void onUserPlanSuccess(CommonUserTeachPlanBean commonUserTeachPlanBean) {
        if (commonUserTeachPlanBean.getCode()==0){
            planBeanList.clear();
            planBeanList.addAll(commonUserTeachPlanBean.getContent());
            showPlanPop();
        }else if (commonUserTeachPlanBean.getCode()==401||commonUserTeachPlanBean.getCode()==801){
            //登录过期
            ToastUtils.showToast("登录过期");
        }else {
            ToastUtils.showToast("获取授课方案列表错误");
        }
    }


    //方案列表
    private void showPlanPop() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_plan, null, false);
        final LinearLayout linearLayout = view.findViewById(R.id.pop_layout);
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
        planPop = new PopupWindow(getActivity());
        planPop.setContentView(view);
        planPop.setWidth(733);
        planPop.setHeight(400);
        planPop.setOutsideTouchable(true);
        planPop.setBackgroundDrawable(new BitmapDrawable());
        planPop.setFocusable(true);
        //planPop.showAtLocation(getActivity().getCurrentFocus(), Gravity.CENTER, 0, 0);
        planPop.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
        planPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                initialize();
            }
        });
        RelativeLayout goTeach = view.findViewById(R.id.go_teach_rl);
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
                        intent.putExtra(Constants.MODULE_ID, String.valueOf(planBeanList.get(selectIndex).getModuleId()));
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
    }

    @Override
    public void onUserPlanError(String msg) {
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

    public void initialize(){
        materialEdition = "";
        subjectId = "";
        semester = "";
        /*materialSelectI = -1;
        gradeSelectI = -1;
        semesterSelectI = -1;*/
        if(null != mDropDownMenu && mDropDownMenu.isShowing()){
            mDropDownMenu.closeMenu();
        }
    }

    @Override
    protected ShouKePresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return shouKePresenter;
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
        //生命周期中获取的弹窗的影藏和显示
        if(null != planPop && planPop.isShowing()){
            planPop.dismiss();
        }
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {

        }else {
            if(null != mDropDownMenu && mDropDownMenu.isShowing()){
                mDropDownMenu.closeMenu();
            }
        }
    }
}
