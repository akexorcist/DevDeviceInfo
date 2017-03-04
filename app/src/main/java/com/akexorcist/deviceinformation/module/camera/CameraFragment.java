package com.akexorcist.deviceinformation.module.camera;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.camera.CameraInfoCollector;
import com.akexorcist.deviceinformation.collector.camera.model.CameraInfo;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.helper.permission.QuickPermission;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.PermissionDeniedView;
import com.akexorcist.deviceinformation.widget.ScrollerLinearLayoutManager;

import java.util.concurrent.TimeUnit;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class CameraFragment extends DdiFragment {
    private LinearLayout layoutContent;
    private FrameLayout layoutLoading;
    private PermissionDeniedView pdCameraPermission;
    private RecyclerView rvContent;
    private SwipeRefreshLayout srlRefresh;
    private TabLayout tlContent;
    private CameraContentAdapter contentAdapter;
    private StickyHeaderDecoration stickyHeaderDecoration;

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
        layoutContent = (LinearLayout) view.findViewById(R.id.layout_camera_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_camera_loading);
        pdCameraPermission = (PermissionDeniedView) view.findViewById(R.id.pd_camera_permission);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_camera_content);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_camera_refresh);
        tlContent = (TabLayout) view.findViewById(R.id.tl_camera_content);
    }

    @Override
    protected void setupView() {
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
        rvContent.setLayoutManager(new ScrollerLinearLayoutManager(getContext()));
        contentAdapter = new CameraContentAdapter();
        stickyHeaderDecoration = new StickyHeaderDecoration(contentAdapter);
        rvContent.addItemDecoration(stickyHeaderDecoration);
        rvContent.setAdapter(contentAdapter);
        tlContent.addOnTabSelectedListener(onTabSelected());
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

    private void setupTabLayout(int cameraCount) {
        Observable.range(0, cameraCount)
                .subscribe(index -> {
                    TabLayout.Tab cameraTab = tlContent.newTab();
                    String title = getString(R.string.camera) + " " + index;
                    cameraTab.setText(title);
                    tlContent.addTab(cameraTab);
                });
    }

    private TabLayout.OnTabSelectedListener onTabSelected() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                rvContent.smoothScrollToPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        };
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
                .doOnNext(onCollectedCameraInfoAction())
                .doOnCompleted(onCompletedCameraInfoAction())
                .subscribe();
    }

    private Action1<? super CameraInfo> onCollectedCameraInfoAction() {
        return (Action1<CameraInfo>) cameraInfo -> {
            int cameraCount = cameraInfo.getCameraItemList().size();
            setupTabLayout(cameraCount);
            contentAdapter.setCameraItemList(cameraInfo.getCameraItemList());
            contentAdapter.notifyDataSetChanged();
        };
    }

    private Action0 onCompletedCameraInfoAction() {
        return this::showContent;
    }

    private Action0 refreshAllInfoAction() {
        return this::collectCameraInfo;
    }

    private Observable<CameraInfo> createCameraInfoObservable() {
        return createScreenInfoCollectorObservable()
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<CameraInfo> createScreenInfoCollectorObservable() {
        return Observable.fromCallable(() -> CameraInfoCollector.getInstance().collect());
    }
}
