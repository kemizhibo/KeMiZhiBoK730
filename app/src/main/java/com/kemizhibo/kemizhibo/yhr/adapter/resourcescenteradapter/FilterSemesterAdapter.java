package com.kemizhibo.kemizhibo.yhr.adapter.resourcescenteradapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.yhr.bean.resourcescenterbean.FilterBean;

import java.util.List;

/**
 * Author: yhr
 * Date: on 2018/7/17.
 * Describe:学期筛选条件
 */

public class FilterSemesterAdapter extends BaseQuickAdapter<FilterBean.ContentBean.SemesterBean, BaseViewHolder> {

    public FilterSemesterAdapter(int layoutResId, @Nullable List<FilterBean.ContentBean.SemesterBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, FilterBean.ContentBean.SemesterBean item) {
        helper.setText(R.id.shaixuan_xueqi_recyclerview_item_txt,item.getSubjectName());
        TextView view = helper.getView(R.id.shaixuan_xueqi_recyclerview_item_txt);
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

