package com.akexorcist.deviceinformation.module.feature;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.feature.FeatureInfoCollector;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureInfo;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureItem;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.RxGenerator;

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

public class FeatureFragment extends DdiFragment {
    private FrameLayout layoutContent;
    private FrameLayout layoutLoading;
    private FrameLayout layoutBottomSheet;
    private LinearLayout layoutFeatureInfoContent;
    private LinearLayout layoutFeatureInfoContainer;
    private RecyclerView rvContent;
    private SwipeRefreshLayout srlRefresh;
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
        layoutContent = (FrameLayout) view.findViewById(R.id.layout_feature_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_feature_loading);
        layoutBottomSheet = (FrameLayout) view.findViewById(R.id.layout_feature_bottom_sheet);
        layoutFeatureInfoContent = (LinearLayout) view.findViewById(R.id.layout_feature_bottom_sheet_content);
        layoutFeatureInfoContainer = (LinearLayout) view.findViewById(R.id.layout_feature_bottom_sheet_container);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_feature_content);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_feature_refresh);
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
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        contentAdapter = new FeatureContentAdapter();
        contentAdapter.setOnFeatureInfoClickListener(onFeatureInfoClick());
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
                .subscribe(showSensorFeature());
    }

    private Observable<FeatureInfo> createCollectFeatureInfoObservable(Context context) {
        return Observable.just(FeatureInfoCollector.getInstance().collect(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Action1<FeatureInfo> showSensorFeature() {
        return featureInfo -> {
            contentAdapter.setFeatureItemList(featureInfo.getSupportedFeatureItem(), featureInfo.getUnsupportedFeatureItem());
            contentAdapter.notifyDataSetChanged();
            showContent();
        };
    }

    private void showFeatureInfoBottomSheet(FeatureItem featureItem) {
        featureBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        layoutFeatureInfoContent.removeAllViews();
//        tvBottomSheetTitle.setText(featureItem.getName());
//        if (featureItem.getDataInfoList() != null) {
//            for (DataInfo sensorData : featureItem.getDataInfoList()) {
//                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_sensor_info_item, layoutSensorContent, false);
//                TextView tvTitle = (TextView) view.findViewById(R.id.tv_sensor_info_title);
//                TextView tvValue = (TextView) view.findViewById(R.id.tv_sensor_info_value);
//                tvTitle.setText(sensorData.getTitle());
//                tvValue.setText(sensorData.getValue());
//                layoutSensorInfoContent.addView(view);
//            }
//        }
    }
}
