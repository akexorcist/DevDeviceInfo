package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class StorageInfo extends BaseInfo {
    @SerializedName("Internal Storage")
    private String internalStorage;
    @SerializedName("SD Card Supported")
    private String sdCardSupported;

    public StorageInfo() {
    }

    public String getInternalStorage() {
        return internalStorage;
    }

    public StorageInfo setInternalStorage(String internalStorage) {
        this.internalStorage = internalStorage;
        return this;
    }

    public String getSdCardSupported() {
        return sdCardSupported;
    }

    public StorageInfo setSdCardSupported(String sdCardSupported) {
        this.sdCardSupported = sdCardSupported;
        return this;
    }
}
