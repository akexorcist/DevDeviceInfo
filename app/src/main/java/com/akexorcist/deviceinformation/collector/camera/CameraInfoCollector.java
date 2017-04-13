package com.akexorcist.deviceinformation.collector.camera;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.os.Build;

import com.akexorcist.deviceinformation.collector.InfoResultType;
import com.akexorcist.deviceinformation.collector.camera.model.CameraInfo;
import com.akexorcist.deviceinformation.collector.camera.model.CameraItem;
import com.akexorcist.deviceinformation.common.BaseInfoCollector;
import com.akexorcist.deviceinformation.network.data.Data;
import com.akexorcist.deviceinformation.utility.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Akexorcist on 11/22/2016 AD.
 */

@SuppressWarnings("deprecation")
public class CameraInfoCollector extends BaseInfoCollector {
    private static CameraInfoCollector collector;

    public static CameraInfoCollector getInstance() {
        if (collector == null) {
            collector = new CameraInfoCollector();
        }
        return collector;
    }

    public CameraInfo collect() {
        // Get info from json
//        List<Data.Camera> cameraDataList = SamsungS8.getInfo().getData().getCamera();
//        List<CameraItem> cameraItemList = new ArrayList<>();
//        for (Data.Camera cameraData : cameraDataList) {
//            CameraItem cameraItem = new CameraItem();
//            cameraItem.setDataInfoList(cameraData.getData());
//            cameraItemList.add(cameraItem);
//        }
//        CameraInfo cameraInfo = new CameraInfo();
//        cameraInfo.setCameraItemList(cameraItemList);
//        return cameraInfo;
        CameraInfo cameraInfo = new CameraInfo();
        int cameraCount = Camera.getNumberOfCameras();
        for (int index = 0; index < cameraCount; index++) {
            Camera.CameraInfo cameraInfoParam = new Camera.CameraInfo();
            Camera.getCameraInfo(index, cameraInfoParam);
            Camera camera = Camera.open(index);
            Camera.Parameters cameraParam = camera.getParameters();
            camera.release();
            CameraItem cameraItem = new CameraItem()
                    .setCameraId(index + "")
                    .setAntibanding(getAntibanding(cameraParam))
                    .setCameraFacing(getCameraFacing(cameraInfoParam))
                    .setColorEffect(getColorEffect(cameraParam))
                    .setCanDisableShutterSound(getCanDisableShutterSound(cameraInfoParam))
                    .setFlashMode(getFlashMode(cameraParam))
                    .setFocusMode(getFocusMode(cameraParam))
                    .setSceneMode(getSceneMode(cameraParam))
                    .setWhiteBalance(getWhiteBalance(cameraParam))
                    .setPictureFormat(getPictureFormat(cameraParam))
                    .setPreviewFormat(getPreviewFormat(cameraParam))
                    .setImageOrientation(getImageOrientation(cameraInfoParam))
                    .setVerticalViewAngle(getVerticalViewAngle(cameraParam))
                    .setExposureCompensationStep(getExposureCompensationStep(cameraParam))
                    .setMinimumExposureCompensation(getMinimumExposureCompensation(cameraParam))
                    .setMaximumExposureCompensation(getMaximumExposureCompensation(cameraParam))
                    .setMaximumNumberOfDetectedFaces(getMaximumNumberOfDetectedFaces(cameraParam))
                    .setMaximumNumberOfFocusAreas(getMaximumNumberOfFocusAreas(cameraParam))
                    .setMaximumNumberOfMeteringAreas(getMaximumNumberOfMeteringAreas(cameraParam))
                    .setJpegThumbnailSize(getJpegThumbnailSize(cameraParam))
                    .setPictureSize(getPictureSize(cameraParam))
                    .setPreviewSize(getPreviewSize(cameraParam))
                    .setPreferredPreviewSizeForVideo(getPreferredPreviewSizeForVideo(cameraParam))
                    .setVideoSize(getVideoSize(cameraParam))
                    .setPreviewFpsRange(getPreviewFpsRange(cameraParam))
                    .setVideoQualityProfile(getVideoQualityProfile(index))
                    .setTimeLapseQualityProfile(getTimeLapseQualityProfile(index))
                    .setHighSpeedQualityProfile(getHighSpeedQualityProfile(index))
                    .setAutoExposureLockSupported(getAutoExposureLockSupported(cameraParam))
                    .setAutoWhiteBalanceLockSupported(getAutoWhiteBalanceLockSupported(cameraParam))
                    .setSmoothZoomSupported(getSmoothZoomSupported(cameraParam))
                    .setVideoSnapshotSupported(getVideoSnapshotSupported(cameraParam))
                    .setVideoStabilizationSupported(getVideoStabilizationSupported(cameraParam))
                    .setZoomSupported(getZoomSupported(cameraParam))
                    .setMaximumZoomValue(getMaximumZoomValue(cameraParam))
                    .setZoomRatio(getZoomRatio(cameraParam));
            cameraInfo.addCameraItem(cameraItem);
        }
        return cameraInfo;
    }

    private String getAntibanding(Camera.Parameters cameraParam) {
        List<String> values = cameraParam.getSupportedAntibanding();
        if (values != null) {
            String result = "";
            for (String value : values) {
                result += value + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getCameraFacing(Camera.CameraInfo cameraInfoParam) {
        int facing = cameraInfoParam.facing;
        if (facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            return "back";
        } else if (facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            return "front";
        }
        return InfoResultType.UNKNOWN;
    }

    private String getColorEffect(Camera.Parameters cameraParam) {
        List<String> effects = cameraParam.getSupportedColorEffects();
        if (effects != null) {
            String result = "";
            for (String effect : effects) {
                result += effect + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getCanDisableShutterSound(Camera.CameraInfo cameraInfoParam) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return cameraInfoParam.canDisableShutterSound ? InfoResultType.YES : InfoResultType.NO;
        }
        return InfoResultType.UNKNOWN;
    }

    private String getFlashMode(Camera.Parameters cameraParam) {
        List<String> modeList = cameraParam.getSupportedFlashModes();
        if (modeList != null) {
            String result = "";
            for (String mode : modeList) {
                result += mode + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getFocusMode(Camera.Parameters cameraParam) {
        List<String> modeList = cameraParam.getSupportedFocusModes();
        if (modeList != null) {
            String result = "";
            for (String mode : modeList) {
                result += mode + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getJpegThumbnailSize(Camera.Parameters cameraParam) {
        List<Camera.Size> sizeList = cameraParam.getSupportedJpegThumbnailSizes();
        if (sizeList != null) {
            String result = "";
            for (Camera.Size size : sizeList) {
                result += size.width + "x" + size.height + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getImageOrientation(Camera.CameraInfo cameraInfoParam) {
        return StringUtility.getInstance().wrapUnknown(cameraInfoParam.orientation + "").trim();
    }

    private String getPictureFormat(Camera.Parameters cameraParam) {
        List<Integer> formatList = cameraParam.getSupportedPictureFormats();
        if (formatList != null) {
            return StringUtility.getInstance().wrapUnknown(getSupportFormat(formatList)).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getPreviewFormat(Camera.Parameters cameraParam) {
        List<Integer> formatList = cameraParam.getSupportedPreviewFormats();
        if (formatList != null) {
            return StringUtility.getInstance().wrapUnknown(getSupportFormat(formatList)).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getSupportFormat(List<Integer> formatList) {
        String type = "";
        for (Integer format : formatList) {
            if (format == ImageFormat.JPEG) {
                type += "JPEG";
            } else if (format == ImageFormat.YV12) {
                type += "YV12";
            } else if (format == ImageFormat.YUY2) {
                type += "YUY2";
            } else if (format == ImageFormat.RGB_565) {
                type += "RGB_565";
            } else if (format == ImageFormat.NV16) {
                type += "NV16";
            } else if (format == ImageFormat.NV21) {
                type += "NV21";
            } else if (format == ImageFormat.RAW10) {
                type += "RAW10";
            } else if (format == ImageFormat.RAW12) {
                type += "RAW12";
            } else if (format == ImageFormat.YUV_420_888) {
                type += "YUV_420_888";
            } else if (format == ImageFormat.YUV_422_888) {
                type += "YUV_422_888";
            } else if (format == ImageFormat.YUV_444_888) {
                type += "YUV_444_888";
            } else if (format == ImageFormat.RAW_SENSOR) {
                type += "RAW_SENSOR";
            } else if (format == ImageFormat.RAW_PRIVATE) {
                type += "RAW_PRIVATE";
            } else if (format == ImageFormat.PRIVATE) {
                type += "PRIVATE";
            } else if (format == ImageFormat.DEPTH16) {
                type += "DEPTH16";
            } else if (format == ImageFormat.DEPTH_POINT_CLOUD) {
                type += "DEPTH_POINT_CLOUD";
            } else if (format == ImageFormat.FLEX_RGB_888) {
                type += "FLEX_RGB_888";
            } else if (format == ImageFormat.FLEX_RGBA_8888) {
                type += "FLEX_RGBA_8888";
            } else {
                type += "UNKNOWN(" + format + ")";
            }
        }
        return type + " ";
    }

    private String getPictureSize(Camera.Parameters cameraParam) {
        List<Camera.Size> sizeList = cameraParam.getSupportedPictureSizes();
        if (sizeList != null) {
            String result = "";
            for (Camera.Size size : sizeList)
                result += size.width + "x" + size.height + " ";
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getPreviewSize(Camera.Parameters cameraParam) {
        List<Camera.Size> sizes = cameraParam.getSupportedPreviewSizes();
        if (sizes != null) {
            String result = "";
            for (Camera.Size size : sizes) {
                result += size.width + "x" + size.height + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getPreferredPreviewSizeForVideo(Camera.Parameters cameraParam) {
        Camera.Size size = cameraParam.getPreferredPreviewSizeForVideo();
        if (size != null) {
            return size.width + "x" + size.height;
        }
        return InfoResultType.UNKNOWN;
    }

    private String getPreviewFpsRange(Camera.Parameters cameraParam) {
        List<int[]> rangeList = cameraParam.getSupportedPreviewFpsRange();
        if (rangeList != null) {
            String result = "";
            for (int i = 0; i < rangeList.size(); i++) {
                result += (float) rangeList.get(i)[0] / 1000 + "-" + (float) rangeList.get(i)[rangeList.get(i).length - 1] / 1000 + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getSceneMode(Camera.Parameters cameraParam) {
        List<String> modeList = cameraParam.getSupportedSceneModes();
        if (modeList != null) {
            String result = "";
            for (String mode : modeList) {
                result += mode + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getVideoQualityProfile(int cameraId) {
        String result = "";
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_QCIF)) {
            result += "176x144 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_QVGA)) {
            result += "320x240 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_CIF)) {
            result += "352x288 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_480P)) {
            result += "720x480 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_720P)) {
            result += "1280x720 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_1080P)) {
            result += "1920x1080 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_2160P)) {
            result += "3840x2160 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_LOW)) {
            result += "lowest_available ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH)) {
            result += "highest_available ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x09)) {
            result += "Unknown(0x09) ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x0A)) {
            result += "Unknown(0x0A) ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x0B)) {
            result += "Unknown(0x0B) ";
        }
        return StringUtility.getInstance().wrapUnknown(result).trim();
    }

    private String getTimeLapseQualityProfile(int cameraId) {
        String result = "";
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_QCIF)) {
            result += "176x144 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_QVGA)) {
            result += "320x240 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_CIF)) {
            result += "352x288 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_480P)) {
            result += "720x480 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_720P)) {
            result += "1280x720 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_1080P)) {
            result += "1920x1080 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_2160P)) {
            result += "3840x2160 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_LOW)) {
            result += "lowest-quality ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_TIME_LAPSE_HIGH)) {
            result += "highest-quality ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x3F1)) {
            result += "Unknown(0x3F1) ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x3F2)) {
            result += "Unknown(0x3F2) ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x3F3)) {
            result += "Unknown(0x3F3) ";
        }
        return StringUtility.getInstance().wrapUnknown(result).trim();
    }

    private String getHighSpeedQualityProfile(int cameraId) {
        String result = "";
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH_SPEED_480P)) {
            result += "720x480 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH_SPEED_720P)) {
            result += "1280x720 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH_SPEED_1080P)) {
            result += "1920x1080 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH_SPEED_2160P)) {
            result += "3840x2160 ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH_SPEED_LOW)) {
            result += "lowest-available ";
        }
        if (CamcorderProfile.hasProfile(cameraId, CamcorderProfile.QUALITY_HIGH_SPEED_HIGH)) {
            result += "highest-available ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x7D6)) {
            result += "Unknown(0x7D6) ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x7D7)) {
            result += "Unknown(0x7D7) ";
        }
        if (CamcorderProfile.hasProfile(cameraId, 0x7D8)) {
            result += "Unknown(0x7D8) ";
        }
        return StringUtility.getInstance().wrapUnknown(result).trim();
    }

    private String getVideoSize(Camera.Parameters cameraParam) {
        List<Camera.Size> sizeList = cameraParam.getSupportedVideoSizes();
        if (sizeList != null) {
            String result = "";
            for (Camera.Size size : sizeList) {
                result += size.width + "x" + size.height + " ";
            }
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getWhiteBalance(Camera.Parameters cameraParam) {
        List<String> modeList = cameraParam.getSupportedWhiteBalance();
        if (modeList != null) {
            String result = "";
            for (String mode : modeList)
                result += mode + " ";
            return StringUtility.getInstance().wrapUnknown(result).trim();
        }
        return InfoResultType.UNKNOWN;
    }

    private String getAutoExposureLockSupported(Camera.Parameters cameraParam) {
        return cameraParam.isAutoExposureLockSupported() ? InfoResultType.YES : InfoResultType.NO;
    }

    private String getAutoWhiteBalanceLockSupported(Camera.Parameters cameraParam) {
        return cameraParam.isAutoWhiteBalanceLockSupported() ? InfoResultType.YES : InfoResultType.NO;
    }

    private String getSmoothZoomSupported(Camera.Parameters cameraParam) {
        return cameraParam.isSmoothZoomSupported() ? InfoResultType.YES : InfoResultType.NO;
    }

    private String getVerticalViewAngle(Camera.Parameters cameraParam) {
        return cameraParam.getVerticalViewAngle() + "";
    }

    private String getExposureCompensationStep(Camera.Parameters cameraParam) {
        return cameraParam.getExposureCompensationStep() + "";
    }

    private String getMinimumExposureCompensation(Camera.Parameters cameraParam) {
        return cameraParam.getMinExposureCompensation() + "";
    }

    private String getMaximumExposureCompensation(Camera.Parameters cameraParam) {
        return cameraParam.getMaxExposureCompensation() + "";
    }

    private String getMaximumNumberOfDetectedFaces(Camera.Parameters cameraParam) {
        return cameraParam.getMaxNumDetectedFaces() + "";
    }

    private String getMaximumNumberOfFocusAreas(Camera.Parameters cameraParam) {
        return cameraParam.getMaxNumFocusAreas() + "";
    }

    private String getMaximumNumberOfMeteringAreas(Camera.Parameters cameraParam) {
        return cameraParam.getMaxNumMeteringAreas() + "";
    }

    private String getVideoSnapshotSupported(Camera.Parameters cameraParam) {
        return cameraParam.isVideoSnapshotSupported() ? InfoResultType.YES : InfoResultType.NO;
    }

    private String getVideoStabilizationSupported(Camera.Parameters cameraParam) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            return cameraParam.isVideoStabilizationSupported() ? InfoResultType.YES : InfoResultType.NO;
        }
        return InfoResultType.UNKNOWN;
    }

    private String getZoomSupported(Camera.Parameters cameraParam) {
        return cameraParam.isZoomSupported() ? InfoResultType.YES : InfoResultType.NO;
    }

    private String getMaximumZoomValue(Camera.Parameters cameraParam) {
        if (cameraParam.isZoomSupported()) {
            return cameraParam.getMaxZoom() + "";
        }
        return InfoResultType.UNKNOWN;
    }

    private String getZoomRatio(Camera.Parameters cameraParam) {
        if (cameraParam.isZoomSupported()) {
            List<Integer> zoomRatioList = cameraParam.getZoomRatios();
            if (zoomRatioList != null) {
                String result = "";
                for (Integer zoomRatio : zoomRatioList)
                    result += String.format(Locale.getDefault(), "%.2f ", zoomRatio / 100f);
                return StringUtility.getInstance().wrapUnknown(result).trim();
            }
        }
        return InfoResultType.UNKNOWN;
    }

}
