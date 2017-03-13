package com.akexorcist.deviceinformation.module.chooser.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.R;

import java.util.List;

/**
 * Created by Akexorcist on 3/14/2017 AD.
 */

public class DeviceChooserContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_EMPTY_ITEM = 0;
    private static final int TYPE_BRAND_ITEM = 1;

    private List<String> brandList;
    private OnItemClickListener onItemClickListener;

    public DeviceChooserContentAdapter() {
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_EMPTY_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_item, parent, false);
            return new DeviceChooserEmptyViewHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_device_chooser_brand_item, parent, false);
        return new DeviceChooserContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DeviceChooserContentViewHolder) {
            String brand = brandList.get(position);
            DeviceChooserContentViewHolder deviceChooserContentViewHolder = (DeviceChooserContentViewHolder) holder;
            deviceChooserContentViewHolder.tvDeviceBrand.setText(brand);
            deviceChooserContentViewHolder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onBrandClick(brand);
                }
            });
        } else if (holder instanceof DeviceChooserEmptyViewHolder) {
            DeviceChooserEmptyViewHolder deviceChooserEmptyViewHolder = (DeviceChooserEmptyViewHolder) holder;
            deviceChooserEmptyViewHolder.itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onReloadClick();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return brandList != null && !brandList.isEmpty() ? brandList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (brandList == null || brandList.isEmpty()) {
            return TYPE_EMPTY_ITEM;
        }
        return TYPE_BRAND_ITEM;
    }

    public interface OnItemClickListener {
        void onBrandClick(String brand);

        void onReloadClick();
    }
}
