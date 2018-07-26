package com.kemizhibo.kemizhibo.yhr.fragment.resourcescenterfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Author: yhr
 * Date: on 2018/7/10.
 * Describe:教师培训讨论区
 */

public class TeacherTrainingTalkFragment extends BaseFragment {

    @BindView(R.id.teacher_training_talk_fragment_recyclerview)
    RecyclerView teacherTrainingTalkFragmentRecyclerview;
    @BindView(R.id.detail_page_do_comment)
    TextView detailPageDoComment;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = UIUtils.inflate(mActivity, R.layout.teachertrainingtalkfragment_layout);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void load() {
        setState(LoadingPager.LoadResult.success);
    }


    @OnClick(R.id.detail_page_do_comment)
    public void onViewClicked() {

    }
}
