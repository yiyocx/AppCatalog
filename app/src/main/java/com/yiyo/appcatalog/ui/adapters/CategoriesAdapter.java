package com.yiyo.appcatalog.ui.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyo.appcatalog.R;
import com.yiyo.appcatalog.ui.activities.AppsCategoryActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String category = categories.get(position);
        holder.categoryText.setText(category);
        Random random = new Random();
        holder.categoryColor.setBackgroundColor(Color.argb(255, random.nextInt(255),
                random.nextInt(255), random.nextInt(255)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = categories.get(position);
                Intent intent = new Intent(v.getContext(), AppsCategoryActivity.class);
                intent.putExtra("category", category);
                v.getContext().startActivity(intent);
            }
        });
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
        @Bind(R.id.category_name) TextView categoryText;
        @Bind(R.id.category_color) TextView categoryColor;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
