package com.akexorcist.deviceinformation.module.upload;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.AndroidInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.AndroidInfo;
import com.akexorcist.deviceinformation.common.BaseDdiActivity;
import com.akexorcist.deviceinformation.utility.EventShortener;
import com.hanks.htextview.HTextView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rx.Observable;

public class UploadDeviceActivity extends BaseDdiActivity {
    private GLSurfaceView svOpenGl;
    private HTextView tvStatus;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_upload_device;
    }

    @Override
    protected void bindView() {
        svOpenGl = (GLSurfaceView) findViewById(R.id.sv_upload_device_open_gl);
        tvStatus = (HTextView) findViewById(R.id.tv_upload_device_status);
    }

    @Override
    protected void setupView() {
        svOpenGl.setBackgroundColor(ContextCompat.getColor(this, R.color.extra_extra_light_gray));
        svOpenGl.setEGLContextClientVersion(2);
        svOpenGl.setRenderer(onSetRenderer());
        tvStatus.animateText("Collect Data...");
    }

    @Override
    protected void setupToolbar() {

    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {

    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {

    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    private EventShortener.GlSurfaceRenderer onSetRenderer() {
        return new EventShortener.GlSurfaceRenderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                collectAllInfo(gl);
            }
        };
    }

    private void collectAllInfo(GL10 gl10) {
//        getAndroidInfo()
//                .compose()
    }

    private Observable<AndroidInfo> getAndroidInfo() {
        return Observable.just(AndroidInfoCollector.getInstance().collect());
    }
}
