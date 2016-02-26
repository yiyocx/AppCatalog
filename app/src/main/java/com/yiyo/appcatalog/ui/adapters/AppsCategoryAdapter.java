package com.yiyo.appcatalog.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yiyo.appcatalog.R;
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
    private Context context;

    public AppsCategoryAdapter() {
        apps = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EntryApp entryApp = apps.get(position);
        Picasso
            .with(context)
            .load(entryApp.img100)
            .placeholder(R.drawable.img_face)
            .into(holder.appIcon);
        holder.appTitle.setText(entryApp.name);
        holder.appSummary.setText(entryApp.summary);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public void addApps(List<EntryApp> apps) {
        this.apps = apps;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.app_icon) ImageView appIcon;
        @Bind(R.id.app_title) TextView appTitle;
        @Bind(R.id.app_summary) TextView appSummary;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
