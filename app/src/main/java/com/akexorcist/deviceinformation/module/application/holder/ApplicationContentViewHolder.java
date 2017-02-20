package com.akexorcist.deviceinformation.module.application.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class ApplicationContentViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvPackageName;
    public ImageView ivAppIcon;

    public ApplicationContentViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_application_name);
        tvPackageName = (TextView) itemView.findViewById(R.id.tv_application_package_name);
        ivAppIcon = (ImageView) itemView.findViewById(R.id.iv_application_icon);
    }
}
