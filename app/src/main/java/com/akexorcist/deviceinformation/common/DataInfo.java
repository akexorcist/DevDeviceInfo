package com.akexorcist.deviceinformation.common;

/**
 * Created by Akexorcist on 2/16/2017 AD.
 */

public class DataInfo {
    private String title;
    private String value;

    public DataInfo() {
    }

    public DataInfo(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DataInfo dataInfo = (DataInfo) object;
        return this.getTitle().equals(dataInfo.getTitle()) && this.getValue().equals(dataInfo.getValue());
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
