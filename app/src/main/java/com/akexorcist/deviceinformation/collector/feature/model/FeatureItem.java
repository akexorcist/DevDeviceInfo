package com.akexorcist.deviceinformation.collector.feature.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.akexorcist.deviceinformation.collector.InfoResultType;

/**
 * Created by Akexorcist on 2/18/2017 AD.
 */

public class FeatureItem implements Parcelable {
    private String name;
    private String packageName;
    private String minimumSdk;

    public FeatureItem() {
        super();
    }

    public String getName() {
        if (name != null && !name.isEmpty()) {
            return name;
        }
        return InfoResultType.UNKNOWN;
    }

    public FeatureItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getPackageName() {
        if (packageName != null && !packageName.isEmpty()) {
            return packageName;
        }
        return InfoResultType.UNKNOWN;
    }

    public FeatureItem setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getMinimumSdk() {
        if (minimumSdk != null && !minimumSdk.isEmpty()) {
            return minimumSdk;
        }
        return InfoResultType.UNKNOWN;
    }

    public FeatureItem setMinimumSdk(String minimumSdk) {
        this.minimumSdk = minimumSdk;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(packageName);
        dest.writeString(minimumSdk);
    }

    protected FeatureItem(Parcel in) {
        name = in.readString();
        packageName = in.readString();
        minimumSdk = in.readString();
    }

    public static final Creator<FeatureItem> CREATOR = new Creator<FeatureItem>() {
        @Override
        public FeatureItem createFromParcel(Parcel in) {
            return new FeatureItem(in);
        }

        @Override
        public FeatureItem[] newArray(int size) {
            return new FeatureItem[size];
        }
    };
}
