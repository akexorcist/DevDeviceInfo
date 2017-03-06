package com.akexorcist.deviceinformation.module.upload;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.akexorcist.deviceinformation.BuildConfig;
import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.camera.Camera2InfoCollector;
import com.akexorcist.deviceinformation.collector.camera.CameraInfoCollector;
import com.akexorcist.deviceinformation.collector.camera.model.Camera2Info;
import com.akexorcist.deviceinformation.collector.camera.model.Camera2Item;
import com.akexorcist.deviceinformation.collector.camera.model.CameraInfo;
import com.akexorcist.deviceinformation.collector.camera.model.CameraItem;
import com.akexorcist.deviceinformation.collector.feature.FeatureInfoCollector;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.AndroidInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.BatteryInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.BuildInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.CommunicationInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.CpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.GpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.MemoryInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.StorageInfoCollector;
import com.akexorcist.deviceinformation.collector.screen.ScreenInfoCollector;
import com.akexorcist.deviceinformation.collector.sensor.SensorInfoCollector;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorInfo;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorItem;
import com.akexorcist.deviceinformation.common.BaseDdiActivity;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.network.UploadDeviceBody;
import com.akexorcist.deviceinformation.network.data.Data;
import com.akexorcist.deviceinformation.network.data.Info;
import com.akexorcist.deviceinformation.network.data.Raw;
import com.akexorcist.deviceinformation.utility.EventShortener;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.antonionicolaspina.revealtextview.RevealTextView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

public class UploadDeviceActivity extends BaseDdiActivity {
    private GLSurfaceView svOpenGl;
    private RevealTextView tvStatus;

    private Subscription uploadDeviceBodySubscription;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_upload_device;
    }

    @Override
    protected void bindView() {
        svOpenGl = (GLSurfaceView) findViewById(R.id.sv_upload_device_open_gl);
        tvStatus = (RevealTextView) findViewById(R.id.tv_upload_device_status);
    }

    @Override
    protected void setupView() {
        svOpenGl.setBackgroundColor(ContextCompat.getColor(this, R.color.extra_extra_light_gray));
        svOpenGl.setEGLContextClientVersion(2);
        svOpenGl.setRenderer(onSetRenderer());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribeAllSubscription();
    }

    private void showDataCollectingMessage() {
        tvStatus.setAnimatedText(getString(R.string.upload_device_data_collecting));
    }

    private void showSendingMessage() {
        tvStatus.setAnimatedText(getString(R.string.upload_device_sending));
    }

    private void showCompletedMessage() {
        tvStatus.setAnimatedText(getString(R.string.upload_device_completed));
    }

    private EventShortener.GlSurfaceRenderer onSetRenderer() {
        return new EventShortener.GlSurfaceRenderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                collectAllInfo(gl);
            }
        };
    }

    private void unsubscribeAllSubscription() {
        if (uploadDeviceBodySubscription != null && !uploadDeviceBodySubscription.isUnsubscribed()) {
            uploadDeviceBodySubscription.unsubscribe();
        }
    }

    private void collectAllInfo(GL10 gl10) {
        showDataCollectingMessage();
        uploadDeviceBodySubscription = getUploadDeviceBody(this, gl10)
                .doOnCompleted(onDataCompleted())
                .subscribe(onUploadDeviceBodyCollected());
    }

    private Action0 onDataCollecting() {
        return () -> Observable.empty()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(this::showDataCollectingMessage)
                .subscribe();
    }

    private Action0 onDataCompleted() {
        return () -> Observable.empty()
                .delay(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(this::showCompletedMessage)
                .subscribe();
    }

    private Action1<UploadDeviceBody> onUploadDeviceBodyCollected() {
        return uploadDeviceBody -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("JSON", new Gson().toJson(uploadDeviceBody));
            clipboard.setPrimaryClip(clip);
        };
    }

    private Observable<UploadDeviceBody> getUploadDeviceBody(Activity activity, GL10 gl10) {
        return Observable.just(collectUploadDeviceBody(activity, gl10))
                .compose(RxGenerator.getInstance().applySchedulers());
    }

    private UploadDeviceBody collectUploadDeviceBody(Activity activity, GL10 gl10) {
        Raw raw = new Raw()
                .setBrand(Build.BRAND)
                .setModel(Build.MODEL)
                .setAndroidVersion(AndroidInfoCollector.getInstance().getVersionCode())
                .setFingerprint(Build.FINGERPRINT);
        Info info = new Info()
                .setVerified(false)
                .setSupportedVersion(BuildConfig.SDK_SUPPORTED_VERSION);
        Data data = new Data()
                .setHardware(new Data.Hardware()
                        .setAndroid(getAndroidInfo())
                        .setBattery(getBatteryInfo(activity))
                        .setBuild(getBuildInfo())
                        .setCommunication(getCommunicationInfo(activity))
                        .setCpu(getCpuInfo(activity))
                        .setGpu(getGpuInfo(activity, gl10))
                        .setMemory(getMemoryInfo())
                        .setStorage(getStorageInfo(activity)))
                .setScreen(getScreenInfo(activity))
                .setCamera(getCameraInfo())
                .setCamera2(getCamera2Info(activity))
                .setSensor(getSensorInfo(activity))
                .setFeature(getFeatureInfo(activity));
        return new UploadDeviceBody()
                .setRaw(raw)
                .setInfo(info)
                .setData(data);
    }

    private List<DataInfo> getAndroidInfo() {
        return AndroidInfoCollector.getInstance().collect().getDataInfoList();
    }

    private List<DataInfo> getBatteryInfo(Context context) {
        return BatteryInfoCollector.getInstance().collect(context).getDataInfoList();
    }

    private List<DataInfo> getBuildInfo() {
        return BuildInfoCollector.getInstance().collect().getDataInfoList();
    }

    private List<DataInfo> getCommunicationInfo(Context context) {
        return CommunicationInfoCollector.getInstance().collect(context).getDataInfoList();
    }

    private List<DataInfo> getCpuInfo(Context context) {
        return CpuInfoCollector.getInstance().collect(context).getDataInfoList();
    }

    private List<DataInfo> getGpuInfo(Context context, GL10 gl10) {
        return GpuInfoCollector.getInstance().collect(context, gl10).getDataInfoList();
    }

    private List<DataInfo> getMemoryInfo() {
        return MemoryInfoCollector.getInstance().collect().getDataInfoList();
    }

    private List<DataInfo> getStorageInfo(Context context) {
        return StorageInfoCollector.getInstance().collect(context).getDataInfoList();
    }

    private List<DataInfo> getScreenInfo(Activity activity) {
        return ScreenInfoCollector.getInstance().collect(activity).getDataInfoList();
    }

    private List<Data.Camera> getCameraInfo() {
        List<Data.Camera> cameraList = new ArrayList<>();
        CameraInfo cameraInfo = CameraInfoCollector.getInstance().collect();
        for (CameraItem cameraItem : cameraInfo.getCameraItemList()) {
            Data.Camera cameraData = new Data.Camera()
                    .setId(cameraItem.getCameraId())
                    .setData(cameraItem.getDataInfoList());
            cameraList.add(cameraData);
        }
        return cameraList;
    }

    private List<Data.Camera> getCamera2Info(Context context) {
        List<Data.Camera> cameraList = new ArrayList<>();
        Camera2Info cameraInfo = Camera2InfoCollector.getInstance().collect(context);
        for (Camera2Item cameraItem : cameraInfo.getCameraItemList()) {
            Data.Camera cameraData = new Data.Camera()
                    .setId(cameraItem.getCameraId())
                    .setData(cameraItem.getDataInfoList());
            cameraList.add(cameraData);
        }
        return cameraList;
    }

    private List<Data.Sensor> getSensorInfo(Context context) {
        List<Data.Sensor> sensorList = new ArrayList<>();
        SensorInfo sensorInfo = SensorInfoCollector.getInstance().collect(context);
        for (SensorItem sensorItem : sensorInfo.getSensorItemList()) {
            Data.Sensor sensorData = new Data.Sensor()
                    .setName(sensorItem.getName())
                    .setData(sensorItem.getData().getDataInfoList());
            sensorList.add(sensorData);
        }
        return sensorList;
    }

    private Data.Feature getFeatureInfo(Context context) {
        FeatureInfo featureInfo = FeatureInfoCollector.getInstance().collect(context);
        return new Data.Feature()
                .setSupported(featureInfo.getSupportedFeatureItem())
                .setUnsupported(featureInfo.getUnsupportedFeatureItem());
    }
}
