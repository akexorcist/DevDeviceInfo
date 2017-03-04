package com.akexorcist.deviceinformation.module.camera.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.widget.InfoCardView;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class CameraContentViewHolder extends RecyclerView.ViewHolder {
    public InfoCardView icvContent;

    public CameraContentViewHolder(View itemView) {
        super(itemView);
        icvContent = (InfoCardView) itemView.findViewById(R.id.icv_camera_content);
    }
}
