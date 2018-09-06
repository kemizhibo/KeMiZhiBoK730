package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterImgScienceAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.TeacherTrainingFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.TeacherTrainingPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.DropDownMenuView;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PeiXunFragment extends BaseMvpFragment<TeacherTrainingPresenterImpl> implements TeacherTrainingView {
    @BindView(R.id.teacher_training_recyclerview)
    RecyclerView teacherTrainingRecyclerview;
    @BindView(R.id.teacher_trainingg_spring)
    SpringView teacherTraininggSpring;
    @BindView(R.id.teacher_training_xiala_imageview)
    ImageView teacherTrainingXialaImageview;
    @BindView(R.id.teacher_training_shaixuan_butn)
    LinearLayout teacherTrainingShaixuanButn;
    @BindView(R.id.teacher_training_shaixuan_jiaocai_recyclerview)
    RecyclerView teacherTrainingShaixuanJiaocaiRecyclerview;
    @BindView(R.id.teacher_training_shaixuan_nianji_recyclerview)
    RecyclerView teacherTrainingShaixuanNianjiRecyclerview;
    @BindView(R.id.teacher_training_shaixuan_xueqi_recyclerview)
    RecyclerView teacherTrainingShaixuanXueqiRecyclerview;
    @BindView(R.id.teacher_training_xiala_dropDownMenu)
    DropDownMenuView teacherTrainingXialaDropDownMenu;

    //筛选条件
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    FilterMaterialAdapter filterMaterialAdapter;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    FilterGradeAdapter filterGradeAdapter;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    FilterSemesterAdapter filterSemesterAdapter;

    @Inject
    public TeacherTrainingPresenterImpl teacherTrainingPresenter;
    //申明presenterImpl对象,教师培训列表
    TeacherTrainingFragmentAdapter teacherTrainingFragmentAdapter;
    private List<TeacherTrainingBean.ContentBean.DataBean> teacherTrainingData = new ArrayList<>();
    private String materialEdition;
    private String knowledgeId;
    private String semester;
    private String subjectId;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;
    //页数
    private int currentPage;
    private SharedPreferences sp;
    private String token;
    private int itemCount;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public int getEmptyPageLayoutId() {
        return 0;
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.peixun_fragment);
        ButterKnife.bind(this, view);
        //展示教师培训的列表数据
        initTeacherTrainingData();
        return view;
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
                        materialEdition="";
                        subjectId="";
                        semester="";
                        isUp = 1;
                        currentPage = 1;
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", currentPage + "", "10", "", "", "", "","");
                        isFlag = true;
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
                        teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", currentPage + "", "10", "", "", "", "","");
                        isFlag = true;
                        teacherTraininggSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        teacherTraininggSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        teacherTraininggSpring.setFooter(new AliFooter(getContext(), true));

    }

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", "1", "10", "", "", "", "", "");
    }

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void onFilterSuccess(FilterBean filterBean) {
        setState(LoadingPager.LoadResult.success);
        filterMaterialdata = new ArrayList<>();
        filterMaterialdata.addAll(filterBean.getContent().getMaterial());
        filterGradedata = new ArrayList<>();
        filterGradedata.addAll(filterBean.getContent().getGrade());
        filterSemesterdata = new ArrayList<>();
        filterSemesterdata.addAll(filterBean.getContent().getSemester());
        if (!teacherTrainingXialaDropDownMenu.isOpen()) {
            teacherTrainingXialaDropDownMenu.open();
            showPopTopWithDarkBg();
        }
    }

    @Override
    public void onFilterError(String msg) {
        //setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onTeacherTrainingSuccess(TeacherTrainingBean teacherTrainingBean) {
        if (teacherTrainingBean.getCode()==0){
            if (isUp == 1) {
                teacherTrainingData.clear();
                LogUtils.i("上拉下拉","1");
                teacherTrainingData.addAll(teacherTrainingBean.getContent().getData());
                LogUtils.i("上拉下拉","2");
                if (teacherTrainingData==null){
                    setState(LoadingPager.LoadResult.empty);
                    LogUtils.i("上拉下拉","3");
                }else {
                    setState(LoadingPager.LoadResult.success);
                    LogUtils.i("上拉下拉","4");
                    if (isFlag) {
                        teacherTrainingFragmentAdapter.notifyDataSetChanged();
                        LogUtils.i("上拉下拉","5");
                    }
                }
            } else if (isUp == 2) {
                if (itemCount>=teacherTrainingBean.getContent().getTotal()){
                    LogUtils.i("上拉下拉当前条目数量1",itemCount+"");
                    /*if (isUp==1){
                        yingXiangFragmentdata.clear();
                    }*/
                    LogUtils.i("上拉下拉","6");
                    ToastUtils.showToast("没有更多数据");
                }else {
                    teacherTrainingData.addAll(teacherTrainingBean.getContent().getData());
                    LogUtils.i("上拉下拉","7");
                    teacherTrainingFragmentAdapter.notifyDataSetChanged();
                    LogUtils.i("上拉下拉","8");
                    LogUtils.i("上拉下拉","9");
                    if (teacherTrainingData==null){
                        setState(LoadingPager.LoadResult.empty);
                        LogUtils.i("上拉下拉","10");
                    }else {
                        setState(LoadingPager.LoadResult.success);
                        LogUtils.i("上拉下拉","11");
                        if (isFlag) {
                            teacherTrainingFragmentAdapter.notifyDataSetChanged();
                            LogUtils.i("上拉下拉","12");
                        }
                    }
                }
            }
        }else {
            initDialogToLogin();
        }
    }

    @Override
    public void onTeacherTrainingError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TeacherTrainingPresenterImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        //this需要去di注册
        fragmentComponent.inject(this);
        return teacherTrainingPresenter;
    }

    @OnClick(R.id.teacher_training_shaixuan_butn)
    public void onViewClicked() {
        if (NoFastClickUtils.isFastClick()) {
        }else {
            materialEdition="";
            subjectId="";
            semester="";
            teacherTrainingPresenter.getFilterData(mActivity);
        }
    }


    private void showPopTopWithDarkBg() {
        //处理popWindow 显示筛选内容
        setDataFilterMaterial();
        setDataFilterGrade();
        setDataFilterSemester();
    }
    private void setDataFilterSemester() {
        //RecyclerView yingxiangShaixuanXueqiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_xueqi_recyclerview);
        //设置适配器
        LinearLayoutManager semesterManage = new LinearLayoutManager(getContext());
        semesterManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        teacherTrainingShaixuanXueqiRecyclerview.setLayoutManager(semesterManage);
        //LogUtils.e("data.size()",data.size()+"");
        filterSemesterAdapter = new FilterSemesterAdapter(R.layout.shaixuan_xueqi_item, filterSemesterdata);
        filterSemesterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()){
                }else {
                    semester = String.valueOf(filterSemesterdata.get(position).getSubjectId());
                    //改变单选状态，并且刷新数据
                    for (int i=0;i<filterSemesterdata.size();i++){
                        filterSemesterdata.get(i).setFlage(false);
                        filterSemesterdata.set(i, filterSemesterdata.get(i));
                    }
                    filterSemesterdata.get(position).setFlage(true);
                    filterSemesterdata.set(position, filterSemesterdata.get(position));
                    filterSemesterAdapter.notifyDataSetChanged();
                    sp = getContext().getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    teacherTrainingData.clear();
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, "", knowledgeId);
                    isFlag = true;
                }
            }
        });
        teacherTrainingShaixuanXueqiRecyclerview.setAdapter(filterSemesterAdapter);
    }

    private void setDataFilterGrade() {
        //设置适配器
        LinearLayoutManager gradeManage = new LinearLayoutManager(getContext());
        gradeManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        teacherTrainingShaixuanNianjiRecyclerview.setLayoutManager(gradeManage);
        //LogUtils.e("data.size()",data.size()+"");
        filterGradeAdapter = new FilterGradeAdapter(R.layout.shaixuan_nianji_item, filterGradedata);
        filterGradeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()){
                }else {
                    subjectId = String.valueOf(filterGradedata.get(position).getSubjectId());
                    //改变单选状态，并且刷新数据
                    for (int i=0;i<filterGradedata.size();i++){
                        filterGradedata.get(i).setFlage(false);
                        filterGradedata.set(i, filterGradedata.get(i));
                    }
                    filterGradedata.get(position).setFlage(true);
                    filterGradedata.set(position, filterGradedata.get(position));
                    filterGradeAdapter.notifyDataSetChanged();
                    sp = getContext().getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    teacherTrainingData.clear();
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, "", knowledgeId);
                    isFlag = true;
                }
            }
        });
        teacherTrainingShaixuanNianjiRecyclerview.setAdapter(filterGradeAdapter);
    }

    private void setDataFilterMaterial() {
        //RecyclerView yingxiangShaixuanJiaocaiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_jiaocai_recyclerview);
        //设置适配器
        LinearLayoutManager materialManage = new LinearLayoutManager(getContext());
        materialManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        teacherTrainingShaixuanJiaocaiRecyclerview.setLayoutManager(materialManage);
        //LogUtils.e("data.size()",data.size()+"");
        filterMaterialAdapter = new FilterMaterialAdapter(R.layout.shaixuanjiaocai_item, filterMaterialdata);
        filterMaterialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()){
                }else {
                    materialEdition = String.valueOf(filterMaterialdata.get(position).getSubjectId());

                    for (int i = 0; i < filterMaterialdata.size(); i++) {
                        filterMaterialdata.get(i).setFlage(false);
                        filterMaterialdata.set(i, filterMaterialdata.get(i));
                    }
                    filterMaterialdata.get(position).setFlage(true);
                    filterMaterialdata.set(position, filterMaterialdata.get(position));
                    filterMaterialAdapter.notifyDataSetChanged();
                    sp = getContext().getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    teacherTrainingData.clear();
                    teacherTrainingPresenter.getTeacherTrainingData(mActivity, "Bearer "+token,"TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, "", knowledgeId);
                    isFlag = true;
                }


                /*YingXiangFragmentBean.ContentBean.DataBean yingxiangbean = new  YingXiangFragmentBean.ContentBean.DataBean();
                yingxiangbean.setSubjectId(filterMaterialdata.get(position).getSubjectId());
                yingXiangFragmentdata.add(yingxiangbean);*/
                //ToastUtils.showToast(materialEdition);
            }
        });
        teacherTrainingShaixuanJiaocaiRecyclerview.setAdapter(filterMaterialAdapter);
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getContext());
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                }else {
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
