package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.GpuInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class GpuInfoCollector extends BaseInfoCollector {
    private static GpuInfoCollector collector;

    public static GpuInfoCollector getInstance() {
        if (collector == null) {
            collector = new GpuInfoCollector();
        }
        return collector;
    }

    public GpuInfo collect(Context context, GL10 gl10) {
        return new GpuInfo()
                .setVendor(gl10.glGetString(GL10.GL_VENDOR))
                .setRenderer(gl10.glGetString(GL10.GL_RENDERER))
                .setVersion(gl10.glGetString(GL10.GL_VERSION))
                .setVulkanSupported(getVulkanSupported(context))
                .setOpenGlSupported(getOpenGLVersion(context));
    }

    private String getVulkanSupported(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_VULKAN_HARDWARE_LEVEL)) {
            return "Yes";
        }
        return "No";
    }

    private String getOpenGLVersion(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        int version = configurationInfo.reqGlEsVersion;
        return (getOpenGLMajorVersion(version) + getOpenGLMinorVersion(version)) + "";
    }

    private int getOpenGLMajorVersion(int glEsVersion) {
        return ((glEsVersion & 0xffff0000) >> 16);
    }

    private float getOpenGLMinorVersion(int glEsVersion) {
        return (float) (Integer.parseInt(Integer.toHexString(glEsVersion).replace("0x", "")) % 10) / 10;
    }
}
