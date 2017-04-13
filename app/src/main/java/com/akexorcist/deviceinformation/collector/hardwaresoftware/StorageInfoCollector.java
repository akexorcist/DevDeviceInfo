package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;

import com.akexorcist.deviceinformation.collector.InfoResultType;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.StorageInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

    public StorageInfo collect(Context context) {
        // Get info from json
//        StorageInfo storageInfo = new StorageInfo();
//        storageInfo.setDataInfoList(SamsungS8.getInfo().getData().getHardware().getStorage());
//        return storageInfo;
        return new StorageInfo()
                .setInternalStorage(getTotalInternalStorage())
                .setSdCardSupported(getSdCardSupported(context));
    }

    @SuppressWarnings("deprecation")
    private String getTotalInternalStorage() {
        StatFs statusFileSystem = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long availableByte = (long) statusFileSystem.getBlockSize() * (long) statusFileSystem.getBlockCount();
        float availableMb = availableByte / 1048576000f;
        return String.format(Locale.getDefault(), "%.3f GB", availableMb);
    }

    // This method can detected when sd card inserted only
    private String getSdCardSupported(Context context) {
        if (context != null) {
            String[] storageDirectoryList = getExternalStorageDirectoryList(context);
            if (storageDirectoryList.length > 0) {
                return InfoResultType.YES;
            }
        }
        return InfoResultType.UNKNOWN;
    }

    // Get external storage directory method from StackOverflow
    // http://stackoverflow.com/questions/36766016/how-to-get-sd-card-path-in-android6-0-programmatically
    private String[] getExternalStorageDirectoryList(Context context) {
        List<String> results = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            File[] externalDirs = context.getExternalFilesDirs(null);
            for (File file : externalDirs) {
                String path = file.getPath().split("/Android")[0];
                boolean addPath;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addPath = Environment.isExternalStorageRemovable(file);
                } else {
                    addPath = Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(file));
                }
                if (addPath) {
                    results.add(path);
                }
            }
        }
        if (results.isEmpty()) {
            String output = "";
            try {
                Process process = new ProcessBuilder()
                        .command("mount | grep /dev/block/vold")
                        .redirectErrorStream(true)
                        .start();
                process.waitFor();
                InputStream is = process.getInputStream();
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    output = output + new String(buffer);
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!output.trim().isEmpty()) {
                String devicePoints[] = output.split("\n");
                for (String voldPoint : devicePoints) {
                    results.add(voldPoint.split(" ")[2]);
                }
            }
        }

        //Below few lines is to remove paths which may not be external memory card, like OTG (feel free to comment them out)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().matches(".*[0-9a-f]{4}[-][0-9a-f]{4}")) {
                    results.remove(i--);
                }
            }
        } else {
            for (int i = 0; i < results.size(); i++) {
                if (!results.get(i).toLowerCase().contains("ext") && !results.get(i).toLowerCase().contains("sdcard")) {
                    results.remove(i--);
                }
            }
        }
        String[] storageDirectories = new String[results.size()];
        for (int i = 0; i < results.size(); ++i) {
            storageDirectories[i] = results.get(i);
        }
        return storageDirectories;
    }
}
