package com.kemizhibo.kemizhibo.other.preparing_online.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.common.bean.CommonUserTeachPlanBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2018/8/13.
 */

public class PlanListAdapter extends BaseAdapter {
    private Context context;
    private List<CommonUserTeachPlanBean.ContentBean> planBeanList;
    private PlanListAdapterCallBack callBack;

    public PlanListAdapter(Context context, List<CommonUserTeachPlanBean.ContentBean> planBeanList) {
        this.context = context;
        this.planBeanList = planBeanList;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PlanListViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_plan_list, null);
            holder = new PlanListViewHolder();
            holder.checkBox = convertView.findViewById(R.id.check_box);
            holder.planName = convertView.findViewById(R.id.plan_name);
            convertView.setTag(holder);
        }else{
            holder = (PlanListViewHolder) convertView.getTag();
        }

        holder.checkBox.setChecked(planBeanList.get(position).isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != callBack){
                    callBack.onCheckBoxClick(holder.checkBox.isChecked(), position);
                }
            }
        });
        holder.planName.setText(planBeanList.get(position).getDocName());

        return convertView;
    }

    class PlanListViewHolder{
        CheckBox checkBox;
        TextView planName;
    }

    public interface PlanListAdapterCallBack{
        void onCheckBoxClick(boolean isChecked, int position);
    }

    public void setPlanListAdapterCallBack(PlanListAdapterCallBack callBack){
        this.callBack = callBack;
    }

}
