package com.akexorcist.deviceinformation.network;

import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public interface DeviceSyncService {
    @GET("/api/v1/ddi/getAllBrand")
    Observable<DeviceSyncResult<BrandList>> getAllBrand();
}
