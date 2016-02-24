package com.yiyo.appcatalog.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.utils.ItemAnimatorFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text_title) TextView titleTextView;
    @Bind(R.id.recycler) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onFakeCreate();
    }

    private void onFakeCreate() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ViewCompat.animate(titleTextView).alpha(1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(ItemAnimatorFactory.slidein());

        toolbar.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        toolbar.getViewTreeObserver().removeOnPreDrawListener(this);
                        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

                        toolbar.measure(widthSpec, heightSpec);
                        int contentViewHeight = toolbar.getHeight();
                        collapseToolbar(contentViewHeight);
                        return true;
                    }
            });
    }

    private void collapseToolbar(int contentViewHeight) {
        int toolBarHeight;
        TypedValue tv = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        toolBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        ValueAnimator valueHeightAnimator = ValueAnimator.ofInt(contentViewHeight, toolBarHeight);
        valueHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
                lp.height = (Integer) animation.getAnimatedValue();
                toolbar.setLayoutParams(lp);
            }
        });

        valueHeightAnimator.start();
        valueHeightAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });
    }
}
