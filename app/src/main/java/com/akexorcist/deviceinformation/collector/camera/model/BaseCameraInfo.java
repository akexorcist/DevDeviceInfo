package com.akexorcist.deviceinformation.collector.camera.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

import java.util.List;

/**
 * Created by Akexorcist on 3/5/2017 AD.
 */

public interface BaseCameraInfo<BI extends BaseInfo> {

    List<BI> getCameraItemList();

    BaseCameraInfo addCameraItem(BI cameraItem);

    BaseCameraInfo setCameraItemList(List<BI> cameraItemList);

    int getCameraItemIndexById(String cameraId);
}
