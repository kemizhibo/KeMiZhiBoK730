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

import static com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder.mcheck;
import static com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder.mdown;
import static com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder.mwendang;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailOtherAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.DataBean> otherBeanList;
    private Handler mHandler;

    private Context context;

    //定义样式常量，注意常量值要从0开始
    private static final int TYPE_PPT = 1;//ppt
    private static final int TYPE_excel = 0;//excal
    private int courseId;
    private int moduleId;

    public PreparingDetailOtherAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.DataBean> otherBeanList) {
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
        if (otherBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        }

        return TYPE_excel;
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
                case TYPE_excel://表格
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
                default:
                    break;
            }
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Log.i("---itemViewType--", "" + itemViewType);
        if (itemViewType == TYPE_PPT) {
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestOtherPPT((Activity) context, holder.mppt, 3, moduleId);
        } else if (itemViewType == TYPE_excel) {
            moduleId = otherBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, mwendang, mdown, mcheck, itemViewType, moduleId, 1);
        }
        return convertView;
    }

}
