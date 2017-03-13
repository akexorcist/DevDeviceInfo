package com.akexorcist.deviceinformation.module.chooser;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.module.chooser.adapter.DeviceChooserContentAdapter;
import com.akexorcist.deviceinformation.network.DeviceSyncManager;
import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;
import com.akexorcist.deviceinformation.utility.AnimationUtility;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class DeviceChooserFragment extends DdiFragment {
    private RecyclerView rvDeviceBrand;
    private DeviceChooserContentAdapter contentAdapter;
    private AVLoadingIndicatorView pbLoading;

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_device_chooser;
    }

    @Override
    protected void bindView(View view) {
        rvDeviceBrand = (RecyclerView) view.findViewById(R.id.rv_device_chooser_brand);
        pbLoading = (AVLoadingIndicatorView) view.findViewById(R.id.pb_device_chooser_loading);
    }

    @Override
    protected void setupView() {
        rvDeviceBrand.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        contentAdapter = new DeviceChooserContentAdapter();
        rvDeviceBrand.setAdapter(contentAdapter);
        forceHideLoading();
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        DeviceSyncManager.getInstance()
                .getAllBrand()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading();
//                        Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnNext(new Action1<DeviceSyncResult<BrandList>>() {
                    @Override
                    public void call(DeviceSyncResult<BrandList> brandListDeviceSyncResult) {
                        if (brandListDeviceSyncResult != null && brandListDeviceSyncResult.isDataAvailable()) {
                            setBrandList(brandListDeviceSyncResult.getData().getBrandList());
                        } else {
                            setBrandList(null);
                            hideLoading();
                        }
//                        Toast.makeText(getContext(), "Set Brand List : " + brandListDeviceSyncResult.getData().getSelfLink(), Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        hideLoading();
//                        Toast.makeText(getContext(), "Finished", Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        setBrandList(null);
                        hideLoading();
//                        Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe();
    }

    private void setBrandList(List<String> brandList) {
        contentAdapter.setBrandList(brandList);
        contentAdapter.notifyDataSetChanged();
    }

    private void forceShowLoading() {
        AnimationUtility.getInstance().fadeIn(pbLoading, 0);
    }

    private void forceHideLoading() {
        AnimationUtility.getInstance().fadeOut(pbLoading, 0);
    }

    private void showLoading() {
        AnimationUtility.getInstance().fadeIn(pbLoading);
    }

    private void hideLoading() {
        AnimationUtility.getInstance().fadeOut(pbLoading);
    }
}
