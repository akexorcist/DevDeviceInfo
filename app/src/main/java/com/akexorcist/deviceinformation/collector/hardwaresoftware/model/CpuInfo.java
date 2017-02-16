package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CpuInfo extends BaseInfo {
    @SerializedName("Battery")
    private String batteryCapacity;

    public CpuInfo() {
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public CpuInfo setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }
}
