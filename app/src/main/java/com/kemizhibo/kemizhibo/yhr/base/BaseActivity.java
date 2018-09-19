package com.kemizhibo.kemizhibo.yhr.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.kemizhibo.kemizhibo.yhr.utils.ToastUtils.showToast;


/**
 *Created by yhr on 2018/4/27.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements BaseView{
    //返回推出应用集合
   /* private List<Activity> activityList = new LinkedList<Activity>();
    Activity activity = new Activity();*/

    protected ViewGroup title_bar = null ;
    Unbinder unbinder;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //返回推出应用
       /* if (!activityList.contains(this)){
            activityList.add(this);
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            Window win = getWindow();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
                // 状态栏字体设置为深色，SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 为SDK23增加
                win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                // 部分机型的statusbar会有半透明的黑色背景
                win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                win.setStatusBarColor(Color.TRANSPARENT);// SDK21
            }
        }
        setContentView(getLayoutId());
        ButterKnife.bind(this) ;
        initData();
        MyApplication.verifyStoragePermissions(this);
    }
    //fanhui 直接退出应用，关闭的是所有的activity
    /*public void finishAllActivity(Activity mActivity) {
        LogUtils.i("走了吗4",activityList.size()+"");
        for (Activity activity : activityList) {
            if (activity != null && activity != mActivity) {
                activity.finish();
                LogUtils.i("走了吗5",activityList.size()+"");
            }
        }
        LogUtils.i("走了吗6",activityList.size()+"");
    }*/
    //关闭指定activity
   /* public void finishActivity() {
        LogUtils.i("走了吗1",activityList.size()+"");
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
                LogUtils.i("走了吗2",activityList.size()+"");
            }
        }
        LogUtils.i("走了吗",activityList.size()+"");
    }*/


    /**
     * 布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected  void getData(){}

    @Override
    protected void onDestroy() {
        /*if (activityList.contains(this)){
            activityList.remove(this);
        }*/
        if (this.unbinder != null) {
            this.unbinder.unbind();
        }
        super.onDestroy();
    }
    //启动新的activity
    public void goToActivity(Class activity, Bundle bundle){
        Intent intent = new Intent(this,activity);
        //携带数据
        if(bundle != null && bundle.size() != 0){
            intent.putExtra("data",bundle);
        }
        startActivity(intent);
    }
    /*@Override
    public void shotToast(String msg) {
        showToast(msg);
    }*/
    /**
     * 设置沉浸式状态栏
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            title_bar = (ViewGroup) findViewById(R.id.statusBar_TapBarLayout);
            final int statusHeight = getStatusBarHeight();
            title_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = title_bar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) title_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    title_bar.setLayoutParams(params);
                }
            });
        }
    }
    /**
     * 获取状态栏的高度
     * @return
     */
    public int getStatusBarHeight(){
        try
        {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
