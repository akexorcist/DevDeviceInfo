package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.content.Context;

import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.BatteryInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BatteryInfoCollector extends BaseInfoCollector {
    private static final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";
    private static final String GET_AVERAGE_POWER_METHOD = "getAveragePower";
    private static final String BATTERY_CAPACITY_VARIABLE = "battery.capacity";

    private static BatteryInfoCollector collector;

    public static BatteryInfoCollector getInstance() {
        if (collector == null) {
            collector = new BatteryInfoCollector();
        }
        return collector;
    }

    @Override
    public BatteryInfo collect(Context context) {
        return new BatteryInfo()
                .setCapacity(getCapacity(context));
    }

    private String getCapacity(Context context) {
        Object powerProfile = null;
        try {
            powerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(context);
        } catch (Exception ignored) {
        }
        try {
            double capability = (Double) Class.forName(POWER_PROFILE_CLASS)
                    .getMethod(GET_AVERAGE_POWER_METHOD, java.lang.String.class)
                    .invoke(powerProfile, BATTERY_CAPACITY_VARIABLE);
            int batteryCapacity = (int) capability;
            if (batteryCapacity != 1000 && batteryCapacity != 0) {
                return batteryCapacity + " mA";
            }
        } catch (Exception ignored) {
        }
        return "Unknown";
    }
}
