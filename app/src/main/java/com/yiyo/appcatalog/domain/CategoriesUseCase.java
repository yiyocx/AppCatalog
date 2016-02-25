package com.yiyo.appcatalog.domain;

import android.content.Context;

import com.yiyo.appcatalog.model.database.AppsCatalogDatasource;
import com.yiyo.appcatalog.model.rest.models.Feed_;

import java.util.List;

/**
 * Created by yiyo on 24/02/16.
 */
public class CategoriesUseCase {

    private AppsCatalogDatasource datasource;

    public CategoriesUseCase(Context context) {
        datasource = new AppsCatalogDatasource(context);
    }

    public void save(Feed_ feed) {
        long feedId = datasource.saveFeed(feed);
        datasource.deleteAllFeed();
        datasource.saveEntryApps(feedId, feed.getEntry());
    }

    public List<String> getAllCategories() {
        List<String> allCategories = datasource.getAllCategories();
        return allCategories;
    }

    public void openDatabase() {
        datasource.open();
    }

    public void closeDatabase() {
        datasource.close();
    }
}
