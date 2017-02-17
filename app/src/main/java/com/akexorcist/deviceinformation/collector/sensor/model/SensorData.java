package com.akexorcist.deviceinformation.collector.sensor.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/27/2016 AD.
 */

public class SensorData extends BaseInfo {
    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String VENDOR = "Vendor";
    private static final String TYPE = "Type";
    private static final String VERSION = "Version";
    private static final String POWER = "Power";
    private static final String MAXIMUM_RANGE = "Maximum Range";
    private static final String RESOLUTION = "Resolution";
    private static final String MINIMUM_DELAY = "Minimum Delay";
    private static final String MAXIMUM_DELAY = "Maximum Delay";
    private static final String FIFO_MAX_EVENT_COUNT = "Fifo Max Event Count";
    private static final String FIFO_RESERVED_EVENT_COUNT = "Fifo Reserved Event Count";
    private static final String DYNAMIC_SENSOR = "Dynamic Sensor";
    private static final String WAKE_UP_SENSOR = "Wake Up Sensor";

    public String getId() {
        return getValueByTitle(ID);
    }

    public SensorData setId(String id) {
        setDataInfo(ID, id);
        return this;
    }

    public String getName() {
        return getValueByTitle(NAME);
    }

    public SensorData setName(String name) {
        setDataInfo(NAME, name);
        return this;
    }

    public String getVendor() {
        return getValueByTitle(VENDOR);
    }

    public SensorData setVendor(String vendor) {
        setDataInfo(VENDOR, vendor);
        return this;
    }

    public String getType() {
        return getValueByTitle(TYPE);
    }

    public SensorData setType(String type) {
        setDataInfo(TYPE, type);
        return this;
    }

    public String getVersion() {
        return getValueByTitle(VERSION);
    }

    public SensorData setVersion(String version) {
        setDataInfo(VERSION, version);
        return this;
    }

    public String getPower() {
        return getValueByTitle(POWER);
    }

    public SensorData setPower(String power) {
        setDataInfo(POWER, power);
        return this;
    }

    public String getMaximumRange() {
        return getValueByTitle(MAXIMUM_RANGE);
    }

    public SensorData setMaximumRange(String maximumRange) {
        setDataInfo(MAXIMUM_RANGE, maximumRange);
        return this;
    }

    public String getResolution() {
        return getValueByTitle(RESOLUTION);
    }

    public SensorData setResolution(String resolution) {
        setDataInfo(RESOLUTION, resolution);
        return this;
    }

    public String getMinimumDelay() {
        return getValueByTitle(MINIMUM_DELAY);
    }

    public SensorData setMinimumDelay(String minimumDelay) {
        setDataInfo(MINIMUM_DELAY, minimumDelay);
        return this;
    }

    public String getMaximumDelay() {
        return getValueByTitle(MAXIMUM_DELAY);
    }

    public SensorData setMaximumDelay(String maximumDelay) {
        setDataInfo(MAXIMUM_DELAY, maximumDelay);
        return this;
    }

    public String getFifoMaxEventCount() {
        return getValueByTitle(FIFO_MAX_EVENT_COUNT);
    }

    public SensorData setFifoMaxEventCount(String fifoMaxEventCount) {
        setDataInfo(FIFO_MAX_EVENT_COUNT, fifoMaxEventCount);
        return this;
    }

    public String getFifoReservedEventCount() {
        return getValueByTitle(FIFO_RESERVED_EVENT_COUNT);
    }

    public SensorData setFifoReservedEventCount(String fifoReservedEventCount) {
        setDataInfo(FIFO_RESERVED_EVENT_COUNT, fifoReservedEventCount);
        return this;
    }

    public String getDynamicSensor() {
        return getValueByTitle(DYNAMIC_SENSOR);
    }

    public SensorData setDynamicSensor(String dynamicSensor) {
        setDataInfo(DYNAMIC_SENSOR, dynamicSensor);
        return this;
    }

    public String getWakeUpSensor() {
        return getValueByTitle(WAKE_UP_SENSOR);
    }

    public SensorData setWakeUpSensor(String wakeUpSensor) {
        setDataInfo(WAKE_UP_SENSOR, wakeUpSensor);
        return this;
    }
}
