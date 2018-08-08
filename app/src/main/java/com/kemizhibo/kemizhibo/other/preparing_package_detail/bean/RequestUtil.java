package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.downloader.DocDownloadManager;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.SampleObserver;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.WordShowActivity;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by asus on 2018/8/6.
 */

public class RequestUtil {

    private static boolean isjump = false;
    private static BDocInfo docInfo;
    private static TextView id;
    private static int type;

    public static void requestPPT(final Activity context, final MyViewHolder holder, final int doctype, int moduleId) {
        Map map = new HashMap();
        Log.i("-pptmoduleId---", moduleId + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(doctype));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //  Log.i("-pptjson---", response.body().string() + "");
                final PreparingPPTBean bean = GsonUtils.getBean(response.body().string(), PreparingPPTBean.class);
                Log.i("-pptcode---", bean.getCode() + "");
                if (null != bean && 0 == bean.getCode()) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String docName = bean.getContent().getFileName();
                            Log.i("-pptdocName---", docName + "");
                            holder.mppt.setText(docName);

                            holder.mcheckppt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    isjump = true;
                                    getDocMessage(context, bean.getContent().getDocId(), holder, 3);
                                }
                            });
                            holder.mdownppt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getDocMessage(context, bean.getContent().getDocId(), holder, 3);

                                }
                            });


                        }
                    });
                }


            }
        });
    }

    public static void requestDoc(final Activity context, final MyViewHolder holder, final int itemViewType, int moduleId, final int type) {
        Map map = new HashMap();
        Log.i("-moduleId---", moduleId + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(itemViewType));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //  Log.i("-json---", response.body().string() + "");
                //  final PreparingDocBean bean = GsonUtils.getBean(response.body().string(), PreparingDocBean.class);

                final PreparingWordBen bean = GsonUtils.getBean(response.body().string(), PreparingWordBen.class);
                Log.i("-code---", bean.getCode() + "");
                if (null != bean && 0 == bean.getCode()) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String docName = bean.getContent().getFileName();
                            final String url = bean.getContent().getUrl();
                            Log.i("-code---", bean.getCode() + "");
                            Log.i("-docurl---", bean.getContent().getUrl() + "");
                            if (type == 4) {
                                holder.mmake.setText(docName);
                            } else if (type == 1) {
                                holder.mwendang.setText(docName);
                            }
                            holder.mdown.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (url != null) {
                                        getDocMessage(context, bean.getContent().getDocId(), holder, 1);
                                    }
                                }
                            });
                            holder.mcheck.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    isjump = true;
                                    getDocMessage(context, bean.getContent().getDocId(), holder, 1);
                                }
                            });
                        }
                    });
                }


            }
        });
    }

    public static void getDocMessage(final Activity context, String docId, final MyViewHolder holder, final int i) {
        Map map = new HashMap();
        //  map.put("docId", docId);
        map.put("docId", "doc-ifrmt6px98u387u");
        Log.i("==docid===", docId);
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOCMESSAGE_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("-docjson---", string);
                final PreviewBean previewBean = GsonUtils.getBean(string, PreviewBean.class);
                if (null != previewBean) {
                    String host = previewBean.getHost();
                    String token = previewBean.getToken();
                    int pageCount = previewBean.getPageCount();
                    String title = previewBean.getTitle();
                    String documentId = previewBean.getDocumentId();
                    String docs = "";
                    if (i == 1) {
                        docs = "doc";
                        id = holder.mdown;
                        type = 1;
                    } else if (i == 3) {
                        docs = "pptx";
                        id = holder.mdownppt;
                        type = 3;
                    }
                    docInfo = new BDocInfo(host, documentId, docs, token)
                            .setLocalFileDir("")
                            .setTotalPage(pageCount)
                            .setDocTitle(previewBean.getTitle())
                            .setStartPage(1);


                    MyPreviewBean doc = new MyPreviewBean(host, documentId, docs, token, "", pageCount, title, 1);

                    if (isjump) {
                        Intent intent = new Intent(context, WordShowActivity.class);
                        intent.putExtra("word", doc);
                        Log.i("----", "zhanshi");
                        context.startActivity(intent);
                        isjump = false;
                    } else {
                        Log.i("----", "xiazai");
                        SampleObserver observer = new SampleObserver(id, docInfo, type);
                        DocDownloadManager.getInstance(context, "mydoc")
                                .startOrResumeDownloader(docInfo.getDocId(),
                                        docInfo.getToken(),
                                        docInfo.getHost(),
                                        observer);

                    }
                }


            }
        });
    }

    public static void requestPic(final Activity context, final MyViewHolder holder, final int itemViewType, int moduleId) {
        Map map = new HashMap();
        Log.i("-picmoduleId---", moduleId + "");
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(itemViewType));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //    Log.i("-json---", response.body().string() + "");
                final PreparingPicBean picbean = GsonUtils.getBean(response.body().string(), PreparingPicBean.class);
                Log.i("-piccode---", picbean.getCode() + "");
                if (null != picbean && 0 == picbean.getCode()) {
                    String url = picbean.getContent().getUrl();
                    Log.i("-picurl---", url + "");
                    Log.i("-picmiaoshu---", picbean.getContent().getFileName() + "");
                    final Uri parse = Uri.parse(Constants.TEST_IMAGE_URL);
                    // final Uri parse = Uri.parse(url);
                    final Bitmap bitmap = getURLimage(Constants.TEST_IMAGE_URL);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder.miv.setImageURI(parse);
                            if (picbean.getContent().getIntroduce() != null) {
                                holder.madjsucai.setText(picbean.getContent().getFileName());
                            }
                        }
                    });
                }


            }
        });
    }

    //加载图片
    public static Bitmap getURLimage(String url) {
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

    public static void requestVideo(final Activity context, int position, final MyViewHolder holder, int courseId) {
        Map map = new HashMap();
        //  Log.i("-courseid---", material.get(position).getCourseId() + "");
        map.put(Constants.COURSE_ID, String.valueOf(courseId));
        map.put(Constants.ENCRYPTION, "true");
        map.put(Constants.VIDEOTYPE, "HLS");
        map.put(Constants.VIDEOCLARITY, "HD");
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_VIDEO_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PreparingBean bean = GsonUtils.getBean(response.body().string(), PreparingBean.class);
                Log.i("-videocode---", bean.getCode() + "");
                Log.i("-videgetMessage---", bean.getMessage() + "");
                //2832课程无章节
                //   if (null != bean && 0 == bean.getCode()) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        holder.madj.setText(bean.getMessage());
                        holder.jcVideoPlayer.setUp(Constants.TEST_VIDEO_URL
                                , 1, "");
                        holder.jcVideoPlayer.thumbImageView.setImageResource(R.drawable.ic_launcher);
                    }
                });
                //  }
            }
        });
    }

}