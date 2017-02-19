package com.akexorcist.deviceinformation.module.sensor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class SensorContentViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;
    TextView tvContent;

    public SensorContentViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_sensor_title);
        tvContent = (TextView) itemView.findViewById(R.id.tv_sensor_type);
    }
}
