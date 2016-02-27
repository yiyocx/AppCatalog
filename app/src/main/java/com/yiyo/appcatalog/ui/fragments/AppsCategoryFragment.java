package com.yiyo.appcatalog.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.model.entities.EntryApp;
import com.yiyo.appcatalog.mvp.presenters.AppsCategoryPresenter;
import com.yiyo.appcatalog.mvp.views.AppsCategoryView;
import com.yiyo.appcatalog.ui.adapters.AppsCategoryAdapter;

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
                Toast.makeText(getActivity(), "Me tocaste en la posición: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        appsRecycler.setAdapter(adapter);
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
