package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class AndroidInfo extends BaseInfo {
    @SerializedName("API Version")
    private String apiVersion;
    @SerializedName("System Type")
    private String systemType;
    @SerializedName("Version Code")
    private String versionCode;
    @SerializedName("Android Version")
    private String androidVersion;
    @SerializedName("Codename")
    private String codename;
    @SerializedName("Incremental")
    private String incremental;

    public AndroidInfo() {
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public AndroidInfo setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public String getSystemType() {
        return systemType;
    }

    public AndroidInfo setSystemType(String systemType) {
        this.systemType = systemType;
        return this;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public AndroidInfo setVersionCode(String versionCode) {
        this.versionCode = versionCode;
        return this;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public AndroidInfo setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
        return this;
    }

    public String getCodename() {
        return codename;
    }

    public AndroidInfo setCodename(String codename) {
        this.codename = codename;
        return this;
    }

    public String getIncremental() {
        return incremental;
    }

    public AndroidInfo setIncremental(String incremental) {
        this.incremental = incremental;
        return this;
    }
}
