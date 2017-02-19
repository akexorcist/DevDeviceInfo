package com.akexorcist.deviceinformation.collector.feature.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 2/18/2017 AD.
 */

public class FeatureItem extends BaseInfo {
    private static final String NAME = "Name";
    private static final String PACKAGE_NAME = "Package Name";
    private static final String MINIMUM_SDK = "Minimum SDK";

    public FeatureItem() {
        super();
    }

    public String getName() {
        return getValueByTitle(NAME);
    }

    public FeatureItem setName(String name) {
        setDataInfo(NAME, name);
        return this;
    }

    public String getPackageName() {
        return getValueByTitle(PACKAGE_NAME);
    }

    public FeatureItem setPackageName(String packageName) {
        setDataInfo(PACKAGE_NAME, packageName);
        return this;
    }

    public String getMinimumSdk() {
        return getValueByTitle(MINIMUM_SDK);
    }

    public FeatureItem setMinimumSdk(String minimumSdk) {
        setDataInfo(MINIMUM_SDK, minimumSdk);
        return this;
    }
}
