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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonFilterBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonTeacherBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserInfoBean;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenter;
import com.kemizhibo.kemizhibo.other.common.presenter.CommonPresenterImp;
import com.kemizhibo.kemizhibo.other.common.view.CommonView;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.load.LoadFailUtil;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.PreparingLessonsFragment;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.TeachingLessonsFragment;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
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
    private int currentIndex = 0;//记录viewPager的当前索引
    private List<Fragment> fragments;//加载的faragments
    private List<String> titles;//对应的title
    private CommonPresenter commonPresenter;//
    private int roleId;//用来判断是管理者还是一般用户
    private List<String> statusList;//状态筛选的集合
    private PopupWindow statusFilterPop;//
    private TimePickerView pvTime;//日期筛选控件对象
    private Calendar selectCalendar;
    private int startYear = 2017;//日期开始的年
    private int endYear;//结束的年，为当前年
    private Calendar startDate;//日期筛选的开始日期
    private Calendar endDate;//日期筛选的截止日期
    private ImageView rightImageView;//筛选按钮
    private OptionsPickerView pvOptions;//条件筛选（管理者筛选）控件对象
    private List<String> teacherItems;//老师的集合
    private List<String> yearItems;//年的集合
    private List<String> monthItems;//月的集合
    private int teacherSelectIndex = 0;//老师选中的索引
    private int yearSelectIndex;//年选中的索引
    private int monthSelectIndex;//月选中的索引
    private boolean lastIsCurrentYear = true;//上一次滑动选中是否为当年
    private List<CommonTeacherBean.ContentBean> content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_bei_shou_ke_ji_lu;
    }

    @Override
    protected void initData() {
        commonPresenter = new CommonPresenterImp(this);
        //commonPresenter.getUserInfo();
        roleId = PreferencesUtils.getIntValue(Constants.ROLE_ID, this);
        bindTitleBar();
        initializeData();
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.setRightImageResouse(R.mipmap.person_filter_icon);
        publicTitleBarRoot.changeTitleBar(roleId == Constants.MANAGER_ROLE_ID ? "教学管理" : "备授课记录");
        publicTitleBarRoot.buildFinish();
        rightImageView = publicTitleBarRoot.getRightImageView();
    }

    private void initializeData() {
        if(roleId == Constants.MANAGER_ROLE_ID){
            publicTitleBarRoot.setRightLinearLayoutListener(new TapBarLayout.RightOnClickListener() {
                @Override
                public void onClick() {
                    if(teacherItems.size() <= 0){
                        commonPresenter.getTeacher();
                    }else{
                        showManagerFilterPop2();
                    }
                }
            });
        }
        if(roleId == Constants.CHILD_ROLE_ID){
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
        selectCalendar = endDate;
        fragments = new ArrayList();
        PreparingLessonsFragment preparingLessonsFragment = new PreparingLessonsFragment();
        TeachingLessonsFragment teachingLessonsFragment = new TeachingLessonsFragment();
        fragments.add(preparingLessonsFragment);
        fragments.add(teachingLessonsFragment);
        titles = new ArrayList<>();
        if(Constants.MANAGER_ROLE_ID == roleId){
            titles.add("备课情况");
            titles.add("授课情况");
        }else if(Constants.CHILD_ROLE_ID == roleId){
            titles.add("备课记录");
            titles.add("授课记录");
        }
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        viewPager.removeAllViews();
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
        teacherItems = new ArrayList();
        yearItems = new ArrayList<>();
        for (int i = startYear; i <= endYear; i++) {
            yearItems.add(String.valueOf(i));
        }
        yearSelectIndex = yearItems.size();
        monthItems = new ArrayList<>();
        for (int i = 1; i <= endDate.get(Calendar.MONTH ) + 1; i++) {
            monthItems.add(String.valueOf(i));
        }
        monthSelectIndex = monthItems.size();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showDateFilterPop() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String time = dateFormat.format(date);
                Log.d("PersonCenterBeiShouKeJi", time);
                if(currentIndex == 1){
                    //selectCalendar.setTime(date);
                    try{
                        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                        TeachingLessonsFragment fragment = (TeachingLessonsFragment) fragmentList.get(1);
                        fragment.onDateFilterSelect(time);
                    }catch (Exception e){

                    }
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
                if(currentIndex == 0){
                    try{
                        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                        PreparingLessonsFragment fragment = (PreparingLessonsFragment) fragmentList.get(0);
                        fragment.onStatusFilterSelect(position);
                    }catch (Exception e){

                    }
                }
            }
        });
        statusFilterPop = new PopupWindow(this);
        statusFilterPop.setWidth(200);
        statusFilterPop.setContentView(view);
        statusFilterPop.setFocusable(true);
        statusFilterPop.setOutsideTouchable(true);
        statusFilterPop.setBackgroundDrawable(new BitmapDrawable());
        statusFilterPop.showAsDropDown(rightImageView, -100, 0, Gravity.BOTTOM);
    }

    /*private void showManagerFilterPop() {
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
        //popupWindow.showAtLocation(getCurrentFocus(), Gravity.BOTTOM, 0 , 0);
    }*/

    private void showManagerFilterPop2(){
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
                /*teacherSelectIndex = options1;
                yearSelectIndex = option2;
                monthSelectIndex = options3;*/
                int userId = content.get(options1).getUserId();
                String year = yearItems.get(option2);
                String monthStr = monthItems.get(options3);
                try{
                    int month = Integer.parseInt(monthStr);
                    if(month < 10){
                        monthStr = "0" + monthStr;
                    }
                }catch (Exception e){

                }
                String time = year + "-" + monthStr;
                try{
                    List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
                    if(currentIndex == 0){
                        PreparingLessonsFragment fragment= (PreparingLessonsFragment) fragmentList.get(0);
                        fragment.onManagerFilterSelect(userId, time);
                    }else{
                        TeachingLessonsFragment fragment = (TeachingLessonsFragment) fragmentList.get(1);
                        fragment.onManagerFilterSelect(userId, time);
                    }
                }catch (Exception e){

                }
                Log.d("PersonCenterBeiShouKeJi", "userId:" + userId);
                Log.d("PersonCenterBeiShouKeJi", time);
            }
        }) .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
            @Override
            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                if(options2 == yearItems.size() -1 && !lastIsCurrentYear){
                    changeMonthItems(true);
                    pvOptions.setNPicker(teacherItems, yearItems, monthItems);
                    pvOptions.setSelectOptions(options1, options2, options3);
                    lastIsCurrentYear = true;
                }else if(options2 < yearItems.size() -1 && lastIsCurrentYear){
                    teacherSelectIndex = options1;
                    yearSelectIndex = options2;
                    monthSelectIndex = options3;
                    changeMonthItems(false);
                    pvOptions.setNPicker(teacherItems, yearItems, monthItems);
                    pvOptions.setSelectOptions(options1, options2, options3);
                    lastIsCurrentYear = false;
                }
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setOutSideCancelable(true)
                .setSubmitColor(getResources().getColor(R.color.filter_text_select))//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.line_f1f1f1))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setLabels("", "", "")//设置选择的三级单位
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(teacherSelectIndex, yearItems.size(), monthItems.size())  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();

        pvOptions.setNPicker(teacherItems, yearItems, monthItems);//添加数据源
        Dialog mDialog = pvOptions.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvOptions.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvOptions.show();
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
        //roleId = Constants.CHILD_ROLE_ID;
        PreferencesUtils.saveIntValue(Constants.ROLE_ID, roleId, this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initializeData();
            }
        });
    }

    @Override
    public void getCommonUserInfoError(final int errorCode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(Constants.OTHER_ERROR_CODE == errorCode){
                    LoadFailUtil.initDialogToLogin(PersonCenterBeiShouKeJiLuActivity.this);
                }
            }
        });
    }

    @Override
    public void getCommonTeacherSuccess(CommonTeacherBean bean) {
        content = bean.getContent();
        for (int i = 0; i < content.size(); i++) {
            teacherItems.add(content.get(i).getUserName());
            Log.d("PersonCenterBeiShouKeJi", content.get(i).getUserName());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showManagerFilterPop2();
            }
        });
    }

    @Override
    public void getCommonTeacherError(final int errorCode) {
        Log.d("PersonCenterBeiShouKeJi", "eoor" + errorCode);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(Constants.OTHER_ERROR_CODE == errorCode){
                    LoadFailUtil.initDialogToLogin(PersonCenterBeiShouKeJiLuActivity.this);
                }
            }
        });
    }

    @Override
    public void getCommonUserTeachPlanSuccess(CommonUserTeachPlanBean bean) {

    }

    @Override
    public void getCommonUserTeachPlanError(int errorCode) {

    }

    public void changeMonthItems(boolean isCurrentYear){
        monthItems.clear();
        if(isCurrentYear){
            for (int i = 1; i <= endDate.get(Calendar.MONTH ) + 1; i++) {
                monthItems.add(String.valueOf(i));
            }
        }else{
            for (int i = 1; i <= 12; i++) {
                monthItems.add(String.valueOf(i));
            }
        }
    }

}
