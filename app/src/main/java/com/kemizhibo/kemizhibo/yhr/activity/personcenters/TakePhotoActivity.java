package com.kemizhibo.kemizhibo.yhr.activity.personcenters;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dueeeke.videoplayer.util.L;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.yhr.activity.logins.LoginActivity;
import com.kemizhibo.kemizhibo.yhr.base.BaseMvpActivity;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.PreservationPictureBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.TakePhotoBean;
import com.kemizhibo.kemizhibo.yhr.presenter.impl.personcenter.PreservationPicturePresenterImpl;
import com.kemizhibo.kemizhibo.yhr.utils.LQRPhotoSelectUtils;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.NoFastClickUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.Transparent;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.PreservationPictureView;
import com.kemizhibo.kemizhibo.yhr.widgets.TapBarLayout;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TakePhotoActivity extends BaseMvpActivity<PreservationPicturePresenterImpl> implements PreservationPictureView {

    @Inject
    public PreservationPicturePresenterImpl preservationPicturePresenter;
    @BindView(R.id.btnSelectPhoto)
    Button btnSelectPhoto;
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.public_title_bar_root)
    TapBarLayout publicTitleBarRoot;
    @BindView(R.id.yes_butn)
    Button yesButn;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private SharedPreferences sp;
    private String token;
    private String filepath;
    private String url;
    private File outputFile;
    private String photo;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what==0){
                finish();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_photo;
    }

    @Override
    protected void getData() {
        super.getData();
        Intent intent = getIntent();
        photo = intent.getStringExtra("photo");
        Glide.with(TakePhotoActivity.this).load(photo).into(ivPic);
    }

    @Override
    protected void initData() {
        init();
        bindTitleBar();
        initListener();
        //上传图片
        //intiPhoto();
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
        publicTitleBarRoot.changeTitleBar("选取图片");
        publicTitleBarRoot.buildFinish();
    }

    private void initListener() {
        /*btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3、调用拍照方法
                PermissionGen.with(TakePhotoActivity.this)
                        .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();
            }
        });*/
        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3、调用从图库选取图片方法
                PermissionGen.needPermission(TakePhotoActivity.this,
                        LQRPhotoSelectUtils.REQ_SELECT_PHOTO,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}
                );
            }
        });
    }

    private void init() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                TakePhotoActivity.this.outputFile = outputFile;
                // 4、当拍照或从图库选取图片成功后回调
                //得到图片的路径和URL
                filepath = outputFile.getAbsolutePath();
                url = outputUri.toString();
                Glide.with(TakePhotoActivity.this).load(outputUri).into(ivPic);
            }
        }, true);//true裁剪，false不裁剪

        //        mLqrPhotoSelectUtils.setAuthorities("com.lqr.lqrnativepicselect.fileprovider");
        //        mLqrPhotoSelectUtils.setImgPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + String.valueOf(System.currentTimeMillis()) + ".jpg");
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        mLqrPhotoSelectUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {
        showDialog();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {
        showDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 2、在Activity中的onActivityResult()方法里与LQRPhotoSelectUtils关联
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }

    public void showDialog() {
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //设置标题
        builder.setTitle("权限申请");
        //设置正文
        builder.setMessage("在设置-应用-科米课堂-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能");

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {//点击完确定后，触发这个事件

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //这里用来跳到手机设置页，方便用户开启权限
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + TakePhotoActivity.this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //使用构建器创建出对话框对象
        AlertDialog dialog = builder.create();
        dialog.show();//显示对话框
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //选取图片成功以后上传头像
    @OnClick(R.id.yes_butn)
    public void onViewClicked() {
        if (NoFastClickUtils.isFastClick()) {
        }else {
            Map map = new HashMap();
            map.put("param","picImg");
            OkHttpRequest.uploadFile(this, "http://39.155.221.165:8080/image/upload", outputFile, "yhr.jpg", map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtils.showToast(String.valueOf(e));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //返回的图片地址
                    //LogUtils.i("返回图片",response.body().string());
                    TakePhotoBean takePhotoBean = GsonUtils.getBean(response.body().string(), TakePhotoBean.class);
                    if(takePhotoBean != null && 0 == (takePhotoBean.getCode())){
                        String picImg = takePhotoBean.getContent().toString();
                        sp = getSharedPreferences("logintoken", 0);
                        token = sp.getString("token", "");
                        preservationPicturePresenter.getPreservationPictureData(TakePhotoActivity.this,"Bearer "+token,picImg);
                    }else{
                        //ToastUtils.showToast("返回失败");
                    }
                }
            });
        /*File file = new File(filepath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        preservationPicturePresenter.getTakePhotoData(this,part);*/
        }
    }

    //保存头像
    @Override
    public void onPreservationPictureSuccess(PreservationPictureBean preservationPictureBean) {
        if (preservationPictureBean.getCode()==0){
            Transparent.showSuccessMessage(this,"修改头像成功!");
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
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlertDialog dialog=builder
                .setView(R.layout.alertdialog_login)
                .setPositiveButton("前往登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (NoFastClickUtils.isFastClick()) {
                        }else {
                            Intent intent = new Intent(TakePhotoActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 520;
        lp.height = 260;
        window.setAttributes(lp);
    }

    @Override
    public void onPreservationPictureError(String msg) {
        ToastUtils.showToast(msg);
    }
    //上传头像
    @Override
    public void onTakePhotoSuccess(TakePhotoBean takePhotoBean) {

    }

    @Override
    public void onTakePhotoError(String msg) {

    }

    @Override
    protected PreservationPicturePresenterImpl initInject() {
        activityComponent.inject(this);
        return preservationPicturePresenter;
    }
}
