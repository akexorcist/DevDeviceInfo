package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CommunicationInfo extends BaseInfo {
    private static final String BLUETOOTH = "Bluetooth";
    private static final String BLUETOOTH_LOW_ENERGY = "Bluetooth Low Energy";
    private static final String CELLULAR = "Cellular";
    private static final String CARDBOARD_VR = "Cardboard VR";
    private static final String DAYDREAM_VR = "Daydream VR";
    private static final String DUAL_SIM = "Dual SIM";
    private static final String ETHERNET = "Ethernet";
    private static final String FINGERPRINT = "Fingerprint";
    private static final String GPS = "GPS";
    private static final String MICROPHONE = "Microphone";
    private static final String NFC = "NFC";
    private static final String NFC_HOST_CARD_EMULATION = "NFC Host Card Emulation";
    private static final String TELEPHONY = "Telephony";
    private static final String USB_OTG = "USB OTG";
    private static final String USB_ACCESSORY = "USB Accessory";
    private static final String VIBRATE_MOTOR = "Vibrate Motor";
    private static final String WIFI = "WiFi";
    private static final String WIFI_DIRECT = "WiFi Direct";
    private static final String WIMAX = "WiMax";

    public CommunicationInfo() {
        super();
    }

    public String getBluetooth() {
        return getValueByTitle(BLUETOOTH);
    }

    public CommunicationInfo setBluetooth(String bluetooth) {
        setDataInfo(BLUETOOTH, bluetooth);
        return this;
    }

    public String getBluetoothLowEnergy() {
        return getValueByTitle(BLUETOOTH_LOW_ENERGY);
    }

    public CommunicationInfo setBluetoothLowEnergy(String bluetoothLowEnergy) {
        setDataInfo(BLUETOOTH_LOW_ENERGY, bluetoothLowEnergy);
        return this;
    }

    public String getCellular() {
        return getValueByTitle(CELLULAR);
    }

    public CommunicationInfo setCellular(String cellular) {
        setDataInfo(CELLULAR, cellular);
        return this;
    }

    public String getCardboardVr() {
        return getValueByTitle(CARDBOARD_VR);
    }

    public CommunicationInfo setCardboardVr(String cardboardVr) {
        setDataInfo(CARDBOARD_VR, cardboardVr);
        return this;
    }

    public String getDaydreamVr() {
        return getValueByTitle(DAYDREAM_VR);
    }

    public CommunicationInfo setDaydreamVr(String daydreamVr) {
        setDataInfo(DAYDREAM_VR, daydreamVr);
        return this;
    }

    public String getDualSim() {
        return getValueByTitle(DUAL_SIM);
    }

    public CommunicationInfo setDualSim(String dualSim) {
        setDataInfo(DUAL_SIM, dualSim);
        return this;
    }

    public String getEthernet() {
        return getValueByTitle(ETHERNET);
    }

    public CommunicationInfo setEthernet(String ethernet) {
        setDataInfo(ETHERNET, ethernet);
        return this;
    }

    public String getFingerprint() {
        return getValueByTitle(FINGERPRINT);
    }

    public CommunicationInfo setFingerprint(String fingerprint) {
        setDataInfo(FINGERPRINT, fingerprint);
        return this;
    }

    public String getGps() {
        return getValueByTitle(GPS);
    }

    public CommunicationInfo setGps(String gps) {
        setDataInfo(GPS, gps);
        return this;
    }

    public String getMicrophone() {
        return getValueByTitle(MICROPHONE);
    }

    public CommunicationInfo setMicrophone(String microphone) {
        setDataInfo(MICROPHONE, microphone);
        return this;
    }

    public String getNfc() {
        return getValueByTitle(NFC);
    }

    public CommunicationInfo setNfc(String nfc) {
        setDataInfo(NFC, nfc);
        return this;
    }

    public String getNfcHostCardEmulation() {
        return getValueByTitle(NFC_HOST_CARD_EMULATION);
    }

    public CommunicationInfo setNfcHostCardEmulation(String nfcHce) {
        setDataInfo(NFC_HOST_CARD_EMULATION, nfcHce);
        return this;
    }

    public String getTelephony() {
        return getValueByTitle(TELEPHONY);
    }

    public CommunicationInfo setTelephony(String telephony) {
        setDataInfo(TELEPHONY, telephony);
        return this;
    }

    public String getUsbOtg() {
        return getValueByTitle(USB_OTG);
    }

    public CommunicationInfo setUsbOtg(String usbOtg) {
        setDataInfo(USB_OTG, usbOtg);
        return this;
    }

    public String getUsbAccessory() {
        return getValueByTitle(USB_ACCESSORY);
    }

    public CommunicationInfo setUsbAccessory(String usbAccessory) {
        setDataInfo(USB_ACCESSORY, usbAccessory);
        return this;
    }

    public String getVibrateMotor() {
        return getValueByTitle(VIBRATE_MOTOR);
    }

    public CommunicationInfo setVibrateMotor(String vibrateMotor) {
        setDataInfo(VIBRATE_MOTOR, vibrateMotor);
        return this;
    }

    public String getWifi() {
        return getValueByTitle(WIFI);
    }

    public CommunicationInfo setWifi(String wifi) {
        setDataInfo(WIFI, wifi);
        return this;
    }

    public String getWifiDirect() {
        return getValueByTitle(WIFI_DIRECT);
    }

    public CommunicationInfo setWifiDirect(String wifiDirect) {
        setDataInfo(WIFI_DIRECT, wifiDirect);
        return this;
    }

    public String getWiMax() {
        return getValueByTitle(WIMAX);
    }

    public CommunicationInfo setWiMax(String wiMax) {
        setDataInfo(WIMAX, wiMax);
        return this;
    }
}
