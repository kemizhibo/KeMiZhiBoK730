package com.kemizhibo.kemizhibo.yhr.view.personcenterview;

import com.kemizhibo.kemizhibo.yhr.base.mvpbase.BaseView;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ChangeUserBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.ClearCollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.CollectionBoxBean;
import com.kemizhibo.kemizhibo.yhr.bean.personcenterbean.GetUserBean;

/**
 * Created by 17600 on 2018/5/18.
 */

public interface CollectionBoxView extends BaseView {
    //获取收藏架
    void onCollectionBoxSuccess(CollectionBoxBean collectionBoxBean);
    void onCollectionBoxError(String msg);

    //清空收藏架
    void onClearCollectionBoxSuccess(ClearCollectionBoxBean clearCollectionBoxBean);
    void onClearCollectionBoxError(String msg);

}
