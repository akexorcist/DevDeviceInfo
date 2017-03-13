package com.akexorcist.deviceinformation.module.chooser;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.DdiFragment;
import com.akexorcist.deviceinformation.module.chooser.adapter.DeviceChooserContentAdapter;
import com.akexorcist.deviceinformation.network.DeviceSyncManager;
import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;
import com.akexorcist.deviceinformation.utility.AnimationUtility;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
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
        contentAdapter.setOnItemClickListener(onItemClick());
        rvDeviceBrand.setAdapter(contentAdapter);
        forceHideLoading();
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        getAllBrand();
    }

    private void getAllBrand() {
        DeviceSyncManager.getInstance()
                .getAllBrand()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable.empty())
                .doOnSubscribe(this::showLoading)
                .doOnNext(this::setBrandListResult)
                .doOnError(throwable -> setBrandListFailure())
                .doOnCompleted(this::hideLoading)
                .subscribe();
    }

    private DeviceChooserContentAdapter.OnItemClickListener onItemClick() {
        return new DeviceChooserContentAdapter.OnItemClickListener() {
            @Override
            public void onBrandClick(String brand) {
                Toast.makeText(getContext(), brand + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReloadClick() {
                getAllBrand();
            }
        };
    }

    private void setBrandListResult(DeviceSyncResult<BrandList> brandListResult) {
        if (brandListResult != null && brandListResult.isDataAvailable()) {
            setBrandList(brandListResult.getData().getBrandList());
        } else {
            setBrandListFailure();
        }
    }

    private void setBrandListFailure() {
        setBrandList(null);
        hideLoading();
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
