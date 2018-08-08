package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.PreservationPictureBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.TakePhotoBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface PreservationPictureView extends BaseView {
    //保存头像
    void onPreservationPictureSuccess(PreservationPictureBean preservationPictureBean);
    void onPreservationPictureError(String msg);
     //上产头像
     void onTakePhotoSuccess(TakePhotoBean takePhotoBean);
    void onTakePhotoError(String msg);
}
