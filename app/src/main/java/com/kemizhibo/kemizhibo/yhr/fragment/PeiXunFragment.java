package com.kemizhibo.kemizhibo.yhr.fragment;

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
import android.widget.ListView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.TeacherTrainingFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.TeacherTrainingPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;
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

public class PeiXunFragment extends BaseMvpFragment<TeacherTrainingPresenterImpl> implements TeacherTrainingView {

    @BindView(R.id.teacher_training_recyclerview)
    RecyclerView teacherTrainingRecyclerview;
    @BindView(R.id.teacher_trainingg_spring)
    SpringView teacherTraininggSpring;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private List<TeacherTrainingBean.ContentBean.DataBean> teacherTrainingData = new ArrayList<>();
    TeacherTrainingFragmentAdapter teacherTrainingFragmentAdapter;
    private String materialEdition = "";
    private String subjectId = "";
    private String semester = "";
    //上或者下拉的状态判断
    int isUp = 1;
    private int currentPage = 1;
    private SharedPreferences sp;
    private String token;
    private int itemCount;
    @Inject
    public TeacherTrainingPresenterImpl teacherTrainingPresenter;

    private String headers[] = {"教材", "年级", "学期"};
    private List<View> popupViews = new ArrayList<>();
    private ListMaterialAdapter cityAdapter;
    private ListGradeAdapter ageAdapter;
    private ListSemesterAdapter sexAdapter;
    //筛选条件
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.peixun_fragment);
        ButterKnife.bind(this, view);
        //展示教师培训的列表数据
        initTeacherTrainingData();
        initView();
        return view;
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
        //筛选条件
        isUp=1;
        teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
        teacherTrainingPresenter.getFilterData(mActivity);
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
    }

    @Override
    public void onFilterError(String msg) {
        LogUtils.i("报错了3",msg);
    }

    @Override
    public void onTeacherTrainingSuccess(TeacherTrainingBean teacherTrainingBean) {
        if (teacherTrainingBean.getCode() == 0) {
            if (isUp == 1) {
                teacherTrainingData.clear();
                teacherTrainingData.addAll(teacherTrainingBean.getContent().getData());
                if (teacherTrainingData.size() == 0) {
                    teacherTrainingFragmentAdapter.notifyDataSetChanged();
                    setState(LoadingPager.LoadResult.empty);
                } else {
                    teacherTrainingFragmentAdapter.notifyDataSetChanged();
                    setState(LoadingPager.LoadResult.success);
                }
            } else if (isUp == 2) {
                if (itemCount >= teacherTrainingBean.getContent().getTotal()) {
                    ToastUtils.showToast("没有更多数据");
                } else {
                    teacherTrainingData.addAll(teacherTrainingBean.getContent().getData());
                    if (teacherTrainingData.size() == 0) {
                        setState(LoadingPager.LoadResult.empty);
                    } else {
                        teacherTrainingFragmentAdapter.notifyDataSetChanged();
                        setState(LoadingPager.LoadResult.success);
                    }
                }
            }
        } else if (teacherTrainingBean.getCode()==401){
            initDialogToLogin();
        }else {
            setState(LoadingPager.LoadResult.error);
        }
    }

    @Override
    public void onTeacherTrainingError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TeacherTrainingPresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return teacherTrainingPresenter;
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }


    private void initTeacherTrainingData() {
        //设置适配器
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        teacherTrainingRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        teacherTrainingRecyclerview.setItemAnimator(new DefaultItemAnimator());
        teacherTraininggSpring.setType(SpringView.Type.FOLLOW);

        teacherTrainingFragmentAdapter = new TeacherTrainingFragmentAdapter(R.layout.teachertraining_item, teacherTrainingData);
        teacherTrainingFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    Intent intent = new Intent(getActivity().getApplicationContext(), YingXinagVideoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(teacherTrainingData.get(position).getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    getActivity().startActivity(intent);
                }
            }
        });
        teacherTrainingRecyclerview.setAdapter(teacherTrainingFragmentAdapter);
        itemCount = teacherTrainingFragmentAdapter.getItemCount();
        teacherTraininggSpring.setListener(new SpringView.OnFreshListener() {
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
                        teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
                        teacherTraininggSpring.onFinishFreshAndLoad();
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
                        teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
                        teacherTraininggSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        teacherTraininggSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        teacherTraininggSpring.setFooter(new AliFooter(getContext(), true));

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
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
                } else {
                    mDropDownMenu.setTabText(filterMaterialdata.get(position).getSubjectName());
                    materialEdition = filterMaterialdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
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
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
                } else {
                    mDropDownMenu.setTabText(filterGradedata.get(position).getSubjectName());
                    subjectId = filterGradedata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
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
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
                } else {
                    mDropDownMenu.setTabText(filterSemesterdata.get(position).getSubjectName());
                    semester = filterSemesterdata.get(position).getSubjectId() + "";
                    currentPage = 1;
                    isUp = 1;
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer " + token, "TEACHERCOURSE", currentPage+"", "10", materialEdition, subjectId, semester, "", "");
                }
                mDropDownMenu.closeMenu();
            }
        });
        //好像文字的水印
        TextView contentView = new TextView(getContext());
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
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