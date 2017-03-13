package com.akexorcist.deviceinformation.network.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.akexorcist.deviceinformation.network.data.Data;
import com.akexorcist.deviceinformation.network.data.Info;
import com.akexorcist.deviceinformation.network.data.Raw;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class UploadDeviceBody implements Parcelable {
    private Raw raw;
    private Info info;
    private Data data;

    public UploadDeviceBody() {
    }

    public Raw getRaw() {
        return raw;
    }

    public UploadDeviceBody setRaw(Raw raw) {
        this.raw = raw;
        return this;
    }

    public Info getInfo() {
        return info;
    }

    public UploadDeviceBody setInfo(Info info) {
        this.info = info;
        return this;
    }

    public Data getData() {
        return data;
    }

    public UploadDeviceBody setData(Data data) {
        this.data = data;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(raw, flags);
        dest.writeParcelable(info, flags);
        dest.writeParcelable(data, flags);
    }

    protected UploadDeviceBody(Parcel in) {
        raw = in.readParcelable(Raw.class.getClassLoader());
        info = in.readParcelable(Info.class.getClassLoader());
        data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Creator<UploadDeviceBody> CREATOR = new Creator<UploadDeviceBody>() {
        @Override
        public UploadDeviceBody createFromParcel(Parcel in) {
            return new UploadDeviceBody(in);
        }

        @Override
        public UploadDeviceBody[] newArray(int size) {
            return new UploadDeviceBody[size];
        }
    };
}
