package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingDocBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPPTBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPicBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingWordBen;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailOneAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.OneKeyBean> oneKeyBeanList;
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

    public PreparingDetailOneAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.OneKeyBean> oneKeyBeanList) {
        this.context = context;
        this.oneKeyBeanList = oneKeyBeanList;
    }


    @Override
    public int getCount() {
        return oneKeyBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return oneKeyBeanList.get(position);
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
        Log.i("-----", oneKeyBeanList.get(position).getDocType() + "");
        if (oneKeyBeanList.get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (oneKeyBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        } else if (oneKeyBeanList.get(position).getDocType() == 5) {
            return TYPE_SHIPIN;
        } else if (oneKeyBeanList.get(position).getDocType() == 7) {
            return TYPE_MAKE;
        }

        return TYPE_PUPIAN;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("---onekeysize--", oneKeyBeanList.size() + "");
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
                case TYPE_MAKE://在线制作
                    convertView = View.inflate(context, R.layout.make_item, null);
                    holder.mmake = (TextView) convertView.findViewById(R.id.makeadj);
                    convertView.setTag(holder);
                    break;
                default:
                    break;
            }
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Log.i("---itemViewType--", "" + itemViewType);
        if (itemViewType == TYPE_SHIPIN) {
            courseId = oneKeyBeanList.get(position).getCourseId();
            moduleId = oneKeyBeanList.get(position).getModuleId();
            RequestUtil.requestVideo((Activity) context, position, holder, courseId);
        } else if (itemViewType == TYPE_PPT) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
            RequestUtil.requestPPT((Activity) context, holder, itemViewType, moduleId);
        } else if (itemViewType == TYPE_WENDANG) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_WENDANG);
        } else if (itemViewType == TYPE_PUPIAN) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
            RequestUtil.requestPic((Activity) context, holder, itemViewType, moduleId);
        } else if (itemViewType == TYPE_MAKE) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_MAKE);
        }
        return convertView;
    }

}
