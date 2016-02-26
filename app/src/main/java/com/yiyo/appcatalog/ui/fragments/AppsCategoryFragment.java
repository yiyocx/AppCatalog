package com.yiyo.appcatalog.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.mvp.presenters.AppsCategoryPresenter;
import com.yiyo.appcatalog.mvp.views.AppsCategoryView;
import com.yiyo.appcatalog.ui.adapters.AppsCategoryAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AppsCategoryFragment extends Fragment implements AppsCategoryView {

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

    public static AppsCategoryFragment newInstance() {
        return new AppsCategoryFragment();
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
        setupGrid();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupGrid() {
        final int spacing = getContext().getResources()
                .getDimensionPixelSize(R.dimen.spacing);
//        appsRecycler.addItemDecoration(new OffsetDecoration(spacing));
        adapter = new AppsCategoryAdapter();
        appsRecycler.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        appsCategoryPresenter.onResume();
    }

    @Override
    public void showProgress() {
        callback.onLoadingData();
    }

    @Override
    public void hideProgress() {
        callback.onLoadedData();
    }
}
