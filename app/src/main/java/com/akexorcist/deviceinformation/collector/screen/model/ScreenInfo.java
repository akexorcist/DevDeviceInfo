package com.akexorcist.deviceinformation.collector.screen.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class ScreenInfo extends BaseInfo {
    private static final String RESOLUTION_PX = "Resolution (PX)";
    private static final String RESOLUTION_DP = "Resolution (DP)";
    private static final String DPI_X = "DPI (X)";
    private static final String DPI_Y = "DPI (Y)";
    private static final String DPI = "DPI";
    private static final String SIZE = "Size";
    private static final String DENSITY = "Density";
    private static final String RATIO = "Ratio";
    private static final String MULTITOUCH = "Multitouch";

    public ScreenInfo() {
        super();
    }

    public String getResolutionPx() {
        return getValueByTitle(RESOLUTION_PX);
    }

    public ScreenInfo setResolutionPx(String resolutionPx) {
        setDataInfo(RESOLUTION_PX, resolutionPx);
        return this;
    }

    public String getResolutionDp() {
        return getValueByTitle(RESOLUTION_DP);
    }

    public ScreenInfo setResolutionDp(String resolutionDp) {
        setDataInfo(RESOLUTION_DP, resolutionDp);
        return this;
    }

    public String getDpiX() {
        return getValueByTitle(DPI_X);
    }

    public ScreenInfo setDpiX(String dpiX) {
        setDataInfo(DPI_X, dpiX);
        return this;
    }

    public String getDpiY() {
        return getValueByTitle(DPI_Y);
    }

    public ScreenInfo setDpiY(String dpiY) {
        setDataInfo(DPI_Y, dpiY);
        return this;
    }

    public String getDpi() {
        return getValueByTitle(DPI);
    }

    public ScreenInfo setDpi(String dpi) {
        setDataInfo(DPI, dpi);
        return this;
    }

    public String getSize() {
        return getValueByTitle(SIZE);
    }

    public ScreenInfo setSize(String size) {
        setDataInfo(SIZE, size);
        return this;
    }

    public String getDensity() {
        return getValueByTitle(DENSITY);
    }

    public ScreenInfo setDensity(String density) {
        setDataInfo(DENSITY, density);
        return this;
    }

    public String getRatio() {
        return getValueByTitle(RATIO);
    }

    public ScreenInfo setRatio(String ratio) {
        setDataInfo(RATIO, ratio);
        return this;
    }

    public String getMultitouch() {
        return getValueByTitle(MULTITOUCH);
    }

    public ScreenInfo setMultitouch(String multitouch) {
        setDataInfo(MULTITOUCH, multitouch);
        return this;
    }
}
