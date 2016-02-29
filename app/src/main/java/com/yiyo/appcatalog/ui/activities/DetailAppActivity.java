package com.yiyo.appcatalog.ui.activities;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.ui.fragments.DetailAppFragment;
import com.yiyo.appcatalog.utils.TextSharedElementCallback;

import java.util.List;

public class DetailAppActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = "DetailApp";

    private View toolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_app);
        setupToolbar();

        int appNameTextSize = getResources()
                .getDimensionPixelSize(R.dimen.app_text_size);
        int paddingStart = getResources().getDimensionPixelSize(R.dimen.padding_start_title);
        final int startDelay = getResources().getInteger(R.integer.toolbar_transition_duration);

        ActivityCompat.setEnterSharedElementCallback(this,
                new TextSharedElementCallback(appNameTextSize, paddingStart) {
                    @Override
                    public void onSharedElementStart(List<String> sharedElementNames,
                                                     List<View> sharedElements,
                                                     List<View> sharedElementSnapshots) {
                        super.onSharedElementStart(sharedElementNames,
                                sharedElements,
                                sharedElementSnapshots);
                        toolbarBack.setScaleX(0f);
                        toolbarBack.setScaleY(0f);
                    }

                    @Override
                    public void onSharedElementEnd(List<String> sharedElementNames,
                                                   List<View> sharedElements,
                                                   List<View> sharedElementSnapshots) {
                        super.onSharedElementEnd(sharedElementNames,
                                sharedElements,
                                sharedElementSnapshots);
                        // Make sure to perform this animation after the transition has ended.
                        ViewCompat.animate(toolbarBack)
                                .setStartDelay(startDelay)
                                .scaleX(1f)
                                .scaleY(1f)
                                .alpha(1f);
                    }
                });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // set an exit transition
            getWindow().setEnterTransition(new Explode());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        long appId = getIntent().getLongExtra(DetailAppFragment.APP_ID, -1);
        DetailAppFragment fragment = DetailAppFragment.newInstance(appId);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.app_fragment_container, fragment, FRAGMENT_TAG)
                .commit();
    }

    private void setupToolbar() {
        toolbarBack = findViewById(R.id.back);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int spacingDouble = getResources().getDimensionPixelSize(R.dimen.spacing_double);
            int paddingLandscape = getResources().getDimensionPixelSize(R.dimen.padding_arrow_landscape);
            toolbarBack.setPadding(spacingDouble, paddingLandscape, paddingLandscape, spacingDouble);
        }
    }

}
