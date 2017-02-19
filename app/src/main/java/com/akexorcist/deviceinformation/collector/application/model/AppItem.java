package com.akexorcist.deviceinformation.collector.application.model;

import java.util.List;

/**
 * Created by Akexorcist on 2/19/2017 AD.
 */

public class AppItem {
    private String name;
    private String packageName;
    private String versionName;
    private String versionCode;
    private int iconResId;
    private List<String> requiredFeatureList;
    private List<String> permissionList;
    private List<String> activityList;
    private List<String> serviceList;
    private List<String> receiverList;
    private List<String> providerList;

    public AppItem() {
    }

    public String getName() {
        return name;
    }

    public AppItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public AppItem setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getVersionName() {
        return versionName;
    }

    public AppItem setVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public AppItem setVersionCode(String versionCode) {
        this.versionCode = versionCode;
        return this;
    }

    public int getIconResId() {
        return iconResId;
    }

    public AppItem setIconResId(int iconResId) {
        this.iconResId = iconResId;
        return this;
    }

    public List<String> getRequiredFeatureList() {
        return requiredFeatureList;
    }

    public AppItem setRequiredFeatureList(List<String> requiredFeatureList) {
        this.requiredFeatureList = requiredFeatureList;
        return this;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public AppItem setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
        return this;
    }

    public List<String> getActivityList() {
        return activityList;
    }

    public AppItem setActivityList(List<String> activityList) {
        this.activityList = activityList;
        return this;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public AppItem setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    public List<String> getReceiverList() {
        return receiverList;
    }

    public AppItem setReceiverList(List<String> receiverList) {
        this.receiverList = receiverList;
        return this;
    }

    public List<String> getProviderList() {
        return providerList;
    }

    public AppItem setProviderList(List<String> providerList) {
        this.providerList = providerList;
        return this;
    }

    public static class Type {
        public static final int DOWNLOADED = 0;
        public static final int SYSTEM = 1;
    }
}
