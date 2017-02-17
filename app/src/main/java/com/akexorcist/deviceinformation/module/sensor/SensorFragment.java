package com.akexorcist.deviceinformation.module.sensor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.sensor.SensorInfoCollector;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorInfo;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorItem;
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

public class SensorFragment extends DdiFragment {
    private FrameLayout layoutSensorLoading;
    private FrameLayout layoutSensorContent;
    private SwipeRefreshLayout srlRefresh;
    private RecyclerView rvContent;
    private SensorContentAdapter contentAdapter;

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
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_sensor_refresh);
        rvContent = (RecyclerView) view.findViewById(R.id.rv_sensor_content);
    }

    @Override
    protected void setupView() {
        setContentLayout(layoutSensorContent);
        setLoadingLayout(layoutSensorLoading);
        srlRefresh.setOnRefreshListener(onContentRefresh());
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        contentAdapter = new SensorContentAdapter();
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

    }

    @Override
    public void saveInstanceState(Bundle outState) {

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
}
