package com.akexorcist.deviceinformation.module.camera.v2;

import com.akexorcist.deviceinformation.collector.camera.Camera2InfoCollector;
import com.akexorcist.deviceinformation.collector.camera.model.Camera2Info;
import com.akexorcist.deviceinformation.collector.camera.model.Camera2Item;
import com.akexorcist.deviceinformation.module.camera.BaseCameraFragment;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class Camera2Fragment extends BaseCameraFragment<Camera2Info, Camera2Item> {

    public static Camera2Fragment newInstance() {
        return new Camera2Fragment();
    }

    public Camera2Fragment() {
    }

    @Override
    protected Camera2Info callCameraInfoCollector() {
        return Camera2InfoCollector.getInstance().collect(getContext());
    }
}
