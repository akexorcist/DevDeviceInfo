package com.akexorcist.deviceinformation.collector.camera.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 3/4/2017 AD.
 */

public class CameraInfo {
    private List<CameraItem> cameraItemList;

    public CameraInfo() {
        cameraItemList = new ArrayList<>();
    }

    public List<CameraItem> getCameraItemList() {
        return cameraItemList;
    }

    public CameraInfo addCameraItem(CameraItem cameraItem) {
        int cameraItemIndex = getCameraItemIndexById(cameraItem.getCameraId());
        if (cameraItemIndex == -1) {
            cameraItemList.add(cameraItem);
        } else {
            cameraItemList.set(cameraItemIndex, cameraItem);
        }
        return this;
    }

    public CameraInfo setCameraItemList(List<CameraItem> cameraItemList) {
        this.cameraItemList = cameraItemList;
        return this;
    }

    private int getCameraItemIndexById(String cameraId) {
        if (cameraItemList != null) {
            for (int index = 0; index < cameraItemList.size(); index++) {
                CameraItem cameraItem = cameraItemList.get(index);
                if (cameraItem.getCameraId().equals(cameraId)) {
                    return index;
                }
            }
        }
        return -1;
    }
}
