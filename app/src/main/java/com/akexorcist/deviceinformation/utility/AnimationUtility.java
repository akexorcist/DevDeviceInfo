package com.akexorcist.deviceinformation.utility;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * Created by Akexorcist on 11/20/2016 AD.
 */

public class AnimationUtility {
    private static AnimationUtility utility;

    public static AnimationUtility getInstance() {
        if (utility == null) {
            utility = new AnimationUtility();
        }
        return utility;
    }

    public void fadeOut(View view) {
        fadeOut(view, 300);
    }

    public void fadeOut(final View view, long duration) {
        view.animate()
                .alpha(0)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.INVISIBLE);
                    }
                });
    }

    public void fadeIn(View view) {
        fadeIn(view, 300);
    }

    public void fadeIn(final View view, long duration) {
        view.animate()
                .alpha(1)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
    }

    public void collapse(View view) {
        collapse(view, 300);
    }

    public void collapse(final View view, long duration) {
        view.animate()
                .alpha(0)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });
    }

    public void expand(View view) {
        expand(view, 300);
    }

    public void expand(final View view, long duration) {
        view.animate()
                .alpha(1)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
    }
}
