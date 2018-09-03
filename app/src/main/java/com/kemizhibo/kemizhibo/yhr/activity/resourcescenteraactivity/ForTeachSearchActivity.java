package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.ForTeachSearchAdapter;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.SearchAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.ForTeachSearchBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.ForTeachSearchPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.ForeTeachSearchIView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class ForTeachSearchActivity extends BaseMvpActivity<ForTeachSearchPresenterImpl> implements ForeTeachSearchIView {
    @Inject
    public ForTeachSearchPresenterImpl forTeachSearchPresenter;
    @BindView(R.id.search_view_box)
    SearchView searchViewBox;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.search_recyclerview)
    RecyclerView searchRecyclerview;
    @BindView(R.id.search_springview)
    SpringView searchSpringview;
    private List<ForTeachSearchBean.ContentBean.DataBean> dataBeans;
    //上或者下拉的状态判断
    int isUp = 1;
    private int currentPage;
    private String coursename;
    private SharedPreferences sp;
    private String token;
    ForTeachSearchAdapter forTeachSearchAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_for_teach_search;
    }

    @Override
    protected void initData() {
        searchViewBox.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String courseName) {
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    coursename = courseName;
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    forTeachSearchPresenter.getForTeachSearchData(ForTeachSearchActivity.this, "Bearer " + token, coursename, "10", "1");
                }
            }
        });

        // 3. 设置点击返回按键后的操作（通过回调接口）
        searchViewBox.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }

    @Override
    protected ForTeachSearchPresenterImpl initInject() {
        activityComponent.inject(this);
        return forTeachSearchPresenter;
    }

    @Override
    public void onForeTeachSearchSuccess(ForTeachSearchBean foreTeachSearchBean) {
        if (foreTeachSearchBean.getCode() == 0) {
            dataBeans = new ArrayList<>();
            //切换控件
            frameLayout.setVisibility(View.GONE);
            searchRecyclerview.setVisibility(View.VISIBLE);
            dataBeans.addAll(foreTeachSearchBean.getContent().getData());
            if (foreTeachSearchBean.getContent().getData().size()>0){
                //加载数据
                initSearchData();
            }else {
                //切换控件
                frameLayout.setVisibility(View.VISIBLE);
                searchRecyclerview.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentEmpty()).commit();
            }

        } else {
            initDialogToLogin();
        }
    }

    private void initSearchData() {
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        searchRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        searchRecyclerview.setItemAnimator(new DefaultItemAnimator());
        searchSpringview.setType(SpringView.Type.FOLLOW);
        forTeachSearchAdapter = new ForTeachSearchAdapter(R.layout.search_item, dataBeans);
        forTeachSearchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    if (dataBeans.get(position).getFileType().equals("VIDEO")) {
                        Intent intent = new Intent(ForTeachSearchActivity.this, YingXinagVideoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        ForTeachSearchActivity.this.startActivity(intent);
                    } else if (dataBeans.get(position).getFileType().equals("LIVE")) {
                        Intent intent = new Intent(ForTeachSearchActivity.this, TeacherTrainingDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        ForTeachSearchActivity.this.startActivity(intent);
                    } else {
                        Intent intent = new Intent(ForTeachSearchActivity.this, PictrueDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        ForTeachSearchActivity.this.startActivity(intent);
                    }
                }
            }
        });
        searchRecyclerview.setAdapter(forTeachSearchAdapter);
        searchSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        currentPage = 1;
                        sp = getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        forTeachSearchPresenter.getForTeachSearchData(ForTeachSearchActivity.this, "Bearer " + token, coursename, "10", "1");
                        searchSpringview.onFinishFreshAndLoad();
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
                        sp = getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        forTeachSearchPresenter.getForTeachSearchData(ForTeachSearchActivity.this, "Bearer " + token, coursename, "10", "1");
                        searchSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        searchSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        searchSpringview.setFooter(new AliFooter(this, true));
    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                }else {
                                    Intent intent = new Intent(ForTeachSearchActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
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

    @Override
    public void onForeTeachSearchError(String msg) {

    }

}
