package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.PreparingPackageDetailActivity;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPPTBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.kemizhibo.kemizhibo.other.web.CommonWebActivity.LOCAL;
import static com.kemizhibo.kemizhibo.other.web.CommonWebActivity.OPERATE_KEY;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailPlanAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.PlanBean> planBeanList;
    private Handler mHandler;
    private Context context;
    private String docId;

    //定义样式常量，注意常量值要从0开始
    private static final int TYPE_PPT = 0;//ppt
    private int courseId;
    private int moduleId;
    private static final int TYPE_MAKE = 1;//在线制作

    public PreparingDetailPlanAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.PlanBean> planBeanList, Handler mHandler) {
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


    @SuppressLint("WrongViewCast")
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
                    holder.mcheckppt = convertView.findViewById(R.id.mcheckppt);
                    holder.xiugai_img = convertView.findViewById(R.id.xiugai_img);
                    holder.xiugai_text = convertView.findViewById(R.id.xiugai_text);
                    if (planBeanList.get(position).isAllowUpdate()==true){
                        holder.mcheckppt.setVisibility(View.VISIBLE);
                        holder.xiugai_img.setVisibility(View.VISIBLE);
                    }
                    holder.mdeleteppt = convertView.findViewById(R.id.mdeleteppt);
                    convertView.setTag(holder);
                    break;
                case TYPE_MAKE://在线制作
                    convertView = View.inflate(context, R.layout.make_item, null);
                    holder.mmake = (TextView) convertView.findViewById(R.id.makeadj);
                    holder.mdeleteonline = convertView.findViewById(R.id.mdeleteonline);
                    holder.mcheckonline = convertView.findViewById(R.id.mcheckonline);
                    /*holder.logoxiugai = convertView.findViewById(R.id.logoxiugai);
                    holder.xiugai = convertView.findViewById(R.id.xiugai_text);*/
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
            holder.mcheckppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    docId = planBeanList.get(position).getDocId();
                    goPreview();
                }
            });
            //是在线制作的ppt有修改和查看功能吗，为授课完成跳哪，授课完成跳哪2
            holder.mdeleteppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(3, position);
                }
            });
            //RequestUtil.requestOtherPPT((Activity) context, holder.mppt, moduleId, 3);
            holder.mppt.setText(planBeanList.get(position).getDocName());
        }else if (itemViewType == TYPE_MAKE) {
            /*if (planBeanList.get(position).getp){

            }*/
            moduleId = planBeanList.get(position).getModuleId();
            final int courseId = planBeanList.get(position).getCourseId();
            holder.mmake.setText(planBeanList.get(position).getDocName());
            holder.mdeleteonline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deletePPT(7, position);
                }
            });
            holder.mcheckonline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, CommonWebActivity.class);
                    intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
                    intent.putExtra(Constants.COURSE_ID, courseId);
                    intent.putExtra(Constants.MODULE_ID, planBeanList.get(position).getModuleId());
                    context.startActivity(intent);
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
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "删除失败", Toast.LENGTH_LONG).show();
                    }
                });
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
                        PreparingPackageDetailActivity activity = (PreparingPackageDetailActivity) context;
                        if(null != activity){
                            activity.onPlanDelComplete();
                        }
                        }
                    });
                }
            }
        });
    }

    private void goPreview() {
        /*Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);*/
        Intent intent = new Intent(context, CommonWebActivity.class);
        //intent.putExtra("url", url);
        intent.putExtra(OPERATE_KEY, LOCAL);
        intent.putExtra("docId", docId);
        context.startActivity(intent);
    }

}
