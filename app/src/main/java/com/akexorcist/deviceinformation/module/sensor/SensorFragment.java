package com.akexorcist.deviceinformation.module.sensor;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.sensor.SensorInfoCollector;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorInfo;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorItem;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.RxGenerator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class SensorFragment extends DdiFragment implements SensorContentAdapter.OnSensorInfoClickListener {
    private FrameLayout layoutSensorLoading;
    private FrameLayout layoutSensorContent;
    private FrameLayout layoutBottomSheet;
    private LinearLayout layoutSensorInfoContent;
    private LinearLayout layoutSensorInfoContainer;
    private SwipeRefreshLayout srlRefresh;
    private RecyclerView rvContent;
    private TextView tvBottomSheetTitle;
    private SensorContentAdapter contentAdapter;
    private BottomSheetBehavior sensorBottomSheetBehavior;

    public static SensorFragment newInstance() {
        return new SensorFragment();
    }

    public SensorFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_sensor;
    }

    @Override
    protected void bindView(View view) {
        layoutSensorContent = (FrameLayout) view.findViewById(R.id.layout_sensor_content);
        layoutSensorLoading = (FrameLayout) view.findViewById(R.id.layout_sensor_loading);
        layoutBottomSheet = (FrameLayout) view.findViewById(R.id.layout_sensor_bottom_sheet);
        layoutSensorInfoContent = (LinearLayout) view.findViewById(R.id.layout_sensor_bottom_sheet_content);
        layoutSensorInfoContainer = (LinearLayout) view.findViewById(R.id.layout_sensor_bottom_sheet_container);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_sensor_refresh);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_sensor_content);
        tvBottomSheetTitle = (TextView) view.findViewById(R.id.tv_sensor_bottom_sheet_title);
    }

    @Override
    protected void setupView() {
        setContentLayout(layoutSensorContent);
        setLoadingLayout(layoutSensorLoading);
        layoutBottomSheet.setOnClickListener(onBottomSheetClick());
        layoutSensorInfoContainer.setClickable(true);
        sensorBottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sensorBottomSheetBehavior.setHideable(true);
        sensorBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        srlRefresh.setOnRefreshListener(onContentRefresh());
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        contentAdapter = new SensorContentAdapter();
        contentAdapter.setOnSensorInfoClickListener(this);
        rvContent.setAdapter(contentAdapter);
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        collectSensorInfo();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        collectSensorInfo();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    @Override
    public void onSensorInfoClick(SensorItem sensorItem) {
        showSensorInfoBottomSheet(sensorItem);
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
        return v -> sensorBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private Action0 refreshAllInfoAction() {
        return this::collectSensorInfo;
    }

    private void collectSensorInfo() {
        createCollectSensorInfoObservable(getContext())
                .flatMap(mapSensorInfoToSensorItem())
                .subscribe(showSensorInfo());
    }

    private Func1<SensorInfo, Observable<List<SensorItem>>> mapSensorInfoToSensorItem() {
        return sensorInfo -> Observable.just(sensorInfo.getSensorItemList());
    }

    private Observable<SensorInfo> createCollectSensorInfoObservable(Context context) {
        return Observable.just(SensorInfoCollector.getInstance().collect(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Action1<List<SensorItem>> showSensorInfo() {
        return sensorItemList -> {
            contentAdapter.setSensorItemList(sensorItemList);
            contentAdapter.notifyDataSetChanged();
            showContent();
        };
    }

    private void showSensorInfoBottomSheet(SensorItem sensorItem) {
        sensorBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        layoutSensorInfoContent.removeAllViews();
        tvBottomSheetTitle.setText(sensorItem.getName());
        if (sensorItem.getSensorData() != null) {
            for (DataInfo sensorData : sensorItem.getSensorData().getDataInfoList()) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_sensor_info_item, layoutSensorContent, false);
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_sensor_info_title);
                TextView tvValue = (TextView) view.findViewById(R.id.tv_sensor_info_value);
                tvTitle.setText(sensorData.getTitle());
                tvValue.setText(sensorData.getValue());
                layoutSensorInfoContent.addView(view);
            }
        }
    }
}
