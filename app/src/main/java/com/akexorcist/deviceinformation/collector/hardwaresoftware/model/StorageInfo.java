package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class StorageInfo extends BaseInfo {
    private static final String INTERNAL_STORAGE = "Internal Storage";
    private static final String SD_CARD_SUPPORTED = "SD Card Supported";

    public StorageInfo() {
    }

    public String getInternalStorage() {
        return getValueByTitle(INTERNAL_STORAGE);
    }

    public StorageInfo setInternalStorage(String internalStorage) {
        setDataInfo(INTERNAL_STORAGE, internalStorage);
        return this;
    }

    public String getSdCardSupported() {
        return getValueByTitle(SD_CARD_SUPPORTED);
    }

    public StorageInfo setSdCardSupported(String sdCardSupported) {
        setDataInfo(SD_CARD_SUPPORTED, sdCardSupported);
        return this;
    }
}
