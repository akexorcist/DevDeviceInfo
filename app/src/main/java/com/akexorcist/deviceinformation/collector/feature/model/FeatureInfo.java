package com.akexorcist.deviceinformation.collector.feature.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

import java.util.List;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class FeatureInfo extends BaseInfo {
    private List<FeatureItem> supportedFeatureItem;
    private List<FeatureItem> unsupportedFeatureItem;

    public FeatureInfo() {
        super();
    }

    public List<FeatureItem> getSupportedFeatureItem() {
        return supportedFeatureItem;
    }

    public FeatureInfo setSupportedFeatureItem(List<FeatureItem> supportedFeatureItem) {
        this.supportedFeatureItem = supportedFeatureItem;
        return this;
    }

    public List<FeatureItem> getUnsupportedFeatureItem() {
        return unsupportedFeatureItem;
    }

    public FeatureInfo setUnsupportedFeatureItem(List<FeatureItem> unsupportedFeatureItem) {
        this.unsupportedFeatureItem = unsupportedFeatureItem;
        return this;
    }
}
