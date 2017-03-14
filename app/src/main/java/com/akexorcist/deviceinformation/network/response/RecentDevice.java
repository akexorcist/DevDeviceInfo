package com.akexorcist.deviceinformation.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.akexorcist.deviceinformation.network.data.DeviceItem;

import java.util.List;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class RecentDevice implements Parcelable {
    private String selfLink;
    private int page;
    private int maxCount;
    private List<DeviceItem> deviceList;

    public RecentDevice() {
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public List<DeviceItem> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceItem> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(selfLink);
        dest.writeInt(page);
        dest.writeInt(maxCount);
        dest.writeTypedList(deviceList);
    }

    protected RecentDevice(Parcel in) {
        selfLink = in.readString();
        page = in.readInt();
        maxCount = in.readInt();
        deviceList = in.createTypedArrayList(DeviceItem.CREATOR);
    }

    public static final Creator<RecentDevice> CREATOR = new Creator<RecentDevice>() {
        @Override
        public RecentDevice createFromParcel(Parcel in) {
            return new RecentDevice(in);
        }

        @Override
        public RecentDevice[] newArray(int size) {
            return new RecentDevice[size];
        }
    };
}
