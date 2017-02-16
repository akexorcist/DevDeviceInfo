package com.akexorcist.deviceinformation.collector.sensor.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 11/27/2016 AD.
 */

public class SensorInfo extends BaseInfo {
    @SerializedName("Sensor List")
    private List<SensorData> sensorDataList;

    public SensorInfo() {
        sensorDataList = new ArrayList<>();
    }

    public SensorInfo(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public SensorInfo setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
        return this;
    }

    public SensorInfo addSensorData(SensorData sensorData) {
        sensorDataList.add(sensorData);
        return this;
    }
}
