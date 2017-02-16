package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BatteryInfo extends BaseInfo {
    private static final String CAPABILITY = "Capacity";

    public BatteryInfo() {
        super();
    }

    public String getCapacity() {
        return getValueByTitle(CAPABILITY);
    }

    public BatteryInfo setCapacity(String capacity) {
        setDataInfo(CAPABILITY, capacity);
        return this;
    }
}
