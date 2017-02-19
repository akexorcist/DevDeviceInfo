package com.akexorcist.deviceinformation.module.application;

import android.os.Bundle;
import android.view.View;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseDdiFragment;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class ApplicationFragment extends BaseDdiFragment {

    public static ApplicationFragment newInstance() {
        return new ApplicationFragment();
    }

    public ApplicationFragment() {
    }

    @Override
    protected int getLayoutView() {
        return R.layout.fragment_app_list;
    }

    @Override
    protected void bindView(View view) {

    }

    @Override
    protected void setupView() {

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
}
