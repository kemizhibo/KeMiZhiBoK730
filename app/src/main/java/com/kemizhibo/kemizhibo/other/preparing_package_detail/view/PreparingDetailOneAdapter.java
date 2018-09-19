package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.MyViewHolder;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.RequestUtil;
import com.kemizhibo.kemizhibo.other.utils.DownloadUtil;
import com.kemizhibo.kemizhibo.other.web.CommonWebActivity;
import com.kemizhibo.kemizhibo.yhr.utils.ToastUtils;
import com.kemizhibo.kemizhibo.yhr.utils.UIUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import static com.kemizhibo.kemizhibo.other.web.CommonWebActivity.LOCAL;
import static com.kemizhibo.kemizhibo.other.web.CommonWebActivity.OPERATE_KEY;

/**
 * Created by asus on 2018/8/1.
 */

public class PreparingDetailOneAdapter extends BaseAdapter {

    private final List<PreparingPackageDetailBean.ContentBean.OneKeyBean> oneKeyBeanList;
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

    public PreparingDetailOneAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.OneKeyBean> oneKeyBeanList) {
        this.context = context;
        this.oneKeyBeanList = oneKeyBeanList;
    }


    @Override
    public int getCount() {
        return oneKeyBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return oneKeyBeanList.get(position);
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
        Log.i("---oneKeyBeanList--", oneKeyBeanList.get(position).getDocType() + "");
        if (oneKeyBeanList.get(position).getDocType() == 1) {
            return TYPE_WENDANG;
        } else if (oneKeyBeanList.get(position).getDocType() == 3) {
            return TYPE_PPT;
        } else if (oneKeyBeanList.get(position).getDocType() == 5) {
            return TYPE_SHIPIN;
        } else if (oneKeyBeanList.get(position).getDocType() == 7) {
            return TYPE_MAKE;
        } else if (oneKeyBeanList.get(position).getDocType() == 6) {
            return TYPE_PUPIAN;
        }

        return 10;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Log.i("---onekeysize--", oneKeyBeanList.size() + "");
        // 获取当前条目的类型
        int itemViewType = getItemViewType(position);
        MyViewHolder holder = null;
        // PreparingPackageDetailBean.ContentBean.MaterialBean materialBean = material.get(position);
        if (convertView == null) {
            switch (itemViewType) {
                case TYPE_WENDANG://文档
                    holder = new MyViewHolder();
                    convertView = View.inflate(context, R.layout.wendang_item, null);
                    holder.mwendang = (TextView) convertView.findViewById(R.id.mword);
                    holder.mcheck = (TextView) convertView.findViewById(R.id.mcheck);
                    holder.mdown = (TextView) convertView.findViewById(R.id.mdown);
                    convertView.setTag(holder);
                    break;
                case TYPE_PPT://ppt
                    holder = new MyViewHolder();
                    convertView = View.inflate(context, R.layout.ppt_item, null);
                    holder.icon = convertView.findViewById(R.id.tianjia_icon);
                    holder.mppt = (TextView) convertView.findViewById(R.id.mppt);
                    holder.mcheckppt = (TextView) convertView.findViewById(R.id.mcheckppt);
                    holder.mdownppt = (TextView) convertView.findViewById(R.id.mdownppt);
                    convertView.setTag(holder);
                    break;
                case TYPE_PUPIAN://图片
                    holder = new MyViewHolder();
                    convertView = View.inflate(context, R.layout.tupian_item, null);
                    holder.miv = (SimpleDraweeView) convertView.findViewById(R.id.mimage);
                    holder.madjsucai = (TextView) convertView.findViewById(R.id.adj);
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    convertView.setTag(holder);
                    break;
                case TYPE_SHIPIN://视频
                    holder = new MyViewHolder();
                    convertView = View.inflate(context, R.layout.shipin_item, null);
                    holder.jcVideoPlayer = (JZVideoPlayerStandard) convertView.findViewById(R.id.jc);
                    holder.madj = (TextView) convertView.findViewById(R.id.adj);
                    holder.mbtn = (Button) convertView.findViewById(R.id.btn);
                    holder.title = (TextView) convertView.findViewById(R.id.title_111);
                    convertView.setTag(holder);
                    break;
               /* case TYPE_MAKE://在线制作
                    convertView = View.inflate(context, R.layout.make_item, null);
                    holder.mmake = (TextView) convertView.findViewById(R.id.makeadj);
                    convertView.setTag(holder);
                    break;*/
                default:
                    break;
            }
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        final MyViewHolder myViewHolder = holder;
        Log.i("---itemViewType--", "" + itemViewType);
        if (itemViewType == TYPE_SHIPIN) {
            courseId = oneKeyBeanList.get(position).getCourseId();
            moduleId = oneKeyBeanList.get(position).getModuleId();
            //RequestUtil.requestVideo((Activity) context, position, holder, courseId);
            holder.madj.setText(TextUtils.isEmpty(oneKeyBeanList.get(position).getVideoIntroduce()) ? "暂无" : oneKeyBeanList.get(position).getVideoIntroduce());
            /*holder.jcVideoPlayer.setUp(oneKeyBeanList.get(position).getUrl()
                    , 1, "");*/
            getPlayUrl(context, holder.jcVideoPlayer, courseId, oneKeyBeanList.get(position).getKpointId());
            if (!TextUtils.isEmpty(oneKeyBeanList.get(position).getVideoLogo())||oneKeyBeanList.get(position).getVideoLogo()!=null){
                holder.jcVideoPlayer.thumbImageView.setImageURI(Uri.parse(oneKeyBeanList.get(position).getVideoLogo()));
            }
            loadLogo(oneKeyBeanList.get(position).getVideoLogo(), holder.jcVideoPlayer);
            holder.title.setText(oneKeyBeanList.get(position).getDocName());
            holder.mbtn.setVisibility(View.GONE);
            /*holder.mbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommonWebActivity.class);
                    intent.putExtra(CommonWebActivity.OPERATE_KEY, CommonWebActivity.MAKE);
                    intent.putExtra(Constants.COURSE_ID, courseId);
                    intent.putExtra(Constants.MODULE_ID, moduleId);
                    context.startActivity(intent);
                }
            });*/
        } else if (itemViewType == TYPE_PPT) {
            if(2 == oneKeyBeanList.get(position).getIsRepeatAdd()){
                holder.icon.setImageResource(R.drawable.yitianjia);
            }
            final ImageView icon2 = holder.icon;
            moduleId = oneKeyBeanList.get(position).getModuleId();
            //RequestUtil.requestPPT((Activity) context, holder.mppt, holder.mcheckppt, holder.mdownppt, 3, moduleId);
            holder.mppt.setText(oneKeyBeanList.get(position).getDocName());

            holder.mcheckppt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //isjump = true;
                    goPreview(String.valueOf(oneKeyBeanList.get(position).getDocId()));
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(oneKeyBeanList.get(position).getDocId()), holder.mcheck, 3, true);
                }
            });
            holder.mdownppt.setText(2 == oneKeyBeanList.get(position).getIsRepeatAdd() ? "已添加" : "加入授课");
            if(2 != oneKeyBeanList.get(position).getIsRepeatAdd()){
                holder.mdownppt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestUtil.requestSuCaiAdd((Activity) context, oneKeyBeanList.get(position).getCourseId(), oneKeyBeanList.get(position).getDocName(), 3, oneKeyBeanList.get(position).getContentIds(), icon2, myViewHolder.mdownppt);
                    }
                });
            }
        } else if (itemViewType == TYPE_WENDANG) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
            //RequestUtil.requestDoc((Activity) context, holder.mwendang, holder.mdown, holder.mcheck, 1, moduleId);
            holder.mwendang.setText(oneKeyBeanList.get(position).getDocName());
            holder.mdown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ToastUtils.showToast("开始下载");
                    //   if (url != null) {
                    DownloadUtil.get().download(String.valueOf(oneKeyBeanList.get(position).getUrl()), "KemiDownload", new DownloadUtil.OnDownloadListener() {
                        @Override
                        public void onDownloadSuccess() {
                            Activity activity = (Activity) context;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    myViewHolder.mdown.setText("已下载");
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
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(oneKeyBeanList.get(position).getDocId()), holder.mdown, 1, false);
                    //  }
                }
            });
            holder.mcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //isjump = true;

                    goPreview(String.valueOf(oneKeyBeanList.get(position).getDocId()));
                    //RequestUtil.getDocMessage((Activity) context, String.valueOf(oneKeyBeanList.get(position).getDocId()), holder.mcheck, 1, true);
                }
            });
        } else if (itemViewType == TYPE_PUPIAN) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
           /* RequestUtil.requestPic((Activity) context, holder, itemViewType, moduleId, picurls);
            holder.jcVideoPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("positionclick==", "" + position);
                }
            });*/
        }
       /* else if (itemViewType == TYPE_MAKE) {
            moduleId = oneKeyBeanList.get(position).getModuleId();
            RequestUtil.requestDoc((Activity) context, holder, itemViewType, moduleId, TYPE_MAKE);
        }*/
        return convertView;
    }

    private void getPlayUrl(final Context context, final JZVideoPlayerStandard jcVideoPlayer, int courseId, int kpointId) {
        Map params = new HashMap();
        params.put(Constants.COURSE_ID, String.valueOf(courseId));
        params.put("videoType", "HLS");
        params.put("encryption", String.valueOf(true));
        params.put("videoClarity", "HD");
        params.put(Constants.KPOINT_ID, String.valueOf(kpointId));
        String s = OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_VIDEO_URL, params);
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_VIDEO_URL, params), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "获取视频播放地址失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                /*String string = response.body().string();
                Log.d("PreparingDetailOneAdapt", string);*/
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int code = jsonObject.optInt("code");
                    if(0 == code){
                        final String url = jsonObject.optString("content");
                        if(null != url){
                            UIUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(null != context){
                                        jcVideoPlayer.setUp(url, 1, "");
                                    }
                                }
                            });
                        }else{
                            UIUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(null != context){
                                        Toast.makeText(context, "获取视频播放地址失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }else{
                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(null != context){
                                    Toast.makeText(context, "获取视频播放地址失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadLogo(final String logo, final JZVideoPlayerStandard jzVideoPlayerStandard) {
        if(null == logo){
            return;
        }
        new AsyncTask<Void, Void, Bitmap>(){

            @Override
            protected Bitmap doInBackground(Void... voids) {
                try {
                    URL loadUrl = new URL(logo);
                    HttpURLConnection connection = (HttpURLConnection) loadUrl.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200){
                        return BitmapFactory.decodeStream(connection.getInputStream());
                    }else{
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(final Bitmap bitmap) {
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(null != context && null != jzVideoPlayerStandard){
                            jzVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            jzVideoPlayerStandard.thumbImageView.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        }.execute();
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
