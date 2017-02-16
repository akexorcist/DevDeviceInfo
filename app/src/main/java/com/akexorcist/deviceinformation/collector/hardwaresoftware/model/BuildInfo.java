package com.akexorcist.deviceinformation.collector.hardwaresoftware.model;

import com.akexorcist.deviceinformation.common.BaseInfo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class BuildInfo extends BaseInfo {
    @SerializedName("Board")
    private String board;
    @SerializedName("Bootloader")
    private String bootloader;
    @SerializedName("Brand")
    private String brand;
    @SerializedName("Characteristic")
    private String characteristic;
    @SerializedName("CPU ABI")
    private String cpuAbi;
    @SerializedName("CPU ABI 2")
    private String cpuAbi2;
    @SerializedName("Device")
    private String device;
    @SerializedName("Display")
    private String display;
    @SerializedName("Fingerprint")
    private String fingerprint;
    @SerializedName("Hardware")
    private String hardware;
    @SerializedName("Host")
    private String host;
    @SerializedName("ID")
    private String id;
    @SerializedName("Manufacturer")
    private String manufacturer;
    @SerializedName("Model")
    private String model;
    @SerializedName("Product")
    private String product;
    @SerializedName("Radio")
    private String radio;
    @SerializedName("Serial")
    private String serial;
    @SerializedName("Supported ABIS")
    private String supportedAbis;
    @SerializedName("Supported 32-bit ABIS")
    private String supported32BitAbis;
    @SerializedName("Supported 64-bit ABIS")
    private String supported64BitAbis;
    @SerializedName("Tags")
    private String tags;
    @SerializedName("Time")
    private String time;
    @SerializedName("Type")
    private String type;
    @SerializedName("User")
    private String user;

    public BuildInfo() {
    }

    public String getBoard() {
        return board;
    }

    public BuildInfo setBoard(String board) {
        this.board = board;
        return this;
    }

    public String getBootloader() {
        return bootloader;
    }

    public BuildInfo setBootloader(String bootloader) {
        this.bootloader = bootloader;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public BuildInfo setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public BuildInfo setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
        return this;
    }

    public String getCpuAbi() {
        return cpuAbi;
    }

    public BuildInfo setCpuAbi(String cpuAbi) {
        this.cpuAbi = cpuAbi;
        return this;
    }

    public String getCpuAbi2() {
        return cpuAbi2;
    }

    public BuildInfo setCpuAbi2(String cpuAbi2) {
        this.cpuAbi2 = cpuAbi2;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public BuildInfo setDevice(String device) {
        this.device = device;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public BuildInfo setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public BuildInfo setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    public String getHardware() {
        return hardware;
    }

    public BuildInfo setHardware(String hardware) {
        this.hardware = hardware;
        return this;
    }

    public String getHost() {
        return host;
    }

    public BuildInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public String getId() {
        return id;
    }

    public BuildInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public BuildInfo setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public BuildInfo setModel(String model) {
        this.model = model;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public BuildInfo setProduct(String product) {
        this.product = product;
        return this;
    }

    public String getRadio() {
        return radio;
    }

    public BuildInfo setRadio(String radio) {
        this.radio = radio;
        return this;
    }

    public String getSerial() {
        return serial;
    }

    public BuildInfo setSerial(String serial) {
        this.serial = serial;
        return this;
    }

    public String getSupportedAbis() {
        return supportedAbis;
    }

    public BuildInfo setSupportedAbis(String supportedAbis) {
        this.supportedAbis = supportedAbis;
        return this;
    }

    public String getSupported32BitAbis() {
        return supported32BitAbis;
    }

    public BuildInfo setSupported32BitAbis(String supported32BitAbis) {
        this.supported32BitAbis = supported32BitAbis;
        return this;
    }

    public String getSupported64BitAbis() {
        return supported64BitAbis;
    }

    public BuildInfo setSupported64BitAbis(String supported64BitAbis) {
        this.supported64BitAbis = supported64BitAbis;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public BuildInfo setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getTime() {
        return time;
    }

    public BuildInfo setTime(String time) {
        this.time = time;
        return this;
    }

    public String getType() {
        return type;
    }

    public BuildInfo setType(String type) {
        this.type = type;
        return this;
    }

    public String getUser() {
        return user;
    }

    public BuildInfo setUser(String user) {
        this.user = user;
        return this;
    }
}
