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
import android.widget.Toast;

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

public class PreparingDetailPlanAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.PlanBean> planBeanList;
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

    public PreparingDetailPlanAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.PlanBean> planBeanList,Handler mHandler) {
        this.context = context;
        this.planBeanList = planBeanList;
        this.mHandler=mHandler;
    }


    @Override
    public int getCount() {
        return planBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return planBeanList.get(position);
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
        Log.i("---yyyy--", planBeanList.get(position).getDocType() + "");
        if (planBeanList.get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (planBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        } else if (planBeanList.get(position).getDocType() == 5) {
            return TYPE_SHIPIN;
        } else if (planBeanList.get(position).getDocType() == 7) {
            return TYPE_MAKE;
        }

        return TYPE_PUPIAN;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("---size--", planBeanList.size() + "");
        // 获取当前条目的类型
        int itemViewType = getItemViewType(position);
        MyViewHolder holder;
        // PreparingPackageDetailBean.ContentBean.MaterialBean materialBean = material.get(position);
        if (convertView == null) {
            holder = new MyViewHolder();
            switch (itemViewType) {
                case TYPE_WENDANG://文档
                    convertView = View.inflate(context, R.layout.wendang_plan_item, null);
                    holder.mwendang = (TextView) convertView.findViewById(R.id.mword);
                    holder.mdeleteword = (TextView) convertView.findViewById(R.id.mdeleteword);
                    convertView.setTag(holder);
                    break;
                case TYPE_PPT://ppt
                    convertView = View.inflate(context, R.layout.ppt_plan_item, null);
                    holder.mppt = (TextView) convertView.findViewById(R.id.mppt);
                    holder.mdeleteppt = (TextView) convertView.findViewById(R.id.mdeleteppt);
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
                    holder.mdeleteonline = (TextView) convertView.findViewById(R.id.mdeleteonline);
                    holder.mcheckonline = (TextView) convertView.findViewById(R.id.mcheckonline);
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
            courseId = planBeanList.get(position).getCourseId();
            moduleId = planBeanList.get(position).getModuleId();
            RequestUtil.requestVideo((Activity) context, position, holder, courseId);
        } else if (itemViewType == TYPE_PPT) {
            moduleId = planBeanList.get(position).getModuleId();
            holder.mdeleteppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(3, position);
                }
            });
            RequestUtil.requestPPT((Activity) context, holder, itemViewType, moduleId);
        } else if (itemViewType == TYPE_WENDANG) {
            moduleId = planBeanList.get(position).getModuleId();
            holder.mdeleteword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(1, position);
                }
            });
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_WENDANG);
        } else if (itemViewType == TYPE_PUPIAN) {
            moduleId = planBeanList.get(position).getModuleId();
            RequestUtil.requestPic((Activity) context, holder, itemViewType, moduleId);
        } else if (itemViewType == TYPE_MAKE) {
            moduleId = planBeanList.get(position).getModuleId();
            holder.mmake.setText(planBeanList.get(position).getDocName());
            holder.mdeleteonline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(7, position);
                }
            });
          /*  moduleId = planBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_MAKE);*/
        }
        return convertView;
    }

    private void deletePPT(int i, final int position) {
        Map map = new HashMap();
        Log.i("-pptmoduleId---", moduleId + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(i));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DEL_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //  Log.i("-pptjson---", response.body().string() + "");
                final PreparingPPTBean bean = GsonUtils.getBean(response.body().string(), PreparingPPTBean.class);
                Log.i("-pptcode---", bean.getCode() + "");
                if (0 == bean.getCode()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show();
                            planBeanList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }


            }
        });
    }


}
