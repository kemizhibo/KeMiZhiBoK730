package com.kemizhibo.kemizhibo.other.preparing_online.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_online.bean.PreparingOnlineBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/25.
 */

public class PreparingOnlineListAdapter extends BaseAdapter{

    private Context context;
    private List<PreparingOnlineBean.ContentBean.DataBean> dataBeanList;

    public PreparingOnlineListAdapter(Context context, List<PreparingOnlineBean.ContentBean.DataBean> dataBeanList) {
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
        PreparingOnlineListViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_preparing_online_list, null);
            holder = new PreparingOnlineListViewHolder();
            holder.logo = convertView.findViewById(R.id.logo);
            holder.name = convertView.findViewById(R.id.name);
            holder.go = convertView.findViewById(R.id.go);
            convertView.setTag(holder);
        }else{
            holder = (PreparingOnlineListViewHolder) convertView.getTag();
        }

        /*GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy FIT_XY = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
        holder.logo.setHierarchy(FIT_XY);*/
        if(!TextUtils.isEmpty(dataBeanList.get(position).getLogo())){
            holder.logo.setImageURI(Uri.parse(dataBeanList.get(position).getLogo()));
        }else{
            holder.logo.setImageURI(Uri.parse(Constants.TEST_IMAGE_URL));
        }
        holder.name.setText(dataBeanList.get(position).getCourseName());

        return convertView;
    }

    class PreparingOnlineListViewHolder{
        SimpleDraweeView logo;
        TextView name;
        LinearLayout go;
    }
}
