package com.yiyo.appcatalog.ui.activities;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.ui.fragments.AppsCategoryFragment;

public class AppsCategoryActivity extends AppCompatActivity implements AppsCategoryFragment.OnFragmentInteractionListener {

    public static final String CATEGORY = "category";
    private View rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String category = getIntent().getStringExtra(CATEGORY);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_apps_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(category);

        rootLayout = findViewById(R.id.root_layout);
        attachAppsCategoryFragment(category);

        if (savedInstanceState == null) {
            rootLayout.setVisibility(View.INVISIBLE);
            rootLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    circularRevealActivity();
                    v.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    private void attachAppsCategoryFragment(String category) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment fragment = supportFragmentManager.findFragmentById(R.id.category_container);
        if (!(fragment instanceof AppsCategoryFragment)) {
            fragment = AppsCategoryFragment.newInstance(category);
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.category_container, fragment)
                .commit();
    }

    private void changeProgressBarVisibility(int visibility) {
        findViewById(R.id.progress).setVisibility(visibility);
    }

    private void circularRevealActivity() {

        int cx = rootLayout.getWidth() / 2;
        int cy = rootLayout.getHeight() / 2;

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        final Animator circularReveal;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
            circularReveal.setDuration(1000);

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            // make the view visible
            rootLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoadingData() {
        changeProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadedData() {
        changeProgressBarVisibility(View.GONE);
    }
}
