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

    public CpuInfo collect(Context context) {
        String rawCpuInfo = getCpuInfo(context);
        return createCpuInfoFromRaw(rawCpuInfo);
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

    private CpuInfo createCpuInfoFromRaw(String raw) {
        CpuInfo cpuInfo = new CpuInfo();
        String[] infoList = raw.split("\n");
        for (String info : infoList) {
            String[] infoItem = info.split(":");
            if (infoItem.length == 2) {
                String title = infoItem[0].trim();
                String value = infoItem[1].trim();
                if (!isExcludeInfo(title)) {
                    cpuInfo.setDataInfo(title, value);
                }
            }
        }
        return cpuInfo;
    }

    private boolean isExcludeInfo(String title) {
        return title.startsWith("processor") ||
                title.startsWith("Features") ||
                title.startsWith("CPU implementer") ||
                title.startsWith("CPU architecture") ||
                title.startsWith("CPU variant") ||
                title.startsWith("CPU part") ||
                title.startsWith("CPU revision") ||
                title.startsWith("BogoMIPS");
    }
}
