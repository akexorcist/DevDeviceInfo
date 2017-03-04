package com.akexorcist.deviceinformation.module.camera;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.camera.CameraInfoCollector;
import com.akexorcist.deviceinformation.collector.camera.model.CameraInfo;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.helper.permission.QuickPermission;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.InfoCardView;
import com.akexorcist.deviceinformation.widget.PermissionDeniedView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class CameraFragment extends DdiFragment {
    private FrameLayout layoutContent;
    private FrameLayout layoutLoading;
    private SwipeRefreshLayout srlRefresh;
    private PermissionDeniedView pdCameraPermission;
    private InfoCardView icvCameraInfo;

    public static CameraFragment newInstance() {
        return new CameraFragment();
    }

    public CameraFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_camera;
    }

    @Override
    protected void bindView(View view) {
        layoutContent = (FrameLayout) view.findViewById(R.id.layout_camera_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_camera_loading);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_camera_refresh);
        pdCameraPermission = (PermissionDeniedView) view.findViewById(R.id.pd_camera_permission);
        icvCameraInfo = (InfoCardView) view.findViewById(R.id.icv_camera_info);
    }

    @Override
    protected void setupView() {
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        pdCameraPermission.setOnRequestPermissionClickListener(onRequestPermissionClick());
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        requestCameraPermission();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        forceHideContent();
        requestCameraPermission();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    private PermissionDeniedView.OnRequestPermissionClickListener onRequestPermissionClick() {
        return this::requestCameraPermission;
    }

    private void requestCameraPermission() {
        QuickPermission.requestPermission(getActivity(), permissionResult -> collectCameraInfo(), Manifest.permission.CAMERA);
    }

    private SwipeRefreshLayout.OnRefreshListener onContentRefresh() {
        return () -> {
            hideContent();
            srlRefresh.setRefreshing(false);
            RxGenerator.getInstance().createDelayObservable(500, TimeUnit.MILLISECONDS)
                    .doOnCompleted(refreshAllInfoAction())
                    .subscribe();
        };
    }

    private void collectCameraInfo() {
        createCameraInfoObservable()
                .doOnCompleted(onCollectedAllInfoAction())
                .subscribe();
    }

    private Action0 onCollectedAllInfoAction() {
        return this::showContent;
    }

    private Action0 refreshAllInfoAction() {
        return this::collectCameraInfo;
    }

    private Observable<CameraInfo> createCameraInfoObservable() {
        return createScreenInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(createCameraInfoFunc());
    }

    private Observable<CameraInfo> createScreenInfoCollectorObservable() {
        return Observable.fromCallable(() -> CameraInfoCollector.getInstance().collect());
    }

    private Observable<CameraInfo> createSetCameraInfoObservable(CameraInfo cameraInfo) {
        return Observable.create(subscriber -> {
            List<DataInfo> dataInfoList = cameraInfo.getCameraItemList().get(0).getDataInfoList();
            icvCameraInfo.setDataInfoList(dataInfoList, false, () -> {
                subscriber.onNext(cameraInfo);
                subscriber.onCompleted();
            });
        });
    }

    private Func1<CameraInfo, Observable<CameraInfo>> createCameraInfoFunc() {
        return this::createSetCameraInfoObservable;
    }
}
