package com.akexorcist.deviceinformation.module.sensor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class SensorInfoViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitlt;
    TextView tvContent;

    public SensorInfoViewHolder(View itemView) {
        super(itemView);
        tvTitlt = (TextView) itemView.findViewById(R.id.tv_sensor_title);
        tvContent = (TextView) itemView.findViewById(R.id.tv_sensor_type);
    }
}
