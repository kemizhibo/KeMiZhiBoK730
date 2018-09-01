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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterImgScienceAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.YingXiangFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.FilterPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.DropDownMenuView;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.FilterView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YingXiangFragment extends BaseMvpFragment<FilterPresenterImpl> implements FilterView {

    @Inject
    public FilterPresenterImpl filterPresenter;
    @BindView(R.id.yingxiang_shaixuan_butn)
    LinearLayout yingxiangShaixuanButn;
    @BindView(R.id.yingxiang_xiala_dropDownMenu)
    DropDownMenuView yingxiangXialaDropDownMenu;
    @BindView(R.id.yinxiang_recyclerview)
    RecyclerView yinxiangRecyclerview;
    @BindView(R.id.yinxiang_spring)
    SpringView yinxiangSpring;
    @BindView(R.id.yingxiang_shaixuan_jiaocai_recyclerview)
    RecyclerView yingxiangShaixuanJiaocaiRecyclerview;
    @BindView(R.id.yingxiang_shaixuan_nianji_recyclerview)
    RecyclerView yingxiangShaixuanNianjiRecyclerview;
    @BindView(R.id.yingxiang_shaixuan_xueqi_recyclerview)
    RecyclerView yingxiangShaixuanXueqiRecyclerview;
    @BindView(R.id.yingxiang_shaixuan_fenlei_recyclerview)
    RecyclerView yingxiangShaixuanFenleiRecyclerview;

    //筛选条件
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    FilterMaterialAdapter filterMaterialAdapter;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    FilterGradeAdapter filterGradeAdapter;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    FilterSemesterAdapter filterSemesterAdapter;
    private List<FilterBean.ContentBean.ImgScienceBean> filterImgSciencedata;
    FilterImgScienceAdapter filterImgScienceAdapter;

    //申明presenterImpl对象,影像素材列表
    YingXiangFragmentAdapter yingXiangFragmentAdapter;
    private List<YingXiangFragmentBean.ContentBean.DataBean> yingXiangFragmentdata = new ArrayList<>();
    private String materialEdition;
    private String knowledgeId;
    private String semester;
    private String subjectId;
    private Intent intent;
    private Bundle bundle;
    private int currentPage = 1;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;
    private SharedPreferences sp;
    private String token;
    private int itemCount = 0;
    private GridLayoutManager gridLayoutManager;

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
        return view;
    }

    private void initYingXiangFragmentData() {
        //设置适配器
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        yinxiangRecyclerview.setLayoutManager(gridLayoutManager);
        //上拉下拉动画效果
        yinxiangRecyclerview.setItemAnimator(new DefaultItemAnimator());
        yinxiangSpring.setType(SpringView.Type.FOLLOW);
        // 这里的yingXiangFragmentdata 这个集合号线目前为止是空吧
        yingXiangFragmentAdapter = new YingXiangFragmentAdapter(R.layout.yingxiang_item, yingXiangFragmentdata);
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
        yinxiangRecyclerview.setAdapter(yingXiangFragmentAdapter);
        itemCount = yingXiangFragmentAdapter.getItemCount();
        LogUtils.i("上拉下拉当前条目数量2",itemCount+"");
        yinxiangSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.i("刚进来下拉没","下来了");
                        materialEdition="";
                        subjectId="";
                        semester="";
                        knowledgeId="";
                        isUp = 1;
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        currentPage=1;
                        filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", currentPage + "", "10", materialEdition, subjectId, semester, knowledgeId);
                        isFlag = true;
                        yinxiangSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.i("刚进来下拉没","上拉了");
                        isUp = 2;
                        currentPage++;
                        sp = getContext().getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", String.valueOf(currentPage), "10", materialEdition, subjectId, semester, knowledgeId);
                        isFlag = true;
                        LogUtils.i("123456",String.valueOf(currentPage));
                        yinxiangSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        yinxiangSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        yinxiangSpring.setFooter(new AliFooter(getContext(), true));
    }

    @Override
    public void load() {
        sp = getContext().getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", "1", "10", materialEdition, subjectId, semester, knowledgeId);
    }

    @OnClick(R.id.yingxiang_shaixuan_butn)
    public void onViewClicked() {
        if (NoFastClickUtils.isFastClick()) {
        }else {
            materialEdition="";
            subjectId="";
            semester="";
            knowledgeId="";
            filterPresenter.getFilterData(mActivity);
        }
    }

    private void showPopTopWithDarkBg() {
        //处理popWindow 显示筛选内容
        setDataFilterMaterial();
        setDataFilterGrade();
        setDataFilterSemester();
        setDataFilterImgScience();
    }


    private void setDataFilterImgScience() {
        //RecyclerView yingxiangShaixuanFenleiRecyclerview = findViewById(R.id.yingxiang_shaixuan_fenlei_recyclerview);
        //设置适配器
        LinearLayoutManager imgScienceManage = new LinearLayoutManager(getContext());
        imgScienceManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        yingxiangShaixuanFenleiRecyclerview.setLayoutManager(imgScienceManage);
        //LogUtils.e("data.size()",data.size()+"");
        filterImgScienceAdapter = new FilterImgScienceAdapter(R.layout.shaixuan_fenlei_item, filterImgSciencedata);
        filterImgScienceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()){
                }else {
                    knowledgeId = String.valueOf(filterImgSciencedata.get(position).getSubjectId());
                    //改变单选状态，并且刷新数据
                    for (int i=0;i<filterImgSciencedata.size();i++){
                        filterImgSciencedata.get(i).setFlage(false);
                        filterImgSciencedata.set(i, filterImgSciencedata.get(i));
                    }
                    filterImgSciencedata.get(position).setFlage(true);
                    filterImgSciencedata.set(position, filterImgSciencedata.get(position));
                    filterImgScienceAdapter.notifyDataSetChanged();
                    sp = getContext().getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    yingXiangFragmentdata.clear();
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                    isFlag = true;
                }
            }
        });
        yingxiangShaixuanFenleiRecyclerview.setAdapter(filterImgScienceAdapter);
    }

    private void setDataFilterSemester() {
        //RecyclerView yingxiangShaixuanXueqiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_xueqi_recyclerview);
        //设置适配器
        LinearLayoutManager semesterManage = new LinearLayoutManager(getContext());
        semesterManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        yingxiangShaixuanXueqiRecyclerview.setLayoutManager(semesterManage);
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
                    yingXiangFragmentdata.clear();
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                    isFlag = true;
                }
            }
        });
        yingxiangShaixuanXueqiRecyclerview.setAdapter(filterSemesterAdapter);
    }

    private void setDataFilterGrade() {
        //RecyclerView yingxiangShaixuanNianjiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_nianji_recyclerview);
        //设置适配器
        LinearLayoutManager gradeManage = new LinearLayoutManager(getContext());
        gradeManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        yingxiangShaixuanNianjiRecyclerview.setLayoutManager(gradeManage);
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
                    yingXiangFragmentdata.clear();
                    filterPresenter.getYingXiangFragmentData(mActivity,"Bearer "+token, "YINGXIANGSUCAI", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                    isFlag = true;
                }
            }
        });
        yingxiangShaixuanNianjiRecyclerview.setAdapter(filterGradeAdapter);
    }

    private void setDataFilterMaterial() {
        //RecyclerView yingxiangShaixuanJiaocaiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_jiaocai_recyclerview);
        //设置适配器
        LinearLayoutManager materialManage = new LinearLayoutManager(getContext());
        materialManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        yingxiangShaixuanJiaocaiRecyclerview.setLayoutManager(materialManage);
        //LogUtils.e("data.size()",data.size()+"");
        filterMaterialAdapter = new FilterMaterialAdapter(R.layout.shaixuanjiaocai_item, filterMaterialdata);
        filterMaterialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()){
                }else {
                    materialEdition = String.valueOf(filterMaterialdata.get(position).getSubjectId());
                    //改变单选状态，并且刷新数据
                    for (int i=0;i<filterMaterialdata.size();i++){
                        filterMaterialdata.get(i).setFlage(false);
                        filterMaterialdata.set(i, filterMaterialdata.get(i));
                    }
                    filterMaterialdata.get(position).setFlage(true);
                    filterMaterialdata.set(position, filterMaterialdata.get(position));
                    filterMaterialAdapter.notifyDataSetChanged();
                    sp = getContext().getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    yingXiangFragmentdata.clear();
                    filterPresenter.getYingXiangFragmentData(mActivity, "Bearer "+token,"YINGXIANGSUCAI", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                    isFlag = true;
                }
            }
        });
        yingxiangShaixuanJiaocaiRecyclerview.setAdapter(filterMaterialAdapter);
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
        filterImgSciencedata = new ArrayList<>();
        filterImgSciencedata.addAll(filterBean.getContent().getImgScience());
        if (!yingxiangXialaDropDownMenu.isOpen()) {
            yingxiangXialaDropDownMenu.open();
            showPopTopWithDarkBg();
        }
    }

    @Override
    public void onFilterError(String msg) {
        //setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onYingXiangFragmentSuccess(YingXiangFragmentBean yingXiangFragmentBean) {
        if (yingXiangFragmentBean.getCode()==0){
            if (isUp == 1) {
                yingXiangFragmentdata.clear();
                LogUtils.i("上拉下拉","1");
                yingXiangFragmentdata.addAll(yingXiangFragmentBean.getContent().getData());
                LogUtils.i("上拉下拉","2");
                if (yingXiangFragmentdata.size()==0){
                    setState(LoadingPager.LoadResult.empty);
                    LogUtils.i("上拉下拉","3");
                }else {
                    setState(LoadingPager.LoadResult.success);
                    LogUtils.i("上拉下拉","4");
                    if (isFlag) {
                        yingXiangFragmentAdapter.notifyDataSetChanged();
                        LogUtils.i("上拉下拉","5");
                    }
                }
            } else if (isUp == 2) {
                if (itemCount>=yingXiangFragmentBean.getContent().getTotal()){
                    LogUtils.i("上拉下拉当前条目数量1",itemCount+"");
                    /*if (isUp==1){
                        yingXiangFragmentdata.clear();
                    }*/
                    LogUtils.i("上拉下拉","6");
                    ToastUtils.showToast("没有更多数据");
                }else {
                    yingXiangFragmentdata.addAll(yingXiangFragmentBean.getContent().getData());
                    LogUtils.i("上拉下拉","7");
                    yingXiangFragmentAdapter.notifyDataSetChanged();
                    LogUtils.i("上拉下拉","8");
                    LogUtils.i("上拉下拉","9");
                    if (yingXiangFragmentdata.size()==0){
                        setState(LoadingPager.LoadResult.empty);
                        LogUtils.i("上拉下拉","10");
                    }else {
                        setState(LoadingPager.LoadResult.success);
                        LogUtils.i("上拉下拉","11");
                        if (isFlag) {
                            yingXiangFragmentAdapter.notifyDataSetChanged();
                            LogUtils.i("上拉下拉","12");
                        }
                    }
                }
            }
        }else {
            setState(LoadingPager.LoadResult.error);
            LogUtils.i("上拉下拉","13");
            Transparent.showErrorMessage(getContext(),"登录失效请重新登录");
        }
    }

    @Override
    public void onYingXiangFragmentError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected FilterPresenterImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        //this需要去di注册
        fragmentComponent.inject(this);
        return filterPresenter;
    }
}
