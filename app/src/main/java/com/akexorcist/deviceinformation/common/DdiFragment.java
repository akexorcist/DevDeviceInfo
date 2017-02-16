package com.akexorcist.deviceinformation.common;

import android.os.Bundle;
import android.view.ViewGroup;

import com.akexorcist.deviceinformation.utility.AnimationUtility;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public abstract class DdiFragment extends BaseDdiFragment {
    private static final String KEY_CONTENT_SHOWING = "key_content_showing";

    private ViewGroup layoutContent;
    private ViewGroup layoutLoading;

    private boolean isContentShowing = true;

    public void setContentLayout(ViewGroup layoutContent) {
        this.layoutContent = layoutContent;
    }

    public void setLoadingLayout(ViewGroup layoutLoading) {
        this.layoutLoading = layoutLoading;
    }

    public boolean isContentShowing() {
        return isContentShowing;
    }

    public void showContent() {
        isContentShowing = true;
        isLayoutNull(layoutContent);
        isLayoutNull(layoutLoading);
        AnimationUtility.getInstance().fadeIn(layoutContent);
        AnimationUtility.getInstance().fadeOut(layoutLoading);
    }

    public void forceShowContent() {
        isContentShowing = true;
        isLayoutNull(layoutContent);
        isLayoutNull(layoutLoading);
        AnimationUtility.getInstance().fadeIn(layoutContent);
        AnimationUtility.getInstance().fadeOut(layoutLoading);
    }

    public void hideContent() {
        isContentShowing = false;
        isLayoutNull(layoutContent);
        isLayoutNull(layoutLoading);
        AnimationUtility.getInstance().fadeOut(layoutContent);
        AnimationUtility.getInstance().fadeIn(layoutLoading);
    }

    public void forceHideContent() {
        isContentShowing = false;
        isLayoutNull(layoutContent);
        isLayoutNull(layoutLoading);
        AnimationUtility.getInstance().fadeOut(layoutContent, 0);
        AnimationUtility.getInstance().fadeIn(layoutLoading, 0);
    }

    private void isLayoutNull(ViewGroup layout) {
        if (layout == null) {
            throw new NullPointerException("Please override " +
                    "setContentLayout(ViewGroup layoutContent) " +
                    "and setLoadingLayout(ViewGroup layoutLoading) " +
                    "then setup layout before call this method.");
        }
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        isContentShowing = savedInstanceState.getBoolean(KEY_CONTENT_SHOWING);
    }

    @Override
    public void restoreView() {
        if (layoutContent != null && layoutLoading != null) {
            if (isContentShowing) {
                forceShowContent();
            } else {
                forceHideContent();
            }
        }
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        outState.putBoolean(KEY_CONTENT_SHOWING, isContentShowing);
    }
}
