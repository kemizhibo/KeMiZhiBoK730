package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.CommonViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class PreparingLessonsListAdapter extends BaseAdapter {
    private Context context;
    private List<PreparingLessonsBean.ContentBean.DataBean> dataBeanList;
    private final int roleId;

    public PreparingLessonsListAdapter(Context context, List<PreparingLessonsBean.ContentBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        roleId = PreferencesUtils.getIntValue(Constants.ROLE_ID, context);
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder;
        if(convertView == null){
            if(roleId == Constants.MANAGER_ROLE_ID){
                convertView = View.inflate(context, R.layout.item_manager_preparing_teaching_lessons_list, null);
                holder = new CommonViewHolder();
                holder.childAccount = convertView.findViewById(R.id.child_account);
                holder.logo = convertView.findViewById(R.id.logo);
                holder.desc = convertView.findViewById(R.id.desc);
                holder.name = convertView.findViewById(R.id.name);
                holder.time = convertView.findViewById(R.id.time);
                holder.docName = convertView.findViewById(R.id.doc_name);
                convertView.setTag(holder);
            }else{
                convertView = View.inflate(context, R.layout.item_preparing_teaching_lessons_list, null);
                holder = new CommonViewHolder();
                holder.logo = convertView.findViewById(R.id.logo);
                holder.desc = convertView.findViewById(R.id.desc);
                holder.name = convertView.findViewById(R.id.name);
                holder.time = convertView.findViewById(R.id.time);
                holder.statusImg = convertView.findViewById(R.id.status_img);
                holder.statusText = convertView.findViewById(R.id.status_text);
                holder.docName = convertView.findViewById(R.id.doc_name);
                convertView.setTag(holder);
            }
        }else{
            holder = (CommonViewHolder) convertView.getTag();
        }

        PreparingLessonsBean.ContentBean.DataBean dataBean = dataBeanList.get(position);
        if(null != dataBean.getLogo()){
            /*GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
            GenericDraweeHierarchy FIT_XY = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
            holder.logo.setHierarchy(FIT_XY);*/
            holder.logo.setImageURI(Uri.parse((String) dataBean.getLogo()));
        }
        if(null != holder.childAccount)
            holder.childAccount.setText(dataBean.getPrepareName());
        holder.desc.setText(dataBean.getMaterialName() + " "  + "(" + dataBean.getGradeName() + ("1".equals(dataBean.getSemester()) ? "上" : "下") + ")");
        holder.name.setText((String) dataBean.getCourseName());
        String time = dataBean.getCreateTime();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(dataBean.getCreateTime());
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String format = dateFormat2.format(date);
            time = format;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.time.setText(time);
        holder.docName.setText(dataBean.getDocName());
        if(null != holder.statusText)
            holder.statusText.setText(dataBean.getPlanIsFinish() == 1 ? "未授课" : "已授课");
        if(null != holder.statusText)
            holder.statusText.setTextColor(dataBean.getPlanIsFinish() == 1 ? Color.GRAY : context.getResources().getColor(R.color.filter_text_select));
        if(null != holder.statusImg)
            holder.statusImg.setImageResource(dataBean.getPlanIsFinish() == 1 ? R.drawable.no_teach : R.drawable.already_teach);

        return convertView;
    }
}
