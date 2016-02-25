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
        datasource.open();
        datasource.deleteAllFeed();
        long feedId = datasource.saveFeed(feed);
        datasource.saveEntryApps(feedId, feed.getEntry());
        datasource.close();
    }

    public List<String> getAllCategories() {
        datasource.open();
        List<String> allCategories = datasource.getAllCategories();
        datasource.close();
        return allCategories;
    }

    public String getTitle() {
        datasource.open();
        String titleApp = datasource.getTitleApp();
        datasource.close();
        return  titleApp;
    }
}
