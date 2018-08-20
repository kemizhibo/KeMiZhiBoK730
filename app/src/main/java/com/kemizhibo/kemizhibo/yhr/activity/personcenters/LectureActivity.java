package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LectureBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.GetLoginPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.LecturePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.videoUtils.DefinitionIjkVideoView;
import com.kemizhibo.kemizhibo.yhr.view.LectureView;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LectureActivity extends BaseMvpActivity<LecturePresenterImpl> implements LectureView {

    @Inject
    public LecturePresenterImpl lecturePresenter;

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.player)
    DefinitionIjkVideoView player;
    @BindView(R.id.lecture_title)
    TextView lectureTitle;
    @BindView(R.id.finish_lecture)
    Button finishLecture;
    private String moduleId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lecture;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        //获取传过来的值
        Intent intent = getIntent();
        moduleId = intent.getStringExtra("moduleId");
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("备课包");
        publicTitleBarRoot.buildFinish();
    }

    @OnClick(R.id.finish_lecture)
    public void onViewClicked() {
        SharedPreferences sp = getSharedPreferences("logintoken", 0);
        String token = sp.getString("token", "");
        lecturePresenter.getLectureData(this,"Bearer "+token,moduleId,"3");
    }

    @Override
    public void onLectureSuccess(LectureBean lectureBean) {
        if (lectureBean.getCode()==0){

        }else {

        }
    }

    @Override
    public void onLectureError(String msg) {

    }

    @Override
    protected LecturePresenterImpl initInject() {
        activityComponent.inject(this);
        return lecturePresenter;
    }
}
