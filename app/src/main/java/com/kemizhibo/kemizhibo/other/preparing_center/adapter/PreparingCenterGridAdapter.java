package com.kemizhibo.kemizhibo.other.preparing_center.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.preparing_center.bean.PreparingCenterBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/25.
 */

public class PreparingCenterGridAdapter extends BaseAdapter {
    private Context context;
    private List<PreparingCenterBean.ContentBean.DataBean> dataBeanList;

    public PreparingCenterGridAdapter(Context context, List<PreparingCenterBean.ContentBean.DataBean> dataBeanList) {
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
        PreparingCenterGridViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_preparing_center_grid, null);
            holder = new PreparingCenterGridViewHolder();
            holder.image = convertView.findViewById(R.id.image);
            holder.name = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else{
            holder = (PreparingCenterGridViewHolder) convertView.getTag();
        }

        if(!TextUtils.isEmpty(dataBeanList.get(position).getLogo())){
            holder.image.setImageURI(Uri.parse(dataBeanList.get(position).getLogo()));
        }else{
            holder.image.setImageURI(Uri.parse(Constants.TEST_IMAGE_URL));
        }
        holder.name.setText(dataBeanList.get(position).getCourseName());

        return convertView;
    }

    class PreparingCenterGridViewHolder{
        SimpleDraweeView image;
        TextView name;
    }

}
