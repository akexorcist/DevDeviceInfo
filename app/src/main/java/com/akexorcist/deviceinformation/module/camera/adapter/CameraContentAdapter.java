package com.akexorcist.deviceinformation.module.camera.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseInfo;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.module.camera.adapter.holder.CameraContentViewHolder;
import com.akexorcist.deviceinformation.module.camera.adapter.holder.CameraEmptyViewHolder;
import com.akexorcist.deviceinformation.module.camera.adapter.holder.CameraHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class CameraContentAdapter<BI extends BaseInfo> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderAdapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CAMERA_ITEM = 0;
    private static final int TYPE_EMPTY_CAMERA_ITEM = 1;

    private List<BI> cameraItemList;

    public CameraContentAdapter() {
        cameraItemList = new ArrayList<>();
    }

    public void setCameraItemList(List<BI> cameraItemList) {
        this.cameraItemList = cameraItemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CAMERA_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_camera_item, parent, false);
            return new CameraContentViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_camera_empty_item, parent, false);
            return new CameraEmptyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CameraContentViewHolder) {
            CameraContentViewHolder contentViewHolder = (CameraContentViewHolder) holder;
            DataInfo dataInfo = getDataInfoByPosition(position);
            if (dataInfo != null) {
                contentViewHolder.tvTitle.setText(dataInfo.getTitle());
                contentViewHolder.tvValue.setText(dataInfo.getValue());
            } else {
                throw new NullPointerException("WTF going on!");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isCameraItemAvailable()) {
            return TYPE_CAMERA_ITEM;
        }
        return TYPE_EMPTY_CAMERA_ITEM;
    }

    @Override
    public int getItemCount() {
        return isCameraItemAvailable() ? getTotalCameraItemCount() : 1;
    }

    private DataInfo getDataInfoByPosition(int position) {
        if (cameraItemList != null) {
            int itemCount = 0;
            for (BI cameraItem : cameraItemList) {
                if ((cameraItem.getDataInfoCount() + itemCount) - 1 >= position) {
                    // Expected item is in this camera item
                    return cameraItem.getDataInfoList().get(position - itemCount);
                } else {
                    // Item isn't here, let's check next camera info
                    itemCount += cameraItem.getDataInfoCount();
                }
            }
        }
        // This case shouldn't occurred
        return null;
    }

    public int getFirstDataInfoPositionByCameraId(int position) {
        if (cameraItemList != null) {
            int itemCount = 0;
            for (int index = 0; index < position; index++) {
                BI cameraItem = cameraItemList.get(index);
                itemCount += cameraItem.getDataInfoCount();
            }
            return itemCount;
        }
        return -1;
    }

    private boolean isCameraItemAvailable() {
        return cameraItemList != null && !cameraItemList.isEmpty() && getTotalCameraItemCount() != 0;
    }

    private int getTotalCameraItemCount() {
        int count = 0;
        for (BI cameraItem : cameraItemList) {
            count += cameraItem.getDataInfoCount();
        }
        return count;
    }

    @Override
    public long getHeaderId(int position) {
        if (cameraItemList != null) {
            int itemCount = 0;
            for (int index = 0; index < cameraItemList.size(); index++) {
                BI cameraItem = cameraItemList.get(index);
                if ((cameraItem.getDataInfoCount() + itemCount) - 1 >= position) {
                    // Expected header ID is in this camera item
                    return index;
                } else {
                    // Header ID isn't here, let's check next camera info
                    itemCount += cameraItem.getDataInfoCount();
                }
            }
        }
        return StickyHeaderDecoration.NO_HEADER_ID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_camera_header_item, parent, false);
        return new CameraHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        CameraHeaderViewHolder headerViewHolder = (CameraHeaderViewHolder) holder;
        String title = String.format("%s %s", holder.itemView.getResources().getString(R.string.camera), getHeaderId(position));
        headerViewHolder.tvHeader.setText(title);
    }
}
