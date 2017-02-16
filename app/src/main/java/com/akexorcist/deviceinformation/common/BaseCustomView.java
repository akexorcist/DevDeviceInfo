package com.akexorcist.deviceinformation.common;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public abstract class BaseCustomView extends FrameLayout {
    public BaseCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    private void setup(Context context, AttributeSet attrs) {
        if (getLayoutView() != 0) {
            inflate(context, getLayoutView(), this);
            bindView();
            setupView();
            setupStyleable(attrs);
        }
    }

    public abstract int getLayoutView();

    protected abstract void bindView();

    protected abstract void setupView();

    protected abstract void setupStyleable(AttributeSet attrs);

    protected abstract BaseSavedState saveInstanceState(Parcelable superState);

    protected abstract void restoreInstanceState(Parcelable state);

    protected abstract void restoreView();

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return saveInstanceState(superState);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof BaseSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        restoreInstanceState(state);
        restoreView();
    }
}
