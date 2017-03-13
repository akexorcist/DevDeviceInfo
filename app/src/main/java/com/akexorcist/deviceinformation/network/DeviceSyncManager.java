package com.akexorcist.deviceinformation.network;

import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;

import rx.Observable;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class DeviceSyncManager {
    private static DeviceSyncManager manager;

    public static DeviceSyncManager getInstance() {
        if (manager == null) {
            manager = new DeviceSyncManager();
        }
        return manager;
    }

    public Observable<DeviceSyncResult<BrandList>> getAllBrand() {
        return DeviceSyncApi.getInstance().getApi().getAllBrand();
    }
}
