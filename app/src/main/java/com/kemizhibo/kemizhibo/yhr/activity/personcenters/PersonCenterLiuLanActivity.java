package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter.LiuLanAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.LiuLanPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.LiuLanView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;


public class PersonCenterLiuLanActivity extends BaseMvpActivity<LiuLanPresenterImpl> implements LiuLanView {

    @Inject
    public LiuLanPresenterImpl liuLanPresenter;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.liulan_recyclerview)
    RecyclerView liulanRecyclerview;
    @BindView(R.id.liulan_springview)
    SpringView liulanSpringview;
    private SharedPreferences sp;
    private String token;

    LiuLanAdapter liuLanAdapter;
    private List<LiuLanBean.ContentBean.DataBean> dataBeans;
    //上或者下拉的状态判断
    int isUp = 1;
    private int page;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_liu_lan;
    }

    @Override
    protected void initData() {
        bindTitleBar();
    }

    @Override
    protected void getData() {
        super.getData();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        liuLanPresenter.getLiuLanData(this, "Bearer " + token, "1", "10");
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("浏览记录");
        publicTitleBarRoot.buildFinish();
    }

    @Override
    public void onLiuLanSuccess(LiuLanBean liuLanBean) {
        dataBeans = new ArrayList<>();
       /* for (int i = 0;liuLanBean.getContent().getData().size()>i;i++){
            dataBeans.add(liuLanBean.getContent().getData().get(i).getCourse());
        }*/
        dataBeans.addAll(liuLanBean.getContent().getData());
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        liulanRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        liulanRecyclerview.setItemAnimator(new DefaultItemAnimator());
        liulanSpringview.setType(SpringView.Type.FOLLOW);
        liuLanAdapter = new LiuLanAdapter(R.layout.item_liulan_layout, dataBeans);
        /*liuLanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
        });*/
        liulanRecyclerview.setAdapter(liuLanAdapter);
        liulanSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        page = 1;
                        liuLanPresenter.getLiuLanData(PersonCenterLiuLanActivity.this, "Bearer " + token, "1", "10");
                        liulanSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 2;
                        page++;
                        liuLanPresenter.getLiuLanData(PersonCenterLiuLanActivity.this, "Bearer " + token, "1", "10");
                        liulanSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        liulanSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        liulanSpringview.setFooter(new AliFooter(this, true));
    }

    @Override
    public void onLiuLanError(String msg) {

    }

    @Override
    protected LiuLanPresenterImpl initInject() {
        activityComponent.inject(this);
        return liuLanPresenter;
    }

}
