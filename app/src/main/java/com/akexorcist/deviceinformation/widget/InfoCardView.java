package com.akexorcist.deviceinformation.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseCustomView;
import com.akexorcist.deviceinformation.common.DataInfo;
import com.akexorcist.deviceinformation.helper.DataInfoComparator;

import java.util.Collections;
import java.util.List;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class InfoCardView extends BaseCustomView {
    private String title;
    private List<DataInfo> dataInfoList;

    private TextView tvInfoTitle;
    private LinearLayout layoutInfoContainer;
    private LinearLayout layoutInfoContent;

    public InfoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InfoCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getLayoutView() {
        return R.layout.widget_info_card;
    }

    @Override
    protected void bindView() {
        tvInfoTitle = (TextView) findViewById(R.id.tv_info_title);
        layoutInfoContainer = (LinearLayout) findViewById(R.id.layout_info_container);
        layoutInfoContent = (LinearLayout) findViewById(R.id.layout_info_content);
    }

    @Override
    protected void setupView() {
        setTitle(title);
    }

    @Override
    protected void setupStyleable(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.InfoCardView);
            title = typedArray.getString(R.styleable.InfoCardView_icv_title);
            typedArray.recycle();
        }
    }

    @Override
    protected BaseSavedState saveInstanceState(Parcelable superState) {
        SavedState ss = new SavedState(superState);
        ss.title = this.title;
        ss.dataInfoList = this.dataInfoList;
        return ss;
    }

    @Override
    protected void restoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.title = ss.title;
        this.dataInfoList = ss.dataInfoList;
        setTitle(title);
        setDataInfoList(dataInfoList, false);
    }

    @Override
    protected void restoreView() {

    }

    public void setTitle(String title) {
        this.title = title;
        updateTitle();
    }

    public void setTitle(int resId) {
        this.title = getContext().getString(resId);
        updateTitle();
    }

    public void setDataInfoList(List<DataInfo> dataInfoList) {
        setDataInfoList(dataInfoList, false);
    }

    public void setDataInfoList(List<DataInfo> dataInfoList, boolean orderByAscending) {
        if (orderByAscending) {
            Collections.sort(dataInfoList, new DataInfoComparator());
        }
        this.dataInfoList = dataInfoList;
        updateDataInfoList();
    }

    private void updateTitle() {
        if (title != null) {
            tvInfoTitle.setVisibility(View.VISIBLE);
            tvInfoTitle.setText(title);
        } else {
            tvInfoTitle.setVisibility(View.GONE);
        }
    }

    private void updateDataInfoList() {
        layoutInfoContent.removeAllViews();
        if (dataInfoList != null) {
            for (DataInfo dataInfo : dataInfoList) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.view_data_info_item, layoutInfoContent, false);
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_data_title);
                TextView tvValue = (TextView) view.findViewById(R.id.tv_data_value);
                tvTitle.setText(dataInfo.getTitle());
                tvValue.setText(dataInfo.getValue());
                layoutInfoContent.addView(view);
            }
        }
    }

    private static class SavedState extends BaseSavedState {
        String title;
        List<DataInfo> dataInfoList;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.title = in.readString();
            this.dataInfoList = in.createTypedArrayList(DataInfo.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.title);
            out.writeTypedList(this.dataInfoList);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
