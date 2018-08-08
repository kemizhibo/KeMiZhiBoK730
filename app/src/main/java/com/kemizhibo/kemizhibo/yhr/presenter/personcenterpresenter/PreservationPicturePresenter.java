package com.kemizhibo.kemizhibo.yhr.presenter.personcenterpresenter;

import com.kemizhibo.kemizhibo.yhr.base.BaseActivity;
import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BasePresenter;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.GetUserView;
import com.kemizhibo.kemizhibo.yhr.view.personcenterview.PreservationPictureView;

import java.io.File;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe  保存头像
 */
public interface PreservationPicturePresenter extends BasePresenter<PreservationPictureView> {
    //保存头像
    void getPreservationPictureData(BaseActivity activity, String token,String picImg);
    //上传头像
    void getTakePhotoData(BaseActivity activity, String token, File uploadfile,String param);

}
