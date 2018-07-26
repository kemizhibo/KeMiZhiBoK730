package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;

import java.util.List;

/**
 * Created by yhr on 2018/7/1.
 * 分类筛选条件
 */
//bean类必须是你要拿到的最里边的想要的数据
public class FilterImgScienceAdapter extends BaseQuickAdapter<FilterBean.ContentBean.ImgScienceBean, BaseViewHolder> {

    public FilterImgScienceAdapter(int layoutResId, @Nullable List<FilterBean.ContentBean.ImgScienceBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, FilterBean.ContentBean.ImgScienceBean item) {
        helper.setText(R.id.shaixuan_fenlei_recyclerview_item_txt,item.getSubjectName());
        TextView view = helper.getView(R.id.shaixuan_fenlei_recyclerview_item_txt);
        //实体类中手动添加是否选中的参数。并且给构函数和set方法
        if(item.isFlage()){
            //LogUtils.i("111111111","yhjttfyujftyhftyhfth");
            view.setTextColor(mContext.getResources().getColor(R.color.lvse));
        }else{
            //LogUtils.i("111111111","2222222222222");
            view.setTextColor(mContext.getResources().getColor(R.color.heise));
        }
    }


}
