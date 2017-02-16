package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.StorageInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import java.io.File;
import java.util.Locale;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class StorageInfoCollector extends BaseInfoCollector {
    private static StorageInfoCollector collector;

    public static StorageInfoCollector getInstance() {
        if (collector == null) {
            collector = new StorageInfoCollector();
        }
        return collector;
    }

    @Override
    public StorageInfo collect(Context context) {
        return new StorageInfo()
                .setInternalStorage(getTotalInternalStorage())
                .setSdCardSupported(getSdCardSupported());
    }

    @SuppressWarnings("deprecation")
    public String getTotalInternalStorage() {
        StatFs statusFileSystem = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long availableByte = (long) statusFileSystem.getBlockSize() * (long) statusFileSystem.getBlockCount();
        float availableMb = availableByte / 1048576000f;
        return String.format(Locale.getDefault(), "%.3f GB", availableMb);
    }

    // This method can detected when sd card inserted only
    @SuppressLint("NewApi")
    public String getSdCardSupported() {
        File storageFile = new File("/storage");
        if (storageFile.exists()) {
            File[] fileList = storageFile.listFiles();
            for (File file : fileList) {
                if (file.isDirectory() && !isPersistentStorage(file.getName())) {
                    return "Yes";
                }
            }
        }
        return "Unknown";
    }

    private boolean isPersistentStorage(String directoryName) {
        return directoryName.equalsIgnoreCase("emulated") || directoryName.equalsIgnoreCase("self");
    }
}
