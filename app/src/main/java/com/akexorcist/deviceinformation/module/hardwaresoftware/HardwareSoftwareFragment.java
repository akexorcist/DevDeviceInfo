package com.akexorcist.deviceinformation.module.hardwaresoftware;

import android.os.Bundle;
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
import com.akexorcist.deviceinformation.widget.InfoCardView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class HardwareSoftwareFragment extends DdiFragment {
    private FrameLayout layoutLoading;
    private FrameLayout layoutContent;
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
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
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

    private SwipeRefreshLayout.OnRefreshListener onContentRefresh() {
        return () -> Observable.empty()
                .delay(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(() -> {
                    HardwareSoftwareFragment.this.initialize();
                    srlRefresh.setRefreshing(false);
                })
                .subscribe();
    }

    @Override
    protected void initialize() {
        hideContent();
        AndroidInfo androidInfo = AndroidInfoCollector.getInstance().collect(getContext());
        BatteryInfo batteryInfo = BatteryInfoCollector.getInstance().collect(getContext());
        BuildInfo buildInfo = BuildInfoCollector.getInstance().collect(getContext());
        CpuInfo cpuInfo = CpuInfoCollector.getInstance().collect(getContext());
        MemoryInfo memoryInfo = MemoryInfoCollector.getInstance().collect(getContext());
        StorageInfo storageInfo = StorageInfoCollector.getInstance().collect(getContext());
        GpuInfo gpuInfo = GpuInfoCollector.getInstance().collect(getContext());
        icvAndroidInfo.setDataInfoList(androidInfo.getDataInfoList(), true);
        icvBatteryInfo.setDataInfoList(batteryInfo.getDataInfoList(), true);
        icvBuildInfo.setDataInfoList(buildInfo.getDataInfoList(), true);
        icvCpuInfo.setDataInfoList(cpuInfo.getDataInfoList(), true);
        icvMemoryInfo.setDataInfoList(memoryInfo.getDataInfoList(), true);
        icvStorageInfo.setDataInfoList(storageInfo.getDataInfoList(), true);
        Observable.empty()
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted(this::showContent)
                .subscribe();
//        icvGpuInfo.setDataInfoList(androidInfo.getDataInfoList(), true);
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
}
