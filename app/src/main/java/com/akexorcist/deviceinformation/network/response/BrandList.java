package com.akexorcist.deviceinformation.network.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class BrandList implements Parcelable {
    private String selfLink;
    private List<String> brandList;

    public BrandList() {
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public boolean isBrandAvailable() {
        return brandList != null && !brandList.isEmpty();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(selfLink);
        dest.writeStringList(brandList);
    }

    protected BrandList(Parcel in) {
        selfLink = in.readString();
        brandList = in.createStringArrayList();
    }

    public static final Creator<BrandList> CREATOR = new Creator<BrandList>() {
        @Override
        public BrandList createFromParcel(Parcel in) {
            return new BrandList(in);
        }

        @Override
        public BrandList[] newArray(int size) {
            return new BrandList[size];
        }
    };
}
