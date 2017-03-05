package com.akexorcist.deviceinformation.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseDdiActivity;
import com.akexorcist.deviceinformation.widget.SyncInfoView;

public class MainActivity extends BaseDdiActivity {
    private Toolbar tbContent;
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
        sivContent = (SyncInfoView) findViewById(R.id.siv_content);
        tlContent = (TabLayout) findViewById(R.id.tl_content);
        vpContent = (ViewPager) findViewById(R.id.vp_content);
    }

    @Override
    protected void setupView() {
        sivContent.setSyncClickListener(onSyncClick());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.menu_search) {
            Log.e("Check", "Menu Search");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        if (sivContent.isShowing()) {
            sivContent.hide();
        } else {
            sivContent.show();
        }
    }

    public SyncInfoView.SyncClickListener onSyncClick() {
        return () -> {
            // Do something
        };
    }
}
