package com.kemizhibo.kemizhibo.yhr.retrofitUtils;

import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by muhanxi on 18/1/a1111.
 */

public class RetrofitManager {
    public static OkHttpClient client = new OkHttpClient.Builder()
            .build();

    public static ApiService apiService = new Retrofit.Builder()
            //.baseUrl("http://120.27.23.105/")
            .baseUrl("http://192.168.1.101:8080/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService.class);

    public static void get(String url, Map<String,String> map,Observer observer){

        apiService.get(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void post(String url,Map<String,String> map,Observer observer){
        apiService.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
