package com.akexorcist.deviceinformation.network.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class Info implements Parcelable {
    private String supportedVersion;
    private boolean verified;

    public Info() {
    }

    public String getSupportedVersion() {
        return supportedVersion;
    }

    public Info setSupportedVersion(String version) {
        this.supportedVersion = version;
        return this;
    }

    public boolean isVerified() {
        return verified;
    }

    public Info setVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(supportedVersion);
        dest.writeByte((byte) (verified ? 1 : 0));
    }

    protected Info(Parcel in) {
        supportedVersion = in.readString();
        verified = in.readByte() != 0;
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
