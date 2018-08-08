package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailOtherAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.OtherBean> otherBeanList;
    private Handler mHandler;

    private Context context;

    //定义样式常量，注意常量值要从0开始
    private static final int TYPE_WENDANG = 1;//文档
    private static final int TYPE_PPT = 2;//ppt
    private static final int TYPE_SHIPIN = 0;//视频
    private static final int TYPE_PUPIAN = 3;//图片
    private int courseId;
    private int moduleId;
    private static final int TYPE_MAKE = 4;//在线制作

    public PreparingDetailOtherAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.OtherBean> otherBeanList) {
        this.context = context;
        this.otherBeanList = otherBeanList;
    }


    @Override
    public int getCount() {
        return otherBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("-----", otherBeanList.get(position).getDocType() + "");
        if (otherBeanList.get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (otherBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        } else if (otherBeanList.get(position).getDocType() == 5) {
            return TYPE_SHIPIN;
        } else if (otherBeanList.get(position).getDocType() == 7) {
            return TYPE_MAKE;
        }

        return TYPE_PUPIAN;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("---otherBeanListsize--", otherBeanList.size() + "");
        // 获取当前条目的类型
        int itemViewType = getItemViewType(position);
        MyViewHolder holder;
        // PreparingPackageDetailBean.ContentBean.MaterialBean materialBean = material.get(position);
        if (convertView == null) {
            holder = new MyViewHolder();
            switch (itemViewType) {
                case TYPE_WENDANG://文档
                    convertView = View.inflate(context, R.layout.wendang_item, null);
                    holder.mwendang = (TextView) convertView.findViewById(R.id.mword);
                    convertView.setTag(holder);
                    break;
                case TYPE_PPT://ppt
                    convertView = View.inflate(context, R.layout.ppt_item, null);
                    holder.mppt = (TextView) convertView.findViewById(R.id.mppt);
                    holder.mcheckppt = (TextView) convertView.findViewById(R.id.mcheckppt);
                    holder.mdownppt = (TextView) convertView.findViewById(R.id.mdownppt);
                    convertView.setTag(holder);
                    break;
                case TYPE_PUPIAN://图片
                    convertView = View.inflate(context, R.layout.tupian_item, null);
                    holder.miv = (SimpleDraweeView) convertView.findViewById(R.id.mimage);
                    holder.madjsucai = (TextView) convertView.findViewById(R.id.adj);
                    convertView.setTag(holder);
                    break;
                case TYPE_SHIPIN://视频
                    convertView = View.inflate(context, R.layout.shipin_item, null);
                    holder.jcVideoPlayer = (JZVideoPlayerStandard) convertView.findViewById(R.id.jc);
                    holder.madj = (TextView) convertView.findViewById(R.id.adj);
                    convertView.setTag(holder);
                    break;
              /*  case TYPE_MAKE://在线制作
                    convertView = View.inflate(context, R.layout.make_item, null);
                    holder.mmake = (TextView) convertView.findViewById(R.id.makeadj);
                    convertView.setTag(holder);
                    break;*/
                default:
                    break;
            }
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Log.i("---itemViewType--", "" + itemViewType);
        if (itemViewType == TYPE_SHIPIN) {
            courseId = otherBeanList.get(position).getCourseId();
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestVideo((Activity) context, position, holder, courseId);
        } else if (itemViewType == TYPE_PPT) {
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestPPT((Activity) context, holder, itemViewType, moduleId);
        } else if (itemViewType == TYPE_WENDANG) {
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_WENDANG);
        } else if (itemViewType == TYPE_PUPIAN) {
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestPic((Activity) context, holder, itemViewType, moduleId);
        }
       /* else if (itemViewType == TYPE_MAKE) {
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_MAKE);
        }*/
        return convertView;
    }

}
