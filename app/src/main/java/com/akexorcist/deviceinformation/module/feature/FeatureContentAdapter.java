package com.akexorcist.deviceinformation.module.feature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.feature.model.FeatureItem;
import com.akexorcist.deviceinformation.module.feature.holder.FeatureContentViewHolder;
import com.akexorcist.deviceinformation.module.feature.holder.FeatureEmptyViewHolder;
import com.akexorcist.deviceinformation.module.feature.holder.FeatureHeaderViewHolder;
import com.akexorcist.deviceinformation.module.feature.holder.FeatureUnknownViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class FeatureContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SUPPORTED_HEADER = 0;
    private static final int TYPE_UNSUPPORTED_HEADER = 1;
    private static final int TYPE_FEATURE_ITEM = 2;
    private static final int TYPE_EMPTY_ITEM = 3;
    private static final int TYPE_UNKNOWN = 4;

    private List<FeatureItem> supportedFeatureItem;
    private List<FeatureItem> unsupportedFeatureItem;
    private OnFeatureInfoClickListener onFeatureInfoClickListener;

    public FeatureContentAdapter() {
        supportedFeatureItem = new ArrayList<>();
        unsupportedFeatureItem = new ArrayList<>();
    }

    public void setFeatureItemList(List<FeatureItem> supportedFeatureItem, List<FeatureItem> unsupportedFeatureItem) {
        this.supportedFeatureItem = supportedFeatureItem;
        this.unsupportedFeatureItem = unsupportedFeatureItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FEATURE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feature_item, parent, false);
            return new FeatureContentViewHolder(view);
        } else if (viewType == TYPE_EMPTY_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feature_empty_item, parent, false);
            return new FeatureEmptyViewHolder(view);
        } else if (viewType == TYPE_SUPPORTED_HEADER || viewType == TYPE_UNSUPPORTED_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feature_header_item, parent, false);
            return new FeatureHeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feature_unknown_item, parent, false);
            return new FeatureUnknownViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FeatureContentViewHolder) {
            FeatureContentViewHolder contentViewHolder = (FeatureContentViewHolder) holder;
            FeatureItem featureItem = getFeatureContentByPosition(position);
            if (featureItem != null) {
                contentViewHolder.tvName.setText(featureItem.getName());
                contentViewHolder.tvPackageName.setText(featureItem.getPackageName());
                contentViewHolder.itemView.setOnClickListener(onFeatureContentClick(featureItem));
            }
        } else if (holder instanceof FeatureHeaderViewHolder) {
            FeatureHeaderViewHolder headerViewHolder = (FeatureHeaderViewHolder) holder;
            headerViewHolder.tvHeader.setVisibility(View.VISIBLE);
            int viewType = getItemViewType(position);
            if (viewType == TYPE_SUPPORTED_HEADER) {
                headerViewHolder.tvHeader.setText(R.string.feature_supported_feature_header);
            } else if (viewType == TYPE_UNSUPPORTED_HEADER) {
                headerViewHolder.tvHeader.setText(R.string.feature_unsupported_feature_header);
            } else {
                headerViewHolder.tvHeader.setVisibility(View.GONE);
            }
        } else if (holder instanceof FeatureEmptyViewHolder) {
            FeatureEmptyViewHolder emptyViewHolder = (FeatureEmptyViewHolder) holder;
            // Nothing to do yet
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isSupportedFeatureHeader(position)) {
            return TYPE_SUPPORTED_HEADER;
        } else if (isSupportedFeatureContent(position)) {
            if (isSupportedFeatureAvailable()) {
                return TYPE_FEATURE_ITEM;
            } else {
                return TYPE_EMPTY_ITEM;
            }
        } else if (isUnsupportedFeatureHeader(position)) {
            return TYPE_UNSUPPORTED_HEADER;
        } else if (isUnsupportedFeatureContent(position)) {
            if (isUnsupportedFeatureAvailable()) {
                return TYPE_FEATURE_ITEM;
            } else {
                return TYPE_EMPTY_ITEM;
            }
        }
        return TYPE_UNKNOWN;
    }

    @Override
    public int getItemCount() {
        int supportedItemCount = getSupportedItemCount();
        int unsupportedItemCount = getUnsupportedItemCount();
        int headerCount = 2;
        return supportedItemCount + unsupportedItemCount + headerCount;
    }

    private int getSupportedItemCount() {
        // Return 1 row if no data available (for empty item type)
        return supportedFeatureItem != null && supportedFeatureItem.size() != 0 ? supportedFeatureItem.size() : 1;
    }

    private int getUnsupportedItemCount() {
        // Return 1 row if no data available (for empty item type)
        return unsupportedFeatureItem != null && unsupportedFeatureItem.size() != 0 ? unsupportedFeatureItem.size() : 1;
    }

    private FeatureItem getFeatureContentByPosition(int position) {
        if (isSupportedFeatureHeader(position)) {
            // Supported header type
            return null;
        } else if (isSupportedFeatureContent(position)) {
            // getSupportedItemCount can be 1 when have no feature in supported feature
            if (isSupportedFeatureAvailable()) {
                // Supported feature item type
                return supportedFeatureItem.get(position - 1);
            }
        } else if (isUnsupportedFeatureHeader(position)) {
            // Unsupported header type
            return null;
        } else if (isUnsupportedFeatureContent(position)) {
            if (isUnsupportedFeatureAvailable()) {
                // Unsupported feature item type
                return unsupportedFeatureItem.get(position - (getSupportedItemCount() + 2));
            }
        }
        // For other view holder type
        return null;
    }

    private boolean isSupportedFeatureAvailable() {
        return supportedFeatureItem != null && supportedFeatureItem.size() != 0;
    }

    private boolean isUnsupportedFeatureAvailable() {
        return unsupportedFeatureItem != null && unsupportedFeatureItem.size() != 0;
    }

    private boolean isSupportedFeatureHeader(int position) {
        return position == 0;
    }

    private boolean isSupportedFeatureContent(int position) {
        return position > 0 && position <= getSupportedItemCount();
    }

    private boolean isUnsupportedFeatureHeader(int position) {
        return position == getSupportedItemCount() + 1;
    }

    private boolean isUnsupportedFeatureContent(int position) {
        return position > getSupportedItemCount() + 1 && position <= getSupportedItemCount() + getUnsupportedItemCount() + 1;
    }

    private View.OnClickListener onFeatureContentClick(final FeatureItem featureItem) {
        return v -> {
            if (onFeatureInfoClickListener != null) {
                onFeatureInfoClickListener.onFeatureContentClick(featureItem);
            }
        };
    }

    public void setOnFeatureInfoClickListener(OnFeatureInfoClickListener onFeatureInfoClickListener) {
        this.onFeatureInfoClickListener = onFeatureInfoClickListener;
    }

    public interface OnFeatureInfoClickListener {
        void onFeatureContentClick(FeatureItem featureItem);
    }
}
