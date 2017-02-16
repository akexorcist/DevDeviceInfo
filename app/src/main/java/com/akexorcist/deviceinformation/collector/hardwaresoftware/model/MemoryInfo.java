package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class MemoryInfo extends BaseInfo {
    @SerializedName("Total Memory")
    private String totalMemory;
    @SerializedName("Heap Size")
    private String heapSize;
    @SerializedName("Heap Start Size")
    private String heapStartSize;
    @SerializedName("Heap Growth Limit")
    private String heapGrowthLimit;

    public MemoryInfo() {
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public MemoryInfo setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
        return this;
    }

    public String getHeapSize() {
        return heapSize;
    }

    public MemoryInfo setHeapSize(String heapSize) {
        this.heapSize = heapSize;
        return this;
    }

    public String getHeapStartSize() {
        return heapStartSize;
    }

    public MemoryInfo setHeapStartSize(String heapStartSize) {
        this.heapStartSize = heapStartSize;
        return this;
    }

    public String getHeapGrowthLimit() {
        return heapGrowthLimit;
    }

    public MemoryInfo setHeapGrowthLimit(String heapGrowthLimit) {
        this.heapGrowthLimit = heapGrowthLimit;
        return this;
    }
}
