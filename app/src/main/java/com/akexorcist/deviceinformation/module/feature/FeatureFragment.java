package com.akexorcist.deviceinformation.module.feature;

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
import com.akexorcist.deviceinformation.collector.feature.FeatureInfoCollector;
import com.akexorcist.deviceinformation.collector.feature.model.Feature;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureInfo;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureItem;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.ScrollerLinearLayoutManager;

import java.util.concurrent.TimeUnit;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class FeatureFragment extends DdiFragment {
    private LinearLayout layoutContent;
    private FrameLayout layoutLoading;
    private FrameLayout layoutBottomSheet;
    private LinearLayout layoutFeatureInfoContainer;
    private TextView tvBottomSheetName;
    private TextView tvBottomSheetPackageName;
    private TextView tvBottomSheetMinimumSdk;
    private TextView tvBottomSheetDescription;
    private RecyclerView rvContent;
    private SwipeRefreshLayout srlRefresh;
    private TabLayout tlContent;
    private FeatureContentAdapter contentAdapter;
    private BottomSheetBehavior featureBottomSheetBehavior;
    private StickyHeaderDecoration stickyHeaderDecoration;

    public static FeatureFragment newInstance() {
        return new FeatureFragment();
    }

    public FeatureFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_feature;
    }

    @Override
    protected void bindView(View view) {
        layoutContent = (LinearLayout) view.findViewById(R.id.layout_feature_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_feature_loading);
        layoutBottomSheet = (FrameLayout) view.findViewById(R.id.layout_feature_bottom_sheet);
        layoutFeatureInfoContainer = (LinearLayout) view.findViewById(R.id.layout_feature_bottom_sheet_container);
        tvBottomSheetName = (TextView) view.findViewById(R.id.tv_feature_bottom_sheet_name);
        tvBottomSheetPackageName = (TextView) view.findViewById(R.id.tv_feature_bottom_sheet_package_name);
        tvBottomSheetMinimumSdk = (TextView) view.findViewById(R.id.tv_feature_bottom_sheet_minimum_sdk);
        tvBottomSheetDescription = (TextView) view.findViewById(R.id.tv_feature_bottom_sheet_description);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_feature_content);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_feature_refresh);
        tlContent = (TabLayout) view.findViewById(R.id.tl_feature_content);
    }

    @Override
    protected void setupView() {
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        layoutBottomSheet.setOnClickListener(onBottomSheetClick());
        featureBottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        featureBottomSheetBehavior.setHideable(true);
        featureBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
        rvContent.setLayoutManager(new ScrollerLinearLayoutManager(getContext()));
        contentAdapter = new FeatureContentAdapter();
        contentAdapter.setOnFeatureInfoClickListener(onFeatureInfoClick());
        stickyHeaderDecoration = new StickyHeaderDecoration(contentAdapter);
        rvContent.addItemDecoration(stickyHeaderDecoration);
        rvContent.setAdapter(contentAdapter);
        tlContent.addOnTabSelectedListener(onTabSelected());
        setupTabLayout();
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        collectFeatureInfo();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        forceHideContent();
        collectFeatureInfo();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    private void setupTabLayout() {
        TabLayout.Tab supportedTab = tlContent.newTab();
        supportedTab.setText(R.string.feature_supported_feature_header);
        tlContent.addTab(supportedTab);
        TabLayout.Tab unsupportedTab = tlContent.newTab();
        unsupportedTab.setText(R.string.feature_unsupported_feature_header);
        tlContent.addTab(unsupportedTab);
    }

    private TabLayout.OnTabSelectedListener onTabSelected() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = contentAdapter.getFirstFeatureContentPositionByHeaderId(tab.getPosition());
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
    }

    private FeatureContentAdapter.OnFeatureInfoClickListener onFeatureInfoClick() {
        return this::showFeatureInfoBottomSheet;
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
        return v -> featureBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private Action0 refreshAllInfoAction() {
        return this::collectFeatureInfo;
    }

    private void collectFeatureInfo() {
        createCollectFeatureInfoObservable(getContext())
                .subscribe(showFeatureInfo());
    }

    private Observable<FeatureInfo> createCollectFeatureInfoObservable(Context context) {
        return Observable.just(FeatureInfoCollector.getInstance().collect(context))
                .compose(RxGenerator.getInstance().applySchedulers());
    }

    private Action1<FeatureInfo> showFeatureInfo() {
        return featureInfo -> {
            contentAdapter.setFeatureItemList(featureInfo.getSupportedFeatureItem(), featureInfo.getUnsupportedFeatureItem());
            contentAdapter.notifyDataSetChanged();
            showContent();
        };
    }

    private void showFeatureInfoBottomSheet(FeatureItem featureItem) {
        tvBottomSheetName.setText(featureItem.getName());
        tvBottomSheetPackageName.setText(featureItem.getPackageName());
        tvBottomSheetMinimumSdk.setText(getString(R.string.feature_api_level) + " " + featureItem.getMinimumSdk());
        tvBottomSheetDescription.setText(Feature.getTextDescription(getContext(), featureItem.getPackageName()));
        featureBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
