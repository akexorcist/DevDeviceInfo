package com.akexorcist.deviceinformation.collector.application;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.util.Log;

import com.akexorcist.deviceinformation.collector.application.model.AppInfo;
import com.akexorcist.deviceinformation.collector.application.model.AppItem;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class ApplicationInfoCollector extends BaseInfoCollector {
    private static ApplicationInfoCollector collector;

    public static ApplicationInfoCollector getInstance() {
        if (collector == null) {
            collector = new ApplicationInfoCollector();
        }
        return collector;
    }

    public AppInfo collect(Context context) {
        return new AppInfo()
                .setDownloadedAppItemList(getApplicationList(context, AppItem.Type.DOWNLOADED))
                .setSystemAppItemList(getApplicationList(context, AppItem.Type.SYSTEM));
    }

    private List<AppItem> getApplicationList(Context context, int applicationType) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> applicationInfoList = packageManager.getInstalledApplications(0);
        List<AppItem> appItemList = new ArrayList<>();
        for (ApplicationInfo applicationInfo : applicationInfoList) {
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == applicationType) {
                String packageName = applicationInfo.packageName;
                if (!isAppInfoContains(appItemList, packageName)) {
                    String name = getApplicationName(packageManager, applicationInfo);
                    PackageInfo packageInfo = getPackageInfo(packageManager, packageName);
                    if (packageInfo != null) {
                        AppItem appItem = new AppItem()
                                .setName(name)
                                .setPackageName(packageName)
                                .setVersionCode(packageInfo.versionCode + "")
                                .setVersionName(packageInfo.versionName)
                                .setIconResId(applicationInfo.icon)
                                .setPermissionList(getPermissionList(packageInfo))
                                .setActivityList(getActivityList(packageInfo))
                                .setServiceList(getServiceList(packageInfo))
                                .setReceiverList(getReceiverList(packageInfo))
                                .setProviderList(getProviderList(packageInfo))
                                .setRequiredFeatureList(getRequiredFeatureList(packageInfo));
                        appItemList.add(appItem);
                    }
                }
            }
        }
        return appItemList;
    }

    private boolean isAppInfoContains(List<AppItem> appItemList, String packageName) {
        if (appItemList != null) {
            for (AppItem appItem : appItemList) {
                if (appItem.getPackageName().equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private PackageInfo getPackageInfo(PackageManager packageManager, String packageName) {
        try {
            return packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getApplicationName(PackageManager packageManager, ApplicationInfo applicationInfo) {
        try {
            return packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (Resources.NotFoundException e) {
            return applicationInfo.packageName;
        }
    }

    private List<String> getPermissionList(PackageInfo packageInfo) {
        List<String> permissionList = Arrays.asList(packageInfo.requestedPermissions);
        permissionList.sort(sortByStringComparator());
        return permissionList;
    }

    private List<String> getActivityList(PackageInfo packageInfo) {
        List<String> activityList = new ArrayList<>();
        ActivityInfo[] activityInfoList = packageInfo.activities;
        for (ActivityInfo activityInfo : activityInfoList) {
            Log.e("Check", "Activity Package Name : " + activityInfo.packageName);
            activityList.add(activityInfo.packageName);
        }
        activityList.sort(sortByStringComparator());
        return activityList;
    }

    private List<String> getServiceList(PackageInfo packageInfo) {
        List<String> serviceList = new ArrayList<>();
        ServiceInfo[] activityInfoList = packageInfo.services;
        for (ServiceInfo serviceInfo : activityInfoList) {
            Log.e("Check", "Service Package Name : " + serviceInfo.packageName);
            serviceList.add(serviceInfo.packageName);
        }
        serviceList.sort(sortByStringComparator());
        return serviceList;
    }

    private List<String> getReceiverList(PackageInfo packageInfo) {
        List<String> receiverList = new ArrayList<>();
        ActivityInfo[] receiverInfoList = packageInfo.receivers;
        for (ActivityInfo receiverInfo : receiverInfoList) {
            Log.e("Check", "Receiver Package Name : " + receiverInfo.packageName);
            receiverList.add(receiverInfo.packageName);
        }
        receiverList.sort(sortByStringComparator());
        return receiverList;
    }

    private List<String> getProviderList(PackageInfo packageInfo) {
        List<String> providerList = new ArrayList<>();
        ProviderInfo[] providerInfoList = packageInfo.providers;
        for (ProviderInfo providerInfo : providerInfoList) {
            Log.e("Check", "Provider Package Name : " + providerInfo.packageName);
            providerList.add(providerInfo.packageName);
        }
        providerList.sort(sortByStringComparator());
        return providerList;
    }

    private List<String> getRequiredFeatureList(PackageInfo packageInfo) {
        List<String> requireFeatureList = new ArrayList<>();
        FeatureInfo[] requiredFeatureInfoList = packageInfo.reqFeatures;
        for (FeatureInfo requiredFeatureInfo : requiredFeatureInfoList) {
            Log.e("Check", "Required Feature Name : " + requiredFeatureInfo.name);
            requireFeatureList.add(requiredFeatureInfo.name);
        }
        requireFeatureList.sort(sortByStringComparator());
        return requireFeatureList;
    }

    private Comparator<String> sortByStringComparator() {
        return String::compareToIgnoreCase;
    }
}
