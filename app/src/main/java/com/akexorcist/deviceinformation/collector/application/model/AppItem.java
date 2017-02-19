package com.akexorcist.deviceinformation.collector.application.model;

import java.util.List;

/**
 * Created by Akexorcist on 2/19/2017 AD.
 */

public class ApplicationItem {
    private String name;
    private String packageName;
    private String versionName;
    private String versionCode;
    private String iconResId;
    private List<String> requiredFeatureList;
    private List<String> permissionList;
    private List<String> activityList;
    private List<String> serviceList;
    private List<String> receiverList;
    private List<String> providerList;

    public ApplicationItem() {
    }

    public String getName() {
        return name;
    }

    public ApplicationItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public ApplicationItem setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getVersionName() {
        return versionName;
    }

    public ApplicationItem setVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public ApplicationItem setVersionCode(String versionCode) {
        this.versionCode = versionCode;
        return this;
    }

    public String getIconResId() {
        return iconResId;
    }

    public ApplicationItem setIconResId(String iconResId) {
        this.iconResId = iconResId;
        return this;
    }

    public List<String> getRequiredFeatureList() {
        return requiredFeatureList;
    }

    public ApplicationItem setRequiredFeatureList(List<String> requiredFeatureList) {
        this.requiredFeatureList = requiredFeatureList;
        return this;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public ApplicationItem setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
        return this;
    }

    public List<String> getActivityList() {
        return activityList;
    }

    public ApplicationItem setActivityList(List<String> activityList) {
        this.activityList = activityList;
        return this;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public ApplicationItem setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    public List<String> getReceiverList() {
        return receiverList;
    }

    public ApplicationItem setReceiverList(List<String> receiverList) {
        this.receiverList = receiverList;
        return this;
    }

    public List<String> getProviderList() {
        return providerList;
    }

    public ApplicationItem setProviderList(List<String> providerList) {
        this.providerList = providerList;
        return this;
    }

    public static class Type {
        public static final int DOWNLOADED = 0;
        public static final int SYSTEM = 1;
    }
}
