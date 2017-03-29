package com.akexorcist.deviceinformation.collector.screen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

import com.akexorcist.deviceinformation.collector.InfoResultType;
import com.akexorcist.deviceinformation.collector.screen.model.Resolution;
import com.akexorcist.deviceinformation.collector.screen.model.ScreenInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

import java.lang.reflect.Method;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class ScreenInfoCollector extends BaseInfoCollector {
    private static ScreenInfoCollector collector;

    public static ScreenInfoCollector getInstance() {
        if (collector == null) {
            collector = new ScreenInfoCollector();
        }
        return collector;
    }

    public ScreenInfo collect(Activity activity) {
        return new ScreenInfo()
                .setResolutionPx(getResolutionPx(activity))
                .setResolutionDp(getResolutionDp(activity))
                .setDpiX(getDpiX(activity))
                .setDpiY(getDpiY(activity))
                .setDpi(getDpi(activity))
                .setSize(getSize(activity))
                .setDensity(getDensity(activity))
                .setRatio(getRatio(activity))
                .setMultitouch(getMultitouch(activity));
    }

    private String getResolutionPx(Activity activity) {
        Resolution screenResolution = getScreenResolutionPX(activity, null);
        return (int) screenResolution.getY() + " x " + (int) screenResolution.getX() + " PX";
    }

    private String getResolutionDp(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        Resolution screenResolution = getScreenResolutionPX(activity, display);
        int heightDp = (int) (screenResolution.getY() * (1f / dm.density));
        int widthDp = (int) (screenResolution.getX() * (1f / dm.density));
        return heightDp + " x " + widthDp + " DP";
    }

    private String getDpiX(Activity activity) {
        return getScreenResolutionDpi(activity).getX() + " DPI";
    }

    private String getDpiY(Activity activity) {
        return getScreenResolutionDpi(activity).getY() + " DPI";
    }

    private String getDpi(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi + " DPI";
    }

    private String getSize(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            return "Small";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            return "Normal";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return "Large";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            return "Extra Large";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_UNDEFINED) {
            return "Undefined";
        }
        return InfoResultType.UNKNOWN;
    }

    private String getDensity(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (dm.densityDpi == DisplayMetrics.DENSITY_LOW) {
            return "Low";
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_MEDIUM) {
            return "Medium";
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_TV) {
            return "TV";
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_HIGH) {
            return "High";
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
            return "Extra High";
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_XXHIGH) {
            return "Extra Extra High";
        } else if (dm.densityDpi == DisplayMetrics.DENSITY_XXXHIGH) {
            return "Extra Extra Extra High";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
                && dm.densityDpi == DisplayMetrics.DENSITY_260) {
            return "Density 260";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
                && dm.densityDpi == DisplayMetrics.DENSITY_280) {
            return "Density 280";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
                && dm.densityDpi == DisplayMetrics.DENSITY_300) {
            return "Density 300";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
                && dm.densityDpi == DisplayMetrics.DENSITY_340) {
            return "Density 340";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && dm.densityDpi == DisplayMetrics.DENSITY_360) {
            return "Density 360";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && dm.densityDpi == DisplayMetrics.DENSITY_400) {
            return "Density 400";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && dm.densityDpi == DisplayMetrics.DENSITY_420) {
            return "Density 420";
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && dm.densityDpi == DisplayMetrics.DENSITY_560) {
            return "Density 560";
        }
        return InfoResultType.UNKNOWN;
    }

    private String getRatio(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_LONG_MASK;
        if (screenSize == Configuration.SCREENLAYOUT_LONG_YES) {
            return "Long";
        } else if (screenSize == Configuration.SCREENLAYOUT_LONG_NO) {
            return "Not Long";
        } else if (screenSize == Configuration.SCREENLAYOUT_LONG_UNDEFINED) {
            return "Undefined";
        }
        return InfoResultType.UNKNOWN;
    }

    private String getMultitouch(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND)) {
            return "5+ Points";
        } else if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT)) {
            return "2-5 Points";
        } else if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)) {
            return "2 Points";
        }
        return "Not supported";
    }

    @SuppressWarnings("deprecation")
    private Resolution getScreenResolutionPX(Activity activity, Display display) {
        if (display == null) {
            display = activity.getWindowManager().getDefaultDisplay();
        }
        int resolutionX = 0, resolutionY = 0;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                resolutionX = (Integer) mGetRawW.invoke(display);
                resolutionY = (Integer) mGetRawH.invoke(display);
            } catch (Exception e) {
                resolutionX = display.getWidth();
                resolutionY = display.getHeight();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getRealMetrics(outMetrics);
            resolutionX = outMetrics.widthPixels;
            resolutionY = outMetrics.heightPixels;
        }
        return new Resolution(resolutionX, resolutionY);
    }

    private Resolution getScreenResolutionDpi(Activity activity) {
        float densityX = 0;
        float densityY = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            densityY = activity.getResources().getDisplayMetrics().ydpi;
            densityX = activity.getResources().getDisplayMetrics().xdpi;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getRealMetrics(outMetrics);
            densityY = outMetrics.ydpi;
            densityX = outMetrics.xdpi;
        }
        return new Resolution(densityX, densityY);
    }
}
