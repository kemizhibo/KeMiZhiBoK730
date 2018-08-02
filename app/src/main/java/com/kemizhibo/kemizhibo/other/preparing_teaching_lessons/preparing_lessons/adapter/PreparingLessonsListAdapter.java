package com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.CommonViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_teaching_lessons.preparing_lessons.bean.PreparingLessonsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/1.
 */

public class PreparingLessonsListAdapter extends BaseAdapter {
    private Context context;
    private List<PreparingLessonsBean.ContentBean.DataBean> dataBeanList;

    public PreparingLessonsListAdapter(Context context, List<PreparingLessonsBean.ContentBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
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
            convertView = View.inflate(context, R.layout.item_preparing_teaching_lessons_list, null);
            holder = new CommonViewHolder();
            holder.desc = convertView.findViewById(R.id.desc);
            holder.name = convertView.findViewById(R.id.name);
            holder.time = convertView.findViewById(R.id.time);
            holder.name2 = convertView.findViewById(R.id.name_2);
            holder.status = convertView.findViewById(R.id.status);
            holder.go = convertView.findViewById(R.id.go);
            convertView.setTag(holder);
        }else{
            holder = (CommonViewHolder) convertView.getTag();
        }

        PreparingLessonsBean.ContentBean.DataBean dataBean = dataBeanList.get(position);
        holder.desc.setText(dataBean.getMaterialName() + " " + dataBean.getGradeName() + " " + "(" + ("1".equals(dataBean.getSemester()) ? "上" : "下") + ")");
        holder.name.setText(dataBean.getCourseName());
        holder.time.setText(dataBean.getCreateTime());
        holder.name2.setText(dataBean.getPrepareName());
        holder.status.setText(dataBean.getPlanIsFinish() == 1 ? "已授课" : "未授课");
        holder.status.setTextColor(dataBean.getPlanIsFinish() == 1 ? Color.GRAY : Color.RED);

        return convertView;
    }
}
