package com.akexorcist.deviceinformation.widget;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.akexorcist.deviceinformation.common.BaseCustomView;

/**
 * Created by Akexorcist on 9/1/2016 AD.
 */

public class Divider extends BaseCustomView {
    public Divider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Divider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Divider(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int getLayoutView() {
        return 0;
    }

    @Override
    protected void bindView() {

    }

    @Override
    protected void setupView() {

    }

    @Override
    protected void setupStyleable(AttributeSet attrs) {

    }

    @Override
    protected BaseSavedState saveInstanceState(Parcelable superState) {
        return new BaseSavedState(superState);
    }

    @Override
    protected void restoreInstanceState(Parcelable state) {

    }

    @Override
    protected void restoreView() {

    }
}
