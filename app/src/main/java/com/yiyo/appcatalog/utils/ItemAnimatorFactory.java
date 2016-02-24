package com.yiyo.appcatalog.utils;

import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by yiyo on 24/02/16.
 */
public class ItemAnimatorFactory extends BaseItemAnimator{

    static public RecyclerView.ItemAnimator slidein() {
        SlideInUpDelayedAnimator animator = new SlideInUpDelayedAnimator(new DecelerateInterpolator(1.2f));
        animator.setAddDuration(600);
        return animator;
    }
}
