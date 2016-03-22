package com.yiyo.appcatalog.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.mvp.presenters.DetailAppPresenter;
import com.yiyo.appcatalog.mvp.views.DetailAppView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailAppFragment extends Fragment implements DetailAppView {

    public static final String APP_ID = "app_id";

    private DetailAppPresenter detailAppPresenter;
    @Bind(R.id.app_image) ImageView appImage;
    @Bind(R.id.app_name) TextView appName;
    @Bind(R.id.app_title) TextView appTitle;
    @Bind(R.id.app_summary) TextView appSummary;
    @Bind(R.id.app_rights) TextView appRights;
    @Bind(R.id.app_release_date_label) TextView appReleaseDateLabel;
    @Bind(R.id.app_artist) TextView appArtist;

    public static DetailAppFragment newInstance(Long appId) {
        if (appId == -1) {
            throw new IllegalArgumentException("The app can not be null");
        }
        Bundle args = new Bundle();
        args.putLong(APP_ID, appId);
        DetailAppFragment fragment = new DetailAppFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_app, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get the data of the selected app from its id
        long appId = getArguments().getLong(APP_ID, -1);
        if (appId == -1) {
            Log.w(DetailAppFragment.class.getSimpleName(), "Didn't find an app. Finishing");
            getActivity().finish();
        }
        detailAppPresenter = new DetailAppPresenter(DetailAppFragment.this);
        detailAppPresenter.findApp(appId, getContext());
    }

    @Override
    public void populateApp(EntryApp entryApp) {
        Glide
            .with(getContext())
            .load(entryApp.img100)
            .centerCrop()
            .into(appImage);
        appName.setText(entryApp.name);
        appSummary.setText(entryApp.summary);
        appRights.setText(entryApp.rights);
        appReleaseDateLabel.setText(entryApp.releaseDateLabel);
        appArtist.setText("By " + entryApp.artist);
        appTitle.setText(entryApp.title);
    }
}
