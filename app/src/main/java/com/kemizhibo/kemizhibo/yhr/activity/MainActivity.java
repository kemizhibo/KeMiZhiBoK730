package com.kemizhibo.kemizhibo.yhr.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    //获取设备名称
    private String deviceName = Build.MODEL;
    //获取设备序列号
    //private String serialNumber = getSerialNumber();
    private static final String TAG = "SplashActivity";
    public static final int SHOW_UPDATE_DIALOG = 0;
    public static final int SHOW_ERROR = 1;
    public final static int CONNECT_TIMEOUT = 5000;
    public final static int READ_TIMEOUT = 5000;
    public final static int WRITE_TIMEOUT = 5000;

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
    private SharedPreferences sp;
    private int versionNo;

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
        //initDatas();
    }
/*
    //初始化数据
    private void initDatas() {
        boolean autoUpdate = PreferenceUtils.getBoolean(this, Constants.AUTO_UPDATE, true);
        if (autoUpdate) {
            //版本更新,去服务器获取最新的版本,和本地版本比较
            new Thread(new CheckServerVersion()).start();
        } else {
            //直接跳转到登录界面
            load2Login();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                */
/** 弹出提示更新的dialog   *//*

                case SHOW_UPDATE_DIALOG:
                    showUpdateDialog();
                    break;

                */
/** 提示错误    *//*

                case SHOW_ERROR:
                    load2Login();
                    break;

                default:
                    break;
            }
        }
    };

    */
/**
     * 检查服务器端版本号
     *//*

    private class CheckServerVersion implements Runnable {
        @Override
        public void run() {


        }
    }

    */
/**
     * 弹出提示更新的dialog
     *//*

    private void showUpdateDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle("版本更新提示");
        dialog.setMessage("檢查到有最新版本,是否更新?");
        dialog.setNegativeButton("暫不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转到登录界面
                load2Login();
            }
        });
        dialog.setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //从服务器端下载最新apk
                downloadApk();
            }
        });
        dialog.show();
    }

    */
/**
     * 从服务器端下载最新apk
     *//*

    private void downloadApk() {
        //显示下载进度
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();

        //访问网络下载apk
        new Thread(new DownloadApk(dialog)).start();
    }

    */
/**
     * 访问网络下载apk
     *//*

    private class DownloadApk implements Runnable {
        private ProgressDialog dialog;
        InputStream is;
        FileOutputStream fos;

        public DownloadApk(ProgressDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            String url = versionDataBean.VERSION_URL;
            Request request = new Request.Builder().get().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    Log.d(TAG, "开始下载apk");
                    //获取内容总长度
                    long contentLength = response.body().contentLength();
                    //设置最大值
                    dialog.setMax((int) contentLength);
                    //保存到sd卡
                    File apkFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".apk");
                    fos = new FileOutputStream(apkFile);
                    //获得输入流
                    is = response.body().byteStream();
                    //定义缓冲区大小
                    byte[] bys = new byte[1024];
                    int progress = 0;
                    int len = -1;
                    while ((len = is.read(bys)) != -1) {
                        try {
                            Thread.sleep(1);
                            fos.write(bys, 0, len);
                            fos.flush();
                            progress += len;
                            //设置进度
                            dialog.setProgress(progress);
                        } catch (InterruptedException e) {
                            Message msg = Message.obtain();
                            msg.what = SHOW_ERROR;
                            msg.obj = "ERROR:10002";
                            handler.sendMessage(msg);
                            load2Login();
                        }
                    }
                    //下载完成,提示用户安装
                    installApk(apkFile);
                }
            } catch (IOException e) {
                Message msg = Message.obtain();
                msg.what = SHOW_ERROR;
                msg.obj = "ERROR:10003";
                handler.sendMessage(msg);
                load2Login();
            } finally {
                //关闭io流
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    is = null;
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fos = null;
                }
            }
            dialog.dismiss();
        }
    }

    */
/**
     * 下载完成,提示用户安装
     *//*

    private void installApk(File file) {
        //调用系统安装程序
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivityForResult(intent, REQUEST_INSTALL_CODE);
    }

    */
/**
     * 跳转到登录界面
     *//*

    private void load2Login() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Intent toLogin = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    */
/**
     * 跳转到主界面
     *//*

    private void load2MainActivity() {
        Intent toMainActivity = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(toMainActivity);
        finish();
    }

    */
/**
     * 获取设备序列号
     *//*

    private String getSerialNumber() {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }

    */
/**
     * 封装版本升级数据
     *//*

    private class VersionDatas {
        String COMMAND = "GET_APP_VERSION";
        String DEVICE_NAME = deviceName;
        String DEVICE_SN = serialNumber;
    }
*/

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MyApplication.YINGXIANG_TO_PICK_req&&resultCode==MyApplication.YINGXIANG_TO_PICK_res){
            finish();
        }
    }
}
