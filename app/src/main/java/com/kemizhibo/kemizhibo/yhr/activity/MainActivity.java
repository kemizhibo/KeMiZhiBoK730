package com.kemizhibo.kemizhibo.yhr.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.PersonCenterSheZhiActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.homepagerbean.VersionInformationBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.fragment.ForTeachingFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.HomePageFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.PersonCenterFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.ResourceLibraryFragment;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.GetLoginPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.UpdateKey;

/**
 * Author: yhr
 * Date: 2018/4/27
 * Describe:主页
 */
public class MainActivity extends BaseMvpActivity<GetLoginPresenterImpl> implements LoginView {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.main_rg_bottom)
    RadioGroup mainRgBottom;
    @Inject
    public GetLoginPresenterImpl getTokenPresenter;

    /**
     * 之前显示的Fragment
     */
    public BaseFragment tempFragment;
    /**
     * 被选中页面的位置
     */
    private int position;
    /**
     * 装各个Fragment的集合
     */
    ArrayList<BaseFragment> fragments;


    private long TOUCH_TIME = 0;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;

    public void gotoDownloadFragment(){
        mainRgBottom.check(R.id.main_rb_forteaching);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initFragment();
        initListener();
        UpdateKey.API_TOKEN = "136e76f4105816219f9c9ca06684ab35";
        UpdateKey.APP_ID = "com.kemizhibo.kemizhibo";
        //下载方式:
        UpdateKey.DialogOrNotification=UpdateKey.WITH_DIALOG;//通过Dialog来进行下载
        //UpdateKey.DialogOrNotification=UpdateKey.WITH_NOTIFITION;通过通知栏来进行下载(默认)
        UpdateFunGO.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }

    @Override
    protected void getData() {
        super.getData();
        getTokenPresenter.getVersionInformationData(this);
    }

    private void initListener() {
        mainRgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_homepage://首页
                        position = 0;
                        break;
                    case R.id.main_rb_forteaching://备授课
                        position = 1;
                        break;
                    case R.id.main_rb_resourcelibrary://资源库
                        position = 2;
                        break;
                    case R.id.main_rb_personalcenter://个人中心
                        position = 3;
                        break;
                }
                BaseFragment currentFragment = (BaseFragment) getFragment(position);
                switchFragment(currentFragment);
                assert currentFragment != null;
                currentFragment.show();
            }
        });
        mainRgBottom.check(R.id.main_rb_homepage);
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();

        fragments.add(new HomePageFragment());//首页
        fragments.add(new ForTeachingFragment());//备授课
        fragments.add(new ResourceLibraryFragment());//资源库
        fragments.add(new PersonCenterFragment());//个人中心
        //fragments.add(new DemoFragment());//个人中心
        defaultFragment(fragments.get(position));
    }

    private void defaultFragment(Fragment fragment) {
        //1.得到FragmentManger
        FragmentManager rm = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = rm.beginTransaction();
        //3.添加
        ft.add(R.id.frameLayout, fragment);
        //4.提交
        ft.commit();
        //5.缓存记录
        tempFragment = (BaseFragment) fragment;
    }

    /**
     * 根据位置得到Fragment
     */
    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    /**
     * 传入当前要显示的Fragment
     */
    public void switchFragment(BaseFragment currentFragment) {
        if (tempFragment != currentFragment) {
            if (currentFragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (!currentFragment.isAdded()) {
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.add(R.id.frameLayout, currentFragment);
                } else {
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.show(currentFragment);
                }
                ft.commit();//统一提交
            }
            tempFragment = currentFragment;
        }
    }

    /**
     * 双击退出应用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((event.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                ToastUtils.showToast("再按一次退出" + getString(R.string.app_name));
            }
        }
        return true;
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {

    }

    @Override
    public void onLoginError(String msg) {

    }

    @Override
    public void onUserSuccess(GetUserBean getUserBean) {


    }

    @Override
    public void onUserError(String msg) {

    }
    //版本跟新
    @Override
    public void onVersionInformationSuccess(VersionInformationBean versionInformationBean) {
        /*// 解析json数据
        if(versionInformationBean!=null){
            versionNo = Integer.parseInt(versionInformationBean.getContent().getVersionNo());
            //获取到服务器端版本号,对比本地的版本号
            int localVersionCode = PackageUtils.getVersionCode(SplashActivity.this);
            if (serverVersionCode > localVersionCode) {
                //服务器端版本号大于本地版本号,弹出dialog提示更新
                Message showUpdateDialog = Message.obtain();
                showUpdateDialog.what = SHOW_UPDATE_DIALOG;
                handler.sendMessage(showUpdateDialog);
            }
        }*/
    }

    @Override
    public void onVersionInformationError(String msg) {

    }

    @Override
    protected GetLoginPresenterImpl initInject() {
        activityComponent.inject(this);
        return getTokenPresenter;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MyApplication.YINGXIANG_TO_PICK_req&&resultCode==MyApplication.YINGXIANG_TO_PICK_res){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }*/

    public void exit() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
