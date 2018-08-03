package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenter;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.PreparingLessonsFragment;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.TeachingLessonsFragment;
import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PersonCenterBeiShouKeJiLuActivity extends BaseActivity implements CommonView{

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.tab_layout)
    XTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private int currentIndex = 0;
    private List<Fragment> fragments;
    private List<String> titles;
    private CommonPresenter userInfoPresenter;
    private int roleId;
    private List<String> statusList;
    private PreparingLessonsFragment preparingLessonsFragment;
    private TeachingLessonsFragment teachingLessonsFragment;
    private PopupWindow statusFilterPop;
    private TimePickerView pvTime;
    private int startYear = 2017;
    private int endYear;
    private Calendar startDate;
    private Calendar endDate;
    private ImageView rightImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_bei_shou_ke_ji_lu;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        userInfoPresenter = new CommonPresenterImp(this);
        userInfoPresenter.getUserInfo();
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.setRightImageResouse(R.mipmap.person_filter_icon);
        publicTitleBarRoot.changeTitleBar("备授课记录");
        publicTitleBarRoot.buildFinish();
        rightImageView = publicTitleBarRoot.getRightImageView();
    }

    private void initializeData() {
        if(roleId == 8){
            publicTitleBarRoot.setRightLinearLayoutListener(new TapBarLayout.RightOnClickListener() {
                @Override
                public void onClick() {
                    showManagerFilterPop();
                }
            });
        }
        if(roleId == 9){
            publicTitleBarRoot.setRightLinearLayoutListener(new TapBarLayout.RightOnClickListener() {
                @Override
                public void onClick() {
                    if(currentIndex == 0){
                        showStatusFilterPop();
                    }else{
                        showDateFilterPop();
                    }
                }
            });
        }
        startDate = Calendar.getInstance();
        startDate.set(Calendar.YEAR, startYear);
        startDate.set(Calendar.MONTH, 0);
        endDate = Calendar.getInstance();
        endYear = endDate.get(Calendar.YEAR);
        fragments = new ArrayList();
        preparingLessonsFragment = new PreparingLessonsFragment();
        teachingLessonsFragment = new TeachingLessonsFragment();
        fragments.add(preparingLessonsFragment);
        fragments.add(teachingLessonsFragment);
        titles = new ArrayList<>();
        if(8 == roleId){
            titles.add("备课情况");
            titles.add("授课情况");
        }else if(9 == roleId){
            titles.add("备课记录");
            titles.add("授课记录");
        }
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showDateFilterPop() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String time = dateFormat.format(date);
                Log.d("PersonCenterBeiShouKeJi", time);
                if(currentIndex == 1){
                    teachingLessonsFragment.onDateFilterClick(time);
                }
            }
        })
                .setType(new boolean[]{true, true, false, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setSubmitColor(getResources().getColor(R.color.filter_text_select))//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.line_f1f1f1))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(endDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("","","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvTime.show();
    }

    private void showStatusFilterPop() {
        statusList = new ArrayList();
        statusList.add("全部");
        statusList.add("未授课");
        statusList.add("已授课");
        View view = LayoutInflater.from(this).inflate(R.layout.pop_status_filter, null, false);
        ListView listView = view.findViewById(R.id.status_list);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return statusList.size();
            }

            @Override
            public Object getItem(int position) {
                return statusList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(PersonCenterBeiShouKeJiLuActivity.this, R.layout.item_pop_status_list, null);
                TextView statusText = convertView.findViewById(R.id.status);
                statusText.setText(statusList.get(position));
                return convertView;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(null != statusFilterPop && statusFilterPop.isShowing()){
                    statusFilterPop.dismiss();
                }
                preparingLessonsFragment.onStatusFilterClick(position);
            }
        });
        statusFilterPop = new PopupWindow(this);
        statusFilterPop.setWidth(240);
        statusFilterPop.setContentView(view);
        statusFilterPop.setFocusable(true);
        statusFilterPop.setOutsideTouchable(true);
        statusFilterPop.setBackgroundDrawable(new BitmapDrawable());
        statusFilterPop.showAsDropDown(rightImageView, 0, 0, Gravity.BOTTOM);
    }

    private void showManagerFilterPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_manager_date_filter, null, false);
        TextView cancel = view.findViewById(R.id.cancel_text);
        TextView sure = view.findViewById(R.id.sure_text);
        WheelView account = view.findViewById(R.id.account);

        WheelView year = view.findViewById(R.id.year);

        WheelView month = view.findViewById(R.id.month);

        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(getCurrentFocus(), Gravity.BOTTOM, 0 , 0);
    }

    @Override
    public Context getCommonCustomContext() {
        return this;
    }

    @Override
    public Map getCommonRequestParams() {
        return null;
    }

    @Override
    public void getCommonFilterSuccess(CommonFilterBean bean) {

    }

    @Override
    public void getCommonFilterError(int errorCode) {

    }

    @Override
    public void getCommonUserInfoSuccess(CommonUserInfoBean bean) {
        roleId = bean.getContent().getRoleId();
        roleId = 9;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initializeData();
            }
        });
    }

    @Override
    public void getCommonUserInfoError(int errorCode) {

    }
}
