package com.akexorcist.deviceinformation.collector.screen.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Akexorcist on 2/18/2017 AD.
 */

public class Resolution implements Parcelable {
    private float x;
    private float y;

    public Resolution(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected Resolution(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }

    public static final Creator<Resolution> CREATOR = new Creator<Resolution>() {
        @Override
        public Resolution createFromParcel(Parcel in) {
            return new Resolution(in);
        }

        @Override
        public Resolution[] newArray(int size) {
            return new Resolution[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(x);
        dest.writeFloat(y);
    }
}
