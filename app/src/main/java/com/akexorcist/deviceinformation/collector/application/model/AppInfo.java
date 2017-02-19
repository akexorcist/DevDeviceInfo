package com.akexorcist.deviceinformation.collector.application.model;

import java.util.List;

/**
 * Created by Akexorcist on 2/19/2017 AD.
 */

public class ApplicationInfo {
    private List<ApplicationItem> downloadedApplicationItemList;
    private List<ApplicationItem> systemApplicationItemList;

    public ApplicationInfo() {
    }

    public ApplicationInfo(List<ApplicationItem> downloadedApplicationItemList, List<ApplicationItem> systemApplicationItemList) {
        this.downloadedApplicationItemList = downloadedApplicationItemList;
        this.systemApplicationItemList = systemApplicationItemList;
    }

    public List<ApplicationItem> getDownloadedApplicationItemList() {
        return downloadedApplicationItemList;
    }

    public ApplicationInfo setDownloadedApplicationItemList(List<ApplicationItem> downloadedApplicationItemList) {
        this.downloadedApplicationItemList = downloadedApplicationItemList;
        return this;
    }

    public List<ApplicationItem> getSystemApplicationItemList() {
        return systemApplicationItemList;
    }

    public ApplicationInfo setSystemApplicationItemList(List<ApplicationItem> systemApplicationItemList) {
        this.systemApplicationItemList = systemApplicationItemList;
        return this;
    }
}
