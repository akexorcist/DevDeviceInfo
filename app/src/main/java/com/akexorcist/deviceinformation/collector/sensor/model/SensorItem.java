package com.akexorcist.deviceinformation.collector.sensor.model;

import com.akexorcist.deviceinformation.collector.InfoResultType;
import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/27/2016 AD.
 */

public class SensorItem {
    private static final String NAME = "Name";

    private String name;
    private SensorItem.Data data;

    public SensorItem() {
        data = new SensorItem.Data();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        if (name != null && !name.isEmpty()) {
            return name;
        }
        return InfoResultType.UNKNOWN;
    }

    public SensorItem.Data getData() {
        return data;
    }

    public void setData(SensorItem.Data data) {
        this.data = data;
    }

    public static class Data extends BaseInfo {
        private static final String ID = "ID";
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

        public Data() {
            super();
        }

        public String getId() {
            return getValueByTitle(ID);
        }

        public Data setId(String id) {
            setDataInfo(ID, id);
            return this;
        }

        public String getVendor() {
            return getValueByTitle(VENDOR);
        }

        public Data setVendor(String vendor) {
            setDataInfo(VENDOR, vendor);
            return this;
        }

        public String getType() {
            return getValueByTitle(TYPE);
        }

        public Data setType(String type) {
            setDataInfo(TYPE, type);
            return this;
        }

        public String getVersion() {
            return getValueByTitle(VERSION);
        }

        public Data setVersion(String version) {
            setDataInfo(VERSION, version);
            return this;
        }

        public String getPower() {
            return getValueByTitle(POWER);
        }

        public Data setPower(String power) {
            setDataInfo(POWER, power);
            return this;
        }

        public String getMaximumRange() {
            return getValueByTitle(MAXIMUM_RANGE);
        }

        public Data setMaximumRange(String maximumRange) {
            setDataInfo(MAXIMUM_RANGE, maximumRange);
            return this;
        }

        public String getResolution() {
            return getValueByTitle(RESOLUTION);
        }

        public Data setResolution(String resolution) {
            setDataInfo(RESOLUTION, resolution);
            return this;
        }

        public String getMinimumDelay() {
            return getValueByTitle(MINIMUM_DELAY);
        }

        public Data setMinimumDelay(String minimumDelay) {
            setDataInfo(MINIMUM_DELAY, minimumDelay);
            return this;
        }

        public String getMaximumDelay() {
            return getValueByTitle(MAXIMUM_DELAY);
        }

        public Data setMaximumDelay(String maximumDelay) {
            setDataInfo(MAXIMUM_DELAY, maximumDelay);
            return this;
        }

        public String getFifoMaxEventCount() {
            return getValueByTitle(FIFO_MAX_EVENT_COUNT);
        }

        public Data setFifoMaxEventCount(String fifoMaxEventCount) {
            setDataInfo(FIFO_MAX_EVENT_COUNT, fifoMaxEventCount);
            return this;
        }

        public String getFifoReservedEventCount() {
            return getValueByTitle(FIFO_RESERVED_EVENT_COUNT);
        }

        public Data setFifoReservedEventCount(String fifoReservedEventCount) {
            setDataInfo(FIFO_RESERVED_EVENT_COUNT, fifoReservedEventCount);
            return this;
        }

        public String getDynamicSensor() {
            return getValueByTitle(DYNAMIC_SENSOR);
        }

        public Data setDynamicSensor(String dynamicSensor) {
            setDataInfo(DYNAMIC_SENSOR, dynamicSensor);
            return this;
        }

        public String getWakeUpSensor() {
            return getValueByTitle(WAKE_UP_SENSOR);
        }

        public Data setWakeUpSensor(String wakeUpSensor) {
            setDataInfo(WAKE_UP_SENSOR, wakeUpSensor);
            return this;
        }
    }
}
