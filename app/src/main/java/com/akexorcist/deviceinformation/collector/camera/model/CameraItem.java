package com.akexorcist.deviceinformation.collector.camera.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class CameraItem extends BaseInfo {
    private static final String CAMERA_ID = "Camera ID";
    private static final String ANTIBANDING = "Antibanding";
    private static final String CAMERA_FACING = "Camera Facing";
    private static final String COLOR_EFFECT = "Color Effect";
    private static final String CAN_DISABLE_SHUTTER_SOUND = "Can Disable Shutter Sound";
    private static final String FLASH_MODE = "Flash Mode";
    private static final String FOCUS_MODE = "Focus Mode";
    private static final String JPEG_THUMBNAIL_SIZE = "JPEG Thumbnail Size (PX)";
    private static final String IMAGE_ORIENTATION = "Image Orientation";
    private static final String PICTURE_FORMAT = "Picture Format";
    private static final String PREVIEW_FORMAT = "Preview Format";
    private static final String PICTURE_SIZE = "Picture Size (PX)";
    private static final String PREVIEW_FRAMERATE = "Preview Framerate (FPS)";
    private static final String PREVIEW_SIZE = "Preview Size (PX)";
    private static final String PREVIEW_FPS_RANGE = "Preview FPS Range";
    private static final String SCENE_MODE = "Scene Mode";
    private static final String VIDEO_QUALITY_PROFILE = "Video Quality Profile (PX)";
    private static final String TIMELAPSE_QUALITY_PROFILE = "Timelapse Quality Profile (PX)";
    private static final String HIGH_SPEED_QUALITY_PROFILE = "High Speed Quality Profile (PX)";
    private static final String VIDEO_SIZE = "Video Size";
    private static final String WHITE_BALANCE = "White Balance";
    private static final String AUTO_EXPOSURE_LOCK_SUPPORTED = "Auto Exposure Lock Supported";
    private static final String AUTO_WHITE_BALANCE_LOCK_SUPPORTED = "Auto White Balance Lock Supported";
    private static final String SMOOTH_ZOOM_SUPPORTED = "Smooth Zoom Supported";
    private static final String VIDEO_SNAPSHOT_SUPPORTED = "Video Snapshot Supported";
    private static final String VIDEO_STABILIZATION_SUPPORTED = "Video Stabilization Supported";
    private static final String ZOOM_SUPPORTED = "Zoom Supported";

    public CameraItem() {
        super();
    }

    public String getCameraId() {
        return getValueByTitle(CAMERA_ID);
    }

    public CameraItem setCameraId(String cameraId) {
        setDataInfo(CAMERA_FACING, cameraId);
        return this;
    }

    public String getAntibanding() {
        return getValueByTitle(ANTIBANDING);
    }

    public CameraItem setAntibanding(String antibanding) {
        setDataInfo(ANTIBANDING, antibanding);
        return this;
    }

    public String getCameraFacing() {
        return getValueByTitle(CAMERA_FACING);
    }

    public CameraItem setCameraFacing(String cameraFacing) {
        setDataInfo(CAMERA_FACING, cameraFacing);
        return this;
    }

    public String getColorEffect() {
        return getValueByTitle(COLOR_EFFECT);
    }

    public CameraItem setColorEffect(String colorEffect) {
        setDataInfo(COLOR_EFFECT, colorEffect);
        return this;
    }

    public String getCanDisableShutterSound() {
        return getValueByTitle(CAN_DISABLE_SHUTTER_SOUND);
    }

    public CameraItem setCanDisableShutterSound(String canDisableShutterSound) {
        setDataInfo(CAN_DISABLE_SHUTTER_SOUND, canDisableShutterSound);
        return this;
    }

    public String getFlashMode() {
        return getValueByTitle(FLASH_MODE);
    }

    public CameraItem setFlashMode(String flashMode) {
        setDataInfo(FLASH_MODE, flashMode);
        return this;
    }

    public String getFocusMode() {
        return getValueByTitle(FOCUS_MODE);
    }

    public CameraItem setFocusMode(String focusMode) {
        setDataInfo(FOCUS_MODE, focusMode);
        return this;
    }

    public String getJpegThumbnailSize() {
        return getValueByTitle(JPEG_THUMBNAIL_SIZE);
    }

    public CameraItem setJpegThumbnailSize(String jpegThumbnailSize) {
        setDataInfo(JPEG_THUMBNAIL_SIZE, jpegThumbnailSize);
        return this;
    }

    public String getImageOrientation() {
        return getValueByTitle(IMAGE_ORIENTATION);
    }

    public CameraItem setImageOrientation(String imageOrientation) {
        setDataInfo(IMAGE_ORIENTATION, imageOrientation);
        return this;
    }

    public String getPictureFormat() {
        return getValueByTitle(PICTURE_FORMAT);
    }

    public CameraItem setPictureFormat(String pictureFormat) {
        setDataInfo(PICTURE_FORMAT, pictureFormat);
        return this;
    }

    public String getPreviewFormat() {
        return getValueByTitle(PREVIEW_FORMAT);
    }

    public CameraItem setPreviewFormat(String previewFormat) {
        setDataInfo(PREVIEW_FORMAT, previewFormat);
        return this;
    }

    public String getPictureSize() {
        return getValueByTitle(PICTURE_SIZE);
    }

    public CameraItem setPictureSize(String pictureSize) {
        setDataInfo(PICTURE_SIZE, pictureSize);
        return this;
    }

    public String getPreviewFramerate() {
        return getValueByTitle(PREVIEW_FRAMERATE);
    }

    public CameraItem setPreviewFramerate(String previewFramerate) {
        setDataInfo(PREVIEW_FRAMERATE, previewFramerate);
        return this;
    }

    public String getPreviewSize() {
        return getValueByTitle(PREVIEW_SIZE);
    }

    public CameraItem setPreviewSize(String previewSize) {
        setDataInfo(PREVIEW_SIZE, previewSize);
        return this;
    }

    public String getPreviewFpsRange() {
        return getValueByTitle(PREVIEW_FPS_RANGE);
    }

    public CameraItem setPreviewFpsRange(String previewFpsRange) {
        setDataInfo(PREVIEW_FPS_RANGE, previewFpsRange);
        return this;
    }

    public String getSceneMode() {
        return getValueByTitle(SCENE_MODE);
    }

    public CameraItem setSceneMode(String sceneMode) {
        setDataInfo(SCENE_MODE, sceneMode);
        return this;
    }

    public String getVideoQualityProfile() {
        return getValueByTitle(VIDEO_QUALITY_PROFILE);
    }

    public CameraItem setVideoQualityProfile(String videoQualityProfile) {
        setDataInfo(VIDEO_QUALITY_PROFILE, videoQualityProfile);
        return this;
    }

    public String getTimelapseQualityProfile() {
        return getValueByTitle(TIMELAPSE_QUALITY_PROFILE);
    }

    public CameraItem setTimelapseQualityProfile(String timelapseQualityProfile) {
        setDataInfo(TIMELAPSE_QUALITY_PROFILE, timelapseQualityProfile);
        return this;
    }

    public String getHighSpeedQualityProfile() {
        return getValueByTitle(HIGH_SPEED_QUALITY_PROFILE);
    }

    public CameraItem setHighSpeedQualityProfile(String highSpeedQualityProfile) {
        setDataInfo(HIGH_SPEED_QUALITY_PROFILE, highSpeedQualityProfile);
        return this;
    }

    public String getVideoSize() {
        return getValueByTitle(VIDEO_SIZE);
    }

    public CameraItem setVideoSize(String videoSize) {
        setDataInfo(VIDEO_SIZE, videoSize);
        return this;
    }

    public String getWhiteBalance() {
        return getValueByTitle(WHITE_BALANCE);
    }

    public CameraItem setWhiteBalance(String whiteBalance) {
        setDataInfo(WHITE_BALANCE, whiteBalance);
        return this;
    }

    public String getAutoExposureLockSupported() {
        return getValueByTitle(AUTO_EXPOSURE_LOCK_SUPPORTED);
    }

    public CameraItem setAutoExposureLockSupported(String autoExposureLockSupported) {
        setDataInfo(AUTO_EXPOSURE_LOCK_SUPPORTED, autoExposureLockSupported);
        return this;
    }

    public String getAutoWhiteBalanceLockSupported() {
        return getValueByTitle(AUTO_WHITE_BALANCE_LOCK_SUPPORTED);
    }

    public CameraItem setAutoWhiteBalanceLockSupported(String autoWhiteBalanceLockSupported) {
        setDataInfo(AUTO_WHITE_BALANCE_LOCK_SUPPORTED, autoWhiteBalanceLockSupported);
        return this;
    }

    public String getSmoothZoomSupported() {
        return getValueByTitle(SMOOTH_ZOOM_SUPPORTED);
    }

    public CameraItem setSmoothZoomSupported(String smoothZoomSupported) {
        setDataInfo(SMOOTH_ZOOM_SUPPORTED, smoothZoomSupported);
        return this;
    }

    public String getVideoSnapshotSupported() {
        return getValueByTitle(VIDEO_SNAPSHOT_SUPPORTED);
    }

    public CameraItem setVideoSnapshotSupported(String videoSnapshotSupported) {
        setDataInfo(VIDEO_SNAPSHOT_SUPPORTED, videoSnapshotSupported);
        return this;
    }

    public String getVideoStabilizationSupported() {
        return getValueByTitle(VIDEO_STABILIZATION_SUPPORTED);
    }

    public CameraItem setVideoStabilizationSupported(String videoStabilizationSupported) {
        setDataInfo(VIDEO_STABILIZATION_SUPPORTED, videoStabilizationSupported);
        return this;
    }

    public String getZoomSupported() {
        return getValueByTitle(ZOOM_SUPPORTED);
    }

    public CameraItem setZoomSupported(String zoomSupported) {
        setDataInfo(ZOOM_SUPPORTED, zoomSupported);
        return this;
    }
}
