package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.TelephonyManager;

import com.akexorcist.deviceinformation.collector.InfoResultType;
import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.CommunicationInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;
import com.akexorcist.deviceinformation.utility.TelephonyInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CommunicationInfoCollector extends BaseInfoCollector {
    private static final String GET_CARACTERISTIC_PROCESS_COMMMAND = "getprop ro.build.characteristics";

    private static CommunicationInfoCollector collector;

    public static CommunicationInfoCollector getInstance() {
        if (collector == null) {
            collector = new CommunicationInfoCollector();
        }
        return collector;
    }

    public CommunicationInfo collect(Context context) {
        return new CommunicationInfo()
                .setBluetooth(hasBluetooth(context))
                .setBluetoothLowEnergy(hasBluetoothLowEnergy(context))
                .setCellular(hasCellular(context))
                .setCardboardVr(hasCardboardVr(context))
                .setDaydreamVr(hasDaydreamVr(context))
                .setDualSim(hasDualSim(context))
                .setEthernet(hasEthernet(context))
                .setFingerprint(hasFingerprint(context))
                .setGps(hasGps(context))
                .setMicrophone(hasMicrophone(context))
                .setNfc(hasNfc(context))
                .setNfcHostCardEmulation(hasNfcHostCardEmulation(context))
                .setNfcFHostCardEmulation(hasNfcFHostCardEmulation(context))
                .setTelephony(hasTelephony(context))
                .setUsbAccessory(hasUsbAccessory(context))
                .setUsbOtg(hasUsbOtg(context))
                .setVibrateMotor(hasVibrateMotor(context))
                .setWifi(hasWiFi(context))
                .setWifiDirect(hasWiFiDirect(context))
                .setWiMax(hasWiMax(context));
    }

    private String hasBluetooth(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasBluetoothLowEnergy(Context context) {
        String hasBluetooth = hasBluetooth(context);
        if (hasBluetooth.equals(InfoResultType.YES)
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
                && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    @SuppressWarnings("deprecation")
    private String hasCellular(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            return InfoResultType.YES;
        } catch (NullPointerException ignored) {
        }
        return InfoResultType.NO;
    }

    private String hasCardboardVr(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_VR_MODE)) {
                return InfoResultType.YES;
            }
            return InfoResultType.NO;
        }
        return InfoResultType.UNKNOWN;
    }

    private String hasDaydreamVr(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N &&
                context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_VR_MODE_HIGH_PERFORMANCE)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    @SuppressWarnings("deprecation")
    private String hasEthernet(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            manager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET).getState();
            return InfoResultType.YES;
        } catch (NullPointerException ignored) {
        }
        return InfoResultType.NO;
    }

    private String hasFingerprint(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasGps(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasMicrophone(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasNfc(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasNfcHostCardEmulation(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasNfcFHostCardEmulation(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION_NFCF)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasTelephony(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasUsbAccessory(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    private String hasUsbOtg(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_HOST)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    @SuppressLint("NewApi")
    private String hasVibrateMotor(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    @SuppressWarnings("deprecation")
    private String hasWiFi(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            return InfoResultType.YES;
        } catch (NullPointerException ignored) {
        }
        return InfoResultType.NO;
    }

    private String hasWiFiDirect(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
                && context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_DIRECT)) {
            return InfoResultType.YES;
        }
        return InfoResultType.NO;
    }

    @SuppressWarnings("deprecation")
    private String hasWiMax(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            manager.getNetworkInfo(ConnectivityManager.TYPE_WIMAX).getState();
            return InfoResultType.YES;
        } catch (NullPointerException ignored) {
        }
        return InfoResultType.NO;
    }

    private String hasDualSim(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int phoneCount = telephonyManager.getPhoneCount();
            if (phoneCount == 2) {
                return InfoResultType.YES;
            }
            return InfoResultType.NO;
        } else {
            TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(context);
            if (telephonyInfo.isDualSIM()) {
                return InfoResultType.YES;
            }
            return InfoResultType.NO;
        }
    }
}
