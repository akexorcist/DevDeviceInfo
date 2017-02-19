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

public class SensorContentAdapter extends RecyclerView.Adapter<SensorContentViewHolder> {
    private List<SensorItem> sensorItemList;
    private OnSensorInfoClickListener onSensorInfoClickListener;

    public SensorContentAdapter() {
        sensorItemList = new ArrayList<>();
    }

    public void setSensorItemList(List<SensorItem> sensorItemList) {
        this.sensorItemList = sensorItemList;
    }

    @Override
    public SensorContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sensor_item, parent, false);
        return new SensorContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SensorContentViewHolder holder, int position) {
        SensorItem sensorItem = sensorItemList.get(position);
        holder.tvTitle.setText(sensorItem.getName());
        holder.tvContent.setText(sensorItem.getSensorData().getType());
        holder.itemView.setOnClickListener(onSensorInfoClick(sensorItem));
    }

    @Override
    public int getItemCount() {
        return sensorItemList != null ? sensorItemList.size() : 0;
    }

    private View.OnClickListener onSensorInfoClick(final SensorItem sensorItem) {
        return v -> {
            if (onSensorInfoClickListener != null) {
                onSensorInfoClickListener.onSensorInfoClick(sensorItem);
            }
        };
    }

    public void setOnSensorInfoClickListener(OnSensorInfoClickListener onSensorInfoClickListener) {
        this.onSensorInfoClickListener = onSensorInfoClickListener;
    }

    public interface OnSensorInfoClickListener {
        void onSensorInfoClick(SensorItem sensorItem);
    }
}
