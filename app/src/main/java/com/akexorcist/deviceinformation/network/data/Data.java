package com.akexorcist.deviceinformation.network.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.akexorcist.deviceinformation.collector.feature.model.FeatureItem;
import com.akexorcist.deviceinformation.common.DataInfo;

import java.util.List;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class Data implements Parcelable {
    private Hardware hardware;
    private List<DataInfo> screen;
    private List<Sensor> sensor;
    private List<Camera> camera;
    private List<Camera> camera2;
    private Feature feature;

    public Data() {
    }

    public Hardware getHardware() {
        return hardware;
    }

    public Data setHardware(Hardware hardware) {
        this.hardware = hardware;
        return this;
    }

    public List<DataInfo> getScreen() {
        return screen;
    }

    public Data setScreen(List<DataInfo> screen) {
        this.screen = screen;
        return this;
    }

    public List<Sensor> getSensor() {
        return sensor;
    }

    public Data setSensor(List<Sensor> sensor) {
        this.sensor = sensor;
        return this;
    }

    public List<Camera> getCamera() {
        return camera;
    }

    public Data setCamera(List<Camera> camera) {
        this.camera = camera;
        return this;
    }

    public List<Camera> getCamera2() {
        return camera2;
    }

    public Data setCamera2(List<Camera> camera2) {
        this.camera2 = camera2;
        return this;
    }

    public Feature getFeature() {
        return feature;
    }

    public Data setFeature(Feature feature) {
        this.feature = feature;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(hardware, flags);
        dest.writeTypedList(screen);
        dest.writeTypedList(sensor);
        dest.writeTypedList(camera);
        dest.writeTypedList(camera2);
        dest.writeParcelable(feature, flags);
    }

    protected Data(Parcel in) {
        hardware = in.readParcelable(Hardware.class.getClassLoader());
        screen = in.createTypedArrayList(DataInfo.CREATOR);
        sensor = in.createTypedArrayList(Sensor.CREATOR);
        camera = in.createTypedArrayList(Camera.CREATOR);
        camera2 = in.createTypedArrayList(Camera.CREATOR);
        feature = in.readParcelable(Feature.class.getClassLoader());
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public static class Hardware implements Parcelable {
        private List<DataInfo> android;
        private List<DataInfo> battery;
        private List<DataInfo> build;
        private List<DataInfo> communication;
        private List<DataInfo> cpu;
        private List<DataInfo> gpu;
        private List<DataInfo> memory;
        private List<DataInfo> storage;

        public Hardware() {
        }

        public List<DataInfo> getAndroid() {
            return android;
        }

        public Hardware setAndroid(List<DataInfo> android) {
            this.android = android;
            return this;
        }

        public List<DataInfo> getBattery() {
            return battery;
        }

        public Hardware setBattery(List<DataInfo> battery) {
            this.battery = battery;
            return this;
        }

        public List<DataInfo> getBuild() {
            return build;
        }

        public Hardware setBuild(List<DataInfo> build) {
            this.build = build;
            return this;
        }

        public List<DataInfo> getCommunication() {
            return communication;
        }

        public Hardware setCommunication(List<DataInfo> communication) {
            this.communication = communication;
            return this;
        }

        public List<DataInfo> getCpu() {
            return cpu;
        }

        public Hardware setCpu(List<DataInfo> cpu) {
            this.cpu = cpu;
            return this;
        }

        public List<DataInfo> getGpu() {
            return gpu;
        }

        public Hardware setGpu(List<DataInfo> gpu) {
            this.gpu = gpu;
            return this;
        }

        public List<DataInfo> getMemory() {
            return memory;
        }

        public Hardware setMemory(List<DataInfo> memory) {
            this.memory = memory;
            return this;
        }

        public List<DataInfo> getStorage() {
            return storage;
        }

        public Hardware setStorage(List<DataInfo> storage) {
            this.storage = storage;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(android);
            dest.writeTypedList(battery);
            dest.writeTypedList(build);
            dest.writeTypedList(communication);
            dest.writeTypedList(cpu);
            dest.writeTypedList(gpu);
            dest.writeTypedList(memory);
            dest.writeTypedList(storage);
        }

        protected Hardware(Parcel in) {
            android = in.createTypedArrayList(DataInfo.CREATOR);
            battery = in.createTypedArrayList(DataInfo.CREATOR);
            build = in.createTypedArrayList(DataInfo.CREATOR);
            communication = in.createTypedArrayList(DataInfo.CREATOR);
            cpu = in.createTypedArrayList(DataInfo.CREATOR);
            gpu = in.createTypedArrayList(DataInfo.CREATOR);
            memory = in.createTypedArrayList(DataInfo.CREATOR);
            storage = in.createTypedArrayList(DataInfo.CREATOR);
        }

        public static final Creator<Hardware> CREATOR = new Creator<Hardware>() {
            @Override
            public Hardware createFromParcel(Parcel in) {
                return new Hardware(in);
            }

            @Override
            public Hardware[] newArray(int size) {
                return new Hardware[size];
            }
        };
    }

    public static class Sensor implements Parcelable {
        private String name;
        private List<DataInfo> data;

        public Sensor() {
        }

        public String getName() {
            return name;
        }

        public Sensor setName(String name) {
            this.name = name;
            return this;
        }

        public List<DataInfo> getData() {
            return data;
        }

        public Sensor setData(List<DataInfo> data) {
            this.data = data;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeTypedList(data);
        }

        protected Sensor(Parcel in) {
            name = in.readString();
            data = in.createTypedArrayList(DataInfo.CREATOR);
        }

        public static final Creator<Sensor> CREATOR = new Creator<Sensor>() {
            @Override
            public Sensor createFromParcel(Parcel in) {
                return new Sensor(in);
            }

            @Override
            public Sensor[] newArray(int size) {
                return new Sensor[size];
            }
        };
    }

    public static class Camera implements Parcelable {
        private String id;
        private List<DataInfo> data;

        public Camera() {
        }

        public String getId() {
            return id;
        }

        public Camera setId(String id) {
            this.id = id;
            return this;
        }

        public List<DataInfo> getData() {
            return data;
        }

        public Camera setData(List<DataInfo> data) {
            this.data = data;
            return this;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeTypedList(data);
        }

        protected Camera(Parcel in) {
            id = in.readString();
            data = in.createTypedArrayList(DataInfo.CREATOR);
        }

        public static final Creator<Camera> CREATOR = new Creator<Camera>() {
            @Override
            public Camera createFromParcel(Parcel in) {
                return new Camera(in);
            }

            @Override
            public Camera[] newArray(int size) {
                return new Camera[size];
            }
        };
    }

    public static class Feature implements Parcelable {
        private List<FeatureItem> supported;
        private List<FeatureItem> unsupported;

        public Feature() {
        }

        public List<FeatureItem> getSupported() {
            return supported;
        }

        public Feature setSupported(List<FeatureItem> supported) {
            this.supported = supported;
            return this;
        }

        public List<FeatureItem> getUnsupported() {
            return unsupported;
        }

        public Feature setUnsupported(List<FeatureItem> unsupported) {
            this.unsupported = unsupported;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(supported);
            dest.writeTypedList(unsupported);
        }

        protected Feature(Parcel in) {
            supported = in.createTypedArrayList(FeatureItem.CREATOR);
            unsupported = in.createTypedArrayList(FeatureItem.CREATOR);
        }

        public static final Creator<Feature> CREATOR = new Creator<Feature>() {
            @Override
            public Feature createFromParcel(Parcel in) {
                return new Feature(in);
            }

            @Override
            public Feature[] newArray(int size) {
                return new Feature[size];
            }
        };
    }
}
