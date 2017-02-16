package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BuildInfo extends BaseInfo {
    private static final String BOARD = "Board";
    private static final String BOOTLOADER = "Bootloader";
    private static final String BRAND = "Brand";
    private static final String CHARACTERISTIC = "Characteristic";
    private static final String CPU_ABI = "CPU ABI";
    private static final String CPU_ABI_2 = "CPU ABI 2";
    private static final String DEVICE = "Device";
    private static final String DISPLAY = "Display";
    private static final String FINGERPRINT = "Fingerprint";
    private static final String HARDWARE = "Hardware";
    private static final String HOST = "Host";
    private static final String ID = "ID";
    private static final String MANUFACTURER = "Manufacturer";
    private static final String MODEL = "Model";
    private static final String PRODUCT = "Product";
    private static final String RADIO = "Radio";
    private static final String SERIAL = "Serial";
    private static final String SUPPORTED_ABIS = "Supported ABIS";
    private static final String SUPPORTED_32_BIT_ABIS = "Supported 32-bit ABIS";
    private static final String SUPPORTED_64_BIT_ABIS = "Supported 64-bit ABIS";
    private static final String TAGS = "Tags";
    private static final String TIME = "Time";
    private static final String TYPE = "Type";
    private static final String USER = "User";

    public BuildInfo() {
        super();
    }

    public String getBoard() {
        return getValueByTitle(BOARD);
    }

    public BuildInfo setBoard(String board) {
        setDataInfo(BOARD, board);
        return this;
    }

    public String getBootloader() {
        return getValueByTitle(BOOTLOADER);
    }

    public BuildInfo setBootloader(String bootloader) {
        setDataInfo(BOOTLOADER, bootloader);
        return this;
    }

    public String getBrand() {
        return getValueByTitle(BRAND);
    }

    public BuildInfo setBrand(String brand) {
        setDataInfo(BRAND, brand);
        return this;
    }

    public String getCharacteristic() {
        return getValueByTitle(CHARACTERISTIC);
    }

    public BuildInfo setCharacteristic(String characteristic) {
        setDataInfo(CHARACTERISTIC, characteristic);
        return this;
    }

    public String getCpuAbi() {
        return getValueByTitle(CPU_ABI);
    }

    public BuildInfo setCpuAbi(String cpuAbi) {
        setDataInfo(CPU_ABI, cpuAbi);
        return this;
    }

    public String getCpuAbi2() {
        return getValueByTitle(CPU_ABI_2);
    }

    public BuildInfo setCpuAbi2(String cpuAbi2) {
        setDataInfo(CPU_ABI_2, cpuAbi2);
        return this;
    }

    public String getDevice() {
        return getValueByTitle(DEVICE);
    }

    public BuildInfo setDevice(String device) {
        setDataInfo(DEVICE, device);
        return this;
    }

    public String getDisplay() {
        return getValueByTitle(DISPLAY);
    }

    public BuildInfo setDisplay(String display) {
        setDataInfo(DISPLAY, display);
        return this;
    }

    public String getFingerprint() {
        return getValueByTitle(FINGERPRINT);
    }

    public BuildInfo setFingerprint(String fingerprint) {
        setDataInfo(FINGERPRINT, fingerprint);
        return this;
    }

    public String getHardware() {
        return getValueByTitle(HARDWARE);
    }

    public BuildInfo setHardware(String hardware) {
        setDataInfo(HARDWARE, hardware);
        return this;
    }

    public String getHost() {
        return getValueByTitle(HOST);
    }

    public BuildInfo setHost(String host) {
        setDataInfo(HOST, host);
        return this;
    }

    public String getId() {
        return getValueByTitle(ID);
    }

    public BuildInfo setId(String id) {
        setDataInfo(ID, id);
        return this;
    }

    public String getManufacturer() {
        return getValueByTitle(MANUFACTURER);
    }

    public BuildInfo setManufacturer(String manufacturer) {
        setDataInfo(MANUFACTURER, manufacturer);
        return this;
    }

    public String getModel() {
        return getValueByTitle(MODEL);
    }

    public BuildInfo setModel(String model) {
        setDataInfo(MODEL, model);
        return this;
    }

    public String getProduct() {
        return getValueByTitle(PRODUCT);
    }

    public BuildInfo setProduct(String product) {
        setDataInfo(PRODUCT, product);
        return this;
    }

    public String getRadio() {
        return getValueByTitle(RADIO);
    }

    public BuildInfo setRadio(String radio) {
        setDataInfo(RADIO, radio);
        return this;
    }

    public String getSerial() {
        return getValueByTitle(SERIAL);
    }

    public BuildInfo setSerial(String serial) {
        setDataInfo(SERIAL, serial);
        return this;
    }

    public String getSupportedAbis() {
        return getValueByTitle(SUPPORTED_ABIS);
    }

    public BuildInfo setSupportedAbis(String supportedAbis) {
        setDataInfo(SUPPORTED_ABIS, supportedAbis);
        return this;
    }

    public String getSupported32BitAbis() {
        return getValueByTitle(SUPPORTED_32_BIT_ABIS);
    }

    public BuildInfo setSupported32BitAbis(String supported32BitAbis) {
        setDataInfo(SUPPORTED_32_BIT_ABIS, supported32BitAbis);
        return this;
    }

    public String getSupported64BitAbis() {
        return getValueByTitle(SUPPORTED_64_BIT_ABIS);
    }

    public BuildInfo setSupported64BitAbis(String supported64BitAbis) {
        setDataInfo(SUPPORTED_64_BIT_ABIS, supported64BitAbis);
        return this;
    }

    public String getTags() {
        return getValueByTitle(TAGS);
    }

    public BuildInfo setTags(String tags) {
        setDataInfo(TAGS, tags);
        return this;
    }

    public String getTime() {
        return getValueByTitle(TIME);
    }

    public BuildInfo setTime(String time) {
        setDataInfo(TIME, time);
        return this;
    }

    public String getType() {
        return getValueByTitle(TYPE);
    }

    public BuildInfo setType(String type) {
        setDataInfo(TYPE, type);
        return this;
    }

    public String getUser() {
        return getValueByTitle(USER);
    }

    public BuildInfo setUser(String user) {
        setDataInfo(USER, user);
        return this;
    }
}
