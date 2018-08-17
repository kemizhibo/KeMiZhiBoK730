package com.kemizhibo.kemizhibo.other.preparing_package_detail.bean;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.downloader.DocDownloadManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kemizhibo.kemizhibo.R;
import com.kemizhibo.kemizhibo.other.config.Constants;
import com.kemizhibo.kemizhibo.other.config.OkHttpRequest;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.MyFragment;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.MyViewpagerAdapter;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.SampleObserver;
import com.kemizhibo.kemizhibo.other.preparing_package_detail.view.WordShowActivity;
import com.kemizhibo.kemizhibo.other.utils.GsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JZVideoPlayerStandard;
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
    private static MyViewpagerAdapter adapter;
    private static MyViewpagerAdapter myViewpagerAdapter;


    public static void requestPPT(final Activity context, final TextView mppt, final TextView mcheck, final TextView mdownppt, final int doctype, int moduleId) {
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
                            mppt.setText(docName);

                            mcheck.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    isjump = true;
                                    getDocMessage(context, bean.getContent().getDocId(), mcheck, 3);
                                }
                            });
                            mdownppt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getDocMessage(context, bean.getContent().getDocId(), mdownppt, 3);

                                }
                            });


                        }
                    });
                }


            }
        });
    }
    public static void requestOtherPPT(final Activity context, final TextView mppt, int moduleId,final int doctype) {
        Map map = new HashMap();
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(doctype));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PreparingPPTBean bean = GsonUtils.getBean(response.body().string(), PreparingPPTBean.class);
                if (null != bean && 0 == bean.getCode()) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String docName = bean.getContent().getFileName();
                            Log.i("-pptdocName---", docName + "");
                            mppt.setText(docName);
                        }
                    });
                }


            }
        });
    }

    public static void requestDoc(final Activity context, final TextView mwendang, final TextView mdown, final TextView mcheck, final int itemViewType, int moduleId, final int type) {
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
                           /* if (type == 4) {
                                holder.mmake.setText(docName);
                            } else if (type == 1) {
                                holder.mwendang.setText(docName);
                            }*/
                            mwendang.setText(docName);
                            mdown.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (url != null) {
                                        getDocMessage(context, bean.getContent().getDocId(), mdown, 1);
                                    }
                                }
                            });
                            mcheck.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    isjump = true;
                                    getDocMessage(context, bean.getContent().getDocId(), mcheck, 1);
                                }
                            });
                        }
                    });
                }


            }
        });
    }

    public static void getDocMessage(final Activity context, String docId, final TextView holder, final int i) {
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
                        id = holder;
                        type = 1;
                    } else if (i == 3) {
                        docs = "pptx";
                        id = holder;
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

    public static void requestPic(final Activity context, final MyViewHolder holder, final int itemViewType, int moduleId, final List<String> picurls) {
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
                final PreparingPicBean picbean = GsonUtils.getBean(response.body().string(), PreparingPicBean.class);
                Log.i("-piccode---", picbean.getCode() + "");
                if (null != picbean && 0 == picbean.getCode()) {
                    String url = picbean.getContent().getUrl();
                    Log.i("-picurl---", url + "");
                    Log.i("-picmiaoshu---", picbean.getContent().getFileName() + "");
                    final Uri parse = Uri.parse(Constants.TEST_IMAGE_URL);
                    // final Uri parse = Uri.parse(url);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            picurls.add(Constants.TEST_IMAGE_URL);
                            Log.i("-picurls---", picurls.size() + "");
                            if (null == adapter) {
                                // adapter = new MyViewpagerAdapter(MyApplication.getContext(), picurls);
                                holder.mviewPager.setAdapter(adapter);
                            } else {
                                adapter.notifyDataSetChanged();

                            }
                            if (picbean.getContent().getIntroduce() != null) {
                                holder.madjsucai.setText(picbean.getContent().getFileName());
                            }
                        }
                    });
                }


            }
        });
    }

    public static List<String> mpicurls = new ArrayList<>();
    public static List<String> mvideourls = new ArrayList<>();

    public static void requestSuCaiPic(final Activity context, final int moduleId, final int itemViewType, final SimpleDraweeView simpleDraweeView) {
        Map map = new HashMap();
        map.put("moduleId", String.valueOf(moduleId));
        map.put(Constants.DOCTYPE, String.valueOf(itemViewType));
        OkHttpRequest.doGet(context, OkHttpRequest.attachHttpGetParams(Constants.PREPARING_PACKAGE_DOC_URL, map), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PreparingPicBean picbean = GsonUtils.getBean(response.body().string(), PreparingPicBean.class);
                if (null != picbean && 0 == picbean.getCode()) {
                    final String url = picbean.getContent().getUrl();
                    // final Uri parse = Uri.parse(url);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Uri parse = Uri.parse(Constants.TEST_IMAGE_URL);
                            simpleDraweeView.setImageURI(parse);
                        }
                    });
                }
            }
        });
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

    public static void requestSuCaiVideo(final Activity context, int courseId, final JZVideoPlayerStandard jzVideoPlayerStandard) {
        Map map = new HashMap();
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
                 context.runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         jzVideoPlayerStandard.setUp(Constants.TEST_VIDEO_URL
                                 , 1, "");
                         jzVideoPlayerStandard.thumbImageView.setImageResource(R.drawable.ic_launcher);
                     }
                 });

            }
        });
    }


}
