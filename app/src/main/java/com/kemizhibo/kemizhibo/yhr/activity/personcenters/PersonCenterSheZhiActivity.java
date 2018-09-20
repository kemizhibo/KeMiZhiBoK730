package com.kemizhibo.kemizhibo.yhr.activity.personcenters;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.MyApplication;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.SignOutBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.SignOutPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.CleanMessageUtil;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.SysApplication;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.SignOutView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import static com.kemizhibo.kemizhibo.yhr.utils.CleanMessageUtil.clearAllCache;


public class PersonCenterSheZhiActivity extends BaseMvpActivity<SignOutPresenterImpl> implements SignOutView {

    @Inject
    public SignOutPresenterImpl signOutPresenter;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.change_pwd_layout)
    LinearLayout changePwdLayout;
    @BindView(R.id.clear_num_text)
    TextView clearNumText;
    @BindView(R.id.clear_layout)
    LinearLayout clearLayout;
    @BindView(R.id.get_new_num_text)
    TextView getNewNumText;
    @BindView(R.id.get_new_lauout)
    LinearLayout getNewLauout;
    @BindView(R.id.finish_login_butn)
    TextView finishLoginButn;
    @BindView(R.id.fuwu)
    TextView fuwu;
    private SharedPreferences sp;
    private String token;
    private Intent intent;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){
                finish();
            }
        }
    };
    private String totalCacheSize;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_she_zhi;
    }

    @Override
    protected void initData() {
        //SysApplication.getInstance().addActivity(this);
        bindTitleBar();
        //计算应用缓存大小
        try {
            totalCacheSize = CleanMessageUtil.getTotalCacheSize(this);
            clearNumText.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("设置");
        publicTitleBarRoot.buildFinish();
    }

    @OnClick({R.id.change_pwd_layout, R.id.clear_layout, R.id.get_new_lauout, R.id.finish_login_butn, R.id.fuwu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_pwd_layout:
                Intent intent = new Intent(PersonCenterSheZhiActivity.this,ChangePwdActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.clear_layout:
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("确定要清空本地缓存吗？");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearAllCache(getApplicationContext());
                            clearNumText.setText("0.0KB");
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.get_new_lauout:
                break;
            case R.id.finish_login_butn:
                sp = getSharedPreferences("logintoken", 0);
                token = sp.getString("token", "");
                signOutPresenter.getSignOutData(this,"Bearer "+ token);
                break;
            case R.id.fuwu:
                intent = new Intent(PersonCenterSheZhiActivity.this, FuWuActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== MyApplication.YINGXIANG_TO_PICK_req&&resultCode==MyApplication.YINGXIANG_TO_PICK_res){
            finish();
        }
    }

    @Override
    public void onSignOutSuccess(SignOutBean signOutBean) {
          if (signOutBean.getCode()==0){
              if(sp!=null){
                  sp.edit().clear().commit();
              }
              setResult(MyApplication.YINGXIANG_TO_PICK_res);
              Transparent.showSuccessMessage(this,"您的账号已退出登录");
              new Thread(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          Thread.sleep(1500);
                          handler.sendEmptyMessage(0);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }).start();
          }else if (signOutBean.getCode()==401||signOutBean.getCode()==801){
              initDialogToLogin();
          }else {
              ToastUtils.showToast("网络连接中断，请检查您的网络状态");
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
                                    Intent intent = new Intent(PersonCenterSheZhiActivity.this, LoginActivity.class);
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

    @Override
    public void onSignOutError(String msg) {
        ToastUtils.showToast("网络连接中断，请检查您的网络状态");
    }

    @Override
    protected SignOutPresenterImpl initInject() {
        activityComponent.inject(this);
        return signOutPresenter;
    }
}
