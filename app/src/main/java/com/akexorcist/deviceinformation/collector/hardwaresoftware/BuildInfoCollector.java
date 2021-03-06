package com.akexorcist.deviceinformation.collector.hardwaresoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import com.akexorcist.deviceinformation.collector.hardwaresoftware.model.BuildInfo;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;
import com.akexorcist.deviceinformation.utility.AndroidUtility;
import com.akexorcist.deviceinformation.utility.StringUtility;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BuildInfoCollector extends BaseInfoCollector {
    private static final String GET_CHARACTERISTIC_PROCESS_COMMAND = "getprop ro.build.characteristics";

    private static BuildInfoCollector collector;

    public static BuildInfoCollector getInstance() {
        if (collector == null) {
            collector = new BuildInfoCollector();
        }
        return collector;
    }

    @Override
    public BuildInfo collect(Context context) {
        return new BuildInfo()
                .setBoard(getBoard())
                .setBootloader(getBootloader())
                .setBrand(getBrand())
                .setCharacteristic(getCharacteristic())
                .setCpuAbi(getCpuAbi())
                .setCpuAbi2(getCpuAbi2())
                .setDevice(getDevice())
                .setDisplay(getDisplay())
                .setFingerprint(getFingerprint())
                .setHardware(getHardware())
                .setHost(getHost())
                .setId(getId())
                .setManufacturer(getManufacturer())
                .setModel(getModel())
                .setProduct(getProduct())
                .setRadio(getRadio())
                .setSerial(getSerial())
                .setSupportedAbis(getSupportAbis())
                .setSupported32BitAbis(getSupport32Abis())
                .setSupported64BitAbis(getSupport64Abis())
                .setTags(getTag())
                .setTime(getTime())
                .setType(getType())
                .setUser(getUser());
    }

    private String getBoard() {
        return Build.BOARD;
    }

    private String getBootloader() {
        return Build.BOOTLOADER;
    }

    private String getBrand() {
        return Build.BRAND;
    }

    private String getCharacteristic() {
        try {
            Process process = Runtime.getRuntime().exec(GET_CHARACTERISTIC_PROCESS_COMMAND);
            InputStream inputStream = process.getInputStream();
            String size = AndroidUtility.getInstance().getStringFromInputStream(inputStream);
            if (!size.equals("\n"))
                return size.trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    @SuppressWarnings("deprecation")
    private String getCpuAbi() {
        return Build.CPU_ABI;
    }

    @SuppressWarnings("deprecation")
    private String getCpuAbi2() {
        return Build.CPU_ABI2;
    }

    private String getDevice() {
        return Build.DEVICE;
    }

    private String getDisplay() {
        return Build.DISPLAY;
    }

    private String getFingerprint() {
        return Build.FINGERPRINT;
    }

    private String getHardware() {
        return Build.HARDWARE;
    }

    private String getHost() {
        return Build.HOST;
    }

    private String getId() {
        return Build.ID;
    }

    private String getManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getModel() {
        return Build.MODEL;
    }

    private String getProduct() {
        return Build.PRODUCT;
    }

    @SuppressWarnings("deprecation")
    private String getRadio() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return StringUtility.getInstance().wrapUnknown(Build.getRadioVersion());
        }
        return Build.RADIO;
    }

    private String getSerial() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Build.SERIAL;
        }
        return "Unknown";
    }

    private String getAbis(String[] supportList) {
        String str = "";
        for (int i = 0; i < supportList.length; i++) {
            str += supportList[i] + " ";
        }
        return str.trim();
    }

    @SuppressLint("NewApi")
    private String getSupportAbis() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getAbis(Build.SUPPORTED_ABIS);
        }
        return "Unknown";
    }

    @SuppressLint("NewApi")
    private String getSupport32Abis() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getAbis(Build.SUPPORTED_32_BIT_ABIS);
        }
        return "Unknown";
    }

    @SuppressLint("NewApi")
    private String getSupport64Abis() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getAbis(Build.SUPPORTED_64_BIT_ABIS);
        }
        return "Unknown";
    }

    private String getTag() {
        return Build.TAGS;
    }

    private String getTime() {
        return Build.TIME + "";
    }

    private String getType() {
        return Build.TYPE;
    }

    private String getUser() {
        return Build.USER;
    }
}
