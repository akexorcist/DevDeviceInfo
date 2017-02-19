package com.akexorcist.deviceinformation.module.feature.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class FeatureEmptyViewHolder extends RecyclerView.ViewHolder {
    public TextView tvEmptyDescription;

    public FeatureEmptyViewHolder(View itemView) {
        super(itemView);
        tvEmptyDescription = (TextView) itemView.findViewById(R.id.tv_feature_empty_description);
    }
}
