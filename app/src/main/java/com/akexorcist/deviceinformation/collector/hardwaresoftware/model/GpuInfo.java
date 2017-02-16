package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class GpuInfo extends BaseInfo {
    @SerializedName("OpenGL Supported")
    private String openGlSupported;
    @SerializedName("Renderer")
    private String renderer;
    @SerializedName("Vendor")
    private String vendor;
    @SerializedName("Version")
    private String version;
    @SerializedName("Vulkan Supported")
    private String vulkanSupported;
    @SerializedName("Extension")
    private String extension;

    public GpuInfo() {
    }

    public String getOpenGlSupported() {
        return openGlSupported;
    }

    public GpuInfo setOpenGlSupported(String openGlSupported) {
        this.openGlSupported = openGlSupported;
        return this;
    }

    public String getRenderer() {
        return renderer;
    }

    public GpuInfo setRenderer(String renderer) {
        this.renderer = renderer;
        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public GpuInfo setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public GpuInfo setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getVulkanSupported() {
        return vulkanSupported;
    }

    public GpuInfo setVulkanSupported(String vulkanSupported) {
        this.vulkanSupported = vulkanSupported;
        return this;
    }

    public String getExtension() {
        return extension;
    }

    public GpuInfo setExtension(String extension) {
        this.extension = extension;
        return this;
    }
}
