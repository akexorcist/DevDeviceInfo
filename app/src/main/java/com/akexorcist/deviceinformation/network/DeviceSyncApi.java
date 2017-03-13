package com.akexorcist.deviceinformation.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class DeviceSyncApi {
    private static DeviceSyncApi api;

    public static DeviceSyncApi getInstance() {
        if (api == null) {
            api = new DeviceSyncApi();
        }
        return api;
    }

    private Retrofit retrofit;

    public DeviceSyncService getApi() {
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(DeviceSyncUrl.BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(DeviceSyncService.class);
    }
}
