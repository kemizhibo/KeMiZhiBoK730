package com.kemizhibo.kemizhibo.other.config;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.kemizhibo.kemizhibo.other.utils.NetUtils;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;
import com.kemizhibo.kemizhibo.yhr.MyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/7/25.
 */

public class OkHttpRequest {
    private static OkHttpClient okHttpClient = null;

    private OkHttpRequest() {
    }

    public static OkHttpClient getInstance(Context context) {
        if (okHttpClient == null) {
            //加同步安全
            synchronized (OkHttpRequest.class) {
                if (okHttpClient == null) {
                    //okhttp可以缓存数据....指定缓存路径
                    File sdcache = new File(Environment.getExternalStorageDirectory(), "cache");
                    //指定缓存大小
                    int cacheSize = 10 * 1024 * 1024;

                    okHttpClient = new OkHttpClient.Builder()//构建器
                            .connectTimeout(15, TimeUnit.SECONDS)//连接超时
                            .writeTimeout(20, TimeUnit.SECONDS)//写入超时
                            .readTimeout(20, TimeUnit.SECONDS)//读取超时
                           // .addInterceptor(new TokenInterceptor(context))
                            .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))//设置缓存
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    private static String getToken(Context context) {
        return PreferencesUtils.getLoginInfo("token", context);
    }
    /**
     * get请求
     * 参数1 url
     * 参数2 回调Callback
     */

    public static void doGet(Context context, String url, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance(context);
        //创建Request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();
        //得到Call对象
        Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(callback);
    }

    /**
     * 为HttpGet 的 url 添加多个name value 参数。
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, Map params){

        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<params.size();i++ ) {
            String value=null;
            try {
                value= URLEncoder.encode(values.next(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            stringBuffer.append(keys.next()+"="+value);
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
        }

        return url + stringBuffer.toString();
    }

    /**
     * post请求
     * 参数1 url
     * 参数2 Map<String, String> params post请求的时候给服务器传的数据
     * add..("","")
     * add()
     */

    public static void doPost(Context context, String url, Map<String, String> params, Callback callback) {
        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = getInstance(context);
        //3.x版本post请求换成FormBody 封装键值对参数

        FormBody.Builder builder = new FormBody.Builder();
        //遍历集合
        for (String key : params.keySet()) {
            if(!TextUtils.isEmpty(key) && !TextUtils.isEmpty(params.get(key))){
                builder.add(key, params.get(key));
            }
        }
        //创建Request
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * post请求上传文件....包括图片....流的形式传任意文件...
     * 参数1 url
     * file表示上传的文件
     * fileName....文件的名字,,例如aaa.jpg
     * params ....传递除了file文件 其他的参数放到map集合
     */
    public static void uploadFile(Context context, String url, File file, String fileName, Map<String, String> params,Callback callback) {
        //创建OkHttpClient请求对象
        final OkHttpClient okHttpClient = getInstance(context);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //参数
        if (params != null) {
            for (String key : params.keySet()) {
                if(!TextUtils.isEmpty(key) && !TextUtils.isEmpty(params.get(key)))
                builder.addFormDataPart(key, params.get(key));
            }
        }
        //文件...参数name指的是请求路径中所接受的参数...如果路径接收参数键值是fileeeee,此处应该改变
        builder.addFormDataPart("uploadfile", fileName, RequestBody.create(MediaType.parse("application/octet-stream"), file));

        //构建
//        MultipartBody multipartBody = builder.build();
        RequestBody requestBody  = builder.build(); //唯一改动 删除上一行  新增本行
        //创建Request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken(context))
                .post(requestBody).build();

        //得到Call
        Call call = okHttpClient.newCall(request);
        /*new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //上传成功回调 目前不需要处理
                if (response.isSuccessful()) {
                    final String s = response.body().string();
                }
            }
        }*/
        //执行请求
        call.enqueue(callback);

    }

    /**
     * 下载文件 以流的形式把apk写入的指定文件 得到file后进行安装
     * 参数er：请求Url
     * 参数san：保存文件的文件夹....download
     */
    public static void download(final Activity context, final String url, final String saveDir) {
        Request request = new Request
                .Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken(context))
                .build();
        Call call = getInstance(context).newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //com.orhanobut.logger.Logger.e(e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();//以字节流的形式拿回响应实体内容
                    //apk保存路径
                    final String fileDir = isExistDir(saveDir);
                    //文件
                    File file = new File(fileDir, getNameFromUrl(url));

                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }

                    fos.flush();

                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "下载成功:" + fileDir + "," + getNameFromUrl(url), Toast.LENGTH_SHORT).show();
                        }
                    });

                    //apk下载完成后 调用系统的安装方法
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    context.startActivity(intent);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) is.close();
                    if (fos != null) fos.close();


                }
            }
        });

    }

    /**
     * 判断下载目录是否存在......并返回绝对路径
     *
     * @param saveDir
     * @return
     * @throws IOException
     */
    public static String isExistDir(String saveDir) throws IOException {
        // 下载位置
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
            if (!downloadFile.mkdirs()) {
                downloadFile.createNewFile();
            }
            String savePath = downloadFile.getAbsolutePath();
            Log.e("savePath", savePath);
            return savePath;
        }
        return null;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    private static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

}
