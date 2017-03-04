package com.akexorcist.deviceinformation.collector.camera.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 3/5/2017 AD.
 */

public abstract class BaseCameraInfo<BI extends BaseInfo> {
    private List<BI> cameraItemList;

    public BaseCameraInfo() {
        cameraItemList = new ArrayList<>();
    }

    public List<BI> getCameraItemList() {
        return cameraItemList;
    }

    public abstract BaseCameraInfo addCameraItem(BI cameraItem);

    public abstract BaseCameraInfo setCameraItemList(List<BI> cameraItemList);

    protected abstract int getCameraItemIndexById(String cameraId);
}
