package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class MemoryInfo extends BaseInfo {
    private static final String TOTAL_MEMORY = "Total Memory";
    private static final String HEAP_SIZE = "Heap Size";
    private static final String HEAP_START_SIZE = "Heap Start Size";
    private static final String HEAP_GROWTH_LIMIT = "Heap Growth Limit";

    public MemoryInfo() {
        super();
    }

    public String getTotalMemory() {
        return getValueByTitle(TOTAL_MEMORY);
    }

    public MemoryInfo setTotalMemory(String totalMemory) {
        setDataInfo(TOTAL_MEMORY, totalMemory);
        return this;
    }

    public String getHeapSize() {
        return getValueByTitle(HEAP_SIZE);
    }

    public MemoryInfo setHeapSize(String heapSize) {
        setDataInfo(HEAP_SIZE, heapSize);
        return this;
    }

    public String getHeapStartSize() {
        return getValueByTitle(HEAP_START_SIZE);
    }

    public MemoryInfo setHeapStartSize(String heapStartSize) {
        setDataInfo(HEAP_START_SIZE, heapStartSize);
        return this;
    }

    public String getHeapGrowthLimit() {
        return getValueByTitle(HEAP_GROWTH_LIMIT);
    }

    public MemoryInfo setHeapGrowthLimit(String heapGrowthLimit) {
        setDataInfo(HEAP_GROWTH_LIMIT, heapGrowthLimit);
        return this;
    }
}
