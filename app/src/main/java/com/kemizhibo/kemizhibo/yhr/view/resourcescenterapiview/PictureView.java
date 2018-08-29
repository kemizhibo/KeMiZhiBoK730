package com.kemizhibo.kemizhibo.yhr.view.resourcescenterapiview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.CollectionBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.OneLookBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.PictureBean;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.YingXiangFragmentBean;

/**
 * Author: yhr
 * Date: 2018/5/24
 * Describe    图文
 */
public interface PictureView extends BaseView{
    void onPictureSuccess(PictureBean pictureBean);
    void onPictureError(String msg);
    //收藏视频
    void onGetCollectionSuccess(CollectionBean collectionBean);
    void onGetCollectionError(String msg);

    //记录第一次播放位置
    void onGetOneLookSuccess(OneLookBean oneLookBean);
    void onGetOneLookError(String msg);

}
