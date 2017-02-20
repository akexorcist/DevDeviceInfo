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
import java.util.Collections;
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
                    AppItem appItem = new AppItem()
                            .setName(name)
                            .setPackageName(packageName)
                            .setVersionCode(getVersionCode(packageManager, packageName))
                            .setVersionName(getVersionName(packageManager, packageName))
                            .setIconResId(applicationInfo.icon)
                            .setPermissionList(getPermissionList(packageManager, packageName))
                            .setActivityList(getActivityList(packageManager, packageName))
                            .setServiceList(getServiceList(packageManager, packageName))
                            .setReceiverList(getReceiverList(packageManager, packageName))
                            .setProviderList(getProviderList(packageManager, packageName))
                            .setRequiredFeatureList(getRequiredFeatureList(packageManager, packageName));
                    appItemList.add(appItem);
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

    private PackageInfo getPackageInfo(PackageManager packageManager, String packageName, int flag) {
        try {
            return packageManager.getPackageInfo(packageName, flag);
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

    private String getVersionName(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, 0);
        if (packageInfo != null) {
            return packageInfo.versionName + "";
        } else {
            return "Unknown";
        }
    }

    private String getVersionCode(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, 0);
        if (packageInfo != null) {
            return packageInfo.versionCode + "";
        } else {
            return "Unknown";
        }
    }

    private List<String> getPermissionList(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, PackageManager.GET_PERMISSIONS);
        if (packageInfo != null) {
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions != null) {
                List<String> permissionList = Arrays.asList(packageInfo.requestedPermissions);
                Collections.sort(permissionList);
                return permissionList;
            }
        }
        return new ArrayList<>();
    }

    private List<String> getActivityList(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, PackageManager.GET_ACTIVITIES);
        if (packageInfo != null) {
            ActivityInfo[] activities = packageInfo.activities;
            if (activities != null) {
                List<String> activityList = new ArrayList<>();
                for (ActivityInfo activityInfo : activities) {
                    activityList.add(activityInfo.name);
                }
                Collections.sort(activityList);
                return activityList;
            }
        }
        return new ArrayList<>();
    }

    private List<String> getServiceList(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, PackageManager.GET_SERVICES);
        if (packageInfo != null) {
            ServiceInfo[] services = packageInfo.services;
            if (services != null) {
                List<String> serviceList = new ArrayList<>();
                for (ServiceInfo service : services) {
                    serviceList.add(service.name);
                }
                Collections.sort(serviceList);
                return serviceList;
            }
        }
        return new ArrayList<>();
    }

    private List<String> getReceiverList(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, PackageManager.GET_RECEIVERS);
        if (packageInfo != null) {
            ActivityInfo[] receivers = packageInfo.receivers;
            if (receivers != null) {
                List<String> receiverList = new ArrayList<>();
                for (ActivityInfo receiver : receivers) {
                    receiverList.add(receiver.name);
                }
                Collections.sort(receiverList);
                return receiverList;
            }
        }
        return new ArrayList<>();
    }

    private List<String> getProviderList(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, PackageManager.GET_PROVIDERS);
        if (packageInfo != null) {
            ProviderInfo[] providers = packageInfo.providers;
            if (providers != null) {
                List<String> providerList = new ArrayList<>();
                for (ProviderInfo provider : providers) {
                    providerList.add(provider.name);
                }
                Collections.sort(providerList);
                return providerList;
            }
        }
        return new ArrayList<>();
    }

    private List<String> getRequiredFeatureList(PackageManager packageManager, String packageName) {
        PackageInfo packageInfo = getPackageInfo(packageManager, packageName, PackageManager.GET_CONFIGURATIONS);
        if (packageInfo != null) {
            FeatureInfo[] features = packageInfo.reqFeatures;
            if (features != null) {
                List<String> featureList = new ArrayList<>();
                for (FeatureInfo feature : features) {
                    // OpenGL ES Version is valid when feature name is null
                    String featureName = feature.name != null ? feature.name : "OpenGL ES " + feature.getGlEsVersion();
                    featureList.add(featureName);
                }
                Collections.sort(featureList);
                return featureList;
            }
        }
        return new ArrayList<>();
    }
}
