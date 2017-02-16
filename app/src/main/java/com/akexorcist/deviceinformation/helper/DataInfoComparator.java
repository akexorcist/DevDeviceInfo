package com.akexorcist.deviceinformation.helper;

import com.akexorcist.deviceinformation.common.DataInfo;

import java.util.Comparator;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class DataInfoComparator implements Comparator<DataInfo> {
    @Override
    public int compare(DataInfo dataInfo1, DataInfo dataInfo2) {
        return dataInfo1.getTitle().compareTo(dataInfo2.getTitle());
    }
}

