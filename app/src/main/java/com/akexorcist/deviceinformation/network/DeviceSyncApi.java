package com.akexorcist.deviceinformation.network;

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

    public DeviceSyncService getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DeviceSyncUrl.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(DeviceSyncService.class);
    }
}
