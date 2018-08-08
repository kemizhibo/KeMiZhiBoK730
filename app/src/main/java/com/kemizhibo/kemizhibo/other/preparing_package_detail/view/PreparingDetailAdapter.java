package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.downloader.DocDownloadManager;
import com.dueeeke.videoplayer.util.L;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.PreparingPackageDetailActivity;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyPreviewBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingDocBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPPTBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPicBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingWordBen;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreviewBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.File;
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

public class PreparingDetailAdapter extends BaseAdapter {

    private Handler mHandler;
    private List<PreparingPackageDetailBean.ContentBean.MaterialBean> material;
    private Context context;

    //定义样式常量，注意常量值要从0开始
    private static final int TYPE_WENDANG = 1;//文档
    private static final int TYPE_PPT = 2;//ppt
    private static final int TYPE_SHIPIN = 0;//视频
    private static final int TYPE_PUPIAN = 3;//图片
    private static final int TYPE_MAKE = 4;//在线制作
    private int courseId;
    private int moduleId;
    private BDocInfo docInfo;
    private boolean isjump = false;


    public PreparingDetailAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.MaterialBean> material) {
        this.context = context;
        this.material = material;
    }


    @Override
    public int getCount() {
        return material.size();
    }

    @Override
    public Object getItem(int position) {
        return material.get(position);
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
        Log.i("-----", material.get(position).getDocType() + "");
        if (material.get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (material.get(position).getDocType() == 3) {
            return TYPE_PPT;
        } else if (material.get(position).getDocType() == 5) {
            return TYPE_SHIPIN;
        } else if (material.get(position).getDocType() == 7) {
            return TYPE_MAKE;
        }

        return TYPE_PUPIAN;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("---size--", material.size() + "");
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
                    holder.mdown = (TextView) convertView.findViewById(R.id.mdown);
                    holder.mcheck = (TextView) convertView.findViewById(R.id.mcheck);
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
               /* case TYPE_MAKE://在线制作
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
            courseId = material.get(position).getCourseId();
            moduleId = material.get(position).getModuleId();
            RequestUtil.requestVideo((Activity) context, position, holder, courseId);
        } else if (itemViewType == TYPE_PPT) {
            moduleId = material.get(position).getModuleId();
            RequestUtil.requestPPT((Activity) context, holder, itemViewType, moduleId);
        } else if (itemViewType == TYPE_WENDANG) {
            moduleId = material.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_WENDANG);
        } else if (itemViewType == TYPE_PUPIAN) {
            moduleId = material.get(position).getModuleId();
            RequestUtil.requestPic((Activity) context, holder, itemViewType, moduleId);
        }
       /* else if (itemViewType == TYPE_MAKE) {
            moduleId = material.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_MAKE);
        }*/

        return convertView;
    }

}