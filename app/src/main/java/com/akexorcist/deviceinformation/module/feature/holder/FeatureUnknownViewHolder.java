package com.akexorcist.deviceinformation.module.feature.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class FeatureHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvHeader;

    public FeatureHeaderViewHolder(View itemView) {
        super(itemView);
        tvHeader = (TextView) itemView.findViewById(R.id.tv_feature_header);
    }
}
