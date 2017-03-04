package com.akexorcist.deviceinformation.collector.camera.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 3/4/2017 AD.
 */

public class Camera2Info extends BaseCameraInfo<Camera2Item> {
    private List<Camera2Item> cameraItemList;

    public Camera2Info() {
        cameraItemList = new ArrayList<>();
    }

    public List<Camera2Item> getCameraItemList() {
        return cameraItemList;
    }

    public Camera2Info addCameraItem(Camera2Item cameraItem) {
        int cameraItemIndex = getCameraItemIndexById(cameraItem.getCameraId());
        if (cameraItemIndex == -1) {
            cameraItemList.add(cameraItem);
        } else {
            cameraItemList.set(cameraItemIndex, cameraItem);
        }
        return this;
    }

    public Camera2Info setCameraItemList(List<Camera2Item> cameraItemList) {
        this.cameraItemList = cameraItemList;
        return this;
    }

    protected int getCameraItemIndexById(String cameraId) {
        if (cameraItemList != null) {
            for (int index = 0; index < cameraItemList.size(); index++) {
                Camera2Item cameraItem = cameraItemList.get(index);
                if (cameraItem.getCameraId().equals(cameraId)) {
                    return index;
                }
            }
        }
        return -1;
    }
}
