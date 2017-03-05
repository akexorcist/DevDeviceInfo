package com.akexorcist.deviceinformation.network.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class Device implements Parcelable {
    private String brand;
    private String series;
    private String model;

    public Device() {
    }

    public String getBrand() {
        return brand;
    }

    public Device setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getSeries() {
        return series;
    }

    public Device setSeries(String series) {
        this.series = series;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Device setModel(String model) {
        this.model = model;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(series);
        dest.writeString(model);
    }

    protected Device(Parcel in) {
        brand = in.readString();
        series = in.readString();
        model = in.readString();
    }

    public static final Creator<Device> CREATOR = new Creator<Device>() {
        @Override
        public Device createFromParcel(Parcel in) {
            return new Device(in);
        }

        @Override
        public Device[] newArray(int size) {
            return new Device[size];
        }
    };
}
