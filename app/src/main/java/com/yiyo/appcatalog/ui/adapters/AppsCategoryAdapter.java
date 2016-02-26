package com.yiyo.appcatalog.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.model.database.AppsCatalogDatasource;
import com.yiyo.appcatalog.model.entities.EntryApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yiyo on 25/02/16.
 */
public class AppsCategoryAdapter extends RecyclerView.Adapter<AppsCategoryAdapter.ViewHolder> {

    private List<EntryApp> apps;

    public AppsCategoryAdapter() {
        apps = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.appTitle.setText("Yiyo paso por here");
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.app_title) TextView appTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
