package com.akexorcist.deviceinformation.module.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.module.application.ApplicationFragment;
import com.akexorcist.deviceinformation.module.camera2.Camera2Fragment;
import com.akexorcist.deviceinformation.module.camera.CameraFragment;
import com.akexorcist.deviceinformation.module.feature.FeatureFragment;
import com.akexorcist.deviceinformation.module.hardwaresoftware.HardwareSoftwareFragment;
import com.akexorcist.deviceinformation.module.screen.ScreenFragment;
import com.akexorcist.deviceinformation.module.sensor.SensorFragment;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class TabContentAdapter extends FragmentStatePagerAdapter {
    private static final int PAGE_HARDWARE_AND_SOFTWARE = 0;
    private static final int PAGE_SENSOR = 1;
    private static final int PAGE_SCREEN = 2;
    private static final int PAGE_CAMERA = 3;
    private static final int PAGE_CAMERA_2 = 4;
    private static final int PAGE_FEATURE = 5;
    private static final int PAGE_APP_LIST = 6;
    private static final int TOTAL_PAGE_COUNT = 7;

    private Context context;

    public TabContentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == PAGE_HARDWARE_AND_SOFTWARE) {
            return HardwareSoftwareFragment.newInstance();
        } else if (position == PAGE_SENSOR) {
            return SensorFragment.newInstance();
        } else if (position == PAGE_SCREEN) {
            return ScreenFragment.newInstance();
        } else if (position == PAGE_CAMERA) {
            return CameraFragment.newInstance();
        } else if (position == PAGE_CAMERA_2) {
            return Camera2Fragment.newInstance();
        } else if (position == PAGE_FEATURE) {
            return FeatureFragment.newInstance();
        } else if (position == PAGE_APP_LIST) {
            return ApplicationFragment.newInstance();
        }
        throw new NullPointerException("Position doesn't match with any fragment");
    }

    @Override
    public int getCount() {
        return TOTAL_PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == PAGE_HARDWARE_AND_SOFTWARE) {
            return context.getString(R.string.title_hardware_software);
        } else if (position == PAGE_SENSOR) {
            return context.getString(R.string.title_sensor);
        } else if (position == PAGE_SCREEN) {
            return context.getString(R.string.title_screen);
        } else if (position == PAGE_CAMERA) {
            return context.getString(R.string.title_camera);
        } else if (position == PAGE_CAMERA_2) {
            return context.getString(R.string.title_camera_2);
        } else if (position == PAGE_FEATURE) {
            return context.getString(R.string.title_feature);
        } else if (position == PAGE_APP_LIST) {
            return context.getString(R.string.title_app_list);
        }
        throw new NullPointerException("Position doesn't match with any page title");
    }
}
