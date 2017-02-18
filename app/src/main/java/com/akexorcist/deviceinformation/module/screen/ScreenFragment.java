package com.akexorcist.deviceinformation.module.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.screen.ScreenInfoCollector;
import com.akexorcist.deviceinformation.collector.screen.model.ScreenInfo;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.utility.RxGenerator;
import com.akexorcist.deviceinformation.widget.InfoCardView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class ScreenFragment extends DdiFragment {
    private FrameLayout layoutContent;
    private FrameLayout layoutLoading;
    private SwipeRefreshLayout srlRefresh;
    private InfoCardView icvScreenInfo;
    private Button btnScreenMeasurement;

    public static ScreenFragment newInstance() {
        return new ScreenFragment();
    }

    public ScreenFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_screen;
    }

    @Override
    protected void bindView(View view) {
        layoutContent = (FrameLayout) view.findViewById(R.id.layout_screen_content);
        layoutLoading = (FrameLayout) view.findViewById(R.id.layout_screen_loading);
        srlRefresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_screen_refresh);
        icvScreenInfo = (InfoCardView) view.findViewById(R.id.icv_screen_info);
        btnScreenMeasurement = (Button) view.findViewById(R.id.btn_screen_measurement);
    }

    @Override
    protected void setupView() {
        srlRefresh.setOnRefreshListener(onContentRefresh());
        // Temporary disable swipe refresh layout in this version
        srlRefresh.setEnabled(false);
        setContentLayout(layoutContent);
        setLoadingLayout(layoutLoading);
        btnScreenMeasurement.setOnClickListener(onScreenMeasurementButtonClick());
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        forceHideContent();
        collectScreenInfo();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void restoreView() {
        forceHideContent();
        collectScreenInfo();
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    public View.OnClickListener onScreenMeasurementButtonClick() {
        return v -> getActivity().startActivity(new Intent(getContext(), ScreenMeasurementActivity.class));
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

    private void collectScreenInfo() {
        createCollectScreenInfoObservable(getActivity())
                .subscribe(showScreenInfo());
    }

    private Observable<ScreenInfo> createCollectScreenInfoObservable(Activity activity) {
        return Observable.just(ScreenInfoCollector.getInstance().collect(activity))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Action1<ScreenInfo> showScreenInfo() {
        return screenInfo -> {
            icvScreenInfo.setDataInfoList(screenInfo.getDataInfoList());
            showContent();
        };
    }

    private Action0 refreshAllInfoAction() {
        return this::collectScreenInfo;
    }
}
