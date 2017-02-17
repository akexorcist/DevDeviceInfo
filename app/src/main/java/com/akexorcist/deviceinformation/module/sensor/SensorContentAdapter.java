package com.akexorcist.deviceinformation.module.sensor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.sensor.model.SensorItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class SensorContentAdapter extends RecyclerView.Adapter<SensorInfoViewHolder> {
    private List<SensorItem> sensorItemList;

    public SensorContentAdapter() {
        sensorItemList = new ArrayList<>();
    }

    public void setSensorItemList(List<SensorItem> sensorItemList) {
        this.sensorItemList = sensorItemList;
    }

    @Override
    public SensorInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sensor_info_item, parent, false);
        return new SensorInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SensorInfoViewHolder holder, int position) {
        SensorItem sensorItem = sensorItemList.get(position);
        holder.tvTitlt.setText(sensorItem.getName());
        holder.tvContent.setText(sensorItem.getSensorData().getType());
    }

    @Override
    public int getItemCount() {
        return sensorItemList != null ? sensorItemList.size() : 0;
    }
}
