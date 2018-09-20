package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.utils.DownloadUtil;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.utils.LogUtils;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;

import java.io.File;
import java.util.List;
import static com.kemizhibo.kemizhibo.other.web.CommonWebActivity.LOCAL;
import static com.kemizhibo.kemizhibo.other.web.CommonWebActivity.OPERATE_KEY;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailOtherAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.OtherBean> otherBeanList;
    private Handler mHandler;

    private Context context;

    //定义样式常量，注意常量值要从0开始
    private static final int TYPE_PPT = 1;//ppt
    private static final int TYPE_excel = 0;//excal
    private static final int TYPE_WORD = 2;//excal
    private int courseId;
    private int moduleId;

    public PreparingDetailOtherAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.OtherBean> otherBeanList) {
        this.context = context;
        this.otherBeanList = otherBeanList;
    }


    @Override
    public int getCount() {
        return otherBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("-----", otherBeanList.get(position).getDocType() + "");
        if (otherBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        }else  if (otherBeanList.get(position).getDocType() == 1) {
            return TYPE_WORD;
        }

        return TYPE_excel;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("---otherBeanListsize--", otherBeanList.size() + "");
        // 获取当前条目的类型
        int itemViewType = getItemViewType(position);
        final MyViewHolder holder;
        //ImageView icon = null;
        if (convertView == null) {
            holder = new MyViewHolder();
            switch (itemViewType) {
                case TYPE_excel://表格
                    convertView = View.inflate(context, R.layout.wendang_item, null);
                    holder.mwendang = (TextView) convertView.findViewById(R.id.mword);
                    holder.mcheck = (TextView) convertView.findViewById(R.id.mcheck);
                    holder.mdown = (TextView) convertView.findViewById(R.id.mdown);
                    convertView.setTag(holder);
                    break;
                case TYPE_PPT://ppt
                    convertView = View.inflate(context, R.layout.ppt_item, null);
                    holder.icon = convertView.findViewById(R.id.tianjia_icon);
                    holder.mppt = (TextView) convertView.findViewById(R.id.mppt);
                    holder.mcheckppt = (TextView) convertView.findViewById(R.id.mcheckppt);
                    holder.mdownppt1 = (TextView) convertView.findViewById(R.id.mdownppt);
                    convertView.setTag(holder);
                    break;
                case TYPE_WORD://文档
                    convertView = View.inflate(context, R.layout.wendang_item, null);
                    holder.mwendangother = (TextView) convertView.findViewById(R.id.mword);
                    holder.mcheck = (TextView) convertView.findViewById(R.id.mcheck);
                    holder.mdown = (TextView) convertView.findViewById(R.id.mdown);
                    convertView.setTag(holder);
                    break;
                default:
                    break;
            }
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        Log.i("---otheritemViewType--", "" + itemViewType);
        if (itemViewType == TYPE_PPT) {
            if(2 == otherBeanList.get(position).getIsRepeatAdd()){
                holder.icon.setImageResource(R.drawable.yitianjia);
            }else{
                holder.icon.setImageResource(R.drawable.tianjia);
            }
            holder.mdownppt1.setText(2 == otherBeanList.get(position).getIsRepeatAdd() ? "已添加" : "加入授课");
            final ImageView icon2 = holder.icon;
            moduleId = otherBeanList.get(position).getModuleId();
            //RequestUtil.requestOtherPPT((Activity) context, holder.mppt, 3, moduleId);
            holder.mppt.setText(otherBeanList.get(position).getDocName());
            if(2 != otherBeanList.get(position).getIsRepeatAdd()){
                holder.mdownppt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("----====finalCourseId",otherBeanList.get(position).getCourseId()+"");
                        Log.i("----====finalDocName",otherBeanList.get(position).getDocName()+"");
                        Log.i("----====finalContentIds",otherBeanList.get(position).getContentIds()+"");
                        //.showToast("开始加入授课");
                        RequestUtil.requestSuCaiAdd((Activity)context, otherBeanList.get(position).getCourseId(), otherBeanList.get(position).getDocName(),3,otherBeanList.get(position).getContentIds(), icon2, holder.mdownppt1);
                    }
                });
            }
            holder.mcheckppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goPreview(otherBeanList.get(position).getDocId());
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(otherBeanList.get(position).getDocId()), holder.mcheck, 3, true);
                }
            });
        } else if (itemViewType == TYPE_excel) {
            moduleId = otherBeanList.get(position).getModuleId();
            //RequestUtil.requestDoc((Activity) context, holder.mwendang, holder.mdown, holder.mcheck, 2, moduleId);
            /*
            holder.mwendang
                    holder.mcheck
                    holder.mdown
             */
            holder.mwendang.setText(otherBeanList.get(position).getDocName());
            holder.mdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast("开始下载");
                    //   if (url != null)
                    // RequestUtil.getDocMessage((Activity) context, String.valueOf(otherBeanList.get(position).getDocId()), holder.mdown, 2, false);
                    //  }
                    LogUtils.d("PreparingDetailOtherAda", "url:" + otherBeanList.get(position).getUrl());
                    DownloadUtil.get().download(otherBeanList.get(position).getUrl(), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                        @Override
                        public void onDownloadSuccess(final File file) {
                            final Activity activity = (Activity) context;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    holder.mdown.setText("已下载");
                                    Uri uri = Uri.fromFile(file);
                                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                                    activity.sendBroadcast(intent);
                                }
                            });
                        }

                        @Override
                        public void onDownloading(int progress) {

                        }

                        @Override
                        public void onDownloadFailed() {

                        }
                    });
                }
            });
            holder.mcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //isjump = true;
                    otherBeanList.get(position).getCourseId();
                    goPreview(otherBeanList.get(position).getDocId());
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(otherBeanList.get(position).getDocId()), holder.mcheck, 2, true);
                }
            });
        }else if (itemViewType == TYPE_WORD) {
            moduleId = otherBeanList.get(position).getModuleId();
            //RequestUtil.requestDoc((Activity) context, holder.mwendangother, holder.mdown, holder.mcheck, 1, moduleId);
            /*
            holder.mwendangother
                    holder.mcheck
                    holder.mdown
             */
            holder.mwendangother.setText(otherBeanList.get(position).getDocName());
            holder.mdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast("开始下载");
                    //   if (url != null) {
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(otherBeanList.get(position).getDocId()), holder.mdown, 1, false);
                    //  }
                    DownloadUtil.get().download(otherBeanList.get(position).getUrl(), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                        @Override
                        public void onDownloadSuccess(final File file) {
                            final Activity activity = (Activity) context;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    holder.mdown.setText("已下载");
                                    Uri uri = Uri.fromFile(file);
                                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                                    activity.sendBroadcast(intent);
                                }
                            });
                        }

                        @Override
                        public void onDownloading(int progress) {

                        }

                        @Override
                        public void onDownloadFailed() {

                        }
                    });
                }
            });
            holder.mcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //isjump = true;\
                    goPreview(otherBeanList.get(position).getDocId());
                    //LogUtils.i("DocId", "mkel.getDocId():" + otherBeanList.get(position).getDocId());
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(otherBeanList.get(position).getDocId()), holder.mcheck, 1, true);
                }
            });
        }
        return convertView;
    }
    private void goPreview(String docId) {
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
