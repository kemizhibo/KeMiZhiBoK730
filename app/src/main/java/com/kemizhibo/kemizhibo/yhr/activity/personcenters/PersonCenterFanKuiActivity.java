package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.FeedBackBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.FeedBackPresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.CustomDialog;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.FeedBackView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class PersonCenterFanKuiActivity extends BaseMvpActivity<FeedBackPresenterImpl> implements FeedBackView {

    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.fankui_butn_one)
    RadioButton fankuiButnOne;
    @BindView(R.id.fankui_butn_two)
    RadioButton fankuiButnTwo;
    @BindView(R.id.fankui_butn_three)
    RadioButton fankuiButnThree;
    @BindView(R.id.fankui_butn_four)
    RadioButton fankuiButnFour;
    @BindView(R.id.fankui_radio_group)
    RadioGroup fankuiRadioGroup;
    @BindView(R.id.fankui_edittext)
    EditText fankuiEdittext;
    @BindView(R.id.fankui_text)
    TextView fankuiText;
    @BindView(R.id.fankui_butn_commit)
    Button fankuiButnCommit;

    @Inject
    public FeedBackPresenterImpl feedBackPresenter;
    private int type = 0;
    private String content;
    private SharedPreferences sp;
    private String token;
    private BottomSheetDialog dialog;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){
                finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_center_fan_kui;
    }

    @Override
    protected void initData() {
        bindTitleBar();
        showCharNumber(200);
    }


    private void showCharNumber(final int i) {
        fankuiEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                content = fankuiEdittext.getText().toString();
                String num = String.valueOf(i- content.length());
                /*SpannableStringBuilder builder = new SpannableStringBuilder("请在您账户中充值0.01元,进行身份验证");
                ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
                builder.setSpan(span, 8, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_tips.setText(builder);*/
                fankuiText.setText("还可以输入"+num+"个字呢～");
            }
        });
    }

    private void bindTitleBar() {
        publicTitleBarRoot.setLeftImageResouse(R.drawable.ic_back).setLeftLinearLayoutListener(new TapBarLayout.LeftOnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        publicTitleBarRoot.changeTitleBar("意见反馈");
        publicTitleBarRoot.buildFinish();
    }


    @OnClick({R.id.fankui_butn_one, R.id.fankui_butn_two, R.id.fankui_butn_three, R.id.fankui_butn_four, R.id.fankui_butn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fankui_butn_one:
                type = 1;
                break;
            case R.id.fankui_butn_two:
                type = 2;
                break;
            case R.id.fankui_butn_three:
                type = 3;
                break;
            case R.id.fankui_butn_four:
                type = 4;
                break;
            case R.id.fankui_butn_commit:
                if (content==null||content.equals("")){
                    ToastUtils.showToast("反馈信息不能为空");
                }else {
                    sp = getSharedPreferences("logintoken", 0);
                    token = sp.getString("token", "");
                    feedBackPresenter.getFeedBackData(this,"Bearer "+ token,content, String.valueOf(type));
                }
                break;
        }
    }

    @Override
    public void onFeedBackSuccess(FeedBackBean feedBackBean) {
        if (feedBackBean.getCode()==0){
            Transparent.showSuccessMessage(this,"提交成功!");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
         }else {
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
                                    Intent intent = new Intent(PersonCenterFanKuiActivity.this, LoginActivity.class);
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
    public void onFeedBackError(String msg) {

    }

    @Override
    protected FeedBackPresenterImpl initInject() {
        activityComponent.inject(this);
        return feedBackPresenter;
    }
}
