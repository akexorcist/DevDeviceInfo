package com.akexorcist.deviceinformation.collector.feature;

import android.content.Context;
import android.os.Build;

import com.akexorcist.deviceinformation.collector.feature.model.Feature;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureInfo;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureItem;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class FeatureInfoCollector extends BaseInfoCollector {
    private static FeatureInfoCollector collector;

    public static FeatureInfoCollector getInstance() {
        if (collector == null) {
            collector = new FeatureInfoCollector();
        }
        return collector;
    }

    public FeatureInfo collect(Context context) {
        return new FeatureInfo()
                .setSupportedFeatureItem(getSupportedFeature(context))
                .setUnsupportedFeatureItem(getUnsupportedFeature(context));
    }

    private List<FeatureItem> getSupportedFeature(Context context) {
        return getFeatureList(context, true);
    }

    private List<FeatureItem> getUnsupportedFeature(Context context) {
        return getFeatureList(context, false);
    }

    private List<FeatureItem> getFeatureList(Context context, boolean isSupportedFeature) {
        List<FeatureItem> expectFeatureItemList = new ArrayList<>();
        List<Feature.Data> allFeatureDataList = Feature.getAllFeatureList();
        for (Feature.Data featureData : allFeatureDataList) {
            boolean isSupported = hasFeature(context, featureData.getPackageName(), featureData.getMinimumSdk());
            if (isSupported == isSupportedFeature) {
                FeatureItem featureItem = new FeatureItem();
                featureItem.setName(featureData.getName());
                featureItem.setPackageName(featureData.getPackageName());
                featureItem.setMinimumSdk(featureData.getMinimumSdk() + "");
                expectFeatureItemList.add(featureItem);
            }
        }
        return expectFeatureItemList;
    }

    private boolean hasFeature(Context context, String feature, int minVersion) {
        int version = Build.VERSION.SDK_INT;
        return version >= minVersion && isFeatureSupported(context, feature);
    }

    private boolean isFeatureSupported(Context context, String feature) {
        return context.getPackageManager().hasSystemFeature(feature);
    }
}
