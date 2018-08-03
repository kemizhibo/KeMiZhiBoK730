package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.adapter;

import android.content.Context;
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
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.teaching_lessons.bean.TeachingLessonsBean;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class TeachingLessonsListAdapter extends BaseAdapter {
    private Context context;
    private List<TeachingLessonsBean.ContentBean.DataBean> dataBeanList;
    private final int roleId;

    public TeachingLessonsListAdapter(Context context, List<TeachingLessonsBean.ContentBean.DataBean> dataBeanList) {
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
                holder.docName = convertView.findViewById(R.id.doc_name);
                holder.statusText = convertView.findViewById(R.id.status_text);
                holder.statusImg = convertView.findViewById(R.id.status_img);
                convertView.setTag(holder);
            }
        }else{
            holder = (CommonViewHolder) convertView.getTag();
        }

        TeachingLessonsBean.ContentBean.DataBean dataBean = dataBeanList.get(position);
        if(null != dataBean.getLogo()){
            /*GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
            GenericDraweeHierarchy FIT_XY = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
            holder.logo.setHierarchy(FIT_XY);*/
            holder.logo.setImageURI(Uri.parse((String) dataBean.getLogo()));
        }
        if(null != holder.childAccount)
            holder.childAccount.setText(dataBean.getPrepareName());
        holder.desc.setText(dataBean.getMaterialName() + " " + "(" + dataBean.getGradeName() + ("1".equals(dataBean.getSemester()) ? "上" : "下") + ")");
        holder.name.setText(dataBean.getCourseName());
        holder.time.setText(dataBean.getCreateTime());
        holder.docName.setText(dataBean.getDocName());
        if(holder.statusText != null)
            holder.statusText.setVisibility(View.GONE);
        if(holder.statusImg != null)
            holder.statusImg.setVisibility(View.GONE);

        return convertView;
    }
}
