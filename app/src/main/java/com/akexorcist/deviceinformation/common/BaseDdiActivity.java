package com.akexorcist.deviceinformation.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public abstract class BaseDdiActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutView();
        if (layoutResId != 0) {
            setContentView(layoutResId);
        }
        bindView();
        setupView();
        setupToolbar();
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    restoreArgument(bundle);
                }
            }
            initialize();
        }
    }

    protected abstract int getLayoutView();

    protected abstract void bindView();

    protected abstract void setupView();

    protected abstract void setupToolbar();

    protected abstract void restoreArgument(Bundle bundle);

    protected abstract void initialize();

    public abstract void restoreInstanceState(Bundle savedInstanceState);

    public abstract void restoreView();

    public abstract void saveInstanceState(Bundle outState);

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreInstanceState(savedInstanceState);
        restoreView();
    }
}
