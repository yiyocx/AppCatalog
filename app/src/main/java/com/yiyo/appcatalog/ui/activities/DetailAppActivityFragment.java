package com.yiyo.appcatalog.ui.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiyo.appcatalog.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailAppActivityFragment extends Fragment {

    public DetailAppActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_app, container, false);
    }
}
