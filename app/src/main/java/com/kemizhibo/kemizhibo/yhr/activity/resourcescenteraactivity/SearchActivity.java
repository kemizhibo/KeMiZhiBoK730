package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.SearchAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentSearchEmpty;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.SearchPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview.SearchIView;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

/**
 * Author: yhr
 * Date: 2018/5/21
 * Describe: 搜索页
 */
public class SearchActivity extends BaseMvpActivity<SearchPresenterImpl> implements SearchIView {

    @BindView(R.id.search_recyclerview)
    RecyclerView searchRecyclerview;
    @BindView(R.id.search_springview)
    SpringView searchSpringview;
    @BindView(R.id.search_view_box)
    SearchView searchViewBox;
    // 1. 初始化搜索框变量
    //private SearchView searchView;
    //申明presenterImpl对象,搜索列表
    @Inject
    public SearchPresenterImpl searchPresenter;
    SearchAdapter searchAdapter;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private List<SearchBean.ContentBean.DataBean> dataBeans = new ArrayList<>();
    //上或者下拉的状态判断
    int isUp = 1;
    private int currentPage = 1;
    private String coursename;
    private SharedPreferences sp;
    private String token;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        //加载数据
        initSearchData();
        searchViewBox.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String courseName) {
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    coursename = courseName;
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    searchPresenter.getSearchData(SearchActivity.this, "Bearer " + token, "YINGXIANGSUCAI,BKEXUEGUAN,TEACHERCOURSE", "1", "10", coursename);
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
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onSearchSuccess(SearchBean searchBean) {
        if (searchBean.getCode() == 0) {
            if(isUp == 1){
                //切换控件
                dataBeans.clear();
                dataBeans.addAll(searchBean.getContent().getData());
                if (searchBean.getContent().getData().size()>0){
                    frameLayout.setVisibility(View.GONE);
                    searchRecyclerview.setVisibility(View.VISIBLE);
                    searchAdapter.notifyDataSetChanged();
                }else {
                    //切换控件
                    frameLayout.setVisibility(View.VISIBLE);
                    searchRecyclerview.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentSearchEmpty()).commit();
                }
            }else if (isUp==2){
                if (searchAdapter.getData().size() >= searchBean.getContent().getTotal()) {
                    ToastUtils.showToast("没有更多数据");
                }else {
                    //切换控件
                    dataBeans.addAll(searchBean.getContent().getData());
                    if (searchBean.getContent().getData().size()>0){
                        frameLayout.setVisibility(View.GONE);
                        searchRecyclerview.setVisibility(View.VISIBLE);
                        searchAdapter.notifyDataSetChanged();
                    }else {
                        //切换控件
                        frameLayout.setVisibility(View.VISIBLE);
                        searchRecyclerview.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentSearchEmpty()).commit();
                    }
                }
            }
        } else if (searchBean.getCode() == 401||searchBean.getCode() == 801){
            initDialogToLogin();
        }
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
                                    Intent intent = new Intent(SearchActivity.this, LoginActivity.class);
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

    private void initSearchData() {
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        searchRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        searchRecyclerview.setItemAnimator(new DefaultItemAnimator());
        searchSpringview.setType(SpringView.Type.FOLLOW);
        searchAdapter = new SearchAdapter(R.layout.search_item, dataBeans);
        searchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (NoFastClickUtils.isFastClick()) {
                } else {
                    if (dataBeans.get(position).getFileType().equals("VIDEO")) {
                        if (dataBeans.get(position).getSellType().equals("TEACHERCOURSE")){
                            Intent intent = new Intent(SearchActivity.this, YingXinagVideoDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                            intent.putExtras(bundle);
                            //这里一定要获取到所在Activity再startActivity()；
                            SearchActivity.this.startActivity(intent);
                        }else if (dataBeans.get(position).getSellType().equals("YINGXIANGSUCAI")){
                            Intent intent = new Intent(SearchActivity.this, TeacherTrainingDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                            intent.putExtras(bundle);
                            //这里一定要获取到所在Activity再startActivity()；
                            SearchActivity.this.startActivity(intent);
                        }
                    } else if (dataBeans.get(position).getFileType().equals("LIVE")) {
                        Intent intent = new Intent(SearchActivity.this, LiveRoomDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        SearchActivity.this.startActivity(intent);
                    } else {
                        Intent intent = new Intent(SearchActivity.this, PictrueDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        SearchActivity.this.startActivity(intent);
                    }
                }
            }
        });
        searchRecyclerview.setAdapter(searchAdapter);
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
                        searchPresenter.getSearchData(SearchActivity.this, "Bearer " + token, "YINGXIANGSUCAI,BKEXUEGUAN,TEACHERCOURSE", currentPage+"", "10", coursename);
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
                        searchPresenter.getSearchData(SearchActivity.this, "Bearer " + token, "YINGXIANGSUCAI,BKEXUEGUAN,TEACHERCOURSE", currentPage+"", "10", coursename);
                        searchSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        searchSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        searchSpringview.setFooter(new AliFooter(this, true));
    }

    @Override
    public void onSearchError(String msg) {
        //切换控件
        frameLayout.setVisibility(View.VISIBLE);
        searchRecyclerview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new FramgmentError()).commit();
    }

    @Override
    protected SearchPresenterImpl initInject() {
        activityComponent.inject(this);
        return searchPresenter;
    }
}
