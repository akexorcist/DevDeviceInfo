package com.akexorcist.deviceinformation.module.hardwaresoftware;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.AndroidInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.BatteryInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.BuildInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.CpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.GpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.MemoryInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.StorageInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.AndroidInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.BatteryInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.BuildInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.CpuInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.GpuInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.MemoryInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.StorageInfo;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.EventShortener;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.InfoCardView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class HardwareSoftwareFragment extends DdiFragment {
    private FrameLayout layoutLoading;
    private FrameLayout layoutContent;
    private GLSurfaceView svOpenGl;
    private SwipeRefreshLayout srlRefresh;
    private InfoCardView icvAndroidInfo;
    private InfoCardView icvBatteryInfo;
    private InfoCardView icvBuildInfo;
    private InfoCardView icvCpuInfo;
    private InfoCardView icvGpuInfo;
    private InfoCardView icvMemoryInfo;
    private InfoCardView icvStorageInfo;

    public static HardwareSoftwareFragment newInstance() {
        return new HardwareSoftwareFragment();
    }

    public HardwareSoftwareFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_hardware_software;
    }

    @Override
    protected void bindView(View view) {
        layoutContent = (FrameLayout) view.findViewById(R.id.layout_hardware_software_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_hardware_software_loading);
        svOpenGl = (GLSurfaceView) view.findViewById(R.id.sv_hardware_and_software_open_gl);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_hardware_software_refresh);
        icvAndroidInfo = (InfoCardView) view.findViewById(R.id.icv_android_info);
        icvBatteryInfo = (InfoCardView) view.findViewById(R.id.icv_battery_info);
        icvBuildInfo = (InfoCardView) view.findViewById(R.id.icv_build_info);
        icvCpuInfo = (InfoCardView) view.findViewById(R.id.icv_cpu_info);
        icvGpuInfo = (InfoCardView) view.findViewById(R.id.icv_gpu_info);
        icvMemoryInfo = (InfoCardView) view.findViewById(R.id.icv_memory_info);
        icvStorageInfo = (InfoCardView) view.findViewById(R.id.icv_storage_info);
    }

    @Override
    protected void setupView() {
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        svOpenGl.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.extra_extra_light_gray));
        svOpenGl.setEGLContextClientVersion(2);
        svOpenGl.setRenderer(onSetRenderer());
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        forceHideContent();
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

    private SwipeRefreshLayout.OnRefreshListener onContentRefresh() {
        return () -> RxGenerator.getInstance().createDelayObservable(500, TimeUnit.MILLISECONDS)
                .doOnCompleted(refreshAllInfoAction())
                .subscribe();
    }

    private Action0 refreshAllInfoAction() {
        return () -> {
//            hideContent();
//            collectGpuInfo();
//            srlRefresh.setRefreshing(false);
        };
    }

    private void collectAllInfo(GL10 gl10) {
        createGpuInfoObservable(gl10)
                .flatMap(flatMapGpuInfoToAndroidInfoFunc())
                .flatMap(flatMapAndroidInfoToBuildInfoFunc())
                .flatMap(flatMapBuildInfoToBatteryInfoFunc())
                .flatMap(flatMapBatteryInfoToCpuInfoFunc())
                .flatMap(flatMapCpuInfoToMemoryInfoFunc())
                .flatMap(flatMapMemoryInfoToStorageInfoFunc())
                .doOnCompleted(onCollectedAllInfoAction())
                .subscribe();
    }

    private Action0 onCollectedAllInfoAction() {
        return this::showContent;
    }

    private Func1<GpuInfo, Observable<AndroidInfo>> flatMapGpuInfoToAndroidInfoFunc() {
        return gpuInfo -> createAndroidInfoObservable();
    }

    private Func1<AndroidInfo, Observable<BuildInfo>> flatMapAndroidInfoToBuildInfoFunc() {
        return androidInfo -> createBuildInfoObservable();
    }

    private Func1<BuildInfo, Observable<BatteryInfo>> flatMapBuildInfoToBatteryInfoFunc() {
        return buildInfo -> createBatteryInfoObservable();
    }

    private Func1<BatteryInfo, Observable<CpuInfo>> flatMapBatteryInfoToCpuInfoFunc() {
        return batteryInfo -> createCpuInfoObservable();
    }

    private Func1<CpuInfo, Observable<MemoryInfo>> flatMapCpuInfoToMemoryInfoFunc() {
        return cpuInfo -> createMemoryInfoObservable();
    }

    private Func1<MemoryInfo, Observable<StorageInfo>> flatMapMemoryInfoToStorageInfoFunc() {
        return memoryInfo -> createStorageInfoObservable();
    }

    /////////////////////////////
    // GPU Info Observable
    /////////////////////////////
    private Observable<GpuInfo> createGpuInfoObservable(GL10 gl10) {
        return createGpuInfoCollectorObservable(gl10)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createGpuInfoFunc());
    }

    private Observable<GpuInfo> createGpuInfoCollectorObservable(GL10 gl10) {
        return Observable.fromCallable(() -> GpuInfoCollector.getInstance().collect(getContext(), gl10));
    }

    private Observable<GpuInfo> createSetGpuInfoObservable(GpuInfo gpuInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = gpuInfo.getDataInfoList();
            icvGpuInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(gpuInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<GpuInfo, Observable<GpuInfo>> createGpuInfoFunc() {
        return this::createSetGpuInfoObservable;
    }

    /////////////////////////////
    // Android Info Observable
    /////////////////////////////
    private Observable<AndroidInfo> createAndroidInfoObservable() {
        return createAndroidInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createAndroidInfoFunc());
    }

    private Observable<AndroidInfo> createAndroidInfoCollectorObservable() {
        return Observable.just(AndroidInfoCollector.getInstance().collect());
    }

    private Observable<AndroidInfo> createSetAndroidInfoObservable(AndroidInfo androidInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = androidInfo.getDataInfoList();
            icvAndroidInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(androidInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<AndroidInfo, Observable<AndroidInfo>> createAndroidInfoFunc() {
        return this::createSetAndroidInfoObservable;
    }

    /////////////////////////////
    // Battery Info Observable
    /////////////////////////////
    private Observable<BatteryInfo> createBatteryInfoObservable() {
        return createBatteryInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createBatteryInfoFunc());
    }

    private Observable<BatteryInfo> createBatteryInfoCollectorObservable() {
        return Observable.just(BatteryInfoCollector.getInstance().collect(getContext()));
    }

    private Observable<BatteryInfo> createSetBatteryInfoObservable(BatteryInfo batteryInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = batteryInfo.getDataInfoList();
            icvBatteryInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(batteryInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<BatteryInfo, Observable<BatteryInfo>> createBatteryInfoFunc() {
        return this::createSetBatteryInfoObservable;
    }

    /////////////////////////////
    // Build Info Observable
    /////////////////////////////
    private Observable<BuildInfo> createBuildInfoObservable() {
        return createBuildInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createBuildInfoFunc());
    }

    private Observable<BuildInfo> createBuildInfoCollectorObservable() {
        return Observable.just(BuildInfoCollector.getInstance().collect());
    }

    private Observable<BuildInfo> createSetBuildInfoObservable(BuildInfo buildInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = buildInfo.getDataInfoList();
            icvBuildInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(buildInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<BuildInfo, Observable<BuildInfo>> createBuildInfoFunc() {
        return this::createSetBuildInfoObservable;
    }

    /////////////////////////////
    // CPU Info Observable
    /////////////////////////////
    private Observable<CpuInfo> createCpuInfoObservable() {
        return createCpuInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createCpuInfoFunc());
    }

    private Observable<CpuInfo> createCpuInfoCollectorObservable() {
        return Observable.just(CpuInfoCollector.getInstance().collect(getContext()));
    }

    private Observable<CpuInfo> createSetCpuInfoObservable(CpuInfo cpuInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = cpuInfo.getDataInfoList();
            icvCpuInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(cpuInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<CpuInfo, Observable<CpuInfo>> createCpuInfoFunc() {
        return this::createSetCpuInfoObservable;
    }

    /////////////////////////////
    // Memory Info Observable
    /////////////////////////////
    private Observable<MemoryInfo> createMemoryInfoObservable() {
        return createMemoryInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createMemoryInfoFunc());
    }

    private Observable<MemoryInfo> createMemoryInfoCollectorObservable() {
        return Observable.just(MemoryInfoCollector.getInstance().collect());
    }

    private Observable<MemoryInfo> createSetMemoryInfoObservable(MemoryInfo memoryInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = memoryInfo.getDataInfoList();
            icvMemoryInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(memoryInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<MemoryInfo, Observable<MemoryInfo>> createMemoryInfoFunc() {
        return this::createSetMemoryInfoObservable;
    }

    /////////////////////////////
    // Storage Info Observable
    /////////////////////////////
    private Observable<StorageInfo> createStorageInfoObservable() {
        return createStorageInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createStorageInfoFunc());
    }

    private Observable<StorageInfo> createStorageInfoCollectorObservable() {
        return Observable.just(StorageInfoCollector.getInstance().collect());
    }

    private Observable<StorageInfo> createSetStorageInfoObservable(StorageInfo storageInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = storageInfo.getDataInfoList();
            icvStorageInfo.setDataInfoList(dataInfoList, true, () -> {
                subscriber.onNext(storageInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<StorageInfo, Observable<StorageInfo>> createStorageInfoFunc() {
        return this::createSetStorageInfoObservable;
    }
}
