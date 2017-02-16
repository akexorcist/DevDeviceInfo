package com.akexorcist.deviceinformation.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BaseInfo {
    private List<DataInfo> dataInfoList;

    public BaseInfo() {
        dataInfoList = new ArrayList<>();
    }

    public List<DataInfo> getDataInfoList() {
        return dataInfoList;
    }

    public void setDataInfoList(List<DataInfo> dataInfoList) {
        this.dataInfoList = dataInfoList;
    }

    public void setDataInfo(String title, String value) {
        setDataInfo(new DataInfo(title, value));
    }

    public void setDataInfo(DataInfo newDataInfo) {
        DataInfo oldDataInfo = getDataInfoByTitle(newDataInfo.getTitle());
        if (oldDataInfo != null) {
            // DataInfo already exist then set new value to old DataInfo.
            oldDataInfo.setValue(newDataInfo.getValue());
        } else {
            // DataInfo not exist then add new DataInfo to the list.
            this.dataInfoList.add(newDataInfo);
        }
    }

    public int getDataInfoCount() {
        return this.dataInfoList.size();
    }

    public DataInfo getDataInfoByTitle(String title) {
        for (DataInfo dataInfo : dataInfoList) {
            if (dataInfo.getTitle().equals(title)) {
                return dataInfo;
            }
        }
        return null;
    }

    public String getValueByTitle(String title) {
        for (DataInfo dataInfo : dataInfoList) {
            if (dataInfo.getTitle().equals(title)) {
                return dataInfo.getValue();
            }
        }
        return null;
    }

    public boolean contains(String title) {
        for (DataInfo dataInfo : dataInfoList) {
            if (dataInfo.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
