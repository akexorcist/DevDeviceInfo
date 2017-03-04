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
import com.akexorcist.deviceinformation.collector.camera.model.BaseCameraInfo;
import com.akexorcist.deviceinformation.common.BaseInfo;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.helper.permission.QuickPermission;
import com.akexorcist.deviceinformation.module.camera.adapter.CameraContentAdapter;
import com.akexorcist.deviceinformation.utility.AnimationUtility;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.PermissionDeniedView;
import com.akexorcist.deviceinformation.widget.ScrollerLinearLayoutManager;

import java.util.concurrent.TimeUnit;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public abstract class BaseCameraFragment<CI extends BaseCameraInfo<BI>, BI extends BaseInfo> extends DdiFragment {
    private LinearLayout layoutContent;
    private FrameLayout layoutLoading;
    private PermissionDeniedView pdCameraPermission;
    private RecyclerView rvContent;
    private SwipeRefreshLayout srlRefresh;
    private TabLayout tlContent;
    private CameraContentAdapter<BI> contentAdapter;
    private StickyHeaderDecoration stickyHeaderDecoration;
    private ScrollerLinearLayoutManager scrollerLinearLayoutManager;

    public BaseCameraFragment() {
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
        setupSwipeLayout();
        setupRecyclerView();
        setupTabLayout();
        setupPermissionDenied();
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        hidePermissionDenied();
        requestCameraPermission();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        forceHideContent();
        hidePermissionDenied();
        requestCameraPermission();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    private void setupSwipeLayout() {
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
    }

    private void setupRecyclerView() {
        scrollerLinearLayoutManager = new ScrollerLinearLayoutManager(getContext());
        rvContent.setLayoutManager(scrollerLinearLayoutManager);
        contentAdapter = new CameraContentAdapter<>();
        stickyHeaderDecoration = new StickyHeaderDecoration(contentAdapter);
        rvContent.addItemDecoration(stickyHeaderDecoration);
        rvContent.setAdapter(contentAdapter);
        rvContent.addOnScrollListener(onTabScroll());
    }

    private void setupTabLayout() {
        tlContent.addOnTabSelectedListener(onTabSelectedListener);
    }

    private void setupPermissionDenied() {
        pdCameraPermission.setPermissionDeniedDescription(R.string.camera_permission_denied);
        pdCameraPermission.setOnRequestPermissionClickListener(onRequestPermissionClick());
    }

    private void initTabLayout(int cameraCount) {
        if (cameraCount > 0) {
            tlContent.setVisibility(View.VISIBLE);
            for (int index = 0; index < cameraCount; index++) {
                if (isAdded()) {
                    TabLayout.Tab cameraTab = tlContent.newTab();
                    String title = getString(R.string.camera) + " " + index;
                    cameraTab.setText(title);
                    tlContent.addTab(cameraTab);
                }
            }
        } else {
            tlContent.setVisibility(View.GONE);
        }
    }

    private RecyclerView.OnScrollListener onTabScroll() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int position = scrollerLinearLayoutManager.findFirstVisibleItemPosition();
                TabLayout.Tab tab = tlContent.getTabAt((int) contentAdapter.getHeaderId(position));
                if (tab != null) {
                    tlContent.removeOnTabSelectedListener(onTabSelectedListener);
                    if (!tab.isSelected()) {
                        tab.select();
                    }
                    tlContent.addOnTabSelectedListener(onTabSelectedListener);
                }
            }
        };
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = contentAdapter.getFirstDataInfoPositionByCameraId(tab.getPosition());
            if (position != -1) {
                rvContent.smoothScrollToPosition(position);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    private PermissionDeniedView.OnRequestPermissionClickListener onRequestPermissionClick() {
        return this::requestCameraPermission;
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

    private void requestCameraPermission() {
        QuickPermission.requestPermission(getActivity(), permissionResult -> {
            if (permissionResult.areAllPermissionsGranted()) {
                hideContent();
                hidePermissionDenied();
                collectCameraInfo();
            } else {
                showPermissionDenied();
            }
        }, Manifest.permission.CAMERA);
    }

    private void showPermissionDenied() {
        AnimationUtility.getInstance().fadeOut(layoutContent);
        AnimationUtility.getInstance().fadeOut(layoutLoading);
        AnimationUtility.getInstance().fadeIn(pdCameraPermission);
    }

    private void hidePermissionDenied() {
        AnimationUtility.getInstance().fadeOut(pdCameraPermission);
    }

    private void collectCameraInfo() {
        createCameraInfoObservable()
                .doOnNext(onCollectedCameraInfoAction())
                .doOnCompleted(onCompletedCameraInfoAction())
                .subscribe();
    }

    private Action1<? super CI> onCollectedCameraInfoAction() {
        return (Action1<CI>) cameraInfo -> {
            int cameraCount = cameraInfo.getCameraItemList().size();
            initTabLayout(cameraCount);
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

    private Observable<CI> createCameraInfoObservable() {
        return createScreenInfoCollectorObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<CI> createScreenInfoCollectorObservable() {
        return Observable.fromCallable(this::callCameraInfoCollector);
    }

    protected abstract CI callCameraInfoCollector();
}
