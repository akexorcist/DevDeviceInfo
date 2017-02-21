package com.akexorcist.deviceinformation.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.helper.permission.QuickPermission;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public abstract class BaseDdiFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                restoreArgument(bundle);
            }
        } else {
            restoreInstanceState(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutView(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
        if (savedInstanceState == null) {
            initialize();
        } else {
            restoreView();
        }
    }

    protected abstract int getLayoutView();

    protected abstract void bindView(View view);

    protected abstract void setupView();

    protected abstract void restoreArgument(Bundle bundle);

    protected abstract void initialize();

    public abstract void restoreInstanceState(Bundle savedInstanceState);

    public abstract void restoreView();

    public abstract void saveInstanceState(Bundle outState);

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveInstanceState(outState);
    }

    public void requestPermission(QuickPermission.PermissionCallback callback, String... permissions) {
        QuickPermission.requestPermission(getActivity(), callback, permissions);
    }
}
