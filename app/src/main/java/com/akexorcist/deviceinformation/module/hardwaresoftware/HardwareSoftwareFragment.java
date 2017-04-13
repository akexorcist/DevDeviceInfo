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
import com.akexorcist.deviceinformation.collector.hardwaresoftware.CommunicationInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.CpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.GpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.MemoryInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.StorageInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.AndroidInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.BatteryInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.BuildInfo;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.CommunicationInfo;
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
    private InfoCardView icvCommunicationInfo;
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
        icvCommunicationInfo = (InfoCardView) view.findViewById(R.id.icv_communication_info);
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
                .flatMap(flatMapBatteryInfoToCommunicationInfoFunc())
                .flatMap(flatMapCommunicationInfoToCpuInfoFunc())
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

    private Func1<BatteryInfo, Observable<CommunicationInfo>> flatMapBatteryInfoToCommunicationInfoFunc() {
        return batteryInfo -> createCommunicationInfoObservable();
    }

    private Func1<CommunicationInfo, Observable<CpuInfo>> flatMapCommunicationInfoToCpuInfoFunc() {
        return communicationInfo -> createCpuInfoObservable();
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
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvGpuInfo.setVisibility(View.VISIBLE);
                icvGpuInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(gpuInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvGpuInfo.setVisibility(View.GONE);
                subscriber.onNext(gpuInfo);
                subscriber.onCompleted();
            }
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
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvAndroidInfo.setVisibility(View.VISIBLE);
                icvAndroidInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(androidInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvAndroidInfo.setVisibility(View.GONE);
                subscriber.onNext(androidInfo);
                subscriber.onCompleted();
            }
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
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvBatteryInfo.setVisibility(View.VISIBLE);
                icvBatteryInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(batteryInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvBatteryInfo.setVisibility(View.GONE);
                subscriber.onNext(batteryInfo);
                subscriber.onCompleted();
            }
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
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvBuildInfo.setVisibility(View.VISIBLE);
                icvBuildInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(buildInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvBuildInfo.setVisibility(View.GONE);
                subscriber.onNext(buildInfo);
                subscriber.onCompleted();
            }
        });
    }

    private Func1<BuildInfo, Observable<BuildInfo>> createBuildInfoFunc() {
        return this::createSetBuildInfoObservable;
    }

    /////////////////////////////
    // Communication Info Observable
    /////////////////////////////
    private Observable<CommunicationInfo> createCommunicationInfoObservable() {
        return createCommunicationInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createCommunicationInfoFunc());
    }

    private Observable<CommunicationInfo> createCommunicationInfoCollectorObservable() {
        return Observable.just(CommunicationInfoCollector.getInstance().collect(getContext()));
    }

    private Observable<CommunicationInfo> createSetCommunicationInfoObservable(CommunicationInfo communicationInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = communicationInfo.getDataInfoList();
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvCommunicationInfo.setVisibility(View.VISIBLE);
                icvCommunicationInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(communicationInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvCommunicationInfo.setVisibility(View.GONE);
                subscriber.onNext(communicationInfo);
                subscriber.onCompleted();
            }
        });
    }

    private Func1<CommunicationInfo, Observable<CommunicationInfo>> createCommunicationInfoFunc() {
        return this::createSetCommunicationInfoObservable;
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
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvCpuInfo.setVisibility(View.VISIBLE);
                icvCpuInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(cpuInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvCpuInfo.setVisibility(View.GONE);
                subscriber.onNext(cpuInfo);
                subscriber.onCompleted();
            }
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
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvMemoryInfo.setVisibility(View.VISIBLE);
                icvMemoryInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(memoryInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvMemoryInfo.setVisibility(View.GONE);
                subscriber.onNext(memoryInfo);
                subscriber.onCompleted();
            }
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
        return Observable.just(StorageInfoCollector.getInstance().collect(getContext()));
    }

    private Observable<StorageInfo> createSetStorageInfoObservable(StorageInfo storageInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = storageInfo.getDataInfoList();
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                icvStorageInfo.setVisibility(View.VISIBLE);
                icvStorageInfo.setDataInfoList(dataInfoList, true, () -> {
                    subscriber.onNext(storageInfo);
                    subscriber.onCompleted();
                });
            } else {
                icvStorageInfo.setVisibility(View.GONE);
                subscriber.onNext(storageInfo);
                subscriber.onCompleted();
            }
        });
    }

    private Func1<StorageInfo, Observable<StorageInfo>> createStorageInfoFunc() {
        return this::createSetStorageInfoObservable;
    }
}
