package com.akexorcist.deviceinformation.network.response;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class DeviceSyncResult<T> {
    private String code;
    private String message;
    private T data;

    public DeviceSyncResult() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isDataAvailable() {
        return data != null;
    }
}
