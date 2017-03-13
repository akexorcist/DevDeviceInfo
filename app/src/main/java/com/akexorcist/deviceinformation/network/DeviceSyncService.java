package com.akexorcist.deviceinformation.network;

import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public interface DeviceSyncService {
    @GET("/api/v1/ddi/getAllBrand")
    Call<DeviceSyncResult<BrandList>> getAllBrand();
}
