package com.akexorcist.deviceinformation.module.search;

import android.os.Bundle;
import android.widget.Toast;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseDdiActivity;
import com.akexorcist.deviceinformation.network.DeviceSyncManager;
import com.akexorcist.deviceinformation.network.response.BrandList;
import com.akexorcist.deviceinformation.network.response.DeviceSyncResult;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DeviceSearchActivity extends BaseDdiActivity {

    @Override
    protected int getLayoutView() {
        return R.layout.activity_device_search;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void setupToolbar() {

    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {
        getAllBrand();
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

    private void getAllBrand() {
        DeviceSyncManager.getInstance()
                .getAllBrand()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Toast.makeText(DeviceSearchActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnNext(new Action1<DeviceSyncResult<BrandList>>() {
                    @Override
                    public void call(DeviceSyncResult<BrandList> brandListDeviceSyncResult) {

                        Toast.makeText(DeviceSearchActivity.this, "Set Brand List : " + brandListDeviceSyncResult.getData().getSelfLink(), Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Toast.makeText(DeviceSearchActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(DeviceSearchActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }
                })
                .subscribe();
    }
}
