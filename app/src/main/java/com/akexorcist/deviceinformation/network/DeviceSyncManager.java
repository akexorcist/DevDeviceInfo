package com.akexorcist.deviceinformation.network;

import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;
import com.akexorcist.deviceinformation.network.response.RecentDevice;

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

    public Observable<DeviceSyncResult<RecentDevice>> getRecentDevice() {
        return DeviceSyncApi.getInstance().getApi().getRecentDevice();
    }
}
