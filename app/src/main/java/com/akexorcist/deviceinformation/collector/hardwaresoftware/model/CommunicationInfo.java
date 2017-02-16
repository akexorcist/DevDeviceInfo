package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CommunicationInfo extends BaseInfo {
    @SerializedName("Bluetooth")
    private String bluetooth;
    @SerializedName("Bluetooth")
    private String bluetoothLe;
    @SerializedName("Cellular")
    private String cellular;
    @SerializedName("Daydream VR")
    private String daydreamVr;
    @SerializedName("Dual SIM")
    private String dualSim;
    @SerializedName("Ethernet")
    private String ethernet;
    @SerializedName("Fingerprint")
    private String fingerprint;
    @SerializedName("GPS")
    private String gps;
    @SerializedName("Microphone")
    private String microphone;
    @SerializedName("NFC")
    private String nfc;
    @SerializedName("NFC HCE")
    private String nfcHce;
    @SerializedName("Telephony")
    private String telephony;
    @SerializedName("USB OTG")
    private String usbOtg;
    @SerializedName("USB Accessory")
    private String usbAccessory;
    @SerializedName("Vibrate Motor")
    private String vibrateMotor;
    @SerializedName("WiFi")
    private String wifi;
    @SerializedName("WiFi Direct")
    private String wifiDirect;
    @SerializedName("WiMax")
    private String wiMax;

    public CommunicationInfo() {
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public CommunicationInfo setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
        return this;
    }

    public String getBluetoothLe() {
        return bluetoothLe;
    }

    public CommunicationInfo setBluetoothLe(String bluetoothLe) {
        this.bluetoothLe = bluetoothLe;
        return this;
    }

    public String getCellular() {
        return cellular;
    }

    public CommunicationInfo setCellular(String cellular) {
        this.cellular = cellular;
        return this;
    }

    public String getDaydreamVr() {
        return daydreamVr;
    }

    public CommunicationInfo setDaydreamVr(String daydreamVr) {
        this.daydreamVr = daydreamVr;
        return this;
    }

    public String getDualSim() {
        return dualSim;
    }

    public CommunicationInfo setDualSim(String dualSim) {
        this.dualSim = dualSim;
        return this;
    }

    public String getEthernet() {
        return ethernet;
    }

    public CommunicationInfo setEthernet(String ethernet) {
        this.ethernet = ethernet;
        return this;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public CommunicationInfo setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    public String getGps() {
        return gps;
    }

    public CommunicationInfo setGps(String gps) {
        this.gps = gps;
        return this;
    }

    public String getMicrophone() {
        return microphone;
    }

    public CommunicationInfo setMicrophone(String microphone) {
        this.microphone = microphone;
        return this;
    }

    public String getNfc() {
        return nfc;
    }

    public CommunicationInfo setNfc(String nfc) {
        this.nfc = nfc;
        return this;
    }

    public String getNfcHce() {
        return nfcHce;
    }

    public CommunicationInfo setNfcHce(String nfcHce) {
        this.nfcHce = nfcHce;
        return this;
    }

    public String getTelephony() {
        return telephony;
    }

    public CommunicationInfo setTelephony(String telephony) {
        this.telephony = telephony;
        return this;
    }

    public String getUsbOtg() {
        return usbOtg;
    }

    public CommunicationInfo setUsbOtg(String usbOtg) {
        this.usbOtg = usbOtg;
        return this;
    }

    public String getUsbAccessory() {
        return usbAccessory;
    }

    public CommunicationInfo setUsbAccessory(String usbAccessory) {
        this.usbAccessory = usbAccessory;
        return this;
    }

    public String getVibrateMotor() {
        return vibrateMotor;
    }

    public CommunicationInfo setVibrateMotor(String vibrateMotor) {
        this.vibrateMotor = vibrateMotor;
        return this;
    }

    public String getWifi() {
        return wifi;
    }

    public CommunicationInfo setWifi(String wifi) {
        this.wifi = wifi;
        return this;
    }

    public String getWifiDirect() {
        return wifiDirect;
    }

    public CommunicationInfo setWifiDirect(String wifiDirect) {
        this.wifiDirect = wifiDirect;
        return this;
    }

    public String getWiMax() {
        return wiMax;
    }

    public CommunicationInfo setWiMax(String wiMax) {
        this.wiMax = wiMax;
        return this;
    }
}
