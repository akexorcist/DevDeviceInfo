package com.akexorcist.deviceinformation.collector.camera.model;

import com.akexorcist.deviceinformation.common.BaseInfo;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

public class Camera2Item extends BaseInfo {
    private static final String CAMERA_ID = "Camera ID";
    private static final String ABERRATION_CORRECTION_MODE = "Aberration Correction Mode";
    private static final String AE_ANTIBANDING_MODE = "AE Antibanding Mode";
    private static final String AE_MODE = "AE Mode";
    private static final String AE_TARGET_FPS_RANGE = "AE Target FPS Range";
    private static final String AE_COMPENSATION_RANGE = "AE Compensation Range";
    private static final String AE_COMPENSATION_STEP = "AE Compensation Step";
    private static final String AE_LOCK = "AE Lock";
    private static final String AF_MODE = "AF Mode";
    private static final String COLOR_EFFECT = "Color Effect";
    private static final String CONTROL_MODE = "Control Mode";
    private static final String SCENE_MODE = "Scene Mode";
    private static final String VIDEO_STABILIZATION_MODE = "Video Stabilization Modes";
    private static final String AWB_MODE = "AWB Mode";
    private static final String AWB_LOCK = "AWB Lock";
    private static final String MAXIMUM_REGIONS_AE = "Maximum Regions AE";
    private static final String MAXIMUM_REGIONS_AF = "Maximum Regions AF";
    private static final String MAXIMUM_REGIONS_AWB = "Maximum Regions AWB";
    private static final String POST_RAW_SENSITIVITY_BOOST_RANGE = "Post Raw Sensitivity Boost Range (ISO)";
    private static final String IS_DEPTH_EXCLUSIVE = "Is Depth Exclusive";
    private static final String EDGE_ENHANCEMENT_MODE = "Edge Enhancement Mode";
    private static final String FLASH_SUPPORTED = "Flash Supported";
    private static final String HOT_PIXEL_CORRECTION_MODE = "Hot Pixel Correction Mode";
    private static final String SUPPORTED_HARDWARE_LEVEL = "Supported Hardware Level";
    private static final String JPEG_THUMBNAIL_SIZE = "JPEG Thumbnail Size (px)";
    private static final String LENS_FACING = "Lens Facing";
    private static final String LENS_APERTURES = "Lens Apertures (f-number)";
    private static final String LENS_FILTER_DENSITY = "Lens Filter Density (EV)";
    private static final String LENS_FOCAL_LENGTH = "Lens Focal Length (mm)";
    private static final String LENS_OPTICAL_STABILIZATION = "Lens Optical Stabilization";
    private static final String LENS_FOCUS_DISTANCE_CALIBRATION = "Lens Focus Distance Calibration";
    private static final String LENS_HYPERFOCAL_DISTANCE = "Lens Hyperfocal Distance";
    private static final String LENS_MINIMUM_FOCUS_DISTANCE = "Lens Minimum Focus Distance";
    private static final String LENS_INTRINSIC_CALIBRATION = "Lens Intrinsic Calibration";
    private static final String LENS_POSE_ROTATION = "Lens Pose Rotation";
    private static final String LENS_POSE_TRANSLATION = "Lens Pose Translation (m)";
    private static final String LENS_RADIAL_DISTORTION = "Lens Radial Distortion";
    private static final String NOISE_REDUCTION_MODES = "Noise Reduction Modes";
    private static final String MAXIMUM_CAPTURE_STALL = "Maximal Capture Stall (Frame)";
    private static final String REQUEST_CAPABILITY = "Request Capability";
    private static final String REQUEST_MAXIMUM_NUMBER_INPUT_STREAMS = "Request Maximum Number Input Streams";
    private static final String REQUEST_MAXIMUM_NUMBER_OUTPUT_PROCESS = "Request Maximum Number Output Process";
    private static final String REQUEST_MAXIMUM_NUMBER_OUTPUT_PROCESS_STALLING = "Request Maximum Number Output Process Stalling";
    private static final String REQUEST_MAXIMUM_NUMBER_OUTPUT_RAW = "Request Maximum Number Output RAW";
    private static final String REQUEST_PARTIAL_RESULT_COUNT = "Request Partial Result Count";
    private static final String REQUEST_PIPELINE_MAX_DEPTH = "Request Pipeline Max Depth";
    private static final String MAXIMUM_DIGITAL_ZOOM = "Maximum Digital Zoom";
    private static final String CROPPING_TYPE = "Cropping Type";
    private static final String SENSOR_TEST_PATTERN_MODES = "Sensor Test Pattern Modes";
    private static final String SENSOR_ACTIVE_ARRAY_SIZE = "Sensor Active Array Size (px)";
    private static final String SENSOR_COLOR_FILTER_ARRANGEMENT = "Sensor Color Filter Arrangement";
    private static final String SENSOR_EXPOSURE_TIME_RANGE = "Sensor Exposure Time Range (ns)";
    private static final String SENSOR_LENS_SHADING_APPLIED = "Sensor Lens Shading Applied";
    private static final String SENSOR_MAX_FRAME_DURATION = "Sensor Max Frame Duration (ns)";
    private static final String SENSOR_PHYSICAL_SIZE = "Sensor Physical Size (mm)";
    private static final String SENSOR_PIXEL_ARRAY_SIZE = "Sensor Pixel Array Size (px)";
    private static final String SENSOR_PRE_CORRECTION_ACTIVE_ARRAY_SIZE = "Sensor Pre-Correction Active Array Size (px)";
    private static final String SENSOR_SENSITIVITY_RANGE = "Sensor Sensitivity Range";
    private static final String SENSOR_TIMESTAMP_SOURCE = "Sensor Timestamp Source";
    private static final String SENSOR_WHITE_LEVEL = "Sensor White Level";
    private static final String SENSOR_MAXIMUM_ANALOG_SENSITIVITY = "Sensor Maximum Analog Sensitivity";
    private static final String SENSOR_ORIENTATION = "Sensor Orientation";
    private static final String SHADING_MODES = "Shading Modes";
    private static final String FACE_DETECTION_MODES = "Face Detection Modes";
    private static final String HOT_PIXEL_MAP_MODES = "Hot Pixel Map Modes";
    private static final String LENS_SHADING_MAP_MODES = "Lens Shading Map Modes";
    private static final String MAXIMUM_FACE_DETECTION_COUNT = "Maximum Face Detection Count";
    private static final String SYNC_MAX_LATENCY = "Sync Max Latency (Frame Count)";
    private static final String TONE_MAP_MODES = "Tone Map Modes";
    private static final String MAX_TONE_MAP_CURVE_POINTS = "Max Tone Map Curve Points";
    private static final String OUTPUT_SIZE = "Output Size";
    private static final String OUTPUT_FORMAT = "Output Format";
    private static final String HIGH_SPEED_VIDEO_FPS_RANGES = "High-Speed Video FPS Ranges";
    private static final String HIGH_SPEED_VIDEO_SIZE = "High-Speed Video Size";

    public Camera2Item() {
        super();
    }

    public String getCameraId() {
        return getValueByTitle(CAMERA_ID);
    }

    public Camera2Item setCameraId(String cameraId) {
        setDataInfo(CAMERA_ID, cameraId);
        return this;
    }

    public String getAberrationCorrectionMode() {
        return getValueByTitle(ABERRATION_CORRECTION_MODE);
    }

    public Camera2Item setAberrationCorrectionMode(String aberrationCorrectionMode) {
        setDataInfo(ABERRATION_CORRECTION_MODE, aberrationCorrectionMode);
        return this;
    }

    public String getAeAntibandingMode() {
        return getValueByTitle(AE_ANTIBANDING_MODE);
    }

    public Camera2Item setAeAntibandingMode(String aeAntibandingMode) {
        setDataInfo(AE_ANTIBANDING_MODE, aeAntibandingMode);
        return this;
    }

    public String getAeMode() {
        return getValueByTitle(AE_MODE);
    }

    public Camera2Item setAeMode(String aeMode) {
        setDataInfo(AE_MODE, aeMode);
        return this;
    }

    public String getAeTargetFpsRange() {
        return getValueByTitle(AE_TARGET_FPS_RANGE);
    }

    public Camera2Item setAeTargetFpsRange(String aeTargetFpsRange) {
        setDataInfo(AE_TARGET_FPS_RANGE, aeTargetFpsRange);
        return this;
    }

    public String getAeCompensationRange() {
        return getValueByTitle(AE_COMPENSATION_RANGE);
    }

    public Camera2Item setAeCompensationRange(String aeCompensationRange) {
        setDataInfo(AE_COMPENSATION_RANGE, aeCompensationRange);
        return this;
    }

    public String getAeCompensationStep() {
        return getValueByTitle(AE_COMPENSATION_STEP);
    }

    public Camera2Item setAeCompensationStep(String aeCompensationStep) {
        setDataInfo(AE_COMPENSATION_STEP, aeCompensationStep);
        return this;
    }

    public String getAeLock() {
        return getValueByTitle(AE_LOCK);
    }

    public Camera2Item setAeLock(String aeLock) {
        setDataInfo(AE_LOCK, aeLock);
        return this;
    }

    public String getAfMode() {
        return getValueByTitle(AF_MODE);
    }

    public Camera2Item setAfMode(String afMode) {
        setDataInfo(AF_MODE, afMode);
        return this;
    }

    public String getColorEffect() {
        return getValueByTitle(COLOR_EFFECT);
    }

    public Camera2Item setColorEffect(String colorEffect) {
        setDataInfo(COLOR_EFFECT, colorEffect);
        return this;
    }

    public String getControlMode() {
        return getValueByTitle(CONTROL_MODE);
    }

    public Camera2Item setControlMode(String controlMode) {
        setDataInfo(CONTROL_MODE, controlMode);
        return this;
    }

    public String getSceneMode() {
        return getValueByTitle(SCENE_MODE);
    }

    public Camera2Item setSceneMode(String sceneMode) {
        setDataInfo(SCENE_MODE, sceneMode);
        return this;
    }

    public String getVideoStabilizationMode() {
        return getValueByTitle(VIDEO_STABILIZATION_MODE);
    }

    public Camera2Item setVideoStabilizationMode(String videoStabilizationMode) {
        setDataInfo(VIDEO_STABILIZATION_MODE, videoStabilizationMode);
        return this;
    }

    public String getAwbMode() {
        return getValueByTitle(AWB_MODE);
    }

    public Camera2Item setAwbMode(String awbMode) {
        setDataInfo(AWB_MODE, awbMode);
        return this;
    }

    public String getAwbLock() {
        return getValueByTitle(AWB_LOCK);
    }

    public Camera2Item setAwbLock(String awbLock) {
        setDataInfo(AWB_LOCK, awbLock);
        return this;
    }

    public String getMaximumRegionsAe() {
        return getValueByTitle(MAXIMUM_REGIONS_AE);
    }

    public Camera2Item setMaximumRegionsAe(String maximumRegionsAe) {
        setDataInfo(MAXIMUM_REGIONS_AE, maximumRegionsAe);
        return this;
    }

    public String getMaximumRegionsAf() {
        return getValueByTitle(MAXIMUM_REGIONS_AF);
    }

    public Camera2Item setMaximumRegionsAf(String maximumRegionsAf) {
        setDataInfo(MAXIMUM_REGIONS_AF, maximumRegionsAf);
        return this;
    }

    public String getMaximumRegionsAwb() {
        return getValueByTitle(MAXIMUM_REGIONS_AWB);
    }

    public Camera2Item setMaximumRegionsAwb(String maximumRegionsAwb) {
        setDataInfo(MAXIMUM_REGIONS_AWB, maximumRegionsAwb);
        return this;
    }

    public String getPostRawSensitivityBoostRange() {
        return getValueByTitle(POST_RAW_SENSITIVITY_BOOST_RANGE);
    }

    public Camera2Item setPostRawSensitivityBoostRange(String postRawSensitivityBoostRange) {
        setDataInfo(POST_RAW_SENSITIVITY_BOOST_RANGE, postRawSensitivityBoostRange);
        return this;
    }

    public String getIsDepthExclusive() {
        return getValueByTitle(IS_DEPTH_EXCLUSIVE);
    }

    public Camera2Item setIsDepthExclusive(String isDepthExclusive) {
        setDataInfo(IS_DEPTH_EXCLUSIVE, isDepthExclusive);
        return this;
    }

    public String getEdgeEnhancementMode() {
        return getValueByTitle(EDGE_ENHANCEMENT_MODE);
    }

    public Camera2Item setEdgeEnhancementMode(String edgeEnhancementModes) {
        setDataInfo(EDGE_ENHANCEMENT_MODE, edgeEnhancementModes);
        return this;
    }

    public String getFlashSupported() {
        return getValueByTitle(FLASH_SUPPORTED);
    }

    public Camera2Item setFlashSupported(String flashSupported) {
        setDataInfo(FLASH_SUPPORTED, flashSupported);
        return this;
    }

    public String getHotPixelCorrectionMode() {
        return getValueByTitle(HOT_PIXEL_CORRECTION_MODE);
    }

    public Camera2Item setHotPixelCorrectionMode(String hotPixelCorrectionModes) {
        setDataInfo(HOT_PIXEL_CORRECTION_MODE, hotPixelCorrectionModes);
        return this;
    }

    public String getSupportedHardwareLevel() {
        return getValueByTitle(SUPPORTED_HARDWARE_LEVEL);
    }

    public Camera2Item setSupportedHardwareLevel(String supportedHardwareLevel) {
        setDataInfo(SUPPORTED_HARDWARE_LEVEL, supportedHardwareLevel);
        return this;
    }

    public String getJpegThumbnailSize() {
        return getValueByTitle(JPEG_THUMBNAIL_SIZE);
    }

    public Camera2Item setJpegThumbnailSize(String jpegThumbnailSizes) {
        setDataInfo(JPEG_THUMBNAIL_SIZE, jpegThumbnailSizes);
        return this;
    }

    public String getLensFacing() {
        return getValueByTitle(LENS_FACING);
    }

    public Camera2Item setLensFacing(String lensFacing) {
        setDataInfo(LENS_FACING, lensFacing);
        return this;
    }

    public String getLensApertures() {
        return getValueByTitle(LENS_APERTURES);
    }

    public Camera2Item setLensApertures(String lensApertures) {
        setDataInfo(LENS_APERTURES, lensApertures);
        return this;
    }

    public String getLensFilterDensity() {
        return getValueByTitle(LENS_FILTER_DENSITY);
    }

    public Camera2Item setLensFilterDensity(String lensFilterDensities) {
        setDataInfo(LENS_FILTER_DENSITY, lensFilterDensities);
        return this;
    }

    public String getLensFocalLength() {
        return getValueByTitle(LENS_FOCAL_LENGTH);
    }

    public Camera2Item setLensFocalLength(String lensFocalLength) {
        setDataInfo(LENS_FOCAL_LENGTH, lensFocalLength);
        return this;
    }

    public String getLensOpticalStabilization() {
        return getValueByTitle(LENS_OPTICAL_STABILIZATION);
    }

    public Camera2Item setLensOpticalStabilization(String lensOpticalStabilization) {
        setDataInfo(LENS_OPTICAL_STABILIZATION, lensOpticalStabilization);
        return this;
    }

    public String getLensFocusDistanceCalibration() {
        return getValueByTitle(LENS_FOCUS_DISTANCE_CALIBRATION);
    }

    public Camera2Item setLensFocusDistanceCalibration(String lensFocusDistanceCalibration) {
        setDataInfo(LENS_FOCUS_DISTANCE_CALIBRATION, lensFocusDistanceCalibration);
        return this;
    }

    public String getLensHyperfocalDistance() {
        return getValueByTitle(LENS_HYPERFOCAL_DISTANCE);
    }

    public Camera2Item setLensHyperfocalDistance(String lensHyperfocalDistance) {
        setDataInfo(LENS_HYPERFOCAL_DISTANCE, lensHyperfocalDistance);
        return this;
    }

    public String getLensMinimumFocusDistance() {
        return getValueByTitle(LENS_MINIMUM_FOCUS_DISTANCE);
    }

    public Camera2Item setLensMinimumFocusDistance(String lensMinimumFocusDistance) {
        setDataInfo(LENS_MINIMUM_FOCUS_DISTANCE, lensMinimumFocusDistance);
        return this;
    }

    public String getLensIntrinsicCalibration() {
        return getValueByTitle(LENS_INTRINSIC_CALIBRATION);
    }

    public Camera2Item setLensIntrinsicCalibration(String lensIntrinsicCalibration) {
        setDataInfo(LENS_INTRINSIC_CALIBRATION, lensIntrinsicCalibration);
        return this;
    }

    public String getLensPoseRotation() {
        return getValueByTitle(LENS_POSE_ROTATION);
    }

    public Camera2Item setLensPoseRotation(String lensPoseRotation) {
        setDataInfo(LENS_POSE_ROTATION, lensPoseRotation);
        return this;
    }

    public String getLensPoseTranslation() {
        return getValueByTitle(LENS_POSE_TRANSLATION);
    }

    public Camera2Item setLensPoseTranslation(String lensPoseTranslation) {
        setDataInfo(LENS_POSE_TRANSLATION, lensPoseTranslation);
        return this;
    }

    public String getLensRadialDistortion() {
        return getValueByTitle(LENS_RADIAL_DISTORTION);
    }

    public Camera2Item setLensRadialDistortion(String lensRadialDistortion) {
        setDataInfo(LENS_RADIAL_DISTORTION, lensRadialDistortion);
        return this;
    }

    public String getNoiseReductionModes() {
        return getValueByTitle(NOISE_REDUCTION_MODES);
    }

    public Camera2Item setNoiseReductionModes(String noiseReductionModes) {
        setDataInfo(NOISE_REDUCTION_MODES, noiseReductionModes);
        return this;
    }

    public String getMaximumCaptureStall() {
        return getValueByTitle(MAXIMUM_CAPTURE_STALL);
    }

    public Camera2Item setMaximumCaptureStall(String maximumCaptureStall) {
        setDataInfo(MAXIMUM_CAPTURE_STALL, maximumCaptureStall);
        return this;
    }

    public String getRequestCapability() {
        return getValueByTitle(REQUEST_CAPABILITY);
    }

    public Camera2Item setRequestCapability(String requestCapability) {
        setDataInfo(REQUEST_CAPABILITY, requestCapability);
        return this;
    }

    public String getRequestMaximumNumberInputStreams() {
        return getValueByTitle(REQUEST_MAXIMUM_NUMBER_INPUT_STREAMS);
    }

    public Camera2Item setRequestMaximumNumberInputStreams(String requestMaximumNumberInputStreams) {
        setDataInfo(REQUEST_MAXIMUM_NUMBER_INPUT_STREAMS, requestMaximumNumberInputStreams);
        return this;
    }

    public String getRequestMaximumNumberOutputProcess() {
        return getValueByTitle(REQUEST_MAXIMUM_NUMBER_OUTPUT_PROCESS);
    }

    public Camera2Item setRequestMaximumNumberOutputProcess(String requestMaximumNumberOutputProcess) {
        setDataInfo(REQUEST_MAXIMUM_NUMBER_OUTPUT_PROCESS, requestMaximumNumberOutputProcess);
        return this;
    }

    public String getRequestMaximumNumberOutputProcessStalling() {
        return getValueByTitle(REQUEST_MAXIMUM_NUMBER_OUTPUT_PROCESS_STALLING);
    }

    public Camera2Item setRequestMaximumNumberOutputProcessStalling(String requestMaximumNumberOutputProcessStalling) {
        setDataInfo(REQUEST_MAXIMUM_NUMBER_OUTPUT_PROCESS_STALLING, requestMaximumNumberOutputProcessStalling);
        return this;
    }

    public String getRequestMaximumNumberOutputRaw() {
        return getValueByTitle(REQUEST_MAXIMUM_NUMBER_OUTPUT_RAW);
    }

    public Camera2Item setRequestMaximumNumberOutputRaw(String requestMaximumNumberOutputRaw) {
        setDataInfo(REQUEST_MAXIMUM_NUMBER_OUTPUT_RAW, requestMaximumNumberOutputRaw);
        return this;
    }

    public String getRequestPartialResultCount() {
        return getValueByTitle(REQUEST_PARTIAL_RESULT_COUNT);
    }

    public Camera2Item setRequestPartialResultCount(String requestPartialResultCount) {
        setDataInfo(REQUEST_PARTIAL_RESULT_COUNT, requestPartialResultCount);
        return this;
    }

    public String getRequestPipelineMaxDepth() {
        return getValueByTitle(REQUEST_PIPELINE_MAX_DEPTH);
    }

    public Camera2Item setRequestPipelineMaxDepth(String requestPipelineMaxDepth) {
        setDataInfo(REQUEST_PIPELINE_MAX_DEPTH, requestPipelineMaxDepth);
        return this;
    }

    public String getMaximumDigitalZoom() {
        return getValueByTitle(MAXIMUM_DIGITAL_ZOOM);
    }

    public Camera2Item setMaximumDigitalZoom(String maximumDigitalZoom) {
        setDataInfo(MAXIMUM_DIGITAL_ZOOM, maximumDigitalZoom);
        return this;
    }

    public String getCroppingType() {
        return getValueByTitle(CROPPING_TYPE);
    }

    public Camera2Item setCroppingType(String croppingType) {
        setDataInfo(CROPPING_TYPE, croppingType);
        return this;
    }

    public String getSensorTestPatternModes() {
        return getValueByTitle(SENSOR_TEST_PATTERN_MODES);
    }

    public Camera2Item setSensorTestPatternModes(String sensorTestPatternModes) {
        setDataInfo(SENSOR_TEST_PATTERN_MODES, sensorTestPatternModes);
        return this;
    }

    public String getSensorActiveArraySize() {
        return getValueByTitle(SENSOR_ACTIVE_ARRAY_SIZE);
    }

    public Camera2Item setSensorActiveArraySize(String sensorActiveArraySize) {
        setDataInfo(SENSOR_ACTIVE_ARRAY_SIZE, sensorActiveArraySize);
        return this;
    }

    public String getSensorColorFilterArrangement() {
        return getValueByTitle(SENSOR_COLOR_FILTER_ARRANGEMENT);
    }

    public Camera2Item setSensorColorFilterArrangement(String sensorColorFilterArrangement) {
        setDataInfo(SENSOR_COLOR_FILTER_ARRANGEMENT, sensorColorFilterArrangement);
        return this;
    }

    public String getSensorExposureTimeRange() {
        return getValueByTitle(SENSOR_EXPOSURE_TIME_RANGE);
    }

    public Camera2Item setSensorExposureTimeRange(String sensorExposureTimeRange) {
        setDataInfo(SENSOR_EXPOSURE_TIME_RANGE, sensorExposureTimeRange);
        return this;
    }

    public String getSensorLensShadingApplied() {
        return getValueByTitle(SENSOR_LENS_SHADING_APPLIED);
    }

    public Camera2Item setSensorLensShadingApplied(String sensorLensShadingApplied) {
        setDataInfo(SENSOR_LENS_SHADING_APPLIED, sensorLensShadingApplied);
        return this;
    }

    public String getSensorMaxFrameDuration() {
        return getValueByTitle(SENSOR_MAX_FRAME_DURATION);
    }

    public Camera2Item setSensorMaxFrameDuration(String sensorMaxFrameDuration) {
        setDataInfo(SENSOR_MAX_FRAME_DURATION, sensorMaxFrameDuration);
        return this;
    }

    public String getSensorPhysicalSize() {
        return getValueByTitle(SENSOR_PHYSICAL_SIZE);
    }

    public Camera2Item setSensorPhysicalSize(String sensorPhysicalSize) {
        setDataInfo(SENSOR_PHYSICAL_SIZE, sensorPhysicalSize);
        return this;
    }

    public String getSensorPixelArraySize() {
        return getValueByTitle(SENSOR_PIXEL_ARRAY_SIZE);
    }

    public Camera2Item setSensorPixelArraySize(String sensorPixelArraySize) {
        setDataInfo(SENSOR_PIXEL_ARRAY_SIZE, sensorPixelArraySize);
        return this;
    }

    public String getSensorPreCorrectionActiveArraySize() {
        return getValueByTitle(SENSOR_PRE_CORRECTION_ACTIVE_ARRAY_SIZE);
    }

    public Camera2Item setSensorPreCorrectionActiveArraySize(String sensorPreCorrectionActiveArraySize) {
        setDataInfo(SENSOR_PRE_CORRECTION_ACTIVE_ARRAY_SIZE, sensorPreCorrectionActiveArraySize);
        return this;
    }

    public String getSensorSensitivityRange() {
        return getValueByTitle(SENSOR_SENSITIVITY_RANGE);
    }

    public Camera2Item setSensorSensitivityRange(String sensorSensitivityRange) {
        setDataInfo(SENSOR_SENSITIVITY_RANGE, sensorSensitivityRange);
        return this;
    }

    public String getSensorTimestampSource() {
        return getValueByTitle(SENSOR_TIMESTAMP_SOURCE);
    }

    public Camera2Item setSensorTimestampSource(String sensorTimestampSource) {
        setDataInfo(SENSOR_TIMESTAMP_SOURCE, sensorTimestampSource);
        return this;
    }

    public String getSensorWhiteLevel() {
        return getValueByTitle(SENSOR_WHITE_LEVEL);
    }

    public Camera2Item setSensorWhiteLevel(String sensorWhiteLevel) {
        setDataInfo(SENSOR_WHITE_LEVEL, sensorWhiteLevel);
        return this;
    }

    public String getSensorMaximumAnalogSensitivity() {
        return getValueByTitle(SENSOR_MAXIMUM_ANALOG_SENSITIVITY);
    }

    public Camera2Item setSensorMaximumAnalogSensitivity(String sensorMaximumAnalogSensitivity) {
        setDataInfo(SENSOR_MAXIMUM_ANALOG_SENSITIVITY, sensorMaximumAnalogSensitivity);
        return this;
    }

    public String getSensorOrientation() {
        return getValueByTitle(SENSOR_ORIENTATION);
    }

    public Camera2Item setSensorOrientation(String sensorOrientation) {
        setDataInfo(SENSOR_ORIENTATION, sensorOrientation);
        return this;
    }

    public String getShadingModes() {
        return getValueByTitle(SHADING_MODES);
    }

    public Camera2Item setShadingModes(String shadingModes) {
        setDataInfo(SHADING_MODES, shadingModes);
        return this;
    }

    public String getFaceDetectionModes() {
        return getValueByTitle(FACE_DETECTION_MODES);
    }

    public Camera2Item setFaceDetectionModes(String faceDetectionModes) {
        setDataInfo(FACE_DETECTION_MODES, faceDetectionModes);
        return this;
    }

    public String getHotPixelMapModes() {
        return getValueByTitle(HOT_PIXEL_MAP_MODES);
    }

    public Camera2Item setHotPixelMapModes(String hotPixelMapModes) {
        setDataInfo(HOT_PIXEL_MAP_MODES, hotPixelMapModes);
        return this;
    }

    public String getLensShadingMapModes() {
        return getValueByTitle(LENS_SHADING_MAP_MODES);
    }

    public Camera2Item setLensShadingMapModes(String lensShadingMapModes) {
        setDataInfo(LENS_SHADING_MAP_MODES, lensShadingMapModes);
        return this;
    }

    public String getMaximumFaceDetectionCount() {
        return getValueByTitle(MAXIMUM_FACE_DETECTION_COUNT);
    }

    public Camera2Item setMaximumFaceDetectionCount(String maximumFaceDetectionCount) {
        setDataInfo(MAXIMUM_FACE_DETECTION_COUNT, maximumFaceDetectionCount);
        return this;
    }

    public String getSyncMaxLatency() {
        return getValueByTitle(SYNC_MAX_LATENCY);
    }

    public Camera2Item setSyncMaxLatency(String syncMaxLatency) {
        setDataInfo(SYNC_MAX_LATENCY, syncMaxLatency);
        return this;
    }

    public String getToneMapModes() {
        return getValueByTitle(TONE_MAP_MODES);
    }

    public Camera2Item setToneMapModes(String toneMapModes) {
        setDataInfo(TONE_MAP_MODES, toneMapModes);
        return this;
    }

    public String getMaxToneMapCurvePoints() {
        return getValueByTitle(MAX_TONE_MAP_CURVE_POINTS);
    }

    public Camera2Item setMaxToneMapCurvePoints(String maxToneMapCurvePoints) {
        setDataInfo(MAX_TONE_MAP_CURVE_POINTS, maxToneMapCurvePoints);
        return this;
    }

    public String getOutputSize() {
        return getValueByTitle(OUTPUT_SIZE);
    }

    public Camera2Item setOutputSize(String outputSize) {
        setDataInfo(OUTPUT_SIZE, outputSize);
        return this;
    }

    public String getOutputFormat() {
        return getValueByTitle(OUTPUT_FORMAT);
    }

    public Camera2Item setOutputFormat(String outputFormat) {
        setDataInfo(OUTPUT_FORMAT, outputFormat);
        return this;
    }

    public String getHighSpeedVideoFpsRanges() {
        return getValueByTitle(HIGH_SPEED_VIDEO_FPS_RANGES);
    }

    public Camera2Item setHighSpeedVideoFpsRanges(String highSpeedVideoFpsRanges) {
        setDataInfo(HIGH_SPEED_VIDEO_FPS_RANGES, highSpeedVideoFpsRanges);
        return this;
    }

    public String getHighSpeedVideoSize() {
        return getValueByTitle(HIGH_SPEED_VIDEO_SIZE);
    }

    public Camera2Item setHighSpeedVideoSize(String highSpeedVideoSize) {
        setDataInfo(HIGH_SPEED_VIDEO_SIZE, highSpeedVideoSize);
        return this;
    }
}
