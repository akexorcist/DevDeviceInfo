package com.akexorcist.deviceinformation.network.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class Raw implements Parcelable {
    private String brand;
    private String model;
    private String androidVersion;
    private String fingerprint;

    public Raw() {
    }

    public String getBrand() {
        return brand;
    }

    public Raw setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Raw setModel(String model) {
        this.model = model;
        return this;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public Raw setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
        return this;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Raw setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(model);
        dest.writeString(androidVersion);
        dest.writeString(fingerprint);
    }

    protected Raw(Parcel in) {
        brand = in.readString();
        model = in.readString();
        androidVersion = in.readString();
        fingerprint = in.readString();
    }

    public static final Creator<Raw> CREATOR = new Creator<Raw>() {
        @Override
        public Raw createFromParcel(Parcel in) {
            return new Raw(in);
        }

        @Override
        public Raw[] newArray(int size) {
            return new Raw[size];
        }
    };
}
