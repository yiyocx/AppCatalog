package com.yiyo.appcatalog.ui.activities;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.mvp.presenters.DetailAppPresenter;
import com.yiyo.appcatalog.utils.TextSharedElementCallback;

import java.util.List;

public class DetailAppActivity extends AppCompatActivity {

    public static final String APP_ID = "app_id";
    private DetailAppPresenter detailAppPresenter;
    private View toolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailAppPresenter = new DetailAppPresenter();

        // Get the data of the selected app from its id
        Long appId = getIntent().getLongExtra(APP_ID, -1);
        loadApp(appId);

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
    }

    private void loadApp(Long appId) {
        if (appId == -1) {
            Log.w(DetailAppActivity.class.getSimpleName(), "Didn't find an app. Finishing");
            finish();
        }

        EntryApp entryApp = detailAppPresenter.findApp(appId, DetailAppActivity.this);
        setContentView(R.layout.activity_detail_app);
        setupToolbar();
    }

    private void setupToolbar() {
        toolbarBack = findViewById(R.id.back);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
