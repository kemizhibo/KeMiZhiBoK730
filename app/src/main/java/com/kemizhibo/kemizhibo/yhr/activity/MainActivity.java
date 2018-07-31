package com.kemizhibo.kemizhibo.yhr.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.base.BaseFragment;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.LoginBean;
import com.kemizhibo.kemizhibo.yhr.bean.TokenBean;
import com.kemizhibo.kemizhibo.yhr.fragment.DemoFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.ForTeachingFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.HomePageFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.PersonCenterFragment;
import com.kemizhibo.kemizhibo.yhr.fragment.ResourceLibraryFragment;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.GetLoginPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.view.LoginView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

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
    //获取token
    private TokenBean tokenContentBean;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initFragment();
        initListener();
    }

    @Override
    protected void getData() {
        super.getData();
        //进来页面就去请求token
        //getTokenPresenter.getTokenData(this, "yanhaoran001","yhr425581972");
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

   /* @OnClick(R.id.main_bt_lecture)
    public void onViewClicked() {
        goToActivity(LectureActivity.class,null);
//        goToActivity(SearchActivity.class,null);
    }*/

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
                shotToast("再按一次退出" + getString(R.string.app_name));
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

    /*@Override
    public void onGetTokenSuccess(TokenBean tokenBean) {
        tokenContentBean = tokenBean;
        //如果登录成功调转到首页
        //如果返回0，代表正确的返回token
        if (tokenBean.getCode() == 0) {
            //获取成功的话保存在本地并且添加到请求头中
            //token保存到本地
           *//* SPUtil spUtil = SPUtil.getInstance(this);
            String token = spUtil.read("token", tokenBean.getContent());*//*
            sp = getSharedPreferences("loginToken", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("token",tokenBean.getContent());
            editor.commit();
            LogUtils.e("11111111111111111111111111111",tokenBean.getContent());
            //否则获取token失败
        } else {
            //失效的话返回一个401，并且清空缓存
            if(sp!=null){
                sp.edit().clear().commit();
            }
        }
    }*/


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
