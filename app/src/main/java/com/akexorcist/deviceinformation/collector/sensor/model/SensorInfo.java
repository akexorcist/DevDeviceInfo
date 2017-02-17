package com.akexorcist.deviceinformation.collector.sensor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class SensorInfo {
    private List<SensorItem> sensorItemList;

    public SensorInfo() {
        sensorItemList = new ArrayList<>();
    }

    public List<SensorItem> getSensorItemList() {
        return sensorItemList;
    }

    public void setSensorItemList(List<SensorItem> sensorItemList) {
        this.sensorItemList = sensorItemList;
    }

    public void setSensorItem(SensorItem newSensorItem) {
        SensorItem oldSensorItem = getSensorInfoByName(newSensorItem.getName());
        if (oldSensorItem != null) {
            // SensorItem already exist then set new value to old SensorItem.
            oldSensorItem.setSensorData(newSensorItem.getSensorData());
        } else {
            // SensorItem not exist then add new SensorItem to the list.
            this.sensorItemList.add(newSensorItem);
        }
    }

    public int getSensorItemCount() {
        return this.sensorItemList.size();
    }

    public SensorItem getSensorInfoByName(String name) {
        for (SensorItem sensorItem : sensorItemList) {
            if (sensorItem.getName().equals(name)) {
                return sensorItem;
            }
        }
        return null;
    }

    public SensorItem.Data getSensorDataByName(String name) {
        for (SensorItem sensorItem : sensorItemList) {
            if (sensorItem.getName().equals(name)) {
                return sensorItem.getSensorData();
            }
        }
        return null;
    }

    public boolean contains(String name) {
        for (SensorItem sensorItem : sensorItemList) {
            if (sensorItem.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
