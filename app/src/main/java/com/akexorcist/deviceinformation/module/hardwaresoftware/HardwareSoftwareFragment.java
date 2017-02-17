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
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.InfoCardView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class HardwareSoftwareFragment extends DdiFragment {
    private FrameLayout layoutLoading;
    private FrameLayout layoutContent;
    private FrameLayout layoutOpenGlContainer;
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
        layoutOpenGlContainer = (FrameLayout) view.findViewById(R.id.layout_open_gl_container);
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
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        collectGpuInfo();
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

    private void collectGpuInfo() {
        GLSurfaceView glSurfaceView = new GLSurfaceView(getContext());
        glSurfaceView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.extra_extra_light_gray));
        layoutOpenGlContainer.addView(glSurfaceView);
        GpuInfoCollector.getInstance().collect(glSurfaceView, onGpuInfoCollected());
    }

    private SwipeRefreshLayout.OnRefreshListener onContentRefresh() {
        return () ->
                RxGenerator.getInstance().createDelayObservable(500, TimeUnit.MILLISECONDS)
                        .doOnCompleted(refreshAllInfoAction())
                        .subscribe();
    }

    private Action0 refreshAllInfoAction() {
        return () -> {
            hideContent();
            collectGpuInfo();
            srlRefresh.setRefreshing(false);
        };
    }

    private GpuInfoCollector.OnInfoCollectCallback onGpuInfoCollected() {
        return (gpuInfo) ->
                Observable.just(gpuInfo)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(collectAllInfoAction());
    }

    private Action1<GpuInfo> collectAllInfoAction() {
        return gpuInfo -> {
            collectAllInfo(gpuInfo);
            layoutOpenGlContainer.removeAllViews();
        };
    }

    private void collectAllInfo(GpuInfo gpuInfo) {
        AndroidInfo androidInfo = AndroidInfoCollector.getInstance().collect(getContext());
        BatteryInfo batteryInfo = BatteryInfoCollector.getInstance().collect(getContext());
        BuildInfo buildInfo = BuildInfoCollector.getInstance().collect(getContext());
        CpuInfo cpuInfo = CpuInfoCollector.getInstance().collect(getContext());
        MemoryInfo memoryInfo = MemoryInfoCollector.getInstance().collect(getContext());
        StorageInfo storageInfo = StorageInfoCollector.getInstance().collect(getContext());
        icvAndroidInfo.setDataInfoList(androidInfo.getDataInfoList(), true);
        icvBatteryInfo.setDataInfoList(batteryInfo.getDataInfoList(), true);
        icvBuildInfo.setDataInfoList(buildInfo.getDataInfoList(), true);
        icvCpuInfo.setDataInfoList(cpuInfo.getDataInfoList(), true);
        icvGpuInfo.setDataInfoList(gpuInfo.getDataInfoList(), true);
        icvMemoryInfo.setDataInfoList(memoryInfo.getDataInfoList(), true);
        icvStorageInfo.setDataInfoList(storageInfo.getDataInfoList(), true);

        RxGenerator.getInstance().createDelayObservable(2, TimeUnit.SECONDS)
                .doOnCompleted(this::showContent)
                .subscribe();
    }
}
