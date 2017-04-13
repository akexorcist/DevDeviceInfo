package com.akexorcist.deviceinformation.collector.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SizeF;

import com.akexorcist.deviceinformation.collector.InfoResultType;
import com.akexorcist.deviceinformation.collector.camera.model.Camera2Info;
import com.akexorcist.deviceinformation.collector.camera.model.Camera2Item;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;
import com.akexorcist.deviceinformation.utility.StringUtility;

import static android.hardware.camera2.CameraMetadata.TONEMAP_MODE_CONTRAST_CURVE;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

@SuppressWarnings("deprecation")
public class Camera2InfoCollector extends BaseInfoCollector {
    private static Camera2InfoCollector collector;

    public static Camera2InfoCollector getInstance() {
        if (collector == null) {
            collector = new Camera2InfoCollector();
        }
        return collector;
    }

    public Camera2Info collect(Context context) {
        // Get info from json
//        List<Camera2Item> camera2ItemList = new ArrayList<>();
//        List<Data.Camera> cameraDataList = SamsungS8.getInfo().getData().getCamera2();
//        for (Data.Camera cameraData : cameraDataList) {
//            Camera2Item cameraItem = new Camera2Item();
//            cameraItem.setDataInfoList(cameraData.getData());
//            camera2ItemList.add(cameraItem);
//        }
//        Camera2Info camera2Info = new Camera2Info();
//        camera2Info.setCameraItemList(camera2ItemList);
//        return camera2Info;
        Camera2Info camera2Info = new Camera2Info();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            try {
                String[] cameraList = manager.getCameraIdList();
                for (int index = 0; index < cameraList.length; index++) {
                    CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics(cameraList[index]);
                    Camera2Item camera2Item = new Camera2Item()
                            .setCameraId(index + "")
                            .setOutputSize(getOutputSize(cameraCharacteristics))
                            .setOutputFormat(getOutputFormat(cameraCharacteristics))
                            .setHighSpeedVideoFpsRanges(getHighSpeedVideoFpsRange(cameraCharacteristics))
                            .setHighSpeedVideoSize(getHighSpeedVideoSize(cameraCharacteristics))
                            .setAberrationCorrectionMode(getAberrationCorrectionMode(cameraCharacteristics))
                            .setAeAntibandingMode(getAeAntibandingMode(cameraCharacteristics))
                            .setAeCompensationRange(getAeCompensationRange(cameraCharacteristics))
                            .setAeCompensationStep(getAeCompensationStep(cameraCharacteristics))
                            .setAeLock(getAeLock(cameraCharacteristics))
                            .setAeMode(getAeMode(cameraCharacteristics))
                            .setAeTargetFpsRange(getAeTargetFpsRange(cameraCharacteristics))
                            .setAfMode(getAfMode(cameraCharacteristics))
                            .setColorEffect(getColorEffect(cameraCharacteristics))
                            .setControlMode(getControlMode(cameraCharacteristics))
                            .setSceneMode(getSceneMode(cameraCharacteristics))
                            .setVideoStabilizationMode(getVideoStabilizationMode(cameraCharacteristics))
                            .setAwbMode(getAwbMode(cameraCharacteristics))
                            .setAwbLock(getAwbLock(cameraCharacteristics))
                            .setMaximumRegionsAe(getMaximumRegionsAe(cameraCharacteristics))
                            .setMaximumRegionsAf(getMaximumRegionsAf(cameraCharacteristics))
                            .setMaximumRegionsAwb(getMaximumRegionsAwb(cameraCharacteristics))
                            .setPostRawSensitivityBoostRange(getPostRawSensitivityBoostRange(cameraCharacteristics))
                            .setIsDepthExclusive(getIsDepthExclusive(cameraCharacteristics))
                            .setEdgeEnhancementMode(getEdgeEnhancementMode(cameraCharacteristics))
                            .setFlashSupported(getFlashSupported(cameraCharacteristics))
                            .setHotPixelCorrectionMode(getHotPixelCorrectionMode(cameraCharacteristics))
                            .setSupportedHardwareLevel(getSupportedHardwareLevel(cameraCharacteristics))
                            .setJpegThumbnailSize(getJpegThumbnailSizes(cameraCharacteristics))
                            .setLensFacing(getLensFacing(cameraCharacteristics))
                            .setLensApertures(getLensApertures(cameraCharacteristics))
                            .setLensFilterDensity(getLensFilterDensities(cameraCharacteristics))
                            .setLensFocalLength(getLensFocalLength(cameraCharacteristics))
                            .setLensOpticalStabilization(getLensOpticalStabilization(cameraCharacteristics))
                            .setLensFocusDistanceCalibration(getLensFocusDistanceCalibration(cameraCharacteristics))
                            .setLensHyperfocalDistance(getLensHyperfocalDistance(cameraCharacteristics))
                            .setLensMinimumFocusDistance(getLensMinimumFocusDistance(cameraCharacteristics))
                            .setLensIntrinsicCalibration(getLensIntrinsicCalibration(cameraCharacteristics))
                            .setLensPoseRotation(getLensPoseRotation(cameraCharacteristics))
                            .setLensPoseTranslation(getLensPoseTranslation(cameraCharacteristics))
                            .setLensRadialDistortion(getLensRadialDistortion(cameraCharacteristics))
                            .setNoiseReductionModes(getNoiseReductionModes(cameraCharacteristics))
                            .setMaximumCaptureStall(getMaximumCaptureStall(cameraCharacteristics))
                            .setRequestCapability(getRequestCapability(cameraCharacteristics))
                            .setRequestMaximumNumberInputStreams(getRequestMaximumNumberInputStreams(cameraCharacteristics))
                            .setRequestMaximumNumberOutputProcess(getRequestMaximumNumberOutputProcess(cameraCharacteristics))
                            .setRequestMaximumNumberOutputProcessStalling(getRequestMaximumNumberOutputProcessStalling(cameraCharacteristics))
                            .setRequestMaximumNumberOutputRaw(getRequestMaximumNumberOutputRaw(cameraCharacteristics))
                            .setRequestPartialResultCount(getRequestPartialResultCount(cameraCharacteristics))
                            .setRequestPipelineMaxDepth(getRequestPipelineMaxDepth(cameraCharacteristics))
                            .setMaximumDigitalZoom(getMaximumDigitalZoom(cameraCharacteristics))
                            .setCroppingType(getCroppingType(cameraCharacteristics))
                            .setSensorTestPatternModes(getSensorTestPatternModes(cameraCharacteristics))
                            .setSensorActiveArraySize(getSensorActiveArraySize(cameraCharacteristics))
                            .setSensorColorFilterArrangement(getSensorColorFilterArrangement(cameraCharacteristics))
                            .setSensorExposureTimeRange(getSensorExposureTimeRange(cameraCharacteristics))
                            .setSensorLensShadingApplied(getSensorLensShadingApplied(cameraCharacteristics))
                            .setSensorMaxFrameDuration(getSensorMaxFrameDuration(cameraCharacteristics))
                            .setSensorPhysicalSize(getSensorPhysicalSize(cameraCharacteristics))
                            .setSensorPixelArraySize(getSensorPixelArraySize(cameraCharacteristics))
                            .setSensorPreCorrectionActiveArraySize(getSensorPreCorrectionActiveArraySize(cameraCharacteristics))
                            .setSensorSensitivityRange(getSensorSensitivityRange(cameraCharacteristics))
                            .setSensorTimestampSource(getSensorTimestampSource(cameraCharacteristics))
                            .setSensorWhiteLevel(getSensorWhiteLevel(cameraCharacteristics))
                            .setSensorMaximumAnalogSensitivity(getSensorMaximumAnalogSensitivity(cameraCharacteristics))
                            .setSensorOrientation(getSensorOrientation(cameraCharacteristics))
                            .setShadingModes(getShadingModes(cameraCharacteristics))
                            .setFaceDetectionModes(getFaceDetectionModes(cameraCharacteristics))
                            .setHotPixelMapModes(getHotPixelMapModes(cameraCharacteristics))
                            .setLensShadingMapModes(getLensShadingMapModes(cameraCharacteristics))
                            .setMaximumFaceDetectionCount(getMaximumFaceDetectionCount(cameraCharacteristics))
                            .setSyncMaxLatency(getSyncMaxLatency(cameraCharacteristics))
                            .setToneMapModes(getToneMapModes(cameraCharacteristics))
                            .setMaxToneMapCurvePoints(getMaxToneMapCurvePoints(cameraCharacteristics));
                    camera2Info.addCameraItem(camera2Item);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
        return camera2Info;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getOutputSize(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            Size[] sizeList = streamConfigurationMap.getOutputSizes(MediaRecorder.class);
            if (sizeList != null && sizeList.length > 0) {
                String result = "";
                for (Size size : sizeList) {
                    result += size.getWidth() + "x" + size.getHeight() + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getOutputFormat(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            int[] formatList = streamConfigurationMap.getOutputFormats();
            if (formatList != null && formatList.length > 0) {
                String result = "";
                for (int format : formatList) {
                    if (format == ImageFormat.JPEG) {
                        result += "JPEG ";
                    } else if (format == ImageFormat.NV16) {
                        result += "NV16 ";
                    } else if (format == ImageFormat.NV21) {
                        result += "NV21 ";
                    } else if (format == ImageFormat.RAW10) {
                        result += "RAW10 ";
                    } else if (format == ImageFormat.RAW12) {
                        result += "RAW12 ";
                    } else if (format == ImageFormat.RAW_SENSOR) {
                        result += "RAW_SENSOR ";
                    } else if (format == ImageFormat.RAW_PRIVATE) {
                        result += "RAW_PRIVATE ";
                    } else if (format == ImageFormat.PRIVATE) {
                        result += "PRIVATE ";
                    } else if (format == ImageFormat.RGB_565) {
                        result += "RGB_565 ";
                    } else if (format == ImageFormat.YUV_420_888) {
                        result += "YUV_420_888 ";
                    } else if (format == ImageFormat.YUV_422_888) {
                        result += "YUV_422_888 ";
                    } else if (format == ImageFormat.YUV_444_888) {
                        result += "YUV_444_888 ";
                    } else if (format == ImageFormat.YUY2) {
                        result += "YUY2 ";
                    } else if (format == ImageFormat.YV12) {
                        result += "YV12 ";
                    } else if (format == ImageFormat.FLEX_RGB_888) {
                        result += "FLEX_RGB_888 ";
                    } else if (format == ImageFormat.FLEX_RGBA_8888) {
                        result += "FLEX_RGBA_8888 ";
                    } else if (format == ImageFormat.DEPTH16) {
                        result += "DEPTH16 ";
                    } else if (format == ImageFormat.DEPTH_POINT_CLOUD) {
                        result += "DEPTH_POINT_CLOUD ";
                    } else {
                        result += InfoResultType.UNKNOWN + "(" + format + ") ";
                    }
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getHighSpeedVideoFpsRange(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            Range<Integer>[] rangeList = streamConfigurationMap.getHighSpeedVideoFpsRanges();
            if (rangeList != null && rangeList.length > 0) {
                String result = "";
                for (Range<Integer> range : rangeList) {
                    result += range.toString() + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getHighSpeedVideoSize(CameraCharacteristics cameraCharacteristics) {
        StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            Size[] sizeList = streamConfigurationMap.getHighSpeedVideoSizes();
            if (sizeList != null && sizeList.length > 0) {
                String result = "";
                for (Size size : sizeList) {
                    result += size.getWidth() + "x" + size.getHeight() + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAberrationCorrectionMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.COLOR_CORRECTION_ABERRATION_MODE_FAST) {
                    result += "fast ";
                } else if (mode == CameraCharacteristics.COLOR_CORRECTION_ABERRATION_MODE_HIGH_QUALITY) {
                    result += "high-quality ";
                } else if (mode == CameraCharacteristics.COLOR_CORRECTION_ABERRATION_MODE_OFF) {
                    result += "Off ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAeAntibandingMode(CameraCharacteristics cameraCharacteristics) {
        int[] modes = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES);
        if (modes != null) {
            String result = "";
            for (int mode : modes) {
                if (mode == CameraCharacteristics.CONTROL_AE_ANTIBANDING_MODE_50HZ) {
                    result += "50hz ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_ANTIBANDING_MODE_60HZ) {
                    result += "60hz ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_ANTIBANDING_MODE_AUTO) {
                    result += "auto ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_ANTIBANDING_MODE_OFF) {
                    result += "off ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAeCompensationRange(CameraCharacteristics cameraCharacteristics) {
        Range<Integer> range = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        if (range != null) {
            return StringUtility.getInstance().wrapUnknown(range.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAeCompensationStep(CameraCharacteristics cameraCharacteristics) {
        Rational rational = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
        if (rational != null) {
            return StringUtility.getInstance().wrapUnknown(rational.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getAeLock(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean available = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE);
            if (available != null) {
                return available ? InfoResultType.YES : InfoResultType.NO;
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAeMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.CONTROL_AE_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_MODE_ON) {
                    result += "on ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_MODE_ON_ALWAYS_FLASH) {
                    result += "on-always-flash ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_MODE_ON_AUTO_FLASH) {
                    result += "on-auto-flash ";
                } else if (mode == CameraCharacteristics.CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE) {
                    result += "on-auto-flash-red-eye ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAeTargetFpsRange(CameraCharacteristics cameraCharacteristics) {
        Range<Integer>[] rangeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        if (rangeList != null && rangeList.length > 0) {
            String result = "";
            for (Range<Integer> range : rangeList) {
                result += range.toString() + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAfMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.CONTROL_AF_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.CONTROL_AF_MODE_AUTO) {
                    result += "auto ";
                } else if (mode == CameraCharacteristics.CONTROL_AF_MODE_EDOF) {
                    result += "edof ";
                } else if (mode == CameraCharacteristics.CONTROL_AF_MODE_CONTINUOUS_PICTURE) {
                    result += "continuous-picture ";
                } else if (mode == CameraCharacteristics.CONTROL_AF_MODE_CONTINUOUS_VIDEO) {
                    result += "continuous-video ";
                } else if (mode == CameraCharacteristics.CONTROL_AF_MODE_MACRO) {
                    result += "macro ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getColorEffect(CameraCharacteristics cameraCharacteristics) {
        int[] effectList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS);
        if (effectList != null) {
            String result = "";
            for (int effect : effectList) {
                if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_OFF) {
                    result += "off ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_AQUA) {
                    result += "aqua ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_BLACKBOARD) {
                    result += "blackboard ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_MONO) {
                    result += "mono ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_NEGATIVE) {
                    result += "negative ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_POSTERIZE) {
                    result += "posterize ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_SEPIA) {
                    result += "sepia ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_SOLARIZE) {
                    result += "solarize ";
                } else if (effect == CameraCharacteristics.CONTROL_EFFECT_MODE_WHITEBOARD) {
                    result += "whiteboard ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + effect + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getControlMode(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int[] modeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_MODES);
            if (modeList != null) {
                String result = "";
                for (int mode : modeList) {
                    if (mode == CameraMetadata.CONTROL_MODE_OFF) {
                        result += "off ";
                    } else if (mode == CameraMetadata.CONTROL_MODE_OFF_KEEP_STATE) {
                        result += "off-keep-state ";
                    } else if (mode == CameraMetadata.CONTROL_MODE_AUTO) {
                        result += "auto ";
                    } else if (mode == CameraMetadata.CONTROL_MODE_USE_SCENE_MODE) {
                        result += "use-scene-mode ";
                    } else {
                        result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                    }
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSceneMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_ACTION) {
                    result += "action ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_BARCODE) {
                    result += "barcode ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_BEACH) {
                    result += "beach ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_CANDLELIGHT) {
                    result += "candlelight ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_DISABLED) {
                    result += "disabled ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_FACE_PRIORITY) {
                    result += "face-priority ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_FIREWORKS) {
                    result += "fireworks ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_HDR) {
                    result += "hdr ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_HIGH_SPEED_VIDEO) {
                    result += "high-speed-video ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_LANDSCAPE) {
                    result += "landscape ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_NIGHT) {
                    result += "night ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_NIGHT_PORTRAIT) {
                    result += "night-portrait ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_PARTY) {
                    result += "party ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_PORTRAIT) {
                    result += "portrait ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_SNOW) {
                    result += "snow ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_SPORTS) {
                    result += "sports ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_STEADYPHOTO) {
                    result += "steady-photo ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_SUNSET) {
                    result += "sunset ";
                } else if (mode == CameraCharacteristics.CONTROL_SCENE_MODE_THEATRE) {
                    result += "theatre ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getVideoStabilizationMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.CONTROL_VIDEO_STABILIZATION_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.CONTROL_VIDEO_STABILIZATION_MODE_ON) {
                    result += "on ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getAwbMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.CONTROL_AWB_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_AUTO) {
                    result += "auto ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_CLOUDY_DAYLIGHT) {
                    result += "cloudy-daylight ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_DAYLIGHT) {
                    result += "daylight ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_FLUORESCENT) {
                    result += "fluorescent ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_INCANDESCENT) {
                    result += "incandescent ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_SHADE) {
                    result += "shade ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_TWILIGHT) {
                    result += "twilight ";
                } else if (mode == CameraCharacteristics.CONTROL_AWB_MODE_WARM_FLUORESCENT) {
                    result += "warm-fluorescent ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getAwbLock(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean available = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE);
            if (available != null) {
                return available ? InfoResultType.YES : InfoResultType.NO;
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getMaximumRegionsAe(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getMaximumRegionsAf(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getMaximumRegionsAwb(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getPostRawSensitivityBoostRange(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Range<Integer> value = cameraCharacteristics.get(CameraCharacteristics.CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE);
            if (value != null) {
                return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    private String getIsDepthExclusive(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean available = cameraCharacteristics.get(CameraCharacteristics.DEPTH_DEPTH_IS_EXCLUSIVE);
            if (available != null) {
                return available ? InfoResultType.YES : InfoResultType.NO;
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getEdgeEnhancementMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.EDGE_AVAILABLE_EDGE_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.EDGE_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.EDGE_MODE_FAST) {
                    result += "fast ";
                } else if (mode == CameraCharacteristics.EDGE_MODE_HIGH_QUALITY) {
                    result += "high-quality ";
                } else if (mode == CameraCharacteristics.EDGE_MODE_ZERO_SHUTTER_LAG) {
                    result += "zero-shutter-lag";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getFlashSupported(CameraCharacteristics cameraCharacteristics) {
        Boolean available = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        if (available != null) {
            return available ? InfoResultType.YES : InfoResultType.NO;
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getHotPixelCorrectionMode(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.HOT_PIXEL_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.HOT_PIXEL_MODE_FAST) {
                    result += "fast ";
                } else if (mode == CameraCharacteristics.HOT_PIXEL_MODE_HIGH_QUALITY) {
                    result += "high-quality ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSupportedHardwareLevel(CameraCharacteristics cameraCharacteristics) {
        Integer level = cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        if (level != null) {
            String result = "";
            if (level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL) {
                result += "full ";
            } else if (level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                result += "legacy ";
            } else if (level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED) {
                result += "limited ";
            } else if (level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_3) {
                result += "3 ";
            } else {
                result += InfoResultType.UNKNOWN + "(" + level + ") ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getJpegThumbnailSizes(CameraCharacteristics cameraCharacteristics) {
        Size[] sizeList = cameraCharacteristics.get(CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES);
        if (sizeList != null && sizeList.length > 0) {
            String result = "";
            for (Size size : sizeList) {
                result += size.toString() + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensFacing(CameraCharacteristics cameraCharacteristics) {
        Integer facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
        if (facing != null) {
            if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
                return "front";
            } else if (facing == CameraCharacteristics.LENS_FACING_BACK) {
                return "back";
            }
            return InfoResultType.UNKNOWN + "(" + facing + ")";
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensApertures(CameraCharacteristics cameraCharacteristics) {
        float[] values = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES);
        if (values != null) {
            String result = "";
            for (float value : values) {
                result += value + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensFilterDensities(CameraCharacteristics cameraCharacteristics) {
        float[] values = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FILTER_DENSITIES);
        if (values != null) {
            String result = "";
            for (float value : values) {
                result += value + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensFocalLength(CameraCharacteristics cameraCharacteristics) {
        float[] values = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
        if (values != null) {
            String result = "";
            for (float value : values) {
                result += value + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensOpticalStabilization(CameraCharacteristics cameraCharacteristics) {
        int[] values = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
        if (values != null) {
            String result = "";
            for (int value : values) {
                if (value == CameraMetadata.LENS_OPTICAL_STABILIZATION_MODE_OFF) {
                    result += "off ";
                } else if (value == CameraMetadata.LENS_OPTICAL_STABILIZATION_MODE_ON) {
                    result += "on ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensFocusDistanceCalibration(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION);
        if (value != null) {
            if (value == CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_APPROXIMATE) {
                return "approximate";
            } else if (value == CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_CALIBRATED) {
                return "calibrated";
            } else if (value == CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_UNCALIBRATED) {
                return "uncalibrated";
            }
            return InfoResultType.UNKNOWN + "(" + value + ") ";
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensHyperfocalDistance(CameraCharacteristics cameraCharacteristics) {
        Float value = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getLensMinimumFocusDistance(CameraCharacteristics cameraCharacteristics) {
        Float value = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getLensIntrinsicCalibration(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            float[] valueList = cameraCharacteristics.get(CameraCharacteristics.LENS_INTRINSIC_CALIBRATION);
            if (valueList != null) {
                String result = "";
                for (float value : valueList) {
                    result += value + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    private String getLensPoseRotation(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            float[] valueList = cameraCharacteristics.get(CameraCharacteristics.LENS_POSE_ROTATION);
            if (valueList != null) {
                String result = "";
                for (float value : valueList) {
                    result += value + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    private String getLensPoseTranslation(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            float[] valueList = cameraCharacteristics.get(CameraCharacteristics.LENS_RADIAL_DISTORTION);
            if (valueList != null) {
                String result = "";
                for (float value : valueList) {
                    result += value + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    private String getLensRadialDistortion(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            float[] valueList = cameraCharacteristics.get(CameraCharacteristics.LENS_RADIAL_DISTORTION);
            if (valueList != null) {
                String result = "";
                for (float value : valueList) {
                    result += value + " ";
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getNoiseReductionModes(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.NOISE_REDUCTION_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.NOISE_REDUCTION_MODE_FAST) {
                    result += "fast ";
                } else if (mode == CameraCharacteristics.NOISE_REDUCTION_MODE_HIGH_QUALITY) {
                    result += "high-quality ";
                } else if (mode == CameraCharacteristics.NOISE_REDUCTION_MODE_MINIMAL) {
                    result += "minimal ";
                } else if (mode == CameraCharacteristics.NOISE_REDUCTION_MODE_ZERO_SHUTTER_LAG) {
                    result += "zero-shutter-lag ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getMaximumCaptureStall(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Integer value = cameraCharacteristics.get(CameraCharacteristics.REPROCESS_MAX_CAPTURE_STALL);
            if (value != null) {
                return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getRequestCapability(CameraCharacteristics cameraCharacteristics) {
        int[] values = cameraCharacteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        if (values != null) {
            String result = "";
            for (int value : values) {
                if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE) {
                    result += "backward-compatible ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING) {
                    result += "manual-post-processing ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR) {
                    result += "manual-sensor ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_RAW) {
                    result += "raw ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING) {
                    result += "private-reprocessing ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS) {
                    result += "read-sensor-settings ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE) {
                    result += "burst-capture ";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING) {
                    result += "yuv-reprocessing";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT) {
                    result += "depth-output";
                } else if (value == CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES_CONSTRAINED_HIGH_SPEED_VIDEO) {
                    result += "constrained-high-speed-video";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + value + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getRequestMaximumNumberInputStreams(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Integer value = cameraCharacteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_INPUT_STREAMS);
            if (value != null) {
                return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getRequestMaximumNumberOutputProcess(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getRequestMaximumNumberOutputProcessStalling(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getRequestMaximumNumberOutputRaw(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW);
        if (value != null) {
            if (value == ImageFormat.RAW_SENSOR) {
                return "raw-sensor";
            } else if (value == ImageFormat.RAW10) {
                return "raw10";
            } else if (value == ImageFormat.RAW12) {
                return "raw12";
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getRequestPartialResultCount(CameraCharacteristics cameraCharacteristics) {
        Integer count = cameraCharacteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
        if (count != null) {
            return StringUtility.getInstance().wrapUnknown(count.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getRequestPipelineMaxDepth(CameraCharacteristics cameraCharacteristics) {
        Byte value = cameraCharacteristics.get(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getMaximumDigitalZoom(CameraCharacteristics cameraCharacteristics) {
        Float value = cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getCroppingType(CameraCharacteristics cameraCharacteristics) {
        Integer type = cameraCharacteristics.get(CameraCharacteristics.SCALER_CROPPING_TYPE);
        if (type != null) {
            if (type == CameraMetadata.SCALER_CROPPING_TYPE_CENTER_ONLY) {
                return "center-only";
            } else if (type == CameraMetadata.SCALER_CROPPING_TYPE_FREEFORM) {
                return "freeform";
            } else {
                return InfoResultType.UNKNOWN + "(" + type + ")";
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorTestPatternModes(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.SENSOR_TEST_PATTERN_MODE_COLOR_BARS) {
                    result += "color-bars ";
                } else if (mode == CameraCharacteristics.SENSOR_TEST_PATTERN_MODE_COLOR_BARS_FADE_TO_GRAY) {
                    result += "color-bars-fade-to-gray ";
                } else if (mode == CameraCharacteristics.SENSOR_TEST_PATTERN_MODE_CUSTOM1) {
                    result += "custom1 ";
                } else if (mode == CameraCharacteristics.SENSOR_TEST_PATTERN_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.SENSOR_TEST_PATTERN_MODE_PN9) {
                    result += "pn9 ";
                } else if (mode == CameraCharacteristics.SENSOR_TEST_PATTERN_MODE_SOLID_COLOR) {
                    result += "solid-color ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorActiveArraySize(CameraCharacteristics cameraCharacteristics) {
        Rect value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.width() + "x" + value.height()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorColorFilterArrangement(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT);
        if (value != null) {
            if (value == CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_BGGR) {
                return "bggr";
            } else if (value == CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_GBRG) {
                return "gbrg";
            } else if (value == CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_GRBG) {
                return "grbg";
            } else if (value == CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGB) {
                return "rgb";
            } else if (value == CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGGB) {
                return "rggb";
            } else {
                return InfoResultType.UNKNOWN + "(" + value + ")";
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorExposureTimeRange(CameraCharacteristics cameraCharacteristics) {
        Range<Long> value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getSensorLensShadingApplied(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Boolean isApplied = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_LENS_SHADING_APPLIED);
            if (isApplied != null) {
                String result = isApplied ? InfoResultType.YES : InfoResultType.NO;
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorMaxFrameDuration(CameraCharacteristics cameraCharacteristics) {
        Long value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorPhysicalSize(CameraCharacteristics cameraCharacteristics) {
        SizeF size = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
        if (size != null) {
            return StringUtility.getInstance().wrapUnknown(size.getWidth() + "x" + size.getHeight()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorPixelArraySize(CameraCharacteristics cameraCharacteristics) {
        Size size = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        if (size != null) {
            return StringUtility.getInstance().wrapUnknown(size.getWidth() + "x" + size.getHeight()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getSensorPreCorrectionActiveArraySize(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Rect rect = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PRE_CORRECTION_ACTIVE_ARRAY_SIZE);
            if (rect != null) {
                return StringUtility.getInstance().wrapUnknown(rect.width() + "x" + rect.height()).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorSensitivityRange(CameraCharacteristics cameraCharacteristics) {
        Range<Integer> value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorTimestampSource(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE);
        if (value != null) {
            if (value == CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE_UNKNOWN) {
                return "unknown-source";
            } else if (value == CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE_REALTIME) {
                return "realtime";
            } else {
                return InfoResultType.UNKNOWN + "(" + value + ")";
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorWhiteLevel(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_WHITE_LEVEL);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorMaximumAnalogSensitivity(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_MAX_ANALOG_SENSITIVITY);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSensorOrientation(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getShadingModes(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int[] modeList = cameraCharacteristics.get(CameraCharacteristics.SHADING_AVAILABLE_MODES);
            if (modeList != null) {
                String result = "";
                for (int mode : modeList) {
                    if (mode == CameraCharacteristics.SHADING_MODE_FAST) {
                        result += "fast ";
                    } else if (mode == CameraCharacteristics.SHADING_MODE_HIGH_QUALITY) {
                        result += "high-quality ";
                    } else if (mode == CameraCharacteristics.SHADING_MODE_OFF) {
                        result += "off ";
                    } else {
                        result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                    }
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getFaceDetectionModes(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == CameraCharacteristics.STATISTICS_FACE_DETECT_MODE_OFF) {
                    result += "off ";
                } else if (mode == CameraCharacteristics.STATISTICS_FACE_DETECT_MODE_SIMPLE) {
                    result += "simple ";
                } else if (mode == CameraCharacteristics.STATISTICS_FACE_DETECT_MODE_FULL) {
                    result += "full ";
                } else {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getHotPixelMapModes(CameraCharacteristics cameraCharacteristics) {
        boolean[] modeList = cameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_HOT_PIXEL_MAP_MODES);
        if (modeList != null) {
            String result = "";
            for (boolean mode : modeList) {
                result += mode ? InfoResultType.YES + " " : InfoResultType.NO + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getLensShadingMapModes(CameraCharacteristics cameraCharacteristics) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int[] modeList = cameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES);
            if (modeList != null) {
                String result = "";
                for (int mode : modeList) {
                    if (mode == CameraMetadata.STATISTICS_LENS_SHADING_MAP_MODE_ON) {
                        result += "on ";
                    }
                    if (mode == CameraMetadata.STATISTICS_LENS_SHADING_MAP_MODE_OFF) {
                        result += "off ";
                    }
                    if (result.isEmpty()) {
                        result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                    }
                }
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getMaximumFaceDetectionCount(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getSyncMaxLatency(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.SYNC_MAX_LATENCY);
        String result = "";
        if (value != null) {
            if (value == CameraCharacteristics.SYNC_MAX_LATENCY_PER_FRAME_CONTROL) {
                result += "per-frame-control ";
            }
            if (value == CameraCharacteristics.SYNC_MAX_LATENCY_UNKNOWN) {
                result += "unknown ";
            }
            if (result.isEmpty()) {
                result += InfoResultType.UNKNOWN + "(" + value + ") ";
            }
        }
        return StringUtility.getInstance().wrapUnknown(result).trim();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getToneMapModes(CameraCharacteristics cameraCharacteristics) {
        int[] modeList = cameraCharacteristics.get(CameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES);
        if (modeList != null) {
            String result = "";
            for (int mode : modeList) {
                if (mode == TONEMAP_MODE_CONTRAST_CURVE) {
                    result += "contrast-curve ";
                }
                if (mode == CameraCharacteristics.TONEMAP_MODE_FAST) {
                    result += "fast ";
                }
                if (mode == CameraCharacteristics.TONEMAP_MODE_HIGH_QUALITY) {
                    result += "high-quality ";
                }
                if (mode == CameraCharacteristics.TONEMAP_MODE_GAMMA_VALUE) {
                    result += "gamma-value";
                }
                if (mode == CameraCharacteristics.TONEMAP_MODE_PRESET_CURVE) {
                    result += "preset-curve";
                }
                if (result.isEmpty()) {
                    result += InfoResultType.UNKNOWN + "(" + mode + ") ";
                }
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getMaxToneMapCurvePoints(CameraCharacteristics cameraCharacteristics) {
        Integer value = cameraCharacteristics.get(CameraCharacteristics.TONEMAP_MAX_CURVE_POINTS);
        if (value != null) {
            return StringUtility.getInstance().wrapUnknown(value.toString()).trim();
        }
        return InfoResultType.UNKNOWN;
    }
}
