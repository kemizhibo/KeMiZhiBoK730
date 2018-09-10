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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dueeeke.videoplayer.util.L;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_center.adapter.PreparingCenterGridAdapter;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListImgScienceAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.filtureadapter.ListSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.YingXiangFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.FilterPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;
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

public class YingXiangFragment extends BaseMvpFragment<FilterPresenterImpl> implements FilterView {

     @Inject
    public FilterPresenterImpl filterPresenter;

    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    @BindView(R.id.yinxiang_recyclerview)
    RecyclerView yinxiangRecyclerview;
    @BindView(R.id.yinxiang_spring)
    SpringView yinxiangSpring;

    private String knowledgeId = "";
    private String materialEdition = "";
    private String semester = "";
    private String subjectId = "";
    private int knowledgeIdI = -1;
    /*private int materialEditionI = -1;
    private int knowledgeIdI = -1;
    private int semesterI = -1;
    private int subjectIdI = -1;*/
    private SharedPreferences sp;
    private String token;
    //上或者下拉的状态判断
    int isUp = 1;
    private Intent intent;
    private Bundle bundle;
    private int currentPage = 1;
    private int itemCount = 0;
    YingXiangFragmentAdapter yingXiangFragmentAdapter;
    private List<YingXiangFragmentBean.ContentBean.DataBean> yingXiangFragmentdata = new ArrayList<>();

    //private String headers[] = {"教材", "年级", "学期", "分类"};
    private String headers[] = {"分类"};
    private List<View> popupViews = new ArrayList<>();
    /*private ListMaterialAdapter cityAdapter;
    private ListGradeAdapter ageAdapter;
    private ListSemesterAdapter sexAdapter;*/
    private ListImgScienceAdapter constellationAdapter;
    //筛选条件
    /*private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;*/
    private List<FilterBean.ContentBean.ImgScienceBean> filterImgSciencedata;
    private ImageView contentView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(R.layout.yingxiang_fragment);
        ButterKnife.bind(this, view);
        //影像素材展示列表的方法
        initYingXiangFragmentData();
        initView();
        return view;
    }


    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        //筛选条件
        isUp=1;
        filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
        filterPresenter.getFilterData(mActivity);
    }

    public void initialize(){
        knowledgeId = "";
        knowledgeIdI= -1;
        if(null != mDropDownMenu && mDropDownMenu.isShowing()){
            mDropDownMenu.closeMenu();
        }
    }

    private void initView() {
        //init city menu
        /*final ListView cityView = new ListView(getContext());
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
        sexView.setAdapter(sexAdapter);*/

        //init constellation
        final ListView constellationView = new ListView(getContext());
        constellationView.setDividerHeight(0);
        constellationAdapter = new ListImgScienceAdapter(getContext(), filterImgSciencedata);
        constellationView.setAdapter(constellationAdapter);

        //init popupViews
        /*popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);*/
        popupViews.add(constellationView);

        //add item click event
       /* cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                cityAdapter.setCheckItem(position);
                if (position==0){
                    mDropDownMenu.setTabText(headers[0]);
                    materialEdition="";
                    currentPage = 1;
                    isUp = 1;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }else {
                    mDropDownMenu.setTabText(filterMaterialdata.get(position).getSubjectName());
                    materialEdition = filterMaterialdata.get(position).getSubjectId()+"";
                    currentPage = 1;
                    isUp=1;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                ageAdapter.setCheckItem(position);
                if (position==0){
                    mDropDownMenu.setTabText(headers[1]);
                    subjectId="";
                    currentPage = 1;
                    isUp = 1;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }else {
                    mDropDownMenu.setTabText(filterGradedata.get(position).getSubjectName());
                    subjectId = filterGradedata.get(position).getSubjectId()+"";
                    currentPage = 1;
                    isUp = 1;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                sexAdapter.setCheckItem(position);
                if (position==0){
                    mDropDownMenu.setTabText(headers[2]);
                    semester="";
                    currentPage = 1;
                    isUp = 1;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }else {
                    mDropDownMenu.setTabText(filterSemesterdata.get(position).getSubjectName());
                    semester = filterSemesterdata.get(position).getSubjectId()+"";
                    currentPage = 1;
                    isUp = 1;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });*/

        constellationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setState(LoadingPager.LoadResult.loading);
                constellationAdapter.setCheckItem(position);
                if (position==0){
                    mDropDownMenu.setTabText(headers[0]);
                    knowledgeId="";
                    currentPage = 1;
                    isUp = 1;
                    knowledgeIdI = position;
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }else {
                    mDropDownMenu.setTabText(filterImgSciencedata.get(position).getSubjectName());
                    knowledgeId = filterImgSciencedata.get(position).getSubjectId()+"";
                    currentPage = 1;
                    isUp = 1;
                    knowledgeIdI = position;
                    setState(LoadingPager.LoadResult.loading);
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage+"", "10", materialEdition, subjectId, semester, knowledgeId);
                }
                mDropDownMenu.closeMenu();
            }
        });

        //中间显示区域
        contentView = new ImageView(getContext());
        contentView.setImageResource(R.mipmap.zanwusucai);
        contentView.setVisibility(View.GONE);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    /*@Override
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

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void onFilterSuccess(FilterBean filterBean) {
        setState(LoadingPager.LoadResult.success);
        //教材版本
        /*filterMaterialdata = new ArrayList<>();
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
        filterSemesterdata.addAll(filterBean.getContent().getSemester());*/
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
    public void onYingXiangFragmentSuccess(YingXiangFragmentBean yingXiangFragmentBean) {
        if (yingXiangFragmentBean.getCode()==0){
            if (isUp == 1) {
                yingXiangFragmentdata.clear();
                yingXiangFragmentdata.addAll(yingXiangFragmentBean.getContent().getData());
                if (yingXiangFragmentdata.size()>0){
                    contentView.setVisibility(View.GONE);
                    yinxiangRecyclerview.setVisibility(View.VISIBLE);
                    setState(LoadingPager.LoadResult.success);
                    yingXiangFragmentAdapter.notifyDataSetChanged();
                }else {
                    yinxiangRecyclerview.setVisibility(View.INVISIBLE);
                    contentView.setVisibility(View.VISIBLE);
                    contentView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            knowledgeId="";
                            isUp = 1;
                            currentPage = 1;
                            constellationAdapter.setCheckItem(0);
                            mDropDownMenu.setTabText(headers[0]);
                            initialize();
                            //设置头部为固定的通用样式
                        /*cityAdapter.setCheckItem(0);
                        ageAdapter.setCheckItem(0);
                        sexAdapter.setCheckItem(0);*/
                            sp = getContext().getSharedPreferences("logintoken", 0);
                            token = sp.getString("token", "");
                            setState(LoadingPager.LoadResult.loading);
                            filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                        }
                    });
                    yingXiangFragmentAdapter.notifyDataSetChanged();
                }
            } else if (isUp == 2) {
                if (itemCount>=yingXiangFragmentBean.getContent().getTotal()){
                    ToastUtils.showToast("没有更多数据");
                }else {
                    yingXiangFragmentdata.addAll(yingXiangFragmentBean.getContent().getData());
                    if (yingXiangFragmentdata.size()==0){
                        setState(LoadingPager.LoadResult.empty);
                    }else {
                        yingXiangFragmentAdapter.notifyDataSetChanged();
                        setState(LoadingPager.LoadResult.success);
                    }
                }
            }
        }else if (yingXiangFragmentBean.getCode()==401){
            initDialogToLogin();
        }else {
            setState(LoadingPager.LoadResult.error);
        }
    }

    @Override
    public void onYingXiangFragmentError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected FilterPresenterImpl initInjector() {
        fragmentComponent.inject(this);
        return filterPresenter;
    }

    @Override
    public int getEmptyPageLayoutId() {
        return R.layout.beishouke_empty_layout;
    }


    private void initYingXiangFragmentData() {
        //设置适配器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        yinxiangRecyclerview.setLayoutManager(gridLayoutManager);
        //上拉下拉动画效果
        yinxiangRecyclerview.setItemAnimator(new DefaultItemAnimator());
        yinxiangSpring.setType(SpringView.Type.FOLLOW);
        // 这里的yingXiangFragmentdata 这个集合号线目前为止是空吧
        yingXiangFragmentAdapter = new YingXiangFragmentAdapter(R.layout.yingxiang_item, yingXiangFragmentdata);
        yinxiangRecyclerview.setAdapter(yingXiangFragmentAdapter);
        itemCount = yingXiangFragmentAdapter.getItemCount();
        LogUtils.i("标记怎么走7","重新复制拿到当前条目数"+itemCount);
        yingXiangFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    switch (yingXiangFragmentdata.get(position).getFileType()) {
                        case "VIDEO":
                            intent = new Intent(getActivity().getApplicationContext(), TeacherTrainingDetailsActivity.class);
                            bundle = new Bundle();
                            bundle.putString("courseId", String.valueOf(yingXiangFragmentdata.get(position).getCourseId()));
                            intent.putExtras(bundle);
                            //这里一定要获取到所在Activity再startActivity()；
                            getActivity().startActivity(intent);
                            break;
                        default:
                            intent = new Intent(getActivity().getApplicationContext(), PictrueDetailsActivity.class);
                            bundle = new Bundle();
                            bundle.putString("courseId", String.valueOf(yingXiangFragmentdata.get(position).getCourseId()));
                            intent.putExtras(bundle);
                            //这里一定要获取到所在Activity再startActivity()；
                            getActivity().startActivityForResult(intent, MyApplication.YINGXIANG_TO_PICK_req);
                            break;
                    }
                }
            }
        });
        yinxiangSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*materialEdition="";
                        subjectId="";
                        semester="";*/
                        knowledgeId="";
                        isUp = 1;
                        currentPage = 1;
                        constellationAdapter.setCheckItem(0);
                        mDropDownMenu.setTabText(headers[0]);
                        initialize();
                        //设置头部为固定的通用样式
                        /*cityAdapter.setCheckItem(0);
                        ageAdapter.setCheckItem(0);
                        sexAdapter.setCheckItem(0);*/
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                        yinxiangSpring.onFinishFreshAndLoad();
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
                        filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", String.valueOf(currentPage), "10", materialEdition, subjectId, semester, knowledgeId);
                        yinxiangSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        yinxiangSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        yinxiangSpring.setFooter(new AliFooter(getContext(), true));
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
