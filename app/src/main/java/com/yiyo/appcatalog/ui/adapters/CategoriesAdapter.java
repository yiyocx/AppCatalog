package com.yiyo.appcatalog.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyo.appcatalog.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yiyo on 24/02/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<String> categories = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String category = categories.get(position);
        holder.categoryText.setText(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void addAll(List<String> items) {
        int pos = getItemCount();
        categories.addAll(items);
        notifyItemRangeInserted(pos, categories.size());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.category) TextView categoryText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}