package com.akexorcist.deviceinformation.collector.sensor.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/27/2016 AD.
 */

public class SensorData {
    @SerializedName("ID")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Vendor")
    private String vendor;
    @SerializedName("Type")
    private String type;
    @SerializedName("Version")
    private String version;
    @SerializedName("Power")
    private String power;
    @SerializedName("Maximum Range")
    private String maximumRange;
    @SerializedName("Resolution")
    private String resolution;
    @SerializedName("Minimum Delay")
    private String minimumDelay;
    @SerializedName("Maximum Delay")
    private String maximumDelay;
    @SerializedName("Fifo Max Event Count")
    private String fifoMaxEventCount;
    @SerializedName("Fifo Reserved Event Count")
    private String fifoReservedEventCount;
    @SerializedName("Dynamic Sensor")
    private String dynamicSensor;
    @SerializedName("Wake Up Sensor")
    private String wakeUpSensor;

    public String getId() {
        return id;
    }

    public SensorData setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SensorData setName(String name) {
        this.name = name;
        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public SensorData setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public String getType() {
        return type;
    }

    public SensorData setType(String type) {
        this.type = type;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public SensorData setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getPower() {
        return power;
    }

    public SensorData setPower(String power) {
        this.power = power;
        return this;
    }

    public String getMaximumRange() {
        return maximumRange;
    }

    public SensorData setMaximumRange(String maximumRange) {
        this.maximumRange = maximumRange;
        return this;
    }

    public String getResolution() {
        return resolution;
    }

    public SensorData setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public String getMinimumDelay() {
        return minimumDelay;
    }

    public SensorData setMinimumDelay(String minimumDelay) {
        this.minimumDelay = minimumDelay;
        return this;
    }

    public String getMaximumDelay() {
        return maximumDelay;
    }

    public SensorData setMaximumDelay(String maximumDelay) {
        this.maximumDelay = maximumDelay;
        return this;
    }

    public String getFifoMaxEventCount() {
        return fifoMaxEventCount;
    }

    public SensorData setFifoMaxEventCount(String fifoMaxEventCount) {
        this.fifoMaxEventCount = fifoMaxEventCount;
        return this;
    }

    public String getFifoReservedEventCount() {
        return fifoReservedEventCount;
    }

    public SensorData setFifoReservedEventCount(String fifoReservedEventCount) {
        this.fifoReservedEventCount = fifoReservedEventCount;
        return this;
    }

    public String getDynamicSensor() {
        return dynamicSensor;
    }

    public SensorData setDynamicSensor(String dynamicSensor) {
        this.dynamicSensor = dynamicSensor;
        return this;
    }

    public String getWakeUpSensor() {
        return wakeUpSensor;
    }

    public SensorData setWakeUpSensor(String wakeUpSensor) {
        this.wakeUpSensor = wakeUpSensor;
        return this;
    }
}
