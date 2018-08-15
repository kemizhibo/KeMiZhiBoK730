package com.kemizhibo.kemizhibo.other.preparing_package_detail.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingDocBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPPTBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPackageDetailBean;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.bean.PreparingPicBean;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

public class PreparingDetailNewAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
/*

    private PreparingPackageDetailBean.ContentBean contentBean;
    private Handler mHandler;
    // private List<PreparingPackageDetailBean.ContentBean.MaterialBean> material;
    private Context context;
    private int itemViewType;
    private int moduleId;
    private RelativeLayout rimage;
    private int courseId;


*/
/*    public PreparingDetailNewAdapter(Context context, List<PreparingPackageDetailBean.ContentBean.MaterialBean> material, Handler mHandler) {
        this.context = context;
        this.material = material;
        this.mHandler = mHandler;
    }*//*


    public PreparingDetailNewAdapter(Context context, PreparingPackageDetailBean.ContentBean contentBean, Handler mHandler) {
        this.context = context;
        this.contentBean = contentBean;
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        if (position == 0) {
            return contentBean.getOneKey().get(position);
        } else if (position == 1) {
            return contentBean.getMaterial().get(position);
        } else if (position == 2) {
            return contentBean.getPlan().get(position);
        }
        return contentBean.getOther().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        List<PreparingPackageDetailBean.ContentBean.OneKeyBean> oneKey = contentBean.getOneKey();//一键投递
        List<PreparingPackageDetailBean.ContentBean.MaterialBean> material = contentBean.getMaterial();//教学素材
        List<PreparingPackageDetailBean.ContentBean.PlanBean> plan = contentBean.getPlan();//教学方案
        List<?> other = contentBean.getOther();//其他

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.prearingdetail_new_item, null);
            holder.mtitle = (TextView) convertView.findViewById(R.id.title);
            holder.miv = (ImageView) convertView.findViewById(R.id.mimage);
            holder.mppt = (TextView) convertView.findViewById(R.id.mppt);
            holder.mwendang = (TextView) convertView.findViewById(R.id.mword);
            holder.jcVideoPlayer = (JZVideoPlayerStandard) convertView.findViewById(R.id.jc);
            holder.rimage = convertView.findViewById(R.id.rimage);
            holder.rppt = convertView.findViewById(R.id.rppt);
            holder.rword = convertView.findViewById(R.id.rword);
            holder.mnone = convertView.findViewById(R.id.none);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            holder.mtitle.setText("一键授课");
            holder.mtitle.setBackgroundColor(Color.GREEN);
            itemViewType = oneKey.get(position).getDocType();
            moduleId = oneKey.get(position).getModuleId();
            courseId = oneKey.get(position).getCourseId();
            Log.i("-itemViewType---", itemViewType + "");
            Log.i("-pptmoduleId---", moduleId + "");
            Log.i("-pptcourseId---", courseId + "");
            holder.mnone.setVisibility(View.GONE);
            show(holder, position);
            //  PreparingPackageDetailBean.ContentBean.MaterialBean materialBean = this.material.get(position);
        } else if (position == 1) {
            holder.mtitle.setText("教学素材");
            holder.mtitle.setBackgroundColor(Color.BLUE);
            itemViewType = material.get(position).getDocType();
            moduleId = material.get(position).getModuleId();
            courseId = material.get(position).getCourseId();
            show(holder, position);
        } else if (position == 2) {
            holder.mtitle.setBackgroundColor(Color.RED);
            holder.mtitle.setText("我的授课方案");
            itemViewType = plan.get(position).getDocType();
            moduleId = plan.get(position).getModuleId();
            courseId = plan.get(position).getCourseId();
            show(holder, position);
        } else if (position == 3) {
            holder.mtitle.setBackgroundColor(Color.YELLOW);
            holder.mtitle.setText("其他");
            holder.mnone.setVisibility(View.VISIBLE);
           */
/* holder.jcVideoPlayer.setVisibility(View.GONE);
            holder.rimage.setVisibility(View.GONE);
            holder.rppt.setVisibility(View.GONE);*//*

        }


        return convertView;
    }

    private void show(ViewHolder holder, int position) {
        switch (itemViewType) {
            case 1://文档
                holder.rword.setVisibility(View.VISIBLE);
                requestDoc(position, holder, itemViewType);
                break;
            case 3://ppt
                holder.rppt.setVisibility(View.VISIBLE);
                requestPPT(position, holder, itemViewType);
                break;
            case 6://图片
                holder.rimage.setVisibility(View.VISIBLE);
                requestPic(position, holder, itemViewType);
                break;
            case 5://视频
                holder.jcVideoPlayer.setVisibility(View.VISIBLE);
                requestVideo(position, holder);
                break;
            default:
                break;
        }
    }

    private void requestPPT(int position, final ViewHolder holder, final int itemViewType) {
        Map map = new HashMap();
        Log.i("-pptmoduleId---", moduleId + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(itemViewType));
        OkHttpRequest.doGet(context,OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("-pptjson---", response.body().string() + "");
                //  final PreparingDocBean bean = GsonUtils.getBean(response.body().string(), PreparingDocBean.class);

                // final PreparingPPTBean bean = GsonUtils.getBean(response.body().string(), PreparingPPTBean.class);
                //   Log.i("-code---", bean.getCode() + "");
             */
/*   if (null != bean && 0 == bean.getCode()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String docName = bean.getContent().getFileName();
                            if (itemViewType == 3) {//pppt
                                holder.mppt.setText(docName);
                            }

                        }
                    });
                }*//*



            }
        });
    }

    private void requestDoc(int position, final ViewHolder holder, final int itemViewType) {
        Map map = new HashMap();
        Log.i("-moduleId---", moduleId + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(itemViewType));
        OkHttpRequest.doGet(context,OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //  Log.i("-json---", response.body().string() + "");
                //  final PreparingDocBean bean = GsonUtils.getBean(response.body().string(), PreparingDocBean.class);

                final PreparingDocBean bean = GsonUtils.getBean(response.body().string(), PreparingDocBean.class);
                Log.i("-code---", bean.getCode() + "");
                if (null != bean && 0 == bean.getCode()) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String docName = bean.getContent().getDocName();
                            holder.mwendang.setText(docName);
                        }
                    });
                }


            }
        });
    }


    private void requestPic(int position, final ViewHolder holder, final int itemViewType) {
        Map map = new HashMap();
        // Log.i("-moduleId---", material.get(position).getModuleId() + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(itemViewType));
        OkHttpRequest.doGet(context,OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //    Log.i("-json---", response.body().string() + "");
                final PreparingPicBean picbean = GsonUtils.getBean(response.body().string(), PreparingPicBean.class);
                Log.i("-code---", picbean.getCode() + "");
                if (null != picbean && 0 == picbean.getCode()) {
                    String url = picbean.getContent().getUrl();

                   // final Bitmap bitmap = getURLimage(Constants.TEST_IMAGE_URL);
                    final Bitmap bitmap = getURLimage(url);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            holder.miv.setImageBitmap(bitmap);
                        }
                    });
                }


            }
        });
    }

    //加载图片
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private void requestVideo(int position, final ViewHolder holder) {
        Map map = new HashMap();
        //  Log.i("-courseid---", material.get(position).getCourseId() + "");
        map.put(Constants.COURSE_ID, String.valueOf(courseId));
        map.put(Constants.ENCRYPTION, "true");
        map.put(Constants.VIDEOTYPE, "HLS");
        map.put(Constants.VIDEOCLARITY, "HD");
        OkHttpRequest.doGet(context,OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_VIDEO_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                PreparingBean bean = GsonUtils.getBean(response.body().string(), PreparingBean.class);
                Log.i("-videocode---", bean.getCode() + "");
                //2832课程无章节
                //   if (null != bean && 0 == bean.getCode()) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.jcVideoPlayer.setUp(Constants.TEST_VIDEO_URL
                                , 1, "");
                        holder.jcVideoPlayer.thumbImageView.setImageResource(R.drawable.ic_launcher);
                    }
                });
                //  }
            }
        });
    }

    static class ViewHolder {
        public TextView mppt, mnone;
        public TextView mtitle;
        public JZVideoPlayerStandard jcVideoPlayer;
        public TextView mwendang;
        public ImageView miv;
        public RelativeLayout rimage, rppt, rword;
    }
*/


}
