package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

/**
 * Created by Akexorcist on 2/16/2017 AD.
 */

public class DataObject {
    private String title;
    private String value;

    public DataObject(String title, String value) {
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
}
