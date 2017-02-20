package com.akexorcist.deviceinformation.module.application;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.application.ApplicationInfoCollector;
import com.akexorcist.deviceinformation.collector.application.model.AppInfo;
import com.akexorcist.deviceinformation.collector.application.model.AppItem;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.RxGenerator;

import java.util.concurrent.Callable;
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

public class ApplicationFragment extends DdiFragment {
    private FrameLayout layoutContent;
    private FrameLayout layoutLoading;
    private FrameLayout layoutBottomSheet;
    private LinearLayout layoutAppInfoContainer;
    private TextView tvBottomSheetName;

    private RecyclerView rvContent;
    private SwipeRefreshLayout srlRefresh;
    private ApplicationContentAdapter contentAdapter;
    private BottomSheetBehavior appBottomSheetBehavior;
    private StickyHeaderDecoration stickyHeaderDecoration;

    public static ApplicationFragment newInstance() {
        return new ApplicationFragment();
    }

    public ApplicationFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_app_list;
    }

    @Override
    protected void bindView(View view) {
        layoutContent = (FrameLayout) view.findViewById(R.id.layout_application_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_application_loading);
        layoutBottomSheet = (FrameLayout) view.findViewById(R.id.layout_application_bottom_sheet);
        layoutAppInfoContainer = (LinearLayout) view.findViewById(R.id.layout_application_bottom_sheet_container);
        tvBottomSheetName = (TextView) view.findViewById(R.id.tv_application_bottom_sheet_name);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_application_content);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_application_refresh);
    }

    @Override
    protected void setupView() {
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        layoutBottomSheet.setOnClickListener(onBottomSheetClick());
        appBottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        appBottomSheetBehavior.setHideable(true);
        appBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        contentAdapter = new ApplicationContentAdapter();
        contentAdapter.setOnAppContentClickListener(onAppInfoClick());
        stickyHeaderDecoration = new StickyHeaderDecoration(contentAdapter);
        rvContent.addItemDecoration(stickyHeaderDecoration);
        rvContent.setAdapter(contentAdapter);
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        collectApplicationInfo();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        forceHideContent();
        collectApplicationInfo();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    private ApplicationContentAdapter.OnAppContentClickListener onAppInfoClick() {
        return this::showAppInfoBottomSheet;
    }

    private SwipeRefreshLayout.OnRefreshListener onContentRefresh() {
        return () -> {
            hideContent();
            srlRefresh.setRefreshing(false);
            RxGenerator.getInstance().createDelayObservable(1, TimeUnit.SECONDS)
                    .doOnCompleted(refreshAllInfoAction())
                    .subscribe();
        };
    }

    private View.OnClickListener onBottomSheetClick() {
        return v -> appBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private Action0 refreshAllInfoAction() {
        return this::collectApplicationInfo;
    }

    private void collectApplicationInfo() {
        createCollectAppInfoObservable(getContext())
                .subscribe(showAppInfo());
    }

    private Observable<AppInfo> createCollectAppInfoObservable(Context context) {
        return Observable.fromCallable(createCollectAppInfoCallable(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Callable<AppInfo> createCollectAppInfoCallable(Context context) {
        return () -> ApplicationInfoCollector.getInstance().collect(context);
    }

    private Action1<AppInfo> showAppInfo() {
        return appInfo -> {
            contentAdapter.setAppItemList(appInfo.getDownloadedAppItemList(), appInfo.getSystemAppItemList());
            contentAdapter.notifyDataSetChanged();
            showContent();
        };
    }

    private void showAppInfoBottomSheet(AppItem appItem) {
        tvBottomSheetName.setText(appItem.getName());
//        tvBottomSheetPackageName.setText(featureItem.getPackageName());
//        tvBottomSheetMinimumSdk.setText(getString(R.string.feature_api_level) + " " + featureItem.getMinimumSdk());
//        tvBottomSheetDescription.setText(Feature.getTextDescription(getContext(), featureItem.getPackageName()));
//        featureBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
