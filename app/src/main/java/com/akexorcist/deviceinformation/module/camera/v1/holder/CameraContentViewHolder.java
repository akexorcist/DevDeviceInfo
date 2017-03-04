package com.akexorcist.deviceinformation.module.camera.v1.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class CameraContentViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitle;
    public TextView tvValue;

    public CameraContentViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_camera_info_title);
        tvValue = (TextView) itemView.findViewById(R.id.tv_camera_info_value);
    }
}
