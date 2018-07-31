package com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter.SearchAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.SearchBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.resourcescenterimpl.SearchPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
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
    private List<SearchBean.ContentBean.DataBean> dataBeans;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }


    @Override
    protected void initData() {

        searchViewBox.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String courseName) {
                searchPresenter.getSearchData(SearchActivity.this,"YINGXIANGSUCAI,BKEXUEGUAN,TEACHERCOURSE", "1", "10", courseName);
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
    public void onSearchSuccess(SearchBean searchBean) {
        dataBeans = new ArrayList<>();
        dataBeans.addAll(searchBean.getContent().getData());
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
                    LogUtils.e("+++++++++++++", dataBeans.get(position).getFileType());
                    if (dataBeans.get(position).getFileType().equals("VIDEO")) {
                        Intent intent = new Intent(SearchActivity.this, YingXinagVideoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        SearchActivity.this.startActivity(intent);
                    } else if(dataBeans.get(position).getFileType().equals("LIVE")){
                        Intent intent = new Intent(SearchActivity.this, TeacherTrainingDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("courseId", String.valueOf(dataBeans.get(position).getCourseId()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        SearchActivity.this.startActivity(intent);
                    }else {
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
                        searchSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        searchSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        searchSpringview.setFooter(new AliFooter(this, true));
        // 2. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
    }

    @Override
    public void onSearchError(String msg) {

    }

    @Override
    protected SearchPresenterImpl initInject() {
        activityComponent.inject(this);
        return searchPresenter;
    }
}
