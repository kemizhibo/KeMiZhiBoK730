package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.adapter.personcenteradapter.LiuLanAdapter;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearLiuLanBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.LiuLanBean;
import com.kemizhibo.kemizhibo.yhr.fragment.stateFragment.FramgmentEmpty;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.LiuLanPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.DividerItemDecoration;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.LiuLanView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.widget.SpringView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

public class PersonCenterLiuLanActivity extends BaseMvpActivity<LiuLanPresenterImpl> implements LiuLanView, View.OnClickListener, LiuLanAdapter.OnItemClickListener {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    @BindView(R.id.liulan_frame_layout)
    FrameLayout liulanFrameLayout;
    private int mEditMode = MYLIVE_MODE_CHECK;
    @Inject
    public LiuLanPresenterImpl liuLanPresenter;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.liulan_recyclerview)
    RecyclerView liulanRecyclerview;
    @BindView(R.id.liulan_springview)
    SpringView liulanSpringview;
    @BindView(R.id.select_all_butn)
    Button selectAllButn;
    @BindView(R.id.delete_butn)
    Button deleteButn;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private SharedPreferences sp;
    private String token;
    private boolean flag;
    //上或者下拉的状态判断
    int isUp = 1;
    //刷新适配器的判断
    private boolean isFlag;
    //当前条目数
    private int page;
    //多选单选
    private LiuLanAdapter liuLanAdapter = null;
    private List<LiuLanBean.ContentBean.DataBean> mList = new ArrayList<>();
    private boolean isSelectAll = false;
    private boolean editorStatus = false;
    private int index = 0;
    private AlertDialog builder;
    //多选下标集合
    List<String> arr = new ArrayList<>();
    private String stringshoucang;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_liu_lan;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        //加载数据
        intiLiuLanData();
    }

    @Override
    protected void getData() {
        super.getData();
        sp = getSharedPreferences("logintoken", 0);
        token = sp.getString("token", "");
        liuLanPresenter.getLiuLanData(this, "Bearer " + token, "1", "10");
        isFlag=true;
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
                if (NoFastClickUtils.isFastClick()) {
                }else {
                    if (flag){
                        publicTitleBarRoot.setRightImageResouse(R.drawable.pan);
                    }else{
                        publicTitleBarRoot.setRightImageResouse(R.drawable.qvxiao_2);
                    }
                    flag =! flag;
                    updataEditMode();
                }
            }
        });
        publicTitleBarRoot.changeTitleBar("浏览记录");
        publicTitleBarRoot.buildFinish();
    }

    //弹出多选框
    private void updataEditMode() {
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            frameLayout.setVisibility(View.VISIBLE);
            editorStatus = true;
            //publicTitleBarRoot.setRightImageResouse(R.drawable.qvxiao_2);
        } else {
            frameLayout.setVisibility(View.GONE);
            //publicTitleBarRoot.setRightImageResouse(R.drawable.pan);
            editorStatus = false;
            clearAll();
        }
        //改变适配器中的远点显示和隐藏
        if (liuLanAdapter != null) {
            liuLanAdapter.setEditMode(mEditMode);
        }
    }

    //清除所有
    private void clearAll() {
        isSelectAll = false;
        selectAllButn.setText("全选");
        setBtnBackground(0);
    }

    @Override
    public void onLiuLanSuccess(LiuLanBean liuLanBean) {
        if (liuLanBean.getCode() == 0) {
            if (isUp==1){
                mList.clear();
                mList.addAll(liuLanBean.getContent().getData());
                if (mList.size()>0){
                    //切换控件
                    liulanFrameLayout.setVisibility(View.GONE);
                    liulanSpringview.setVisibility(View.VISIBLE);
                    if (isFlag) {
                        liuLanAdapter.notifyDataSetChanged();
                    }
                }else {
                    //切换控件
                    liulanFrameLayout.setVisibility(View.VISIBLE);
                    liulanSpringview.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.liulan_frame_layout,new FramgmentEmpty()).commit();
                }
            }else if (isUp==2){
                if (liuLanAdapter.getMyLiveList().size()>=liuLanBean.getContent().getTotal()){
                    ToastUtils.showToast("没有更多数据");
                }else {
                    mList.addAll(liuLanBean.getContent().getData());
                    if (mList.size()>0){
                        //切换控件
                        liulanFrameLayout.setVisibility(View.GONE);
                        liulanSpringview.setVisibility(View.VISIBLE);
                        if (isFlag) {
                            liuLanAdapter.notifyDataSetChanged();
                        }
                    }else {
                        //切换控件
                        liulanFrameLayout.setVisibility(View.VISIBLE);
                        liulanSpringview.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.liulan_frame_layout,new FramgmentEmpty()).commit();
                    }
                }
            }
        } else {
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
                                    Intent intent = new Intent(PersonCenterLiuLanActivity.this, LoginActivity.class);
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


    private void intiLiuLanData() {
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
        liuLanAdapter = new LiuLanAdapter(this);
        initListener();
        LinearLayoutManager layoutManage = new LinearLayoutManager(this);
        liulanRecyclerview.setLayoutManager(layoutManage);
        //上拉下拉动画效果
        liulanRecyclerview.setItemAnimator(new DefaultItemAnimator());
        liulanSpringview.setType(SpringView.Type.FOLLOW);
        DividerItemDecoration itemDecorationHeader = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        itemDecorationHeader.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_main_bg_height_1));
        liulanRecyclerview.addItemDecoration(itemDecorationHeader);
        liulanRecyclerview.setAdapter(liuLanAdapter);
        //*************
        if (liuLanAdapter != null) {
            liuLanAdapter.setEditMode(mEditMode);
        }
        liuLanAdapter.notifyAdapter(mList, false);
        liulanSpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isUp = 1;
                        page = 1;
                        liuLanPresenter.getLiuLanData(PersonCenterLiuLanActivity.this, "Bearer " + token, String.valueOf(page), "10");
                        isFlag = true;
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
                        liuLanPresenter.getLiuLanData(PersonCenterLiuLanActivity.this, "Bearer " + token, String.valueOf(page), "10");
                        isFlag = true;
                        liulanSpringview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        liulanSpringview.setHeader(new AliHeader(this, R.drawable.ali, true));   //参数为：logo图片资源，是否显示文字
        liulanSpringview.setFooter(new AliFooter(this, true));
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
            deleteButn.setTextColor(ContextCompat.getColor(this, R.color.text_bbbbbb));
        }
    }

    private void initListener() {
        liuLanAdapter.setOnItemClickListener(this);
        deleteButn.setOnClickListener(this);
        selectAllButn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_butn:
                deleteVideo();
                break;
            case R.id.select_all_butn:
                selectAllMain();
                break;
            default:
                break;
        }
    }

    /**
     * 全选和反选
     */
    private void selectAllMain() {
        if (liuLanAdapter == null) return;
        if (!isSelectAll) {
            for (int i = 0, j = liuLanAdapter.getMyLiveList().size(); i < j; i++) {
                liuLanAdapter.getMyLiveList().get(i).setSelect(true);
                //添加集合
            }
            index = liuLanAdapter.getMyLiveList().size();
            deleteButn.setEnabled(true);
            selectAllButn.setText("取消全选");
            isSelectAll = true;
        } else {
            for (int i = 0, j = liuLanAdapter.getMyLiveList().size(); i < j; i++) {
                liuLanAdapter.getMyLiveList().get(i).setSelect(false);
            }
            index = 0;
            deleteButn.setEnabled(false);
            selectAllButn.setText("全选");
            isSelectAll = false;
        }
        liuLanAdapter.notifyDataSetChanged();
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
                        liuLanPresenter.getClearLiuLanData(PersonCenterLiuLanActivity.this, "Bearer " + token);
                    } else {
                        //否则存数组删除多个
                        liuLanPresenter.getClearOneOrMoreLiuLanData(PersonCenterLiuLanActivity.this, "Bearer " + token, stringshoucang);
                    }
                }
            }
        });
    }

    @Override
    public void onLiuLanError(String msg) {

    }

    //如果是全选，清空浏览记录
    @Override
    public void onClearLiuLanSuccess(ClearLiuLanBean clearLiuLanBean) {
        if (clearLiuLanBean.getCode() == 0) {
            for (int i = liuLanAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                LiuLanBean.ContentBean.DataBean myLive = liuLanAdapter.getMyLiveList().get(i - 1);
                if (myLive.isSelect()) {
                    liuLanAdapter.getMyLiveList().remove(myLive);
                    index--;
                }
            }
            index = 0;
            setBtnBackground(index);
            if (liuLanAdapter.getMyLiveList().size() == 0) {
                frameLayout.setVisibility(View.GONE);
            }
            liuLanPresenter.getLiuLanData(this, "Bearer " + token, "1", "10");
            liuLanAdapter.notifyDataSetChanged();
            builder.dismiss();
        }else {
            initDialogToLogin();
        }
    }

    @Override
    public void onClearLiuLanError(String msg) {
          ToastUtils.showToast(msg);
    }

    //删除一个或者多个浏览记录
    @Override
    public void onClearOneOrMoreLiuLanSuccess(ClearLiuLanBean clearLiuLanBean) {
        if (clearLiuLanBean.getCode() == 0) {
            for (int i = liuLanAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                LiuLanBean.ContentBean.DataBean myLive = liuLanAdapter.getMyLiveList().get(i - 1);
                if (myLive.isSelect()) {
                    liuLanAdapter.getMyLiveList().remove(myLive);
                    index--;
                }
            }
            index = 0;
            setBtnBackground(index);
            if (liuLanAdapter.getMyLiveList().size() == 0) {
                frameLayout.setVisibility(View.GONE);
            }
            liuLanAdapter.notifyDataSetChanged();
            builder.dismiss();
        }else {
            initDialogToLogin();
        }
    }

    @Override
    public void onClearOneOrMoreLiuLanError(String msg) {
        ToastUtils.showToast(msg);
    }

    @Override
    protected LiuLanPresenterImpl initInject() {
        activityComponent.inject(this);
        return liuLanPresenter;
    }


    @Override
    public void onItemClickListener(int pos, List<LiuLanBean.ContentBean.DataBean> myLiveList) {
        if (NoFastClickUtils.isFastClick()) {
        } else {
            arr.clear();
            if (editorStatus) {
                LiuLanBean.ContentBean.DataBean myLive = myLiveList.get(pos);
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
                for (int i = 0; i < myLiveList.size(); i++) {
                    if (myLiveList.get(i).isSelect() == true) {
                        arr.add(String.valueOf(myLiveList.get(i).getCourse().getCourseId()));
                    }
                }
                stringshoucang = "";
                for (int i = 0; i < arr.size(); i++) {
                    stringshoucang = stringshoucang + arr.get(i) + ",";
                }
                setBtnBackground(index);
                liuLanAdapter.notifyDataSetChanged();
            }
        }
    }

}
