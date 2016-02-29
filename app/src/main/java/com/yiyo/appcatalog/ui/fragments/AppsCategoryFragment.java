package com.yiyo.appcatalog.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.mvp.presenters.AppsCategoryPresenter;
import com.yiyo.appcatalog.mvp.views.AppsCategoryView;
import com.yiyo.appcatalog.ui.activities.DetailAppActivity;
import com.yiyo.appcatalog.ui.adapters.AppsCategoryAdapter;
import com.yiyo.appcatalog.utils.TransitionHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AppsCategoryFragment extends Fragment implements AppsCategoryView {

    private static final String CATEGORY = "category";

    private String category;
    private AppsCategoryPresenter appsCategoryPresenter;
    private OnFragmentInteractionListener callback;
    @Bind(R.id.apps_recycler) RecyclerView appsRecycler;
    private AppsCategoryAdapter adapter;

    public interface OnFragmentInteractionListener {

        void onLoadingData();

        void onLoadedData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public static AppsCategoryFragment newInstance(String category) {
        AppsCategoryFragment fragment = new AppsCategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps_category, container, false);
        appsCategoryPresenter = new AppsCategoryPresenter(AppsCategoryFragment.this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupAdapter();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupAdapter() {
        adapter = new AppsCategoryAdapter();
        adapter.setOnItemClickListener(new AppsCategoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                startDetailActivityWithTransition(getActivity(),
                        v.findViewById(R.id.app_title),
                        adapter.getItem(position));
            }
        });
        appsRecycler.setAdapter(adapter);
    }

    private void startDetailActivityWithTransition(Activity activity, View toolbar, EntryApp app) {
        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, activity.getString(R.string.transition_toolbar)));

        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);

        // Start the activity with the participants, animating from one to the other.
        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        Intent startIntent = new Intent(getContext(), DetailAppActivity.class);
        startIntent.putExtra(DetailAppFragment.APP_ID, app.id);
        ActivityCompat.startActivity(activity, startIntent, transitionBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        appsCategoryPresenter.onResume(category);
    }

    @Override
    public void showProgress() {
        callback.onLoadingData();
    }

    @Override
    public void hideProgress() {
        callback.onLoadedData();
    }

    @Override
    public void showApps(List<EntryApp> apps) {
        adapter.addApps(apps);
    }
}
