package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPPTBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.IOException;
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

    private final List<PreparingPackageDetailBean.ContentBean.DataBean> planBeanList;
    private Handler mHandler;

    private Context context;

    //定义样式常量，注意常量值要从0开始
    private static final int TYPE_PPT = 0;//ppt
    private int courseId;
    private int moduleId;
    private static final int TYPE_MAKE = 1;//在线制作

    public PreparingDetailPlanAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.DataBean> planBeanList, Handler mHandler) {
        this.context = context;
        this.planBeanList = planBeanList;
        this.mHandler = mHandler;
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
        if (planBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        }
        return TYPE_MAKE;
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
                case TYPE_PPT://ppt
                    convertView = View.inflate(context, R.layout.ppt_plan_item, null);
                    holder.mppt = (TextView) convertView.findViewById(R.id.mppt);
                    holder.mdeleteppt = convertView.findViewById(R.id.mdeleteppt);
                    convertView.setTag(holder);
                    break;
                case TYPE_MAKE://在线制作
                    convertView = View.inflate(context, R.layout.make_item, null);
                    holder.mmake = (TextView) convertView.findViewById(R.id.makeadj);
                    holder.mdeleteonline = convertView.findViewById(R.id.mdeleteonline);
                    holder.mcheckonline = convertView.findViewById(R.id.mcheckonline);
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
            moduleId = planBeanList.get(position).getModuleId();
            holder.mdeleteppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(3, position);
                }
            });
            RequestUtil.requestOtherPPT((Activity) context, holder.mppt, moduleId, 3);
        }
        if (itemViewType == TYPE_MAKE) {
            moduleId = planBeanList.get(position).getModuleId();
            holder.mmake.setText(planBeanList.get(position).getDocName());
            holder.mdeleteonline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(7, position);
                }
            });
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
