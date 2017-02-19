package com.akexorcist.deviceinformation.collector.application.model;

import java.util.List;

/**
 * Created by Akexorcist on 2/19/2017 AD.
 */

public class AppInfo {
    private List<AppItem> downloadedAppItemList;
    private List<AppItem> systemAppItemList;

    public AppInfo() {
    }

    public AppInfo(List<AppItem> downloadedAppItemList, List<AppItem> systemAppItemList) {
        this.downloadedAppItemList = downloadedAppItemList;
        this.systemAppItemList = systemAppItemList;
    }

    public List<AppItem> getDownloadedAppItemList() {
        return downloadedAppItemList;
    }

    public AppInfo setDownloadedAppItemList(List<AppItem> downloadedAppItemList) {
        this.downloadedAppItemList = downloadedAppItemList;
        return this;
    }

    public List<AppItem> getSystemAppItemList() {
        return systemAppItemList;
    }

    public AppInfo setSystemAppItemList(List<AppItem> systemAppItemList) {
        this.systemAppItemList = systemAppItemList;
        return this;
    }
}
