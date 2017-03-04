package com.akexorcist.deviceinformation.module.application;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.collector.application.model.AppItem;
import com.akexorcist.deviceinformation.module.application.holder.ApplicationContentViewHolder;
import com.akexorcist.deviceinformation.module.application.holder.ApplicationEmptyViewHolder;
import com.akexorcist.deviceinformation.module.application.holder.ApplicationHeaderViewHolder;
import com.akexorcist.deviceinformation.module.application.holder.ApplicationUnknownViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class ApplicationContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderAdapter<RecyclerView.ViewHolder> {
    private static final int TYPE_APPLICATION_ITEM = 0;
    private static final int TYPE_EMPTY_SUPPORTED_ITEM = 1;
    private static final int TYPE_EMPTY_UNSUPPORTED_ITEM = 2;
    private static final int TYPE_UNKNOWN = 3;
    private static final int HEADER_ID_DOWNLOADED = 0;
    private static final int HEADER_ID_SYSTEM = 1;

    private List<AppItem> downloadedAppItem;
    private List<AppItem> systemAppItem;
    private OnAppContentClickListener onAppContentClickListener;

    public ApplicationContentAdapter() {
        downloadedAppItem = new ArrayList<>();
        systemAppItem = new ArrayList<>();
    }

    public void setAppItemList(List<AppItem> downloadedAppItem, List<AppItem> systemAppItem) {
        this.downloadedAppItem = downloadedAppItem;
        this.systemAppItem = systemAppItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_APPLICATION_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_application_item, parent, false);
            return new ApplicationContentViewHolder(view);
        } else if (viewType == TYPE_EMPTY_SUPPORTED_ITEM || viewType == TYPE_EMPTY_UNSUPPORTED_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_application_empty_item, parent, false);
            return new ApplicationEmptyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_application_unknown_item, parent, false);
            return new ApplicationUnknownViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ApplicationContentViewHolder) {
            ApplicationContentViewHolder contentViewHolder = (ApplicationContentViewHolder) holder;
            AppItem appItem = getAppContentByPosition(position);
            if (appItem != null) {
                contentViewHolder.tvName.setText(appItem.getName());
                contentViewHolder.tvPackageName.setText(appItem.getPackageName());
                contentViewHolder.itemView.setOnClickListener(onAppContentClick(appItem));
                int iconResId = appItem.getIconResId();
                if (iconResId != 0) {
                    Uri uri = Uri.parse("android.resource://" + appItem.getPackageName() + "/" + iconResId);
                    Glide.with(holder.itemView.getContext()).load(uri).into(contentViewHolder.ivAppIcon);
                } else {
                    Glide.with(holder.itemView.getContext()).load(R.mipmap.ic_launcher).into(contentViewHolder.ivAppIcon);
                }
            }
        } else if (holder instanceof ApplicationEmptyViewHolder) {
            ApplicationEmptyViewHolder emptyViewHolder = (ApplicationEmptyViewHolder) holder;
            emptyViewHolder.tvEmptyDescription.setVisibility(View.VISIBLE);
            int viewType = getItemViewType(position);
            if (viewType == TYPE_EMPTY_SUPPORTED_ITEM) {
                emptyViewHolder.tvEmptyDescription.setText(R.string.application_empty_downloaded_app_available);
            } else if (viewType == TYPE_EMPTY_UNSUPPORTED_ITEM) {
                emptyViewHolder.tvEmptyDescription.setText(R.string.application_empty_system_app_available);
            } else {
                emptyViewHolder.tvEmptyDescription.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isDownloadedAppContent(position)) {
            if (isDownloadedAppAvailable()) {
                return TYPE_APPLICATION_ITEM;
            } else {
                return TYPE_EMPTY_SUPPORTED_ITEM;
            }
        } else if (isSystemAppContent(position)) {
            if (isSystemAppAvailable()) {
                return TYPE_APPLICATION_ITEM;
            } else {
                return TYPE_EMPTY_UNSUPPORTED_ITEM;
            }
        }
        return TYPE_UNKNOWN;
    }

    @Override
    public int getItemCount() {
        int supportedItemCount = getDownloadedAppItemCount();
        int unsupportedItemCount = getSystemAppItemCount();
        return supportedItemCount + unsupportedItemCount;
    }

    private int getDownloadedAppItemCount() {
        // Return 1 row if no data available (for empty item type)
        return downloadedAppItem != null && downloadedAppItem.size() != 0 ? downloadedAppItem.size() : 1;
    }

    private int getSystemAppItemCount() {
        // Return 1 row if no data available (for empty item type)
        return systemAppItem != null && systemAppItem.size() != 0 ? systemAppItem.size() : 1;
    }

    private AppItem getAppContentByPosition(int position) {
        if (isDownloadedAppContent(position)) {
            // getDownloadedAppItemCount can be 1 when have no downloaded app
            if (isDownloadedAppAvailable()) {
                // Download app item type
                return downloadedAppItem.get(position);
            }
        } else if (isSystemAppContent(position)) {
            if (isSystemAppAvailable()) {
                // System app item type
                return systemAppItem.get(position - getDownloadedAppItemCount());
            }
        }
        // For other view holder type
        return null;
    }

    public int getFirstAppContentPositionByHeaderId(int index) {
        if (index == HEADER_ID_DOWNLOADED) {
            return 0;
        } else if (index == HEADER_ID_SYSTEM) {
            return getDownloadedAppItemCount();
        }
        return -1;
    }

    public boolean isDownloadedAppAvailable() {
        return downloadedAppItem != null && downloadedAppItem.size() != 0;
    }

    public boolean isSystemAppAvailable() {
        return systemAppItem != null && systemAppItem.size() != 0;
    }

    public boolean isDownloadedAppContent(int position) {
        return position >= 0 && position < getDownloadedAppItemCount();
    }

    public boolean isSystemAppContent(int position) {
        return position >= getDownloadedAppItemCount() && position < getDownloadedAppItemCount() + getSystemAppItemCount();
    }

    private View.OnClickListener onAppContentClick(final AppItem appItem) {
        return v -> {
            if (onAppContentClickListener != null) {
                onAppContentClickListener.onAppContentClick(appItem);
            }
        };
    }

    public void setOnAppContentClickListener(OnAppContentClickListener onAppContentClickListener) {
        this.onAppContentClickListener = onAppContentClickListener;
    }

    @Override
    public long getHeaderId(int position) {
        if (isDownloadedAppContent(position)) {
            return HEADER_ID_DOWNLOADED;
        } else if (isSystemAppContent(position)) {
            return HEADER_ID_SYSTEM;
        } else {
            return StickyHeaderDecoration.NO_HEADER_ID;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_application_header_item, parent, false);
        return new ApplicationHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        ApplicationHeaderViewHolder headerViewHolder = (ApplicationHeaderViewHolder) holder;
        headerViewHolder.tvHeader.setVisibility(View.VISIBLE);
        if (isDownloadedAppContent(position)) {
            headerViewHolder.tvHeader.setText(R.string.application_downloaded_app_header);
        } else if (isSystemAppContent(position)) {
            headerViewHolder.tvHeader.setText(R.string.application_system_app_header);
        } else {
            headerViewHolder.tvHeader.setVisibility(View.GONE);
        }
    }

    public interface OnAppContentClickListener {
        void onAppContentClick(AppItem appItem);
    }
}
