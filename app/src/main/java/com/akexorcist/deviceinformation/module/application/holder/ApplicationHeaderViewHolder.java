package com.akexorcist.deviceinformation.module.application.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class ApplicationHeaderViewHolder extends RecyclerView.ViewHolder {
    public TextView tvHeader;

    public ApplicationHeaderViewHolder(View itemView) {
        super(itemView);
        tvHeader = (TextView) itemView.findViewById(R.id.tv_application_header);
    }
}
