package com.akexorcist.deviceinformation.module.chooser.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class DeviceChooserContentViewHolder extends RecyclerView.ViewHolder {
    public TextView tvDeviceBrand;

    public DeviceChooserContentViewHolder(View itemView) {
        super(itemView);
        tvDeviceBrand = (TextView) itemView.findViewById(R.id.tv_device_chooser_brand);
    }
}
