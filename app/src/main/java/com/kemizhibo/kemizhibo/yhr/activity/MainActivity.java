package com.kemizhibo.kemizhibo.yhr.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.kemizhibo.kemizhibo.BuildConfig;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.personcenters.ChangePwdActivity;
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
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;

import static com.kemizhibo.kemizhibo.yhr.utils.CleanMessageUtil.clearAllCache;

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
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){

            }
        }
    };

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
        //SysApplication.getInstance().addActivity(this);
        initFragment();
        initListener();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
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


    //获取版本信息
    @Override
    public void onVersionInformationSuccess(VersionInformationBean versionInformationBean) {
        //获取当前studio的版本
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        if (versionInformationBean.getCode()==0){
            int fileType = versionInformationBean.getContent().getFileType();
            final String filePath = versionInformationBean.getContent().getFilePath();
            if (versionCode>fileType){
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("确定要升级新版本吗？");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AllenVersionChecker
                                    .getInstance()
                                    .requestVersion()
                                    //.setRequestUrl("http://www.kemiketang.com/kemiapi/version/downloadAPK")
                                    .request(new RequestVersionListener() {
                                        @Nullable
                                        @Override
                                        public UIData onRequestVersionSuccess(String result) {
                                            //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                                            //如果是最新版本直接return null
                                            return UIData.create().setDownloadUrl(filePath);
                                        }

                                        @Override
                                        public void onRequestVersionFailure(String message) {

                                        }
                                    })
                                    .excuteMission(MainActivity.this);
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

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
