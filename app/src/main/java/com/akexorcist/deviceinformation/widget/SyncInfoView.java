package com.akexorcist.deviceinformation.widget;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseCustomView;
import com.akexorcist.deviceinformation.utility.AnimationUtility;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class SyncInfoView extends BaseCustomView implements View.OnClickListener {
    private TextView tvSyncMessage;
    private Button btnSync;
    private SyncClickListener syncClickListener;

    private boolean isShowing = true;

    public SyncInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SyncInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SyncInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getLayoutView() {
        return R.layout.widget_sync_info;
    }

    @Override
    protected void bindView() {
        tvSyncMessage = (TextView) findViewById(R.id.tv_sync_message);
        btnSync = (Button) findViewById(R.id.btn_sync);
    }

    @Override
    protected void setupView() {
        btnSync.setVisibility(GONE);
        btnSync.setOnClickListener(this);
    }

    @Override
    protected void setupStyleable(AttributeSet attrs) {

    }

    @Override
    protected BaseSavedState saveInstanceState(Parcelable superState) {
        SavedState ss = new SavedState(superState);
        ss.isShowing = this.isShowing;
        return ss;
    }

    @Override
    protected void restoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.isShowing = ss.isShowing;
    }

    @Override
    protected void restoreView() {
        updateViewShowing();
    }

    public void setMessage(String message) {
        tvSyncMessage.setText(message);
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void show() {
        if (!isShowing) {
            isShowing = true;
            AnimationUtility.getInstance().expand(this);
        }
    }

    public void hide() {
        if (isShowing) {
            isShowing = false;
            AnimationUtility.getInstance().collapse(this);
        }
    }

    private void updateViewShowing() {
        if (isShowing) {
            AnimationUtility.getInstance().expand(this, 0);
        } else {
            AnimationUtility.getInstance().collapse(this, 0);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnSync) {
            onSyncClick();
        }
    }

    private void onSyncClick() {
        if (syncClickListener != null) {
            syncClickListener.onSyncClick();
        }
    }

    public void setSyncClickListener(SyncClickListener listener) {
        this.syncClickListener = listener;
    }

    public interface SyncClickListener {
        void onSyncClick();
    }

    private static class SavedState extends BaseSavedState {
        boolean isShowing;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.isShowing = in.readInt() != 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.isShowing ? 1 : 0);
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
