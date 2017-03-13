package com.akexorcist.deviceinformation.widget;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akexorcist.deviceinformation.R;
import com.akexorcist.deviceinformation.common.BaseCustomView;

/**
 * Created by Akexorcist on 3/4/2017 AD.
 */

public class PermissionDeniedView extends BaseCustomView {
    private TextView tvPermissionDeniedDescription;
    private Button btnRequestPermission;

    private OnRequestPermissionClickListener onRequestPermissionClickListener;

    public PermissionDeniedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PermissionDeniedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PermissionDeniedView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getLayoutView() {
        return R.layout.widget_permission_denied;
    }

    @Override
    protected void bindView() {
        tvPermissionDeniedDescription = (TextView) findViewById(R.id.tv_permission_denied_description);
        btnRequestPermission = (Button) findViewById(R.id.btn_request_permission);
    }

    @Override
    protected void setupView() {
        btnRequestPermission.setOnClickListener(onRequestPermissionClick());
    }

    @Override
    protected void setupStyleable(AttributeSet attrs) {

    }

    @Override
    protected BaseSavedState saveInstanceState(Parcelable superState) {
        return null;
    }

    @Override
    protected void restoreInstanceState(Parcelable state) {

    }

    @Override
    protected void restoreView() {

    }

    public void setPermissionDeniedDescription(String description) {
        tvPermissionDeniedDescription.setText(description);
    }

    public void setPermissionDeniedDescription(int resId) {
        tvPermissionDeniedDescription.setText(resId);
    }

    private View.OnClickListener onRequestPermissionClick() {
        return v -> {
            if (onRequestPermissionClickListener != null) {
                onRequestPermissionClickListener.onRequestPermissionAgain();
            }
        };
    }

    public void setOnRequestPermissionClickListener(OnRequestPermissionClickListener listener) {
        onRequestPermissionClickListener = listener;
    }

    public interface OnRequestPermissionClickListener {
        void onRequestPermissionAgain();
    }
}
