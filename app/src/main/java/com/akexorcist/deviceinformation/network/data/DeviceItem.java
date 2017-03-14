package com.akexorcist.deviceinformation.network.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class DeviceItem implements Parcelable {
    private Raw raw;
    private Info info;
    private Data data;
    private Device device;

    public DeviceItem() {
    }

    public Raw getRaw() {
        return raw;
    }

    public DeviceItem setRaw(Raw raw) {
        this.raw = raw;
        return this;
    }

    public Info getInfo() {
        return info;
    }

    public DeviceItem setInfo(Info info) {
        this.info = info;
        return this;
    }

    public Data getData() {
        return data;
    }

    public DeviceItem setData(Data data) {
        this.data = data;
        return this;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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
        dest.writeParcelable(device, flags);
    }

    protected DeviceItem(Parcel in) {
        raw = in.readParcelable(Raw.class.getClassLoader());
        info = in.readParcelable(Info.class.getClassLoader());
        data = in.readParcelable(Data.class.getClassLoader());
        device = in.readParcelable(Device.class.getClassLoader());
    }

    public static final Creator<DeviceItem> CREATOR = new Creator<DeviceItem>() {
        @Override
        public DeviceItem createFromParcel(Parcel in) {
            return new DeviceItem(in);
        }

        @Override
        public DeviceItem[] newArray(int size) {
            return new DeviceItem[size];
        }
    };
}
