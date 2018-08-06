package com.kemizhibo.kemizhibo.other.config;

import android.content.Context;
import android.text.TextUtils;

import com.kemizhibo.kemizhibo.other.utils.GsonUtils;
import com.kemizhibo.kemizhibo.other.utils.PreferencesUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 检测是否token失效
 */

public class TokenInterceptor implements Interceptor {

    private Context mContext;

    public TokenInterceptor(Context context){
        this.mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        String responseStr = response.body().string();
        int code = 0;
        try {
            JSONObject jsonObject = new JSONObject(responseStr);
            code = jsonObject.optInt("code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (code != 0 && code == 401){
            getNewToken();
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer " + PreferencesUtils.getLoginInfo("token", mContext))
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    public synchronized void getNewToken(){
        String json = refreshSysToken();
        if (!TextUtils.isEmpty(json)){
            TokenBean bean = GsonUtils.getBean(json, TokenBean.class);
            String token = bean.getContent();
            if(!TextUtils.isEmpty(token)){
                PreferencesUtils.saveLoginInfo("token", token, mContext);
            }else {
                /*Intent intent=new Intent();
                //与清单文件的receiver的action对应
                intent.setAction(Constants.BROADCAST_BASIC);
                //发送广播
                mContext.sendBroadcast(intent);*/
            }
        }else {
            /*Intent intent=new Intent();
            //与清单文件的receiver的action对应
            intent.setAction(Constants.BROADCAST_BASIC);
            //发送广播
            mContext.sendBroadcast(intent);*/
        }
    }

    public synchronized String refreshSysToken() {
        String name = PreferencesUtils.getLoginInfo("name", mContext);
        String pwd = PreferencesUtils.getLoginInfo("pwd", mContext);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
            OkHttpClient mHttpClient = new OkHttpClient();
            FormBody formBody = new FormBody.Builder()
                    .add("account", name)
                    .add("password", pwd)
                    .build();
            Request request = new Request.Builder().url(Constants.LOGIN).post(formBody).build();
            Response response = null;
            try {
                response = mHttpClient.newCall(request).execute();
                if(response.code() == 200) {
                    String json = response.body().string();
                    return json;
                }
            } catch (IOException e) {
                return  null;
            }
        }
        return  null;
    }
}
