package com.akexorcist.deviceinformation.module.feature.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class FeatureContentViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvPackageName;

    public FeatureContentViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_feature_name);
        tvPackageName = (TextView) itemView.findViewById(R.id.tv_feature_package_name);
    }
}
