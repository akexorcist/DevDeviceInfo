package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class AndroidInfo extends BaseInfo {
    private static final String API_VERSION = "API Version";
    private static final String SYSTEM_TYPE = "System Type";
    private static final String VERSION_CODE = "Version Code";
    private static final String ANDROID_VERSION = "Android Version";
    private static final String CODENAME = "Codename";
    private static final String INCREMENTAL = "Incremental";

    public AndroidInfo() {
        super();
    }

    public String getApiVersion() {
        return getValueByTitle(API_VERSION);
    }

    public AndroidInfo setApiVersion(String apiVersion) {
        setDataInfo(API_VERSION, apiVersion);
        return this;
    }

    public String getSystemType() {
        return getValueByTitle(SYSTEM_TYPE);
    }

    public AndroidInfo setSystemType(String systemType) {
        setDataInfo(SYSTEM_TYPE, systemType);
        return this;
    }

    public String getVersionCode() {
        return getValueByTitle(VERSION_CODE);
    }

    public AndroidInfo setVersionCode(String versionCode) {
        setDataInfo(VERSION_CODE, versionCode);
        return this;
    }

    public String getAndroidVersion() {
        return getValueByTitle(ANDROID_VERSION);
    }

    public AndroidInfo setAndroidVersion(String androidVersion) {
        setDataInfo(ANDROID_VERSION, androidVersion);
        return this;
    }

    public String getCodename() {
        return getValueByTitle(CODENAME);
    }

    public AndroidInfo setCodename(String codename) {
        setDataInfo(CODENAME, codename);
        return this;
    }

    public String getIncremental() {
        return getValueByTitle(INCREMENTAL);
    }

    public AndroidInfo setIncremental(String incremental) {
        setDataInfo(INCREMENTAL, incremental);
        return this;
    }
}
