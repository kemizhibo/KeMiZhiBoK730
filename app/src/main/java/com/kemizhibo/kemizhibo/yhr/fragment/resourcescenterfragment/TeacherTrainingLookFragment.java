package com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.TeacherTrainingLookFragmentAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpFragment;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingDetailsVideoUrlBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.TeacherTrainingLookBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.TeacherTrainingPresenterLookFragmentImpl;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.TeacherTrainingLookFragmentView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: yhr
 * Date: on 2018/7/10.
 * Describe:教师培训看讲解
 */

public class TeacherTrainingLookFragment extends BaseMvpFragment<TeacherTrainingPresenterLookFragmentImpl> implements TeacherTrainingLookFragmentView {
    @Inject
    public TeacherTrainingPresenterLookFragmentImpl teacherTrainingPresenterLookFragment;
    //创建适配器
    TeacherTrainingLookFragmentAdapter teacherTrainingLookFragmentAdapter;
    private List<TeacherTrainingLookBean.ContentBean.DataBean> teacherLookListBeans;

    @BindView(R.id.teacher_training_lookfragment_recyclerview)
    RecyclerView teacherTrainingLookfragmentRecyclerview;


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
        View view = UIUtils.inflate(mActivity, R.layout.teachertraininglookfragment_layout);
        ButterKnife.bind(this, view);
        //展示数据的方法
        showLookFragmentData();
        return view;
    }



    @Override
    public void load() {
        teacherTrainingPresenterLookFragment.getTeacherTrainingLookFragmentData(mActivity, "LIVEEXPLAIN", "1003162", "1", "8");
    }

    @Override
    public void onEmptyViewClick() {

    }

    @Override
    public void onTeacherTrainingLookFragmentSuccess(TeacherTrainingLookBean teacherTrainingLookBean) {
        //成功的状态显示UI操作,添加数据
        setState(LoadingPager.LoadResult.success);
        teacherLookListBeans = new ArrayList<>();
        teacherLookListBeans.addAll(teacherTrainingLookBean.getContent().getData());

    }

    private void showLookFragmentData() {
        //设置适配器
        teacherTrainingLookfragmentRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        //LogUtils.e("gradedata.size()",gradedata.size()+"");
        teacherTrainingLookFragmentAdapter = new TeacherTrainingLookFragmentAdapter(R.layout.teachertraininglookfragment_item, teacherLookListBeans);
        teacherTrainingLookfragmentRecyclerview.setAdapter(teacherTrainingLookFragmentAdapter);
    }

    @Override
    public void onTeacherTrainingLookFragmentError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void onTeacherTrainingDetailsVideoUrlSuccess(TeacherTrainingDetailsVideoUrlBean teacherTrainingDetailsVideoUrlBean) {
        //成功的状态显示UI操作,添加数据
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onTeacherTrainingDetailsVideoUrlError(String msg) {
        //加载失败的状态
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TeacherTrainingPresenterLookFragmentImpl initInjector() {
        //如果是fragment，就是他，如果是acyivity就是acyivityComponent
        //this需要去di注册
        fragmentComponent.inject(this);
        return teacherTrainingPresenterLookFragment;
    }

}
