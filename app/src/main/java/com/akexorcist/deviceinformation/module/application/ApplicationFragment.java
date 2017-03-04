package com.akexorcist.deviceinformation.module.application;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.akexorcist.deviceinformation.widget.ScrollerLinearLayoutManager;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class ApplicationFragment extends DdiFragment {
    private LinearLayout layoutContent;
    private FrameLayout layoutLoading;
    private FrameLayout layoutBottomSheet;
    private LinearLayout layoutAppInfoContainer;
    private TextView tvBottomSheetName;
    private RecyclerView rvContent;
    private SwipeRefreshLayout srlRefresh;
    private TabLayout tlContent;
    private ApplicationContentAdapter contentAdapter;
    private BottomSheetBehavior appBottomSheetBehavior;
    private StickyHeaderDecoration stickyHeaderDecoration;
    private ScrollerLinearLayoutManager scrollerLinearLayoutManager;

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
        layoutContent = (LinearLayout) view.findViewById(R.id.layout_application_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_application_loading);
        layoutBottomSheet = (FrameLayout) view.findViewById(R.id.layout_application_bottom_sheet);
        layoutAppInfoContainer = (LinearLayout) view.findViewById(R.id.layout_application_bottom_sheet_container);
        tvBottomSheetName = (TextView) view.findViewById(R.id.tv_application_bottom_sheet_name);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_application_content);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_application_refresh);
        tlContent = (TabLayout) view.findViewById(R.id.tl_application_content);
    }

    @Override
    protected void setupView() {
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        setupBottomSheet();
        setupSwipeRefresh();
        setupRecyclerView();
        setupTabLayout();
        initTabLayout();
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

    private void setupBottomSheet() {
        layoutBottomSheet.setOnClickListener(onBottomSheetClick());
        appBottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        appBottomSheetBehavior.setHideable(true);
        appBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void setupSwipeRefresh() {
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
    }

    private void setupRecyclerView() {
        scrollerLinearLayoutManager = new ScrollerLinearLayoutManager(getContext());
        rvContent.setLayoutManager(scrollerLinearLayoutManager);
        contentAdapter = new ApplicationContentAdapter();
        contentAdapter.setOnAppContentClickListener(onAppInfoClick());
        stickyHeaderDecoration = new StickyHeaderDecoration(contentAdapter);
        rvContent.addItemDecoration(stickyHeaderDecoration);
        rvContent.setAdapter(contentAdapter);
        rvContent.addOnScrollListener(onTabScroll());
    }

    private void setupTabLayout() {
        tlContent.addOnTabSelectedListener(onTabSelectedListener);
    }

    private void initTabLayout() {
        TabLayout.Tab supportedTab = tlContent.newTab();
        supportedTab.setText(R.string.application_downloaded_app_header);
        tlContent.addTab(supportedTab);
        TabLayout.Tab unsupportedTab = tlContent.newTab();
        unsupportedTab.setText(R.string.application_system_app_header);
        tlContent.addTab(unsupportedTab);
    }

    private RecyclerView.OnScrollListener onTabScroll() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int position = scrollerLinearLayoutManager.findFirstVisibleItemPosition();
                TabLayout.Tab tab = null;
                if (contentAdapter.isDownloadedAppContent(position)) {
                    tab = tlContent.getTabAt(0);
                } else if (contentAdapter.isSystemAppContent(position)) {
                    tab = tlContent.getTabAt(1);
                }
                if (tab != null) {
                    tlContent.removeOnTabSelectedListener(onTabSelectedListener);
                    tab.select();
                    tlContent.addOnTabSelectedListener(onTabSelectedListener);
                }
            }
        };
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = contentAdapter.getFirstAppContentPositionByHeaderId(tab.getPosition());
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
                .subscribe(showAppInfoAction());
    }

    private Observable<AppInfo> createCollectAppInfoObservable(Context context) {
        return Observable.fromCallable(createCollectAppInfoCallable(context))
                .compose(RxGenerator.getInstance().applySchedulers());
    }

    private Callable<AppInfo> createCollectAppInfoCallable(Context context) {
        return () -> ApplicationInfoCollector.getInstance().collect(context);
    }

    private Action1<AppInfo> showAppInfoAction() {
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
