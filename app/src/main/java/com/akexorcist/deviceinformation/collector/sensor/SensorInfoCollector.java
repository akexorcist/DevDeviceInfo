package com.akexorcist.deviceinformation.collector.sensor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;

import com.akexorcist.deviceinformation.collector.sensor.model.SensorInfo;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorItem;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class SensorInfoCollector extends BaseInfoCollector {

    private static SensorInfoCollector collector;

    public static SensorInfoCollector getInstance() {
        if (collector == null) {
            collector = new SensorInfoCollector();
        }
        return collector;
    }

    public SensorInfo collect(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<SensorItem> sensorItemList = new ArrayList<>();
        if (sensorList != null) {
            for (Sensor sensor : sensorList) {
                SensorItem.Data sensorData = new SensorItem.Data()
                        .setId(getId(sensor))
                        .setType(getType(sensor))
                        .setVendor(getVendor(sensor))
                        .setVersion(getVersion(sensor))
                        .setPower(getPower(sensor))
                        .setResolution(getResolution(sensor))
                        .setMinimumDelay(getMinimumDelay(sensor))
                        .setMaximumDelay(getMaximumDelay(sensor))
                        .setMaximumRange(getMaximumRange(sensor))
                        .setFifoReservedEventCount(getFifoReservedEventCount(sensor))
                        .setFifoMaxEventCount(getFifoMaxEventCount(sensor))
                        .setWakeUpSensor(getWakeUpSensor(sensor))
                        .setDynamicSensor(getDynamicSensor(sensor));
                SensorItem sensorItem = new SensorItem();
                sensorItem.setSensorData(sensorData);
                sensorItem.setName(getName(sensor));
                sensorItemList.add(sensorItem);
            }
        }
        SensorInfo sensorInfo = new SensorInfo();
        sensorInfo.setSensorItemList(sensorItemList);
        return sensorInfo;
    }

    private String getId(Sensor sensor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return sensor.getId() + "";
        }
        return "Unknown";
    }

    private String getName(Sensor sensor) {
        return sensor.getName();
    }

    private String getVendor(Sensor sensor) {
        return sensor.getVendor();
    }

    private String getVersion(Sensor sensor) {
        return sensor.getVersion() + "";
    }

    private String getPower(Sensor sensor) {
        return sensor.getPower() + "";
    }

    private String getResolution(Sensor sensor) {
        return sensor.getResolution() + "";
    }

    @SuppressLint("NewApi")
    private String getMinimumDelay(Sensor sensor) {
        return sensor.getMinDelay() + "";
    }

    @SuppressLint("NewApi")
    private String getMaximumDelay(Sensor sensor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return sensor.getMaxDelay() + "";
        }
        return "Unknown";
    }

    private String getMaximumRange(Sensor sensor) {
        return sensor.getMaximumRange() + "";
    }

    @SuppressLint("NewApi")
    private String getFifoReservedEventCount(Sensor sensor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return sensor.getFifoReservedEventCount() + "";
        }
        return "Unknown";
    }

    @SuppressLint("NewApi")
    private String getFifoMaxEventCount(Sensor sensor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return sensor.getFifoMaxEventCount() + "";
        }
        return "Unknown";
    }

    @SuppressWarnings("deprecation")
    private String getType(Sensor sensor) {
        int type = sensor.getType();
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER:
                return "Accelerometer";
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                return "Temperature";
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                return "Game Rotation Vector";
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                return "Geomagnetic Rotation Vector";
            case Sensor.TYPE_GRAVITY:
                return "Gravity";
            case Sensor.TYPE_GYROSCOPE:
                return "Gyroscope";
            case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                return "Gyroscope Uncalibrated";
            case Sensor.TYPE_HEART_BEAT:
                return "Heart Beat";
            case Sensor.TYPE_HEART_RATE:
                return "Heart Rate";
            case Sensor.TYPE_LIGHT:
                return "Light";
            case Sensor.TYPE_LINEAR_ACCELERATION:
                return "Linear Acceleration";
            case Sensor.TYPE_MAGNETIC_FIELD:
                return "Magnetic Field";
            case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                return "Magnetic Field Uncalibrated";
            case Sensor.TYPE_MOTION_DETECT:
                return "Motion Detect";
            case Sensor.TYPE_ORIENTATION:
                return "Orientation";
            case Sensor.TYPE_POSE_6DOF:
                return "Post 6 DOF";
            case Sensor.TYPE_PRESSURE:
                return "Pressure";
            case Sensor.TYPE_PROXIMITY:
                return "Proximity";
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                return "Humidity";
            case Sensor.TYPE_ROTATION_VECTOR:
                return "Rotation Vector";
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                return "Significant Motion";
            case Sensor.TYPE_STATIONARY_DETECT:
                return "Stationary Detect";
            case Sensor.TYPE_STEP_COUNTER:
                return "Step Counter";
            case Sensor.TYPE_STEP_DETECTOR:
                return "Step Detector";
            case Sensor.TYPE_TEMPERATURE:
                return "Temperature";
        }
        return "Unknown (" + type + ")";
    }

    private String getWakeUpSensor(Sensor sensor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                sensor.isWakeUpSensor()) {
            return "Yes";
        }
        return "No";
    }

    private String getDynamicSensor(Sensor sensor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N &&
                sensor.isDynamicSensor()) {
            return "Yes";
        }
        return "No";
    }
}
