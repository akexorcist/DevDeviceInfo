package com.akexorcist.deviceinformation.network;

import com.akexorcist.deviceinformation.network.data.Data;
import com.akexorcist.deviceinformation.network.data.Info;
import com.akexorcist.deviceinformation.network.data.Raw;

/**
 * Created by Akexorcist on 3/6/2017 AD.
 */

public class UploadDeviceBody {
    private Raw raw;
    private Info info;
    private Data data;

    public UploadDeviceBody() {
    }

    public Raw getRaw() {
        return raw;
    }

    public UploadDeviceBody setRaw(Raw raw) {
        this.raw = raw;
        return this;
    }

    public Info getInfo() {
        return info;
    }

    public UploadDeviceBody setInfo(Info info) {
        this.info = info;
        return this;
    }

    public Data getData() {
        return data;
    }

    public UploadDeviceBody setData(Data data) {
        this.data = data;
        return this;
    }
}
