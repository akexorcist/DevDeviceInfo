package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BatteryInfo extends BaseInfo {
    @SerializedName("Battery")
    private String batteryCapacity;

    public BatteryInfo() {
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public BatteryInfo setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }
}
