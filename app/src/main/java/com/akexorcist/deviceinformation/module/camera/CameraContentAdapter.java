package com.akexorcist.deviceinformation.module.camera;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.camera.model.CameraItem;
import com.akexorcist.deviceinformation.module.camera.holder.CameraContentViewHolder;
import com.akexorcist.deviceinformation.module.camera.holder.CameraEmptyViewHolder;
import com.akexorcist.deviceinformation.module.camera.holder.CameraHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class CameraContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderAdapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CAMERA_ITEM = 0;
    private static final int TYPE_EMPTY_CAMERA_ITEM = 1;

    private List<CameraItem> cameraItemList;

    public CameraContentAdapter() {
        cameraItemList = new ArrayList<>();
    }

    public void setCameraItemList(List<CameraItem> cameraItemList) {
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
            CameraItem cameraItem = cameraItemList.get(position);
            contentViewHolder.icvContent.setDataInfoList(cameraItem.getDataInfoList());
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
        return isCameraItemAvailable() ? cameraItemList.size() : 1;
    }

    private boolean isCameraItemAvailable() {
        return cameraItemList != null && !cameraItemList.isEmpty();
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_camera_header_item, parent, false);
        return new CameraHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        CameraHeaderViewHolder headerViewHolder = (CameraHeaderViewHolder) holder;
        String title = holder.itemView.getResources().getString(R.string.camera) + " " + position;
        headerViewHolder.tvHeader.setText(title);
    }
}
