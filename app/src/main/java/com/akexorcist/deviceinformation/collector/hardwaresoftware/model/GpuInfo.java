package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class GpuInfo extends BaseInfo {
    private static final String OPENGL_SUPPORTED = "OpenGL Supported";
    private static final String RENDERER = "Renderer";
    private static final String VENDOR = "Vendor";
    private static final String VERSION = "Version";
    private static final String VULKAN_SUPPORTED = "Vulkan Supported";

    public GpuInfo() {
        super();
    }

    public String getOpenGlSupported() {
        return getValueByTitle(OPENGL_SUPPORTED);
    }

    public GpuInfo setOpenGlSupported(String openGlSupported) {
        setDataInfo(OPENGL_SUPPORTED, openGlSupported);
        return this;
    }

    public String getRenderer() {
        return getValueByTitle(RENDERER);
    }

    public GpuInfo setRenderer(String renderer) {
        setDataInfo(RENDERER, renderer);
        return this;
    }

    public String getVendor() {
        return getValueByTitle(VENDOR);
    }

    public GpuInfo setVendor(String vendor) {
        setDataInfo(VENDOR, vendor);
        return this;
    }

    public String getVersion() {
        return getValueByTitle(VERSION);
    }

    public GpuInfo setVersion(String version) {
        setDataInfo(VERSION, version);
        return this;
    }

    public String getVulkanSupported() {
        return getValueByTitle(VULKAN_SUPPORTED);
    }

    public GpuInfo setVulkanSupported(String vulkanSupported) {
        setDataInfo(VULKAN_SUPPORTED, vulkanSupported);
        return this;
    }
}
