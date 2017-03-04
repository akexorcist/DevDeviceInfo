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

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class FeatureContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderAdapter<RecyclerView.ViewHolder> {
    private static final int TYPE_FEATURE_ITEM = 0;
    private static final int TYPE_EMPTY_SUPPORTED_ITEM = 1;
    private static final int TYPE_EMPTY_UNSUPPORTED_ITEM = 2;
    private static final int TYPE_UNKNOWN = 3;
    private static final int HEADER_ID_SUPPORTED = 0;
    private static final int HEADER_ID_UNSUPPORTED = 1;

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
        } else if (viewType == TYPE_EMPTY_SUPPORTED_ITEM || viewType == TYPE_EMPTY_UNSUPPORTED_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feature_empty_item, parent, false);
            return new FeatureEmptyViewHolder(view);
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
        } else if (holder instanceof FeatureEmptyViewHolder) {
            FeatureEmptyViewHolder emptyViewHolder = (FeatureEmptyViewHolder) holder;
            emptyViewHolder.tvEmptyDescription.setVisibility(View.VISIBLE);
            int viewType = getItemViewType(position);
            if (viewType == TYPE_EMPTY_SUPPORTED_ITEM) {
                emptyViewHolder.tvEmptyDescription.setText(R.string.feature_empty_supported_feature_available);
            } else if (viewType == TYPE_EMPTY_UNSUPPORTED_ITEM) {
                emptyViewHolder.tvEmptyDescription.setText(R.string.feature_empty_unsupported_feature_available);
            } else {
                emptyViewHolder.tvEmptyDescription.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isSupportedFeatureContent(position)) {
            if (isSupportedFeatureAvailable()) {
                return TYPE_FEATURE_ITEM;
            } else {
                return TYPE_EMPTY_SUPPORTED_ITEM;
            }
        } else if (isUnsupportedFeatureContent(position)) {
            if (isUnsupportedFeatureAvailable()) {
                return TYPE_FEATURE_ITEM;
            } else {
                return TYPE_EMPTY_UNSUPPORTED_ITEM;
            }
        }
        return TYPE_UNKNOWN;
    }

    @Override
    public int getItemCount() {
        int supportedItemCount = getSupportedItemCount();
        int unsupportedItemCount = getUnsupportedItemCount();
        return supportedItemCount + unsupportedItemCount;
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
        if (isSupportedFeatureContent(position)) {
            // getSupportedItemCount can be 1 when have no feature in supported feature
            if (isSupportedFeatureAvailable()) {
                // Supported feature item type
                return supportedFeatureItem.get(position);
            }
        } else if (isUnsupportedFeatureContent(position)) {
            if (isUnsupportedFeatureAvailable()) {
                // Unsupported feature item type
                return unsupportedFeatureItem.get(position - getSupportedItemCount());
            }
        }
        // For other view holder type
        return null;
    }

    public int getFirstFeatureContentPositionByHeaderId(int index) {
        if (index == HEADER_ID_SUPPORTED) {
            return 0;
        } else if (index == HEADER_ID_UNSUPPORTED) {
            return getSupportedItemCount();
        }
        return -1;
    }

    public boolean isSupportedFeatureAvailable() {
        return supportedFeatureItem != null && supportedFeatureItem.size() != 0;
    }

    public boolean isUnsupportedFeatureAvailable() {
        return unsupportedFeatureItem != null && unsupportedFeatureItem.size() != 0;
    }

    public boolean isSupportedFeatureContent(int position) {
        return position >= 0 && position < getSupportedItemCount();
    }

    public boolean isUnsupportedFeatureContent(int position) {
        return position >= getSupportedItemCount() && position < getSupportedItemCount() + getUnsupportedItemCount();
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

    @Override
    public long getHeaderId(int position) {
        if (isSupportedFeatureContent(position)) {
            return HEADER_ID_SUPPORTED;
        } else if (isUnsupportedFeatureContent(position)) {
            return HEADER_ID_UNSUPPORTED;
        } else {
            return StickyHeaderDecoration.NO_HEADER_ID;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_feature_header_item, parent, false);
        return new FeatureHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        FeatureHeaderViewHolder headerViewHolder = (FeatureHeaderViewHolder) holder;
        headerViewHolder.tvHeader.setVisibility(View.VISIBLE);
        if (isSupportedFeatureContent(position)) {
            headerViewHolder.tvHeader.setText(R.string.feature_supported_feature_header);
        } else if (isUnsupportedFeatureContent(position)) {
            headerViewHolder.tvHeader.setText(R.string.feature_unsupported_feature_header);
        } else {
            headerViewHolder.tvHeader.setVisibility(View.GONE);
        }
    }

    public interface OnFeatureInfoClickListener {
        void onFeatureContentClick(FeatureItem featureItem);
    }
}
