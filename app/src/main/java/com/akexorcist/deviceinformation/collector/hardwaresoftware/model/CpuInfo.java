package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CpuInfo extends BaseInfo {

    public CpuInfo() {
    }

    public CpuInfo setCpuDetailInfo(String title, String value) {
        setDataInfo(title, value);
        return this;
    }
}
