package com.kemizhibo.kemizhibo.other.preparing_package_detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.load.LoadFailUtil;
import com.kemizhibo.kemizhibo.other.load.LoadingErrorFragment;
import com.kemizhibo.kemizhibo.other.load.LoadingFragment;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter.PreparingPackageDetailPresenter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.presenter.PreparingPackageDetailPresenterImp;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.MyListView;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailNewAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailOneAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailOtherAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingDetailPlanAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.PreparingPackageDetailView;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.LoadingPager;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

@SuppressLint("RestrictedApi")
public class PreparingPackageDetailActivity extends BaseActivity implements PreparingPackageDetailView {
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.list_view_one)
    MyListView listViewone;
    @BindView(R.id.list_view_su)
    MyListView listViewsu;
    @BindView(R.id.list_view_shou)
    ListView listViewshou;
    @BindView(R.id.list_view_qi)
    ListView listViewqi;
    @BindView(R.id.one_none)
    TextView one_none;
    @BindView(R.id.module_none)
    TextView module_none;
    @BindView(R.id.plan_none)
    TextView plan_none;
    @BindView(R.id.other_none)
    TextView other_none;
    @BindView(R.id.detaile_scrollView)
    ScrollView mdetailescrollView;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.make_img)
    ImageView makeImg;
    private PreparingPackageDetailPresenter detailPresenter;
    private int courseId;
    Handler mHandler = new Handler();
    private LoadingFragment loadingFragment;
    private LoadingErrorFragment loadingErrorFragment;
    //private int moduleId;
    //private PreparingDetailAdapter preparingDetailAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preparing_package_detail;
    }

    @Override
    protected void initData() {
        loadingFragment = new LoadingFragment();
        loadingErrorFragment = new LoadingErrorFragment();
        loadingErrorFragment.setListener(new LoadingErrorFragment.OnErrorPageCickListener() {
            @Override
            public void onErrorPageClick() {
                if(null != detailPresenter){
                    detailPresenter.getPreparingPackageDetailData();
                }
            }
        });
        bindTitleBar();
        detailPresenter = new PreparingPackageDetailPresenterImp(this);
       // detailPresenter.getPreparingPackageDetailData();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        Intent intent = getIntent();
        courseId = intent.getIntExtra(Constants.COURSE_ID, 0);
        //moduleId = intent.getIntExtra(Constants.MODULE_ID, 0);
        //courseId = 2832;
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

    @Override
    public String getCourseId() {
        return String.valueOf(courseId);
    }

    @Override
    public Context getCustomContext() {
        return this;
    }

    @OnClick(R.id.make_img)
    public void make(View view) {
        Intent intent = new Intent(this, CommonWebActivity.class);
        intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
        intent.putExtra(Constants.COURSE_ID, courseId);
        //intent.putExtra(Constants.MODULE_ID, moduleId);
        startActivity(intent);

        /*Intent intent = new Intent(this.getApplicationContext(), CommonWebActivity.class);
        intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.LIVE);
        intent.putExtra("courseId", "1005172");
        //这里一定要获取到所在Activity再startActivity()；
        this.startActivity(intent);*/
    }

    @Override
    public void getPreparingPackageDetailDataSuccess(final PreparingPackageDetailBean bean) {
       /* LogUtils.i("detail log", "name" + bean.getContent().getAppMaterial().getKemiPic().get(0).getDocName());
        Log.d("PreparingPackageDetailA", "bean.getCode():" + bean.getCode());*/
        if (bean.getCode() == 0) {
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    mdetailescrollView.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                    makeImg.setVisibility(View.VISIBLE);
                    PreparingDetailOneAdapter preparingDetailOneAdapter = new PreparingDetailOneAdapter(PreparingPackageDetailActivity.this, bean.getContent().getOneKey());
                    if(null != bean.getContent().getOneKey() && bean.getContent().getOneKey().size() > 0){
                        one_none.setVisibility(View.GONE);
                        listViewone.setAdapter(preparingDetailOneAdapter);
                    }
                     PreparingDetailAdapter preparingDetailAdapter = new PreparingDetailAdapter(PreparingPackageDetailActivity.this, bean.getContent().getAppMaterial(), getSupportFragmentManager());
                    if(null != bean.getContent().getAppMaterial()){
                        int moduleCount = 0;
                        try{
                            PreparingPackageDetailBean.ContentBean.AppMaterialBean appMaterial = bean.getContent().getAppMaterial();
                            for (int i = 0; i < 10; i++) {
                                switch (i){
                                    case 0:
                                        if(null != appMaterial.getKemiVideo()){
                                            moduleCount += appMaterial.getKemiVideo().size();
                                        }
                                        break;
                                    case 1:
                                        if(null != appMaterial.getKemiPic()){
                                            moduleCount += appMaterial.getKemiPic().size();
                                        }
                                        break;
                                    case 2:
                                        if(null != appMaterial.getKemiPpt()){
                                            moduleCount += appMaterial.getKemiPpt().size();
                                        }
                                        break;
                                    case 3:
                                        if(null != appMaterial.getKemiWord()){
                                            moduleCount += appMaterial.getKemiWord().size();
                                        }
                                        break;
                                    case 4:
                                        if(null != appMaterial.getKemiExcel()){
                                            moduleCount += appMaterial.getKemiExcel().size();
                                        }
                                        break;
                                    case 5:
                                        if(null != appMaterial.getUserVideo()){
                                            moduleCount += appMaterial.getUserVideo().size();
                                        }
                                        break;
                                    case 6:
                                        if(null != appMaterial.getUserPic()){
                                            moduleCount += appMaterial.getUserPic().size();
                                        }
                                        break;
                                    case 7:
                                        if(null != appMaterial.getUserPpt()){
                                            moduleCount += appMaterial.getUserPpt().size();
                                        }
                                        break;
                                    case 8:
                                        if(null != appMaterial.getUserWord()){
                                            moduleCount += appMaterial.getUserWord().size();
                                        }
                                        break;
                                    case 9:
                                        if(null != appMaterial.getUserExcel()){
                                            moduleCount += appMaterial.getUserExcel().size();
                                        }
                                        break;
                                }
                            }
                        }catch (Exception e){

                        }
                        if(moduleCount > 0){
                            module_none.setVisibility(View.GONE);
                            listViewsu.setAdapter(preparingDetailAdapter);
                        }
                    }
                    PreparingDetailPlanAdapter preparingDetailPlanAdapter = new PreparingDetailPlanAdapter(PreparingPackageDetailActivity.this, bean.getContent().getPlan(), mHandler);
                    if(null != bean.getContent().getPlan() && bean.getContent().getPlan().size() >0){
                        plan_none.setVisibility(View.GONE);
                        listViewshou.setAdapter(preparingDetailPlanAdapter);
                    }
                    List<PreparingPackageDetailBean.ContentBean.OtherBean> other = bean.getContent().getOther();
                    if (other.size() > 0) {
                        other_none.setVisibility(View.GONE);
                        listViewqi.setAdapter(new PreparingDetailOtherAdapter(PreparingPackageDetailActivity.this, other));
                    }
                }
            });
        }
    }

    @Override
    public void error(String operate, final String errorCode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mdetailescrollView.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                makeImg.setVisibility(View.GONE);
                getSupportFragmentManager().openTransaction().replace(R.id.frame_layout, loadingErrorFragment).commit();
                if(String.valueOf(Constants.OTHER_ERROR_CODE ).equals(errorCode)){
                    LoadFailUtil.initDialogToLogin(PreparingPackageDetailActivity.this);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("--hhhhh--", "====hhhhhh");
        frameLayout.setVisibility(View.VISIBLE);
        mdetailescrollView.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
        if(null != detailPresenter)
            detailPresenter.getPreparingPackageDetailData();
    }

    public void onPlanDelComplete() {
        if(null != detailPresenter){
            frameLayout.setVisibility(View.VISIBLE);
            mdetailescrollView.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
            detailPresenter.getPreparingPackageDetailData();;
        }


    }

    public void onAddComplete() {
        if(null != detailPresenter){
            frameLayout.setVisibility(View.VISIBLE);
            mdetailescrollView.setVisibility(View.INVISIBLE);
            getSupportFragmentManager().openTransaction().replace(R.id.frame_layout, loadingFragment).commit();
            detailPresenter.getPreparingPackageDetailData();
        }
    }
}

