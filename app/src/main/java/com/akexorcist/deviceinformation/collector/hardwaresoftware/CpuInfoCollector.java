package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.content.Context;

import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.CpuInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;
import com.akexorcist.deviceinformation.utility.AndroidUtility;

import java.io.InputStream;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CpuInfoCollector extends BaseInfoCollector {
    private static final String GET_CPU_INFO_COMMAND = "cat /proc/cpuinfo";

    private static CpuInfoCollector collector;

    public static CpuInfoCollector getInstance() {
        if (collector == null) {
            collector = new CpuInfoCollector();
        }
        return collector;
    }

    @Override
    public CpuInfo collect(Context context) {
//        return new CpuInfo()
//                .setBatteryCapacity(getCapacity(context));
        // TODO Fix this
        return null;
    }

    private String getCpuInfo(Context context) {
        try {
            Process process = Runtime.getRuntime().exec(GET_CPU_INFO_COMMAND);
            InputStream is = process.getInputStream();
            return AndroidUtility.getInstance().getStringFromInputStream(is);
        } catch (Exception ignored) {
        }
        return "Unknown";
    }
}
