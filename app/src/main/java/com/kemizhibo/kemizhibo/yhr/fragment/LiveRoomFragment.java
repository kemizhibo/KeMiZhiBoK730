package com.kemizhibo.kemizhibo.yhr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.web.MyLiveRoomWebActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterGradeAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterImgScienceAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterMaterialAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.FilterSemesterAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.LiveRoomFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.LiveRoomBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.LiveRoomPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.DropDownMenuView;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.LiveRoomView;
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

public class LiveRoomFragment extends BaseMvpFragment<LiveRoomPresenterImpl> implements LiveRoomView {


    @Inject
    public LiveRoomPresenterImpl liveRoomPresenter;
    //申明presenterImpl对象,教材筛选条件
    LiveRoomFragmentAdapter liveRoomFragmentAdapter;
    @BindView(R.id.live_room_recyclerview)
    RecyclerView liveRoomRecyclerview;
    @BindView(R.id.live_room_spring)
    SpringView liveRoomSpring;
    @BindView(R.id.live_room_xiala_imageview)
    ImageView liveRoomXialaImageview;
    @BindView(R.id.live_room_shaixuan_butn)
    LinearLayout liveRoomShaixuanButn;
    @BindView(R.id.live_room_shaixuan_jiaocai_recyclerview)
    RecyclerView liveRoomShaixuanJiaocaiRecyclerview;
    @BindView(R.id.live_room_shaixuan_nianji_recyclerview)
    RecyclerView liveRoomShaixuanNianjiRecyclerview;
    @BindView(R.id.live_room_shaixuan_xueqi_recyclerview)
    RecyclerView liveRoomShaixuanXueqiRecyclerview;
    @BindView(R.id.live_room_shaixuan_fenlei_recyclerview)
    RecyclerView liveRoomShaixuanFenleiRecyclerview;
    @BindView(R.id.live_room_xiala_dropDownMenu)
    DropDownMenuView liveRoomXialaDropDownMenu;

    private List<LiveRoomBean.ContentBean.DataBean> liveDataBean;
    //筛选条件
    FilterMaterialAdapter filterMaterialAdapter;
    private List<FilterBean.ContentBean.MaterialBean> filterMaterialdata;
    FilterGradeAdapter filterGradeAdapter;
    private List<FilterBean.ContentBean.GradeBean> filterGradedata;
    FilterSemesterAdapter filterSemesterAdapter;
    private List<FilterBean.ContentBean.SemesterBean> filterSemesterdata;
    FilterImgScienceAdapter filterImgScienceAdapter;
    private List<FilterBean.ContentBean.ImgScienceBean> filterImgSciencedata;
    private String materialEdition;
    private String knowledgeId;
    private String semester;
    private String subjectId;
   //分页
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
        View view = UIUtils.inflate(R.layout.kexue_fragment);
        ButterKnife.bind(this, view);
        //科学观察室展示列表的方法
        initLiveRoomFragmentData();
        return view;
    }

    private void initLiveRoomFragmentData() {
        //设置适配器
        GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
        liveRoomRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        liveRoomRecyclerview.setItemAnimator(new DefaultItemAnimator());
        liveRoomSpring.setType(SpringView.Type.FOLLOW);
        //LogUtils.e("gradedata.size()",gradedata.size()+"");
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
                    /*//判断点击的是图片还是视频还是直播
                    if (liveDataBean.get(position).getFileType().equals("VIDEO")) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), YingXinagVideoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(liveDataBean.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        getActivity().startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity().getApplicationContext(), TeacherTrainingDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(liveDataBean.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        getActivity().startActivity(intent);
                    }*/
                }
            }
        });
        liveRoomRecyclerview.setAdapter(liveRoomFragmentAdapter);
        liveRoomSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        currentPage = 1;
                        liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", currentPage + "", "10", "", "", "", "");
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
                        liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", currentPage + "", "10", "", "", "", "");
                        liveRoomSpring.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        liveRoomSpring.setHeader(new AliHeader(getContext(), R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        liveRoomSpring.setFooter(new AliFooter(getContext(), true));
    }

    @Override
    public void load() {
        liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", "1", "8", "", "", "", "");
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
        if (!liveRoomXialaDropDownMenu.isOpen()) {
            liveRoomXialaDropDownMenu.open();
            showPopTopWithDarkBg();
        }
    }

    @Override
    public void onFilterError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onLiveRoomSuccess(LiveRoomBean liveRoomBean) {
        setState(LoadingPager.LoadResult.success);
        liveDataBean = new ArrayList<>();
        liveDataBean.addAll(liveRoomBean.getContent().getData());
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

    @OnClick(R.id.live_room_shaixuan_butn)
    public void onViewClicked() {
        liveRoomPresenter.getFilterData(mActivity);
    }


    private void showPopTopWithDarkBg() {
        //处理popWindow 显示筛选内容
        setDataFilterMaterial();
        setDataFilterGrade();
        setDataFilterSemester();
        setDataFilterImgScience();
    }


    private void setDataFilterImgScience() {
        //RecyclerView yingxiangShaixuanFenleiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_fenlei_recyclerview);
        //设置适配器
        LinearLayoutManager imgScienceManage = new LinearLayoutManager(getContext());
        imgScienceManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        liveRoomShaixuanFenleiRecyclerview.setLayoutManager(imgScienceManage);
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
                liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
            }
        });
        liveRoomShaixuanFenleiRecyclerview.setAdapter(filterImgScienceAdapter);
    }

    private void setDataFilterSemester() {
        //RecyclerView yingxiangShaixuanXueqiRecyclerview = contentView.findViewById(R.id.yingxiang_shaixuan_xueqi_recyclerview);
        //设置适配器
        LinearLayoutManager semesterManage = new LinearLayoutManager(getContext());
        semesterManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        liveRoomShaixuanXueqiRecyclerview.setLayoutManager(semesterManage);
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
                liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
            }
        });
        liveRoomShaixuanXueqiRecyclerview.setAdapter(filterSemesterAdapter);
    }

    private void setDataFilterGrade() {
        //设置适配器
        LinearLayoutManager gradeManage = new LinearLayoutManager(getContext());
        gradeManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        liveRoomShaixuanNianjiRecyclerview.setLayoutManager(gradeManage);
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
                liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
            }
        });
        liveRoomShaixuanNianjiRecyclerview.setAdapter(filterGradeAdapter);
    }

    private void setDataFilterMaterial() {
        //设置适配器
        LinearLayoutManager materialManage = new LinearLayoutManager(getContext());
        materialManage.setOrientation(LinearLayoutManager.HORIZONTAL);
        liveRoomShaixuanJiaocaiRecyclerview.setLayoutManager(materialManage);
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
                liveRoomPresenter.getLiveRoomData(mActivity, "SCIENCEROOM", "1", "10", materialEdition, subjectId, semester, knowledgeId);
                isFlag = true;
            }
        });
        liveRoomShaixuanJiaocaiRecyclerview.setAdapter(filterMaterialAdapter);
    }

}
