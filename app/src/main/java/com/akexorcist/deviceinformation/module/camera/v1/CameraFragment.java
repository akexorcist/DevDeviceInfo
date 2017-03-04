package com.akexorcist.deviceinformation.module.camera.v1;

import com.akexorcist.deviceinformation.collector.camera.CameraInfoCollector;
import com.akexorcist.deviceinformation.collector.camera.model.CameraInfo;
import com.akexorcist.deviceinformation.collector.camera.model.CameraItem;
import com.akexorcist.deviceinformation.module.camera.BaseCameraFragment;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class CameraFragment extends BaseCameraFragment<CameraInfo, CameraItem> {

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    public CameraFragment() {
    }

    @Override
    protected CameraInfo callCameraInfoCollector() {
        return CameraInfoCollector.getInstance().collect();
    }
}
