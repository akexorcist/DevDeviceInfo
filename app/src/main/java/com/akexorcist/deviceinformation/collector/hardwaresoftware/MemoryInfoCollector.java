package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.content.Context;

import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.MemoryInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;
import com.akexorcist.deviceinformation.utility.AndroidUtility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class MemoryInfoCollector extends BaseInfoCollector {
    private static final String GET_MEMORY_INFO_COMMAND = "cat /proc/meminfo";
    private static final String GET_HEAP_GROWTH_LIMIT_COMMAND = "getprop dalvik.vm.heapgrowthlimit";
    private static final String GET_HEAP_SIZE_COMMAND = "getprop dalvik.vm.heapsize";
    private static final String GET_HEAP_START_SIZE_COMMAND = "getprop dalvik.vm.heapstartsize";

    private static MemoryInfoCollector collector;

    public static MemoryInfoCollector getInstance() {
        if (collector == null) {
            collector = new MemoryInfoCollector();
        }
        return collector;
    }

    @Override
    public MemoryInfo collect(Context context) {
        return new MemoryInfo()
                .setTotalMemory(getTotalMemory())
                .setHeapSize(getHeapSize())
                .setHeapStartSize(getHeapStartSize())
                .setHeapGrowthLimit(getHeapGrowthLimit());
    }

    private String getTotalMemory() {
        String memory = getMemory().replace("MemTotal:", "").replace(" ", "").replace("kB", "");
        if (!memory.equals("Unknown")) {
            float mem = Float.parseFloat(memory) / 1000f;
            return String.format(Locale.getDefault(), "%.3f MB", mem);
        } else {
            return "Unknown";
        }
    }

    private String getMemory() {
        try {
            Process process = Runtime.getRuntime().exec(GET_MEMORY_INFO_COMMAND);
            InputStream inputStream = process.getInputStream();
            String[] listMemory = AndroidUtility.getInstance().getStringFromInputStream(inputStream).split("\n");
            for (int i = 0; i < listMemory.length; i++) {
                if (listMemory[i].contains("MemTotal"))
                    return listMemory[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    private static String getHeapGrowthLimit() {
        try {
            Process process = Runtime.getRuntime().exec(GET_HEAP_GROWTH_LIMIT_COMMAND);
            InputStream inputStream = process.getInputStream();
            String size = AndroidUtility.getInstance().getStringFromInputStream(inputStream);
            if (!size.equals("\n"))
                return size.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    private static String getHeapSize() {
        try {
            Process process = Runtime.getRuntime().exec(GET_HEAP_SIZE_COMMAND);
            InputStream inputStream = process.getInputStream();
            String size = AndroidUtility.getInstance().getStringFromInputStream(inputStream);
            if (!size.equals("\n"))
                return size.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    private static String getHeapStartSize() {
        try {
            Process process = Runtime.getRuntime().exec(GET_HEAP_START_SIZE_COMMAND);
            InputStream inputStream = process.getInputStream();
            String size = AndroidUtility.getInstance().getStringFromInputStream(inputStream);
            if (!size.equals("\n"))
                return size.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

}
