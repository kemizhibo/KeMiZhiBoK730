package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.PictrueDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.TeacherTrainingDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.resourcescenteraactivity.YingXinagVideoDetailsActivity;
import com.kemizhibo.kemizhibo.yhr.activity.web.MyLiveRoomWebActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter.CollectionBoxAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearCollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentError;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentShouCnagEmpty;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.CollectionBoxPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.DividerItemDecoration;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.CollectionBoxView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonCenterShouCangActivity extends BaseMvpActivity<CollectionBoxPresenterImpl> implements CollectionBoxView, View.OnClickListener, CollectionBoxAdapter.OnItemClickListener {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @Inject
    public CollectionBoxPresenterImpl collectionBoxPresenter;
    @BindView(R.id.shoucang_frame_layout)
    FrameLayout shoucangFrameLayout;
    private CollectionBoxAdapter collectionBoxAdapter = null;
    @BindView(R.id.collection_box_recyclerview)
    RecyclerView collectionBoxRecyclerview;
    @BindView(R.id.collection_box_springview)
    SpringView collectionBoxSpringview;
    @BindView(R.id.frame_layout_shoucang)
    FrameLayout frameLayout;
    @BindView(R.id.select_all_butn)
    Button selectAllButn;
    @BindView(R.id.delete_butn)
    Button deleteButn;
    private int page;
    //上或者下拉的状态判断
    int isUp = 1;
    //多选下标集合
    private List<String> arr = new ArrayList<>();
    private SharedPreferences sp;
    private String token;
    //多选删除
    private List<CollectionBoxBean.ContentBean.DataBean> mList = new ArrayList<>();
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    private int mEditMode = MYLIVE_MODE_CHECK;
    private boolean isSelectAll = false;
    private boolean editorStatus = false;
    private int index = 0;
    private AlertDialog builder;
    private String stringshoucang;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_shou_cang;
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
        collectionBoxPresenter.getCollectionBoxData(this, "Bearer " + token, "1", "10");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.setRightImageResouse(R.drawable.pan).setRightLinearLayoutListener(new TapBarLayout.RightOnClickListener() {
            @Override
            public void onClick() {
                updataEditMode();
            }
        });
        publicTitleBarRoot.changeTitleBar("收藏夹");
        publicTitleBarRoot.buildFinish();
    }

    //弹出多选框
    private void updataEditMode() {
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            publicTitleBarRoot.setRightImageResouse(R.drawable.qvxiao_2);
            frameLayout.setVisibility(View.VISIBLE);
            editorStatus = true;
        } else {
            publicTitleBarRoot.setRightImageResouse(R.drawable.pan);
            frameLayout.setVisibility(View.GONE);
            editorStatus = false;
            clearAll();
        }
        collectionBoxAdapter.setEditMode(mEditMode);
    }

    //清除所有
    private void clearAll() {
        isSelectAll = false;
        selectAllButn.setText("全选");
        setBtnBackground(0);
    }

    /**
     * 根据选择的数量是否为0来判断按钮的是否可点击.
     *
     * @param size
     */
    private void setBtnBackground(int size) {
        if (size != 0) {
            deleteButn.setBackgroundResource(R.drawable.button_shape);
            deleteButn.setEnabled(true);
            deleteButn.setTextColor(Color.WHITE);
        } else {
            deleteButn.setBackgroundResource(R.drawable.button_noclickable_shape);
            deleteButn.setEnabled(false);
            deleteButn.setTextColor(ContextCompat.getColor(this, R.color.text_67c58c));
        }
    }

    @Override
    public void onCollectionBoxSuccess(CollectionBoxBean collectionBoxBean) {
        if (collectionBoxBean.getCode() == 0) {
            if (isUp==1){
                mList.clear();
                //切换控件
                mList.addAll(collectionBoxBean.getContent().getData());
                if (mList.size()>0){
                    shoucangFrameLayout.setVisibility(View.GONE);
                    collectionBoxSpringview.setVisibility(View.VISIBLE);
                    initCollectionData();
                }
                else {
                    //切换控件
                    shoucangFrameLayout.setVisibility(View.VISIBLE);
                    collectionBoxSpringview.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.shoucang_frame_layout, new FramgmentShouCnagEmpty()).commit();
                }
            }else if (isUp==2){
                    mList.clear();
                    //切换控件
                    shoucangFrameLayout.setVisibility(View.GONE);
                    collectionBoxSpringview.setVisibility(View.VISIBLE);
                    mList.addAll(collectionBoxBean.getContent().getData());
                    if (mList.size() > 0) {
                        initCollectionData();
                    } else {
                        //切换控件
                        shoucangFrameLayout.setVisibility(View.VISIBLE);
                        collectionBoxSpringview.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.shoucang_frame_layout, new FramgmentShouCnagEmpty()).commit();
                    }
            }
        } else {
            //token失效，重新登录
            initDialogToLogin();
        }

    }

    private void initDialogToLogin() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        CustomDialog dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.alertdialog_login)
                        .addViewOnclick(R.id.yes_butn,new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (NoFastClickUtils.isFastClick()) {
                                }else {
                                    Intent intent = new Intent(PersonCenterShouCangActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        })
                        .build();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

    private void initCollectionData() {
        //改变数据源之后，多选框状态与数据源做比对
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < mList.size(); j++) {
                if (arr.get(i).equals(mList.get(j).getId())) {
                    mList.get(j).setSelect(true);
                    //更改数据源,
                    mList.set(j, mList.get(j));
                }
            }
        }
        collectionBoxAdapter = new CollectionBoxAdapter(this);
        initListener();
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        collectionBoxRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        collectionBoxRecyclerview.setItemAnimator(new DefaultItemAnimator());
        collectionBoxSpringview.setType(SpringView.Type.FOLLOW);
        DividerItemDecoration itemDecorationHeader = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        itemDecorationHeader.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_main_bg_height_1));
        collectionBoxRecyclerview.addItemDecoration(itemDecorationHeader);
        collectionBoxRecyclerview.setAdapter(collectionBoxAdapter);
        collectionBoxAdapter.notifyAdapter(mList, false);
        collectionBoxRecyclerview.setAdapter(collectionBoxAdapter);
        collectionBoxSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        page = 1;
                        collectionBoxPresenter.getCollectionBoxData(PersonCenterShouCangActivity.this, "Bearer " + token, "1", "12");
                        collectionBoxSpringview.onFinishFreshAndLoad();
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
                        collectionBoxPresenter.getCollectionBoxData(PersonCenterShouCangActivity.this, "Bearer " + token, "1", "12");
                        collectionBoxSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        collectionBoxSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        collectionBoxSpringview.setFooter(new AliFooter(this, true));
    }

    //点击事件
    private void initListener() {
        collectionBoxAdapter.setOnItemClickListener(this);
        deleteButn.setOnClickListener(this);
        selectAllButn.setOnClickListener(this);
    }

    @Override
    public void onCollectionBoxError(String msg) {
        shoucangFrameLayout.setVisibility(View.VISIBLE);
        collectionBoxSpringview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.shoucang_frame_layout,new FramgmentError()).commit();
    }

    //清空收藏夹
    @Override
    public void onClearCollectionBoxSuccess(ClearCollectionBoxBean clearCollectionBoxBean) {
        if (clearCollectionBoxBean.getCode() == 0) {
            for (int i = collectionBoxAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                CollectionBoxBean.ContentBean.DataBean dataBean = collectionBoxAdapter.getMyLiveList().get(i - 1);
                if (dataBean.isSelect()) {
                    collectionBoxAdapter.getMyLiveList().remove(dataBean);
                    index--;
                }
            }
            index = 0;
            setBtnBackground(index);
            if (collectionBoxAdapter.getMyLiveList().size() == 0) {
                frameLayout.setVisibility(View.GONE);
            }
            collectionBoxPresenter.getCollectionBoxData(this, "Bearer " + token, "1", "10");
            collectionBoxAdapter.notifyDataSetChanged();
            builder.dismiss();
        }else {
            initDialogToLogin();
        }
    }

    @Override
    public void onClearCollectionBoxError(String msg) {
        shoucangFrameLayout.setVisibility(View.VISIBLE);
        collectionBoxSpringview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.shoucang_frame_layout,new FramgmentError()).commit();
    }

    //取消一个或者多个回调
    @Override
    public void onGetCollectionSuccess(CollectionBean collectionBean) {
        LogUtils.i("123456", collectionBean.getCode() + "");
        if (collectionBean.getCode() == 0) {
            for (int i = collectionBoxAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                CollectionBoxBean.ContentBean.DataBean dataBean = collectionBoxAdapter.getMyLiveList().get(i - 1);
                if (dataBean.isSelect()) {
                    collectionBoxAdapter.getMyLiveList().remove(dataBean);
                    index--;
                }
            }
            index = 0;
            setBtnBackground(index);
            if (collectionBoxAdapter.getMyLiveList().size() == 0) {
                frameLayout.setVisibility(View.GONE);
            }
            collectionBoxAdapter.notifyDataSetChanged();
            builder.dismiss();
        }else {
            initDialogToLogin();
        }
    }

    @Override
    public void onGetCollectionError(String msg) {
        shoucangFrameLayout.setVisibility(View.VISIBLE);
        collectionBoxSpringview.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.shoucang_frame_layout,new FramgmentError()).commit();
    }

    @Override
    protected CollectionBoxPresenterImpl initInject() {
        activityComponent.inject(this);
        return collectionBoxPresenter;
    }


    /**
     * 全选和反选
     */
    private void selectAllMain() {
        if (collectionBoxAdapter == null) return;
        if (!isSelectAll) {
            for (int i = 0, j = collectionBoxAdapter.getMyLiveList().size(); i < j; i++) {
                collectionBoxAdapter.getMyLiveList().get(i).setSelect(true);
                //添加集合
            }
            index = collectionBoxAdapter.getMyLiveList().size();
            deleteButn.setEnabled(true);
            selectAllButn.setText("取消全选");
            isSelectAll = true;
        } else {
            for (int i = 0, j = collectionBoxAdapter.getMyLiveList().size(); i < j; i++) {
                collectionBoxAdapter.getMyLiveList().get(i).setSelect(false);
            }
            index = 0;
            deleteButn.setEnabled(false);
            selectAllButn.setText("全选");
            isSelectAll = false;
        }
        collectionBoxAdapter.notifyDataSetChanged();
        setBtnBackground(index);
    }

    /**
     * 删除逻辑
     */
    private void deleteVideo() {
        if (index == 0) {
            deleteButn.setEnabled(false);
            return;
        }
        builder = new AlertDialog.Builder(this)
                .create();
        builder.show();
        if (builder.getWindow() == null) return;
        builder.getWindow().setContentView(R.layout.pop_user);//设置弹出框加载的布局
        TextView msg = (TextView) builder.findViewById(R.id.tv_msg);
        Button cancle = (Button) builder.findViewById(R.id.btn_cancle);
        Button sure = (Button) builder.findViewById(R.id.btn_sure);
        if (msg == null || cancle == null || sure == null) return;

        if (index == 1) {
            msg.setText("是否删除该条目？");
        } else {
            msg.setText("是否删除这" + index + "个条目？");
        }
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    //删除接口
                    if (selectAllButn.getText().equals("取消全选")) {
                        //请求取消全部收藏
                        collectionBoxPresenter.getClearCollectionBoxData(PersonCenterShouCangActivity.this, "Bearer " + token);
                    } else {
                        //否则取消一个或多个收藏
                        collectionBoxPresenter.getCollectionData(PersonCenterShouCangActivity.this, "Bearer " + token, stringshoucang);
                        LogUtils.i("多选", stringshoucang);
                    }
                }
            }
        });
    }

    //111
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_all_butn:
                selectAllMain();
                break;
            case R.id.delete_butn:
                deleteVideo();
                break;
        }
    }

    //111
    @Override
    public void onItemClickListener(int pos, List<CollectionBoxBean.ContentBean.DataBean> myLiveList) {
        CollectionBoxBean.ContentBean.DataBean myLive = myLiveList.get(pos);
        if (editorStatus) {
            if (NoFastClickUtils.isFastClick()) {
            } else {
                boolean isSelect = myLive.isSelect();
                if (!isSelect) {
                    index++;
                    myLive.setSelect(true);
                    if (index == myLiveList.size()) {
                        isSelectAll = true;
                        selectAllButn.setText("取消全选");
                    }
                } else {
                    myLive.setSelect(false);
                    index--;
                    isSelectAll = false;
                    selectAllButn.setText("全选");
                }

                //适配器条目有选中有没选中的。根据适配器集合中选中长度确定数组长度。
                arr.clear();
                for (int i = 0; i < myLiveList.size(); i++) {
                    if (myLiveList.get(i).isSelect() == true) {
                        arr.add(String.valueOf(myLiveList.get(i).getCourse().getCourseId()));
                        //去重
                    }
                }
                stringshoucang = "";
                for (int i = 0; i < arr.size(); i++) {
                    stringshoucang = stringshoucang + arr.get(i) + ",";
                }
                setBtnBackground(index);
                collectionBoxAdapter.notifyDataSetChanged();
            }
            //展示的时候只多选生效否则子条目点击生效
        } else {
            if (NoFastClickUtils.isFastClick()) {
            } else {
                if (myLive.getCourse().getCourseType().equals("SCIENCEROOM")) {
                    Intent intent = new Intent(this, MyLiveRoomWebActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    this.startActivity(intent);
                } else if (myLive.getCourse().getIsImageText() == 1) {
                    Intent intent = new Intent(this, PictrueDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    this.startActivity(intent);
                } else {
                    if (myLive.getCourse().getCourseType().equals("YINGXIANGSUCAI")){
                        Intent intent = new Intent(this, TeacherTrainingDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        //视频和当前时长
                        bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                        //bundle.putString("watchTime", String.valueOf(myLive.getCourse().getWatchTime()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        this.startActivity(intent);
                    }else if (myLive.getCourse().getCourseType().equals("TEACHERCOURSE")){
                        Intent intent = new Intent(this, YingXinagVideoDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        //视频和当前时长
                        bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                        //bundle.putString("watchTime", String.valueOf(myLive.getCourse().getWatchTime()));
                        intent.putExtras(bundle);
                        //这里一定要获取到所在Activity再startActivity()；
                        this.startActivity(intent);
                    }
                    /*Intent intent = new Intent(this, YingXinagVideoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("courseId", String.valueOf(myLive.getCourse().getCourseId()));
                    intent.putExtras(bundle);
                    //这里一定要获取到所在Activity再startActivity()；
                    this.startActivity(intent);*/
                }
            }
        }
    }

}
