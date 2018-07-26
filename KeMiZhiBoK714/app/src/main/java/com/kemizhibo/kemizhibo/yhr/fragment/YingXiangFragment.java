package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
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
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
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
import butterknife.Unbinder;

public class YingXiangFragment extends BaseMvpFragment<FilterPresenterImpl> implements FilterView {

    @Inject
    public FilterPresenterImpl filterPresenter;

    //筛选条件
    FilterMaterialAdapter filterMaterialAdapter;
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

    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    FilterGradeAdapter filterGradeAdapter;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    FilterSemesterAdapter filterSemesterAdapter;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    FilterImgScienceAdapter filterImgScienceAdapter;
    private List<FilterBean.ContentBean.ImgScienceBean> filterImgSciencedata;

    //申明presenterImpl对象,影像素材列表
    YingXiangFragmentAdapter yingXiangFragmentAdapter;
    private List<YingXiangFragmentBean.ContentBean.DataBean> yingXiangFragmentdata = new ArrayList<>();
    private String materialEdition;
    private String knowledgeId;
    private String semester;
    private String subjectId;
    private Intent intent;
    private Bundle bundle;
    private int currentPage;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;

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
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        yinxiangRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        yinxiangRecyclerview.setItemAnimator(new DefaultItemAnimator());
        yinxiangSpring.setType(SpringView.Type.FOLLOW);
        yingXiangFragmentAdapter = new YingXiangFragmentAdapter(R.layout.yingxiang_item, yingXiangFragmentdata);
        yingXiangFragmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    switch (yingXiangFragmentdata.get(position).getFileType()) {
                        case "VIDEO":
                            intent = new Intent(getActivity().getApplicationContext(), YingXinagVideoDetailsActivity.class);
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
        yinxiangSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        currentPage = 1;
                        filterPresenter.getYingXiangFragmentData(mActivity, "YINGXIANGSUCAI", currentPage + "", "10", "", "", "", "");
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
                        filterPresenter.getYingXiangFragmentData(mActivity, "YINGXIANGSUCAI", currentPage + "", "10", "", "", "", "");
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
        filterPresenter.getYingXiangFragmentData(mActivity, "YINGXIANGSUCAI", "1", "10", materialEdition, subjectId, semester, knowledgeId);
    }

    @OnClick(R.id.yingxiang_shaixuan_butn)
    public void onViewClicked() {
        filterPresenter.getFilterData(mActivity);
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
                knowledgeId = String.valueOf(filterImgSciencedata.get(position).getSubjectId());
                //改变单选状态，并且刷新数据
                for (int i=0;i<filterImgSciencedata.size();i++){
                    filterImgSciencedata.get(i).setFlage(false);
                    filterImgSciencedata.set(i, filterImgSciencedata.get(i));
                }
                filterImgSciencedata.get(position).setFlage(true);
                filterImgSciencedata.set(position, filterImgSciencedata.get(position));
                filterImgScienceAdapter.notifyDataSetChanged();
                filterPresenter.getYingXiangFragmentData(mActivity, "TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
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
                semester = String.valueOf(filterSemesterdata.get(position).getSubjectId());
                //改变单选状态，并且刷新数据
                for (int i=0;i<filterSemesterdata.size();i++){
                    filterSemesterdata.get(i).setFlage(false);
                    filterSemesterdata.set(i, filterSemesterdata.get(i));
                }
                filterSemesterdata.get(position).setFlage(true);
                filterSemesterdata.set(position, filterSemesterdata.get(position));
                filterSemesterAdapter.notifyDataSetChanged();
                filterPresenter.getYingXiangFragmentData(mActivity, "TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
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
                subjectId = String.valueOf(filterGradedata.get(position).getSubjectId());
                //改变单选状态，并且刷新数据
                for (int i=0;i<filterGradedata.size();i++){
                    filterGradedata.get(i).setFlage(false);
                    filterGradedata.set(i, filterGradedata.get(i));
                }
                filterGradedata.get(position).setFlage(true);
                filterGradedata.set(position, filterGradedata.get(position));
                filterGradeAdapter.notifyDataSetChanged();
                filterPresenter.getYingXiangFragmentData(mActivity, "TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
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
                materialEdition = String.valueOf(filterMaterialdata.get(position).getSubjectId());
               //改变单选状态，并且刷新数据
                for (int i=0;i<filterMaterialdata.size();i++){
                    filterMaterialdata.get(i).setFlage(false);
                    filterMaterialdata.set(i, filterMaterialdata.get(i));
                }
                filterMaterialdata.get(position).setFlage(true);
                filterMaterialdata.set(position, filterMaterialdata.get(position));
                filterMaterialAdapter.notifyDataSetChanged();
                filterPresenter.getYingXiangFragmentData(mActivity, "TEACHERCOURSE", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;

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
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onYingXiangFragmentSuccess(YingXiangFragmentBean yingXiangFragmentBean) {
        //成功的状态显示UI操作,添加数据
        setState(LoadingPager.LoadResult.success);

        if (isUp == 1) {
            yingXiangFragmentdata.clear();
            yingXiangFragmentdata.addAll(yingXiangFragmentBean.getContent().getData());
            if (isFlag) {
                yingXiangFragmentAdapter.notifyDataSetChanged();
            }
        } else if (isUp == 2) {
            yingXiangFragmentdata.addAll(yingXiangFragmentBean.getContent().getData());
            if (isFlag) {
                yingXiangFragmentAdapter.notifyDataSetChanged();
            }
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