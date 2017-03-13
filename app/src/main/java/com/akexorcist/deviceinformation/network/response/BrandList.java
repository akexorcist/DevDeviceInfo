package com.akexorcist.deviceinformation.network.response;

import java.util.List;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class BrandList {
    private String selfLink;
    private List<String> brandList;

    public BrandList() {
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public boolean isBrandAvailable() {
        return brandList != null && !brandList.isEmpty();
    }
}
