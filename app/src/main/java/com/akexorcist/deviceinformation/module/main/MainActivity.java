package com.akexorcist.deviceinformation.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.CpuInfoCollector;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.CpuInfo;
import com.akexorcist.deviceinformation.common.BaseDdiActivity;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.widget.SyncInfoView;

public class MainActivity extends BaseDdiActivity implements View.OnClickListener, SyncInfoView.SyncClickListener {
    private Toolbar tbContent;
    private Button btnClick;
    private SyncInfoView sivContent;
    private TabLayout tlContent;
    private ViewPager vpContent;
    private TabContentAdapter tabContentAdapter;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindView() {
        tbContent = (Toolbar) findViewById(R.id.tb_content);
        btnClick = (Button) findViewById(R.id.btn_click);
        sivContent = (SyncInfoView) findViewById(R.id.siv_content);
        tlContent = (TabLayout) findViewById(R.id.tl_content);
        vpContent = (ViewPager) findViewById(R.id.vp_content);
    }

    @Override
    protected void setupView() {
        btnClick.setOnClickListener(this);
        sivContent.setSyncClickListener(this);
        tabContentAdapter = new TabContentAdapter(this, getSupportFragmentManager());
        vpContent.setAdapter(tabContentAdapter);
        tlContent.setupWithViewPager(vpContent);
    }

    @Override
    protected void setupToolbar() {
        setSupportActionBar(tbContent);
        setTitle(R.string.app_name);
    }

    @Override
    protected void restoreArgument(Bundle bundle) {

    }

    @Override
    protected void initialize() {

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

    @Override
    public void onClick(View view) {
        if (view == btnClick) {
            toggle();
        }
    }

    private void toggle() {
        if (sivContent.isShowing()) {
            sivContent.hide();
        } else {
            sivContent.show();
        }
    }

    @Override
    public void onSyncClick() {
        CpuInfo cpuInfo = CpuInfoCollector.getInstance().collect(this);
        if (cpuInfo != null) {
            for (DataInfo dataInfo : cpuInfo.getDataInfoList()) {
                Log.e("Check", dataInfo.getTitle() + " : " + dataInfo.getValue());
            }
        }
//        SensorInfoCollector.getInstance().collect(this);
//        Log.e("Check", "Daydream VR Supported : " + CommunicationInfoCollector.getInstance().collect(this).getDaydreamVr());
//        Log.e("Check", "Total Internal Storage : " + StorageInfoCollector.getInstance().getTotalInternalStorage());
//        Log.e("Check", "SD Card Supported : " + StorageInfoCollector.getInstance().getSdCardSupported());
    }
}
